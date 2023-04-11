package egovframework.quo.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import egovframework.cmm.service.BasicResponse;
import egovframework.cmm.service.ComParam;
import egovframework.cmm.service.EgovFileMngService;
import egovframework.cmm.service.FileVO;
import egovframework.cmm.service.HisDTO;
import egovframework.cmm.service.LoginVO;
import egovframework.cmm.service.PagingVO;
import egovframework.cmm.service.ResponseMessage;
import egovframework.cmm.util.EgovFileMngUtil;
import egovframework.cmm.util.EgovUserDetailsHelper;
import egovframework.quo.service.QuoDTO;
import egovframework.quo.service.QuoModDTO;
import egovframework.quo.service.QuoService;
import egovframework.rte.fdl.property.EgovPropertyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = {"견적서"})
@RestController
@RequestMapping("/quo")
public class QuoController {

	@Resource(name = "QuoService")
	private QuoService quoService;
	
    @Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;
    
    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;
    
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
    
    @ApiOperation(value = "견적서리스트", notes = "1. 결과값은 QuoDTO.Res 참고\n2.검색박스는 공통코드 CS, 필요한항목만 노출시켜서 사용\\n고객유형(PT)")
    @GetMapping(value="/list.do")
    public BasicResponse quoList(@ModelAttribute ComParam param) throws Exception{
    	
//    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	LoginVO user = new LoginVO();
    	boolean result = true;
    	String msg = "";
    	List<QuoDTO.Res> list = new ArrayList<QuoDTO.Res>();

    	//페이징
    	param.setPageUnit(propertyService.getInt("pageUnit"));
    	param.setPageSize(propertyService.getInt("pageSize"));
		
    	PagingVO pagingVO = new PagingVO();
		
    	pagingVO.setCurrentPageNo(param.getPageIndex());
    	pagingVO.setDisplayRow(param.getPageUnit());
    	pagingVO.setDisplayPage(param.getPageSize());
		
    	param.setFirstIndex(pagingVO.getFirstRecordIndex());
    	int cnt = quoService.selectListCnt(param);
    	
    	pagingVO.setTotalCount(cnt);
		pagingVO.setTotalPage((int) Math.ceil(pagingVO.getTotalCount() / (double) pagingVO.getDisplayRow()));
    	list = quoService.selectList(param);

    	if (list == null) {
    		result = false;
    		msg = ResponseMessage.NO_DATA;
    	}
    	
    	BasicResponse res = BasicResponse.builder().result(result)
    			.message(msg)
				.data(list)
				.paging(pagingVO)
				.build();
    	
        return res;
    }
    
    @ApiOperation(value = "견적서 상세보기", notes = "견적서상태 : 0 매출확정전, 1 매출확정, 2 수정요청, 3 수정허가, 4 수정완료")
    @GetMapping(value="/{quoId}/detail.do")
    public BasicResponse quoDetail(@ApiParam(value = "견적서 고유번호", required = true, example = "Q2303-G0018") @PathVariable(name = "quoId") String quoId) throws Exception{
    	
    	boolean result = true;
    	String msg = "";
    	QuoDTO.Res detail = new QuoDTO.Res();
    	QuoDTO.Req req = new QuoDTO.Req();
    	
    	req.setQuoId(quoId);
    	detail = quoService.selectDetail(req);
    	
    	if (detail == null) {
    		result = false;
    		msg = ResponseMessage.NO_DATA;
    	}
    	
    	BasicResponse res = BasicResponse.builder().result(result)
    			.message(msg)
				.data(detail)
				.build();
    	
        return res;
    }

//    @ApiOperation(value = "견적서 발급번호 생성")
//    @GetMapping(value="/getRef.do")
//    public String quoRef(@ApiParam(value = "견적서 종류(G:일반견적서, M:의료견적서)", required = true, example = "G") @RequestParam String type) throws Exception{
//    	
//    	String req = quoService.selectRef(type);
//    	
//        return req;
//    }
    
    @ApiOperation(value = "견적서 등록", notes="견적서 수정시 고유번호(quoSeq) 필수")
    @PostMapping(value="/insert.do", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public BasicResponse quoInsert(@RequestPart(value = "files", required = false) final List<MultipartFile> multiRequest,
    							@RequestPart(value="quo") QuoDTO.Req quo) throws Exception{
    	
//    	LoginVO user = new LoginVO();
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	String msg = "";
    	boolean result = false;
//    	if (user == null) { return new ResponseEntity(BasicResponse.res(StatusCode.UNAUTHORIZED, ResponseMessage.NO_LOGIN), HttpStatus.OK); }
    	
    	// 로그인정보
    	quo.setInsMemId(user.getId());
    	quo.setUdtMemId(user.getId());
    	
    	System.out.println("=-===========");
    	System.out.println(quo.toString());
    	System.out.println(quo.getTestItems().toString());
    	System.out.println("=-===========");
    	ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        
        Set<ConstraintViolation<QuoDTO.Req>> violations = validator.validate(quo);
        
        for (ConstraintViolation<QuoDTO.Req> violation : violations) {
        	msg = violation.getMessage();
        	
        	System.out.println(msg);
        	BasicResponse res = BasicResponse.builder().result(false)
        			.message(msg)
    				.build();
        	
        	return res;
        }
    	
    	//Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	Boolean isAuthenticated = true;
    	
    	if (isAuthenticated) {
    		List<FileVO> FileResult = null;
    		
    		final List<MultipartFile> files = multiRequest;
    		String atchFileId = "";
    	    if (!files.isEmpty()) {
	    		FileResult = fileUtil.parseFile(files, "QUO", 0, "", "");
	    		atchFileId = fileMngService.insertFileInfs(FileResult);
	    		quo.setSgnUrl(atchFileId);
    	    }
    	    
    	    if (StringUtils.isEmpty(quo.getQuoId()))
    	    	result = quoService.insert(quo);
    	    else 
    	    	result = quoService.update(quo);
    	    
    	} else {
    		result = false;
    		msg = ResponseMessage.UNAUTHORIZED;
    	}
    	
    	BasicResponse res = BasicResponse.builder().result(result)
    			.message(msg)
				.build();
    	
    	return res;
    }
   
    @ApiOperation(value = "견적서 수정요청", notes="stateCode : 수정요청 = 2,  수정허용 = 3, 수정완료 = 4")
    @PostMapping(value="/updateStatus.do")
    public BasicResponse updateStatus(@ApiParam(value = "quoId, memo, stateCode 입력") @RequestBody QuoModDTO.Req req) throws Exception{
    	
//    	LoginVO user = new LoginVO();
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
//    	if (user == null) { return new ResponseEntity(BasicResponse.res(StatusCode.UNAUTHORIZED, ResponseMessage.NO_LOGIN), HttpStatus.OK); }
    	
    	// 로그인정보
    	req.setInsMemId(user.getId());
    	req.setUdtMemId(user.getId());
    	
    	boolean result = false;
    	String msg = "";
    	
    	ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        
        Set<ConstraintViolation<QuoModDTO.Req>> violations = validator.validate(req);
        
        for (ConstraintViolation<QuoModDTO.Req> violation : violations) {
        	msg = violation.getMessage();
        	
        	System.out.println(msg);
        	BasicResponse res = BasicResponse.builder().result(false)
        			.message(msg)
    				.build();
        	
        	return res;
        }
        
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	
    	if (isAuthenticated) {
   			result = quoService.updateStatus(req);
    	} else {
    		result = false;
    		msg = ResponseMessage.UNAUTHORIZED;
    	}
    	
    	BasicResponse res = BasicResponse.builder().result(result)
    			.message(msg)
				.build();
    	
    	return res;
    }
    
    @ApiOperation(value = "수정요청리스트")
    @GetMapping(value="/statusList.do")
    public BasicResponse statusList(@ApiParam(value = "견적서 고유번호", required = true, example = "Q2303-G0032") @RequestParam(value="quoSeq") String quoSeq) throws Exception{
    	
//    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	LoginVO user = new LoginVO();
    	boolean result = true;
    	String msg = "";
    	List<QuoModDTO.Res> list = new ArrayList<QuoModDTO.Res>();
		
    	list = quoService.selectStatusList(quoSeq);

    	if (list == null) {
    		result = false;
    		msg = ResponseMessage.NO_DATA;
    	}
    	
    	BasicResponse res = BasicResponse.builder().result(result)
    			.message(msg)
    			.data(list)
				.build();
    	
        return res;    
    }
    
    @ApiOperation(value = "본건 견적서 작성")
    @PostMapping(value="/makeQuo.do")
    public BasicResponse makeQuo(@ApiParam(value = "sbkId 값만 전송") @RequestBody QuoDTO.Req req) throws Exception{
    	
//    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	LoginVO user = new LoginVO();
    	boolean result = true;
    	String msg = "";
    	QuoDTO.Res detail = new QuoDTO.Res();
    	
    	// 로그인정보
    	req.setInsMemId(user.getId());
    	req.setUdtMemId(user.getId());
    	
    	System.out.println("================");
    	System.out.println(req.toString());
    	System.out.println("================");
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	
    	if (isAuthenticated) {
	    	// 이미 연결된 견적서가 있는지 확인
	    	if (quoService.selectDetail(req) != null) {
	    		result = false;
    			msg = ResponseMessage.DUPLICATE_QUO;
	    	} else {
	    		
	    		// 견적서 생성
	    		result = quoService.insert(req);
	    		
	    		// 견적서 정보 보내주기
	        	detail = quoService.selectDetail(req);
	        	
	        	if (detail == null) {
	        		result = false;
	        		msg = ResponseMessage.NO_DATA;
	        	}
	        	
	    	}
    		
    	}
    	
    	BasicResponse res = BasicResponse.builder().result(result)
    			.message(msg)
    			.data(detail)
				.build();
    	
        return res;   
        
    }
    
    @ApiOperation(value = "견적서 수정 히스토리")
    @GetMapping(value="/hisList.do")
    public BasicResponse hisList(@ApiParam(value = "견적서 고유번호", required = true, example = "Q2303-G0086") @RequestParam(value="quoId") String quoId) throws Exception{
    	
//    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	LoginVO user = new LoginVO();
    	boolean result = true;
    	String msg = "";
    	List<HisDTO> list = new ArrayList<HisDTO>();
		
    	list = quoService.hisList(quoId);

    	if (list == null) {
    		result = false;
    		msg = ResponseMessage.NO_DATA;
    	}
    	
    	BasicResponse res = BasicResponse.builder().result(result)
    			.message(msg)
    			.data(list)
				.build();
    	
        return res;  
    }
}
