package egovframework.tst.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egovframework.cmm.service.BasicResponse;
import egovframework.cmm.service.ComParam;
import egovframework.cmm.service.LoginVO;
import egovframework.cmm.service.PagingVO;
import egovframework.cmm.service.ResponseMessage;
import egovframework.cmm.util.EgovUserDetailsHelper;
import egovframework.cnf.service.TestStndr;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.tst.service.Test;
import egovframework.tst.service.TestCate;
import egovframework.tst.service.TestDTO;
import egovframework.tst.service.TstParam;
import egovframework.tst.service.TstService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = {"시험"})
@RestController
@RequestMapping("/tst")
public class TstController {
	
	@Resource(name = "TstService")
	private TstService tstService;
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;
	 
	@ApiOperation(value = "시험항목 > 인증종류리스트", notes = "시험항목 > 시험종류리스트는 공통코드 리스트로 조회 = /cmm/comcode/list.do?code=TT")
	@GetMapping(value="/crtfcList.do")
	public List<TestCate> crtfcTypeList(@ApiParam(value = "상위인증코드(없으면 값 넣지 않음)", example = "1") @RequestParam int topCode) throws Exception{
    	List<TestCate> result = new ArrayList<TestCate>();
    	
    	result = tstService.selectCrtfList(topCode);
    	
    	return result;
    }
	@ApiOperation(value = "시험항목 시험규격리스트")
	@GetMapping(value="/stndrList.do")
	public List<TestStndr> stndrList(TstParam param) throws Exception{
    	List<TestStndr> result = new ArrayList<TestStndr>();
    	
    	result = tstService.selectStndrList(param);
    	
    	return result;   	
    }
	
    @ApiOperation(value = "시험 신청하기")
    @PostMapping(value="/makeTest.do")
    public BasicResponse makeTest(@ApiParam(value = "testItemSeq 값만 전송") @RequestBody TestDTO.Req req) throws Exception{
    	
//    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	LoginVO user = new LoginVO();
    	boolean result = true;
    	String msg = "";
    	Test detail = new Test();
    	
    	// 로그인정보
    	req.setInsMemId(user.getId());
    	req.setUdtMemId(user.getId());
    	
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	
    	if (isAuthenticated) {
	    	// 이미 연결된 시험이 있는지 확인
	    	if (tstService.selectDetail(req) != null) {
	    		result = false;
    			msg = ResponseMessage.DUPLICATE_TEST;
	    	} else {
	    		// 시험 생성
	    		result = tstService.insert(req);
	    	}
    		
    	}
    	
    	BasicResponse res = BasicResponse.builder().result(result)
    			.message(msg)
    			.data(detail)
				.build();
    	
        return res;   
        
    }
    
    
    @ApiOperation(value = "시험리스트", notes = "결과값은 StbDTO.Res 참고")
    @GetMapping(value="/list.do")
    public BasicResponse sbkList(@ModelAttribute ComParam param) throws Exception{
    	
//    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	LoginVO user = new LoginVO();
    	boolean result = true;
    	String msg = "";
    	List<TestDTO.Res> list = new ArrayList<TestDTO.Res>();

    	//페이징
    	param.setPageUnit(propertyService.getInt("pageUnit"));
    	param.setPageSize(propertyService.getInt("pageSize"));
		
    	PagingVO pagingVO = new PagingVO();
		
    	pagingVO.setCurrentPageNo(param.getPageIndex());
    	pagingVO.setDisplayRow(param.getPageUnit());
    	pagingVO.setDisplayPage(param.getPageSize());
		
    	param.setFirstIndex(pagingVO.getFirstRecordIndex());
    	int cnt = tstService.selectListCnt(param);
    	
    	pagingVO.setTotalCount(cnt);
		pagingVO.setTotalPage((int) Math.ceil(pagingVO.getTotalCount() / (double) pagingVO.getDisplayRow()));
    	list = tstService.selectList(param);

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
    
    @ApiOperation(value = "시험담당자 저장", notes="testSeq:시험고유항목, testMngId:시험담당자ID, memo:메모")
    @PostMapping(value="/testMemInsert.do")
    public BasicResponse testMemInsert(@RequestBody TestDTO.Req req) throws Exception{
//    	LoginVO user = new LoginVO();
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
//    	if (user == null) { return new ResponseEntity(BasicResponse.res(StatusCode.UNAUTHORIZED, ResponseMessage.NO_LOGIN), HttpStatus.OK); }
    	
    	// 로그인정보
    	req.setInsMemId(user.getId());
    	req.setUdtMemId(user.getId());
    	
    	boolean result = false;
    	String msg = "";
    	
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	
    	if (isAuthenticated) {
   			result = tstService.testMemInsert(req);
    	} else {
    		result = false;
    		msg = ResponseMessage.UNAUTHORIZED;
    	}
    	
    	BasicResponse res = BasicResponse.builder().result(result)
    			.message(msg)
				.build();
    	
    	return res;
    }
    
    @ApiOperation(value = "시험담당자 변경 내역")
    @GetMapping(value="/testMemList.do")
    public BasicResponse testMemList(@ApiParam(value = "시험 고유번호", required = true, example = "1") @RequestParam(value="testSeq") String testSeq) throws Exception{
    	
//    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	LoginVO user = new LoginVO();
    	boolean result = true;
    	String msg = "";
    	List<TestDTO.Res> list = new ArrayList<TestDTO.Res>();
		
    	list = tstService.testMemList(testSeq);

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
    
    @ApiOperation(value = "시험상태 변경", notes="testSeq:시험고유항목, 시험상태:공통코드(TS), memo:메모")
    @PostMapping(value="/testStateInsert.do")
    public BasicResponse testStateInsert(@RequestBody TestDTO.Req req) throws Exception{
//    	LoginVO user = new LoginVO();
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
//    	if (user == null) { return new ResponseEntity(BasicResponse.res(StatusCode.UNAUTHORIZED, ResponseMessage.NO_LOGIN), HttpStatus.OK); }
    	
    	// 로그인정보
    	req.setInsMemId(user.getId());
    	req.setUdtMemId(user.getId());
    	
    	boolean result = false;
    	String msg = "";
    	
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	
    	if (isAuthenticated) {
   			result = tstService.testStateInsert(req);
    	} else {
    		result = false;
    		msg = ResponseMessage.UNAUTHORIZED;
    	}
    	
    	BasicResponse res = BasicResponse.builder().result(result)
    			.message(msg)
				.build();
    	
    	return res;
    }
    
    @ApiOperation(value = "시험상태 변경 내역")
    @GetMapping(value="/testStateList.do")
    public BasicResponse testStateList(@ApiParam(value = "시험 고유번호", required = true, example = "1") @RequestParam(value="testSeq") String testSeq) throws Exception{
    	
//    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	LoginVO user = new LoginVO();
    	boolean result = true;
    	String msg = "";
    	List<TestDTO.Res> list = new ArrayList<TestDTO.Res>();
		
    	list = tstService.testStateList(testSeq);

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
 
    @ApiOperation(value = "시험게시판 등록", notes="testSeq:시험고유항목, memo:메모")
    @PostMapping(value="/testBoardInsert.do")
    public BasicResponse testBoardInsert(@RequestBody TestDTO.Req req) throws Exception{
//    	LoginVO user = new LoginVO();
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
//    	if (user == null) { return new ResponseEntity(BasicResponse.res(StatusCode.UNAUTHORIZED, ResponseMessage.NO_LOGIN), HttpStatus.OK); }
    	
    	// 로그인정보
    	req.setInsMemId(user.getId());
    	req.setUdtMemId(user.getId());
    	
    	boolean result = false;
    	String msg = "";
    	
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	
    	if (isAuthenticated) {
   			result = tstService.testBoardInsert(req);
    	} else {
    		result = false;
    		msg = ResponseMessage.UNAUTHORIZED;
    	}
    	
    	BasicResponse res = BasicResponse.builder().result(result)
    			.message(msg)
				.build();
    	
    	return res;
    }
    
    @ApiOperation(value = "시험게시판 내역")
    @GetMapping(value="/testBoardList.do")
    public BasicResponse testBoardList(@ApiParam(value = "시험 고유번호", required = true, example = "1") @RequestParam(value="testSeq") String testSeq) throws Exception{
    	
//    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	LoginVO user = new LoginVO();
    	boolean result = true;
    	String msg = "";
    	List<TestDTO.Res> list = new ArrayList<TestDTO.Res>();
		
    	list = tstService.testBoardList(testSeq);

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