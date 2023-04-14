package egovframework.raw.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import egovframework.raw.dto.EftDTO;
import egovframework.raw.dto.EsdDTO;
import egovframework.raw.dto.RawDTO;
import egovframework.raw.dto.RawSearchDTO;
import egovframework.raw.dto.ReDTO;
import egovframework.raw.dto.RsDTO;
import egovframework.raw.dto.SetupDTO;
import egovframework.raw.dto.SurgeDTO;
import egovframework.raw.service.RawData;
import egovframework.raw.service.RawService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
    
    @ApiOperation(value = "로데이터 기본정보 상세보기")
    @GetMapping(value="/{testSeq}/detail.do")
    public BasicResponse rawDetail(@ApiParam(value = "시험 고유번호", required = true, example = "9") @PathVariable(name = "testSeq") int testSeq) throws Exception{
    	boolean result = true;
    	String msg = "";
    	RawData detail = new RawData();
		
    	detail = rawService.detail(testSeq);
    	
    	if (detail == null) {
    		result = false;
    		msg = ResponseMessage.NO_DATA;
    	} else {
    		/* 세부데이터 추가로 가지고 오기 */
    		// 4-1. Technical Requirements (기술적 요구항목)
    		detail.setRawTchnList(rawService.tchnList(detail.getRawSeq()));
    		// 4-2. method (시험방법)
    		detail.setMethodList(rawService.methodList(detail.getRawSeq()));
    		// 6. Technical specifications (기술제원)
    		detail.setRawSpecList(rawService.specList(detail.getRawSeq()));
    		// 8. EUT Modifications (보완사항) - 파일리스트
    		FileVO fileVO = new FileVO();
    		fileVO.setAtchFileId(detail.getModUrl());
    		List<FileVO> modResult = fileMngService.selectImageFileList(fileVO);
    		//detail.setModFileList(modReulst.stream().map(FileVO::getFileSn).collect(Collectors.toList()));
    		List<String> modList = new ArrayList<String>();
    		if (modResult != null) {
    			for (FileVO item : modResult) {
    				modList.add("/file/getImage.do?atchFileId=".concat(detail.getModUrl()).concat("&fileSn=").concat(item.getFileSn()));
    			}
    		}
    		detail.setModFileList(modList);
    		// 9. Assistance Device and Cable(시험기기 전체구성)
    		detail.setRawAsstnList(rawService.asstnList(detail.getRawSeq()));
    		// 10. System Configuration (시스템구성)
    		detail.setRawSysList(rawService.sysList(detail.getRawSeq()));
    		// 11. Type of Cable Used (접속 케이블)
    		detail.setRawCableList(rawService.cableList(detail.getRawSeq()));
    		// 14. Test Set-up Configuraiotn for EUT - 타이틀&파일 리스트
    		fileVO = new FileVO();
    		fileVO.setAtchFileId(detail.getSetupUrl());
    		List<FileVO> setupReulst = fileMngService.selectImageFileList(fileVO);
    		List<SetupDTO> setupList = new ArrayList<SetupDTO>();
    		if (setupReulst != null) {
    			for (FileVO item : setupReulst) {
    				SetupDTO map = new SetupDTO();
    				map.setTitle(item.getFileCn());
    				map.setImageUrl("/file/getImage.do?atchFileId=".concat(detail.getSetupUrl()).concat("&fileSn=").concat(item.getFileSn()));
    				setupList.add(map);
    				
    			}
    		}
    		detail.setSetupList(setupList);
    		
    	}
    	
    	BasicResponse res = BasicResponse.builder().result(result)
    			.message(msg)
				.data(detail)
				.build();
    	
        return res;
    }
    
    @ApiOperation(value = "기본정보 등록", notes="0. 신규등록시 TestSeq 값 필수\n"
    											+ "1. 수정시 rawseq 필수, 파일추가시 ModUrl 과 SetupUrl 필수, 리스트항목은 견적서 시험항목 처리와 동일(state)\n"
    											+ "2. modFileList(보완사항파일) 같은이름으로 여러개\n"
    											+ "3. delFileList(리스트파일일 경우만) : atchFileId(ModUrl 과 SetupUrl 값), fileCn(파일순번)\n"
    											+ "4. 기술제원 주파수구분(RC)")
    @PostMapping(value="/insert.do")
    public BasicResponse insert(@RequestPart(value="rawData") RawData req,
    		@RequestPart(value = "delFileList", required = false) List<FileVO> delFileList,
    		@ModelAttribute RawDTO raw) throws Exception {
    	
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	String msg = "";
    	boolean result = false;
    	RawSearchDTO search = new RawSearchDTO();
		search.setTestSeq(req.getTestSeq());
		
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
        	System.out.println("violation ERROR::"+msg);
        	
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
			    
			    try {
		        	if (req.getRawSeq() == 0)
		    	    	result = rawService.insert(req);
		    	    else 
		    	    	result = rawService.update(req);
		        	
	            } catch (Exception e) {
	        		msg = e.getCause().toString();
	        	}
            
            }
	        	
        } else {
    		result = false;
    		msg = ResponseMessage.UNAUTHORIZED;
    	}
        

    	
    	BasicResponse res = BasicResponse.builder().result(result)
    			.data(req.getRawSeq())
    			.message(msg)
				.build();
    	
    	return res;
    }

    @ApiOperation(value = "로데이터 CE 상세보기")
    @GetMapping(value="/{rawSeq}/ce/detail.do")
    public BasicResponse ceDetail(@ApiParam(value = "로데이터 고유번호", required = true, example = "7") @PathVariable(name = "rawSeq") int rawSeq) throws Exception{
    	boolean result = true;
    	String msg = "";
    	CeDTO detail = new CeDTO();
		
    	detail = rawService.ceDetail(rawSeq);
    	
    	if (detail == null) {
    		result = false;
    		msg = ResponseMessage.NO_DATA;
    	} else {
    		// 측정설비
    		detail.setMacList(rawService.macList("CE", rawSeq));
    		
    		// 시험결과별도첨부
    		FileVO fileVO = new FileVO();
    		fileVO.setAtchFileId(detail.getResultUrl());
    		List<FileVO> fileReulst = fileMngService.selectImageFileList(fileVO);
    		List<String> resultList = new ArrayList<String>();
    		if (fileReulst != null) {
    			for (FileVO item : fileReulst) {
    				resultList.add("/file/getImage.do?atchFileId=".concat(detail.getResultUrl()).concat("&fileSn=").concat(item.getFileSn()));
    				
    			}
    		}
    		detail.setResultList(resultList);
    	}
    	
    	BasicResponse res = BasicResponse.builder().result(result)
    			.message(msg)
				.data(detail)
				.build();
    	
        return res;
    }
    
    @ApiOperation(value = "로데이터 > CE 등록"
    			, notes="0. 신규등록시 rawSeq 또는 TestSeq(rawSeq없을때만)값 필수\n"
    					+ "1. 수정시 rawSeq 필수, 파일추가시 resultUrl 필수\n "
    					+ "2. delFileList(리스트파일일  경우만) : atchFileId(resultUrl값), fileCn(파일순번)\n"
    					+ "3. signFile 시험자 서명, resultFiles 시험결과")
    @PostMapping(value="/ce/insert.do")
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
        	
        	System.out.println("violation ERROR::"+msg);
        	BasicResponse res = BasicResponse.builder().result(false)
        			.message(msg)
    				.build();
        	
        	return res;
        }
        
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        
	    // 신규등록시 필수 값 확인
	    if(req.getRawSeq() == 0 && req.getTestSeq() == 0) {
	    	result = false;
			msg = ResponseMessage.CHECK_DATA;
	    }
	    else {
        
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
    			.data(req.getRawSeq())
    			.message(msg)
				.build();
    	
    	return res;
    }
 
    @ApiOperation(value = "로데이터 RE 상세보기")
    @GetMapping(value="/{rawSeq}/re/detail.do")
    public BasicResponse reDetail(@ApiParam(value = "로데이터 고유번호", required = true, example = "7") @PathVariable(name = "rawSeq") int rawSeq) throws Exception{
    	boolean result = true;
    	String msg = "";
    	ReDTO detail = new ReDTO();
		
    	detail = rawService.reDetail(rawSeq);
    	
    	if (detail == null) {
    		result = false;
    		msg = ResponseMessage.NO_DATA;
    	} else {
    		// 측정설비
    		detail.setMacList(rawService.macList("RE", rawSeq));
    		
    		// 시험결과별도첨부
    		FileVO fileVO = new FileVO();
    		fileVO.setAtchFileId(detail.getResultUrl());
    		List<FileVO> fileReulst = fileMngService.selectImageFileList(fileVO);
    		List<String> resultList = new ArrayList<String>();
    		if (fileReulst != null) {
    			for (FileVO item : fileReulst) {
    				resultList.add("/file/getImage.do?atchFileId=".concat(detail.getResultUrl()).concat("&fileSn=").concat(item.getFileSn()));
    				
    			}
    		}
    		detail.setResultList(resultList);
    	}
    	
    	BasicResponse res = BasicResponse.builder().result(result)
    			.message(msg)
				.data(detail)
				.build();
    	
        return res;
    }
    
    @ApiOperation(value = "로데이터 > RE 등록"
			, notes="0. 신규등록시 rawSeq 또는 TestSeq(rawSeq없을때만)값 필수\n"
					+ "1. 수정시 rawSeq 필수, 파일추가시 resultUrl 필수\n "
					+ "2. delFileList(리스트파일일  경우만) : atchFileId(resultUrl값), fileCn(파일순번)\n"
					+ "3. signFile1 대역1 서명, signFile2 대역2 서명, resultFiles 시험결과\n"
					+ "4. 대역코드(RH)")
	@PostMapping(value="/re/insert.do")
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
	    	
	    	System.out.println("violation ERROR::"+msg);
	    	BasicResponse res = BasicResponse.builder().result(false)
	    			.message(msg)
					.build();
	    	
	    	return res;
	    }
	    
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    
	    // 신규등록시 필수 값 확인
	    if(req.getRawSeq() == 0 && req.getTestSeq() == 0) {
	    	result = false;
			msg = ResponseMessage.CHECK_DATA;
	    }
        else {
	    
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
				.data(req.getRawSeq())
				.message(msg)
				.build();
		
		return res;
	}

    @ApiOperation(value = "로데이터 ESD 상세보기")
    @GetMapping(value="/{rawSeq}/esd/detail.do")
    public BasicResponse esdDetail(@ApiParam(value = "로데이터 고유번호", required = true, example = "7") @PathVariable(name = "rawSeq") int rawSeq) throws Exception{
    	boolean result = true;
    	String msg = "";
    	EsdDTO detail = new EsdDTO();
		
    	detail = rawService.esdDetail(rawSeq);
    	
    	if (detail == null) {
    		result = false;
    		msg = ResponseMessage.NO_DATA;
    	} else {
    		/* 세부데이터 추가로 가지고 오기 */
    		// 측정설비
    		detail.setMacList(rawService.macList("ED", rawSeq));
    		// 직접인가
    		detail.setSubList(rawService.esdSubList(detail.getEsdSeq()));
    	}
    	
    	BasicResponse res = BasicResponse.builder().result(result)
    			.message(msg)
				.data(detail)
				.build();
    	
        return res;
    }
    
    @ApiOperation(value = "로데이터 > ESD 등록"
			, notes="0. 신규등록시 rawSeq 또는 TestSeq(rawSeq없을때만)값 필수\n"
					+ "1. 수정시 rawSeq 필수\n "
					+ "2. signFile 시험자 서명\n"
					+ "3. 시험결과(A,B,C)")
	@PostMapping(value="/esd/insert.do")
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
	    	
	    	System.out.println("violation ERROR::"+msg);
	    	BasicResponse res = BasicResponse.builder().result(false)
	    			.message(msg)
					.build();
	    	
	    	return res;
	    }
	    
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    
	    // 신규등록시 필수 값 확인
	    if(req.getRawSeq() == 0 && req.getTestSeq() == 0) {
	    	result = false;
			msg = ResponseMessage.CHECK_DATA;
	    }
	    else {
	    
	        if (isAuthenticated) {
	        	
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
				.data(req.getRawSeq())
				.message(msg)
				.build();
		
		return res;
	}
    
    @ApiOperation(value = "로데이터 RS 상세보기")
    @GetMapping(value="/{rawSeq}/rs/detail.do")
    public BasicResponse rsDetail(@ApiParam(value = "로데이터 고유번호", required = true, example = "7") @PathVariable(name = "rawSeq") int rawSeq) throws Exception{
    	boolean result = true;
    	String msg = "";
    	RsDTO detail = new RsDTO();
		
    	detail = rawService.rsDetail(rawSeq);
    	
    	if (detail == null) {
    		result = false;
    		msg = ResponseMessage.NO_DATA;
    	} else {
    		/* 세부데이터 추가로 가지고 오기 */
    		// 측정설비
    		detail.setMacList(rawService.macList("RS", rawSeq));
    	}
    	
    	BasicResponse res = BasicResponse.builder().result(result)
    			.message(msg)
				.data(detail)
				.build();
    	
        return res;
    }
 
    @ApiOperation(value = "로데이터 > RS 등록"
			, notes="0. 신규등록시 rawSeq 또는 TestSeq(rawSeq없을때만)값 필수\n"
					+"1. 수정시 rawSeq 필수\n "
					+ "2. signFile 시험자 서명")
	@PostMapping(value="/rs/insert.do")
	public BasicResponse insertRs(@RequestPart(value="rsDTO") RsDTO req,
			@RequestPart(value = "signFile", required = false) MultipartFile signFile) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String msg = "";
		boolean result = false;
		
		// 로그인정보
		req.setInsMemId(user.getId());
		req.setUdtMemId(user.getId());
		req.setMacType("RS");
		
		System.out.println("=-===========");
		System.out.println(req.toString());
		System.out.println("=-===========");
		
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	    Validator validator = validatorFactory.getValidator();
	    
	    Set<ConstraintViolation<RsDTO>> violations = validator.validate(req);
	    
	    for (ConstraintViolation<RsDTO> violation : violations) {
	    	msg = violation.getMessage();
	    	
	    	System.out.println("violation ERROR::"+msg);
	    	BasicResponse res = BasicResponse.builder().result(false)
	    			.message(msg)
					.build();
	    	
	    	return res;
	    }
	    
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    
	    // 신규등록시 필수 값 확인
	    if(req.getRawSeq() == 0 && req.getTestSeq() == 0) {
	    	result = false;
			msg = ResponseMessage.CHECK_DATA;
	    } else {
	    
	        if (isAuthenticated) {
	        	
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
				.data(req.getRawSeq())
				.message(msg)
				.build();
		
		return res;
	}

    @ApiOperation(value = "로데이터 EFT / B U R S T 상세보기")
    @GetMapping(value="/{rawSeq}/eft/detail.do")
    public BasicResponse eftDetail(@ApiParam(value = "로데이터 고유번호", required = true, example = "7") @PathVariable(name = "rawSeq") int rawSeq) throws Exception{
    	boolean result = true;
    	String msg = "";
    	EftDTO detail = new EftDTO();
		
    	detail = rawService.eftDetail(rawSeq);
    	
    	if (detail == null) {
    		result = false;
    		msg = ResponseMessage.NO_DATA;
    	} else {
    		/* 세부데이터 추가로 가지고 오기 */
    		// 측정설비
    		detail.setMacList(rawService.macList("ET", rawSeq));
    	}
    	
    	BasicResponse res = BasicResponse.builder().result(result)
    			.message(msg)
				.data(detail)
				.build();
    	
        return res;
    }
    
    @ApiOperation(value = "로데이터 > EFT / B U R S T 등록"
    			, notes="0. 신규등록시 rawSeq 또는 TestSeq(rawSeq없을때만)값 필수\n"
						+ "1. 수정시 rawSeq 필수\n "
					    + "2. signFile 시험자 서명")
	@PostMapping(value="/eft/insert.do")
	public BasicResponse insertEft(@RequestPart(value="eftDTO") EftDTO req,
			@RequestPart(value = "signFile", required = false) MultipartFile signFile) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String msg = "";
		boolean result = false;
		
		// 로그인정보
		req.setInsMemId(user.getId());
		req.setUdtMemId(user.getId());
		req.setMacType("ET");
		
		System.out.println("=-===========");
		System.out.println(req.toString());
		System.out.println("=-===========");
		
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	    Validator validator = validatorFactory.getValidator();
	    
	    Set<ConstraintViolation<EftDTO>> violations = validator.validate(req);
	    
	    for (ConstraintViolation<EftDTO> violation : violations) {
	    	msg = violation.getMessage();
	    	
	    	System.out.println("violation ERROR::"+msg);
	    	BasicResponse res = BasicResponse.builder().result(false)
	    			.message(msg)
					.build();
	    	
	    	return res;
	    }
	    
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    
	    // 신규등록시 필수 값 확인
	    if(req.getRawSeq() == 0 && req.getTestSeq() == 0) {
	    	result = false;
			msg = ResponseMessage.CHECK_DATA;
	    }
	    else {
	    
	        if (isAuthenticated) {
	        	
		        	FileVO oneFile = null;
		        	String atchFileId = "";
		    		
		        	// 시험자 서명
		    	    if (!ObjectUtils.isEmpty(signFile)) {
		    	    	oneFile = fileUtil.parseFile(signFile, "RAW", 0, "", "");
			    		atchFileId = fileMngService.insertFileInf(oneFile);
			    		req.setSignUrl(atchFileId);
		    	    }
		    	    
	    	    	result = rawService.insertEft(req);
		    	    
	        } else {
	    		result = false;
	    		msg = ResponseMessage.UNAUTHORIZED;
	    	}
	    
	    }   
		
		BasicResponse res = BasicResponse.builder().result(result)
				.data(req.getRawSeq())
				.message(msg)
				.build();
		
		return res;
	}
    
    @ApiOperation(value = "등록")
    @PostMapping(value="/insert2.do")
    public BasicResponse insert2(@RequestBody SurgeDTO req) {
    	boolean result = false;
    	
    	System.out.println(req.toString());
    	
//    	rawService.insert(req);
    	
    	BasicResponse res = BasicResponse.builder().result(result)
    			.message("")
				.build();
    	
    	return res;
    }
    
    @ApiOperation(value = "로데이터 S U R G E 상세보기")
    @GetMapping(value="/{rawSeq}/surge/detail.do")
    public BasicResponse surgeDetail(@ApiParam(value = "로데이터 고유번호", required = true, example = "7") @PathVariable(name = "rawSeq") int rawSeq) throws Exception{
    	boolean result = true;
    	String msg = "";
    	SurgeDTO detail = new SurgeDTO();
		
    	detail = rawService.surgeDetail(rawSeq);
    	
    	if (detail == null) {
    		result = false;
    		msg = ResponseMessage.NO_DATA;
    	} else {
    		/* 세부데이터 추가로 가지고 오기 */
    		// 측정설비
    		detail.setMacList(rawService.macList("SG", rawSeq));
    	}
    	
    	BasicResponse res = BasicResponse.builder().result(result)
    			.message(msg)
				.data(detail)
				.build();
    	
        return res;
    }
    
    @ApiOperation(value = "로데이터 > S U R G E 등록"
				, notes="0. 신규등록시 rawSeq 또는 TestSeq(rawSeq없을때만)값 필수\n"
						+ "1. 수정시 rawSeq 필수\n "
					    + "2. signFile 시험자 서명")
	@PostMapping(value="/surge/insert.do")
	public BasicResponse insertSurge(@RequestPart(value="surgeDTO") SurgeDTO req,
			@RequestPart(value = "signFile", required = false) MultipartFile signFile) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String msg = "";
		boolean result = false;
		
		// 로그인정보
		req.setInsMemId(user.getId());
		req.setUdtMemId(user.getId());
		req.setMacType("SG");
		
		System.out.println("=-===========");
		System.out.println(req.toString());
		System.out.println("=-===========");
		
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	    Validator validator = validatorFactory.getValidator();
	    
	    Set<ConstraintViolation<SurgeDTO>> violations = validator.validate(req);
	    
	    for (ConstraintViolation<SurgeDTO> violation : violations) {
	    	msg = violation.getMessage();
	    	
	    	System.out.println("violation ERROR::"+msg);
	    	BasicResponse res = BasicResponse.builder().result(false)
	    			.message(msg)
					.build();
	    	
	    	return res;
	    }
	    
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    
	    // 신규등록시 필수 값 확인
	    if(req.getRawSeq() == 0 && req.getTestSeq() == 0) {
	    	result = false;
			msg = ResponseMessage.CHECK_DATA;
	    }
	    else {
	    
	        if (isAuthenticated) {
	        	
		        	FileVO oneFile = null;
		        	String atchFileId = "";
		    		
		        	// 시험자 서명
		    	    if (!ObjectUtils.isEmpty(signFile)) {
		    	    	oneFile = fileUtil.parseFile(signFile, "RAW", 0, "", "");
			    		atchFileId = fileMngService.insertFileInf(oneFile);
			    		req.setSignUrl(atchFileId);
		    	    }
		    	    
	    	    	result = rawService.insertSurge(req);
		    	    
	        } else {
	    		result = false;
	    		msg = ResponseMessage.UNAUTHORIZED;
	    	}
	    
	    }   
		
		BasicResponse res = BasicResponse.builder().result(result)
				.data(req.getRawSeq())
				.message(msg)
				.build();
		
		return res;
	}
}
