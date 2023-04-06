package egovframework.raw.web;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import egovframework.cmm.service.BasicResponse;
import egovframework.cmm.service.EgovFileMngService;
import egovframework.cmm.service.FileVO;
import egovframework.cmm.service.LoginVO;
import egovframework.cmm.service.ResponseMessage;
import egovframework.cmm.util.EgovFileMngUtil;
import egovframework.cmm.util.EgovUserDetailsHelper;
import egovframework.raw.dto.CeDTO;
import egovframework.raw.dto.EsdDTO;
import egovframework.raw.dto.RawDTO;
import egovframework.raw.dto.ReDTO;
import egovframework.raw.dto.RsDTO;
import egovframework.raw.service.RawData;
import egovframework.raw.service.RawService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"로데이터"})
@RestController
@RequestMapping("/raw")
public class RawController {
    
	@Resource(name = "RawService")
	private RawService rawService;
	
    @Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;
    
    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;
    
    @ApiOperation(value = "기본정보 등록", notes="1. 수정시 rawseq 필수, 파일추가시 ModUrl 과 SetupUrl 필수, 리스트항목은 견적서 시험항목 처리와 동일(state)\n 2. modFileList(보완사항파일) 같은이름으로 여러개\n 3. delFileList(리스트파일일  경우만) : atchFileId(ModUrl 과 SetupUrl 값), fileCn(파일순번)")
    @PostMapping(value="/insert.do")
    public BasicResponse insert(@RequestPart(value="rawData") RawData req,
    		@RequestPart(value = "delFileList", required = false) List<FileVO> delFileList,
    		@ModelAttribute RawDTO raw) throws Exception {
    	
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	String msg = "";
    	boolean result = false;
    	
    	// 로그인정보
    	req.setInsMemId(user.getId());
    	req.setUdtMemId(user.getId());
    	
    	System.out.println("=-===========");
    	System.out.println(req.toString());
    	System.out.println("=-===========");
    	
    	ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        
        Set<ConstraintViolation<RawData>> violations = validator.validate(req);
        
        for (ConstraintViolation<RawData> violation : violations) {
        	msg = violation.getMessage();
        	
        	System.out.println(msg);
        	BasicResponse res = BasicResponse.builder().result(false)
        			.message(msg)
    				.build();
        	
        	return res;
        }
        
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        
        if (isAuthenticated) {

        	// 이미 등록된 로데이터가 있는지 확인
            if(req.getRawSeq() == 0 && !ObjectUtils.isEmpty(rawService.detail(req.getTestSeq()))) {
            	result = false;
    			msg = ResponseMessage.DUPLICATE_RAW;
            } else {
        	
	        	List<FileVO> FileResult = null;
	        	FileVO oneFile = null;
	        	String atchFileId = "";
	    		
	        	// 시험자 서명
	    	    if (!ObjectUtils.isEmpty(raw.getTestSign())) {
	    	    	oneFile = fileUtil.parseFile(raw.getTestSign(), "RAW", 0, "", "");
		    		atchFileId = fileMngService.insertFileInf(oneFile);
		    		req.setTestSignUrl(atchFileId);
	    	    }
	 
	        	// 기술책임자 서명
	    	    if (!ObjectUtils.isEmpty(raw.getRevSign())) {
	    	    	oneFile = fileUtil.parseFile(raw.getRevSign(), "RAW", 0, "", "");
		    		atchFileId = fileMngService.insertFileInf(oneFile);
		    		req.setRevSignUrl(atchFileId);
	    	    }
	    	    
	        	// 보완파일
	        	if (!ObjectUtils.isEmpty(raw.getModFileList())) {
	        		
	        		// 신규등록
	        		if (StringUtils.isEmpty(req.getModUrl())) {
		        		FileResult = fileUtil.parseFile(raw.getModFileList(), "RAW", 0, "", "");
						atchFileId = fileMngService.insertFileInfs(FileResult);
						req.setModUrl(atchFileId);
	        		}
	        		// 수정
	        		else {
	        			// 현재 등록된 파일 수 가져오기
	        			FileVO fvo = new FileVO();
	        			fvo.setAtchFileId(req.getModUrl());
	        			int cnt = fileMngService.getMaxFileSN(fvo);
	        			
	        			// 추가 파일 등록
	        			List<FileVO> _result = fileUtil.parseFile(raw.getModFileList(), "RAW", cnt, req.getModUrl(), "");
	        			fileMngService.updateFileInfs(_result);
	        		}
	        	}
	        	
	        	// 셋업파일
	        	if (!ObjectUtils.isEmpty(raw.getSetupList())) {
	        		
	        		// 신규등록
	        		if(StringUtils.isEmpty(req.getSetupUrl())) {
	        			FileResult = fileUtil.parseSetupFile(raw.getSetupList(), "RAW", 0, "", "");
	        			atchFileId = fileMngService.insertFileInfs(FileResult);
	        			req.setSetupUrl(atchFileId);
	        		}
	        		// 수정
	        		else {
	        			// 현재 등록된 파일 수 가져오기
	        			FileVO fvo = new FileVO();
	        			fvo.setAtchFileId(req.getSetupUrl());
	        			int cnt = fileMngService.getMaxFileSN(fvo);
	        			
	        			// 추가 파일 등록
	        			List<FileVO> _result = fileUtil.parseSetupFile(raw.getSetupList(), "RAW", cnt, req.getSetupUrl(), "");
	        			fileMngService.updateFileInfs(_result);
	        		}
	        		
	        	}
	        	
	        	// 파일삭제
			    FileVO delFile = null;
			    if (!ObjectUtils.isEmpty(delFileList)) {
			    	for (FileVO del : delFileList) {
			    		delFile = new FileVO();
			    		delFile.setAtchFileId(del.getAtchFileId());
			    		delFile.setFileSn(del.getFileCn());
			    		fileMngService.deleteFileInf(delFile);
			    	}
			    }
			    
	        	if (req.getRawSeq() == 0)
	    	    	result = rawService.insert(req);
	    	    else 
	    	    	result = rawService.update(req);
            }
	        	
        } else {
    		result = false;
    		msg = ResponseMessage.UNAUTHORIZED;
    	}
        

    	
    	BasicResponse res = BasicResponse.builder().result(result)
    			.message(msg)
				.build();
    	
    	return res;
    }

    @ApiOperation(value = "로데이터 > CE 등록"
    			, notes="1. 수정시 ceSeq 필수, 파일추가시 resultUrl 필수\n "
    				  + "2. delFileList(리스트파일일  경우만) : atchFileId(resultUrl값), fileCn(파일순번)"
    			      + "3. signFile 시험자 서명, resultFiles 시험결과")
    @PostMapping(value="/insertCe.do")
    public BasicResponse insertCe(@RequestPart(value="ceDTO") CeDTO req,
    		@RequestPart(value = "delFileList", required = false) List<FileVO> delFileList,
    		@RequestPart(value = "signFile", required = false) MultipartFile signFile,
    		@RequestPart(value = "resultFiles", required = false) final List<MultipartFile> resultFiles) throws Exception {
    	
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	String msg = "";
    	boolean result = false;
    	
    	// 로그인정보
    	req.setInsMemId(user.getId());
    	req.setUdtMemId(user.getId());
    	req.setMacType("CE");
    	
    	System.out.println("=-===========");
    	System.out.println(req.toString());
    	System.out.println("=-===========");
    	
    	ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        
        Set<ConstraintViolation<CeDTO>> violations = validator.validate(req);
        
        for (ConstraintViolation<CeDTO> violation : violations) {
        	msg = violation.getMessage();
        	
        	System.out.println(msg);
        	BasicResponse res = BasicResponse.builder().result(false)
        			.message(msg)
    				.build();
        	
        	return res;
        }
        
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        
        // 이미 등록된 로데이터가 있는지 확인
        if(req.getCeSeq() == 0 && !ObjectUtils.isEmpty(rawService.ceDetail(req.getRawSeq()))) {
        	result = false;
			msg = ResponseMessage.DUPLICATE_RAW;
        } else {
        
	        if (isAuthenticated) {
	        	
	        	List<FileVO> FileResult = null;
	        	FileVO oneFile = null;
	        	String atchFileId = "";
	    		
	        	// 시험자 서명
	    	    if (!ObjectUtils.isEmpty(signFile)) {
	    	    	oneFile = fileUtil.parseFile(signFile, "RAW", 0, "", "");
		    		atchFileId = fileMngService.insertFileInf(oneFile);
		    		req.setSignUrl(atchFileId);
	    	    }
	 
	        	// 시험결과 별도첨부
	        	if (!ObjectUtils.isEmpty(resultFiles)) {
	        		
	        		// 신규등록
	        		if (StringUtils.isEmpty(req.getResultUrl())) {
		        		FileResult = fileUtil.parseFile(resultFiles, "RAW", 0, "", "");
						atchFileId = fileMngService.insertFileInfs(FileResult);
						req.setResultUrl(atchFileId);
	        		}
	        		// 수정
	        		else {
	        			// 현재 등록된 파일 수 가져오기
	        			FileVO fvo = new FileVO();
	        			fvo.setAtchFileId(req.getResultUrl());
	        			int cnt = fileMngService.getMaxFileSN(fvo);
	        			
	        			// 추가 파일 등록
	        			List<FileVO> _result = fileUtil.parseFile(resultFiles, "RAW", cnt, req.getResultUrl(), "");
	        			fileMngService.updateFileInfs(_result);
	        		}
	        	}
	        	
	        	// 파일삭제
			    FileVO delFile = null;
			    if (!ObjectUtils.isEmpty(delFileList)) {
			    	for (FileVO del : delFileList) {
			    		delFile = new FileVO();
			    		delFile.setAtchFileId(del.getAtchFileId());
			    		delFile.setFileSn(del.getFileCn());
			    		fileMngService.deleteFileInf(delFile);
			    	}
			    }
			    
    	    	result = rawService.insertCe(req);
	        	
	        } else {
	    		result = false;
	    		msg = ResponseMessage.UNAUTHORIZED;
	    	}
        
        }   
    	
    	BasicResponse res = BasicResponse.builder().result(result)
    			.message(msg)
				.build();
    	
    	return res;
    }
 
    @ApiOperation(value = "로데이터 > RE 등록"
			, notes="1. 수정시 reSeq 필수, 파일추가시 resultUrl 필수\n "
				  + "2. delFileList(리스트파일일  경우만) : atchFileId(resultUrl값), fileCn(파일순번)"
			      + "3. signFile1 대역1 서명, signFile2 대역2 서명, resultFiles 시험결과")
	@PostMapping(value="/insertRe.do")
	public BasicResponse insertRe(@RequestPart(value="reDTO") ReDTO req,
			@RequestPart(value = "delFileList", required = false) List<FileVO> delFileList,
			@RequestPart(value = "signFile1", required = false) MultipartFile signFile1,
			@RequestPart(value = "signFile2", required = false) MultipartFile signFile2,
			@RequestPart(value = "resultFiles", required = false) final List<MultipartFile> resultFiles) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String msg = "";
		boolean result = false;
		
		// 로그인정보
		req.setInsMemId(user.getId());
		req.setUdtMemId(user.getId());
		req.setMacType("RE");
		
		System.out.println("=-===========");
		System.out.println(req.toString());
		System.out.println("=-===========");
		
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	    Validator validator = validatorFactory.getValidator();
	    
	    Set<ConstraintViolation<ReDTO>> violations = validator.validate(req);
	    
	    for (ConstraintViolation<ReDTO> violation : violations) {
	    	msg = violation.getMessage();
	    	
	    	System.out.println(msg);
	    	BasicResponse res = BasicResponse.builder().result(false)
	    			.message(msg)
					.build();
	    	
	    	return res;
	    }
	    
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    
	    // 이미 등록된 로데이터가 있는지 확인
	    if(req.getReSeq() == 0 && !ObjectUtils.isEmpty(rawService.reDetail(req.getRawSeq()))) {
	    	result = false;
			msg = ResponseMessage.DUPLICATE_RAW;
	    } else {
	    
	        if (isAuthenticated) {
	        	
	        	List<FileVO> FileResult = null;
	        	FileVO oneFile = null;
	        	String atchFileId = "";
	    		
	        	// 대역1 시험자 서명
	    	    if (!ObjectUtils.isEmpty(signFile1)) {
	    	    	oneFile = fileUtil.parseFile(signFile1, "RAW", 0, "", "");
		    		atchFileId = fileMngService.insertFileInf(oneFile);
		    		req.setHz1SignUrl(atchFileId);
	    	    }
	        	// 대역2 시험자 서명
	    	    if (!ObjectUtils.isEmpty(signFile2)) {
	    	    	oneFile = fileUtil.parseFile(signFile2, "RAW", 0, "", "");
		    		atchFileId = fileMngService.insertFileInf(oneFile);
		    		req.setHz2SignUrl(atchFileId);
	    	    }
	    	    
	        	// 시험결과 별도첨부
	        	if (!ObjectUtils.isEmpty(resultFiles)) {
	        		
	        		// 신규등록
	        		if (StringUtils.isEmpty(req.getResultUrl())) {
		        		FileResult = fileUtil.parseFile(resultFiles, "RAW", 0, "", "");
						atchFileId = fileMngService.insertFileInfs(FileResult);
						req.setResultUrl(atchFileId);
	        		}
	        		// 수정
	        		else {
	        			// 현재 등록된 파일 수 가져오기
	        			FileVO fvo = new FileVO();
	        			fvo.setAtchFileId(req.getResultUrl());
	        			int cnt = fileMngService.getMaxFileSN(fvo);
	        			
	        			// 추가 파일 등록
	        			List<FileVO> _result = fileUtil.parseFile(resultFiles, "RAW", cnt, req.getResultUrl(), "");
	        			fileMngService.updateFileInfs(_result);
	        		}
	        	}
	        	
	        	// 파일삭제
			    FileVO delFile = null;
			    if (!ObjectUtils.isEmpty(delFileList)) {
			    	for (FileVO del : delFileList) {
			    		delFile = new FileVO();
			    		delFile.setAtchFileId(del.getAtchFileId());
			    		delFile.setFileSn(del.getFileCn());
			    		fileMngService.deleteFileInf(delFile);
			    	}
			    }
			    
    	    	result = rawService.insertRe(req);
	        	
	        } else {
	    		result = false;
	    		msg = ResponseMessage.UNAUTHORIZED;
	    	}
	    
	    }   
		
		BasicResponse res = BasicResponse.builder().result(result)
				.message(msg)
				.build();
		
		return res;
	}

    @ApiOperation(value = "로데이터 > ESD 등록"
			, notes="1. 수정시 esdSeq 필수\n "
				  + "2. signFile 시험자 서명")
	@PostMapping(value="/insertEsd.do")
	public BasicResponse insertEsd(@RequestPart(value="esdDTO") EsdDTO req,
			@RequestPart(value = "signFile", required = false) MultipartFile signFile) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String msg = "";
		boolean result = false;
		
		// 로그인정보
		req.setInsMemId(user.getId());
		req.setUdtMemId(user.getId());
		req.setMacType("ED");
		
		System.out.println("=-===========");
		System.out.println(req.toString());
		System.out.println("=-===========");
		
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	    Validator validator = validatorFactory.getValidator();
	    
	    Set<ConstraintViolation<EsdDTO>> violations = validator.validate(req);
	    
	    for (ConstraintViolation<EsdDTO> violation : violations) {
	    	msg = violation.getMessage();
	    	
	    	System.out.println(msg);
	    	BasicResponse res = BasicResponse.builder().result(false)
	    			.message(msg)
					.build();
	    	
	    	return res;
	    }
	    
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    
	    // 이미 등록된 로데이터가 있는지 확인
	    if(req.getEsdSeq() == 0 && !ObjectUtils.isEmpty(rawService.esdDetail(req.getRawSeq()))) {
	    	result = false;
			msg = ResponseMessage.DUPLICATE_RAW;
	    } else {
	    
	        if (isAuthenticated) {
	        	
	        	List<FileVO> FileResult = null;
	        	FileVO oneFile = null;
	        	String atchFileId = "";
	    		
	        	// 시험자 서명
	    	    if (!ObjectUtils.isEmpty(signFile)) {
	    	    	oneFile = fileUtil.parseFile(signFile, "RAW", 0, "", "");
		    		atchFileId = fileMngService.insertFileInf(oneFile);
		    		req.setSignUrl(atchFileId);
	    	    }

	    	    result = rawService.insertEsd(req);
	        	
	        } else {
	    		result = false;
	    		msg = ResponseMessage.UNAUTHORIZED;
	    	}
	    
	    }   
		
		BasicResponse res = BasicResponse.builder().result(result)
				.message(msg)
				.build();
		
		return res;
	}
 
    @ApiOperation(value = "로데이터 > RS 등록"
			, notes="1. 수정시 rsSeq 필수\n "
				  + "2. signFile 시험자 서명")
	@PostMapping(value="/insertRs.do")
	public BasicResponse insertRs(@RequestPart(value="rsDTO") RsDTO req,
			@RequestPart(value = "signFile", required = false) MultipartFile signFile) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String msg = "";
		boolean result = false;
		
		// 로그인정보
		req.setInsMemId(user.getId());
		req.setUdtMemId(user.getId());
		req.setMacType("ED");
		
		System.out.println("=-===========");
		System.out.println(req.toString());
		System.out.println("=-===========");
		
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	    Validator validator = validatorFactory.getValidator();
	    
	    Set<ConstraintViolation<RsDTO>> violations = validator.validate(req);
	    
	    for (ConstraintViolation<RsDTO> violation : violations) {
	    	msg = violation.getMessage();
	    	
	    	System.out.println(msg);
	    	BasicResponse res = BasicResponse.builder().result(false)
	    			.message(msg)
					.build();
	    	
	    	return res;
	    }
	    
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    
	    // 이미 등록된 로데이터가 있는지 확인
	    if(req.getRsSeq() == 0 && !ObjectUtils.isEmpty(rawService.rsDetail(req.getRawSeq()))) {
	    	result = false;
			msg = ResponseMessage.DUPLICATE_RAW;
	    } else {
	    
	        if (isAuthenticated) {
	        	
	        	List<FileVO> FileResult = null;
	        	FileVO oneFile = null;
	        	String atchFileId = "";
	    		
	        	// 시험자 서명
	    	    if (!ObjectUtils.isEmpty(signFile)) {
	    	    	oneFile = fileUtil.parseFile(signFile, "RAW", 0, "", "");
		    		atchFileId = fileMngService.insertFileInf(oneFile);
		    		req.setSignUrl(atchFileId);
	    	    }

	    	    result = rawService.insertRs(req);
	        	
	        } else {
	    		result = false;
	    		msg = ResponseMessage.UNAUTHORIZED;
	    	}
	    
	    }   
		
		BasicResponse res = BasicResponse.builder().result(result)
				.message(msg)
				.build();
		
		return res;
	}
    
    @ApiOperation(value = "등록")
    @PostMapping(value="/insert2.do")
    public BasicResponse insert2(@RequestBody RsDTO req) {
    	boolean result = false;
    	
    	System.out.println(req.toString());
    	
//    	rawService.insert(req);
    	
    	BasicResponse res = BasicResponse.builder().result(result)
    			.message("")
				.build();
    	
    	return res;
    }
}
