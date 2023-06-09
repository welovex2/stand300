package egovframework.cnf;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import egovframework.cmm.service.BasicResponse;
import egovframework.cmm.service.ComParam;
import egovframework.cmm.service.Dept;
import egovframework.cmm.service.LoginVO;
import egovframework.cmm.service.PagingVO;
import egovframework.cmm.service.Pos;
import egovframework.cmm.service.ResponseMessage;
import egovframework.cmm.util.EgovFileScrty;
import egovframework.cmm.util.EgovStringUtil;
import egovframework.cmm.util.EgovUserDetailsHelper;
import egovframework.cnf.service.MemService;
import egovframework.cnf.service.Member;
import egovframework.rte.fdl.property.EgovPropertyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = {"사용자"})
@RestController
@RequestMapping("/cnf/mem")
public class MemController {

  @Resource(name = "MemService")
  private MemService memService;

  @Resource(name = "propertiesService")
  protected EgovPropertyService propertyService;

  @ApiOperation(value = "사용자 리스트", notes = "검색코드\n42   이름\n43  직위\n44  연락처\n45 권한\n15   작성일 ")
  @GetMapping(value = "/list.do")
  public BasicResponse list(@ModelAttribute ComParam param) throws Exception {

    LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    boolean result = true;
    String msg = "";
    List<Member> list = new ArrayList<Member>();

    // 페이징
    param.setPageUnit(propertyService.getInt("pageUnit"));
    param.setPageSize(propertyService.getInt("pageSize"));

    PagingVO pagingVO = new PagingVO();

    pagingVO.setCurrentPageNo(param.getPageIndex());
    pagingVO.setDisplayRow(param.getPageUnit());
    pagingVO.setDisplayPage(param.getPageSize());

    param.setFirstIndex(pagingVO.getFirstRecordIndex());
    int cnt = memService.selectListCnt(param);

    pagingVO.setTotalCount(cnt);
    pagingVO.setTotalPage(
        (int) Math.ceil(pagingVO.getTotalCount() / (double) pagingVO.getDisplayRow()));
    list = memService.selectList(param);

    if (list == null) {
      result = false;
      msg = ResponseMessage.NO_DATA;
    }

    BasicResponse res =
        BasicResponse.builder().result(result).message(msg).data(list).paging(pagingVO).build();

    return res;
  }

  @ApiOperation(value = "사용자 등록", notes = "수정시, memberSeq 필수")
  @PostMapping(value = "/insert.do")
  public BasicResponse insert(
      @ApiParam(value = "", required = true, example = "") @RequestBody Member req)
      throws Exception {

    LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    String msg = "";

    // 로그인정보
    req.setInsMemId(user.getId());
    req.setUdtMemId(user.getId());

    // 기본 패스워드 설정
    req.setPassword(EgovFileScrty.encryptPassword(req.getId().concat(req.getInDt()), req.getId()));

    System.out.println("=-===========");
    System.out.println(req.toString());
    System.out.println("=-===========");

    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    Validator validator = validatorFactory.getValidator();

    Set<ConstraintViolation<Member>> violations = validator.validate(req);

    for (ConstraintViolation<Member> violation : violations) {
      msg = violation.getMessage();

      BasicResponse res = BasicResponse.builder().result(false).message(msg).build();

      return res;
    }

    boolean result = false;

    result = memService.insert(req);

    BasicResponse res = BasicResponse.builder().result(result).build();

    return res;
  }

  @ApiOperation(value = "사용자 상세보기")
  @GetMapping(value = "/{memSeq}/detail.do")
  public BasicResponse detail(@ApiParam(value = "사용자 고유번호", required = true,
      example = "0004") @PathVariable(name = "memSeq") int memSeq) throws Exception {
    boolean result = true;
    String msg = "";
    Member detail = new Member();

    detail = memService.detail(memSeq);

    if (detail == null) {
      result = false;
      msg = ResponseMessage.NO_DATA;
    }

    BasicResponse res = BasicResponse.builder().result(result).message(msg).data(detail).build();

    return res;
  }

  @ApiOperation(value = "비밀번호 변경", notes = "password : 현재비밀번호, newPassword : 신규비밀번호")
  @PostMapping(value = "/updatePassword.do")
  public BasicResponse updatePassword(@RequestBody LoginVO loginVO) throws Exception {

    boolean result = true;

    LoginVO resultVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

    // 비밀번호 정규식 확인
    String msg = EgovStringUtil.checkPassword(loginVO.getNewPassword(), loginVO.getId());
    if ("".contentEquals(msg)) {

      // 세션에서 현재 로그인한 아이디 가져오기
      loginVO.setId(resultVO.getId());
      result = memService.updatePassword(loginVO);

      if (result)
        RequestContextHolder.getRequestAttributes().removeAttribute("LoginVO",
            RequestAttributes.SCOPE_SESSION);
      else
        msg = ResponseMessage.NOT_FOUND_PASS;

    } else {
      result = false;
    }

    BasicResponse res = BasicResponse.builder().result(result).message(msg).build();

    return res;
  }

  @ApiOperation(value = "부서 리스트", notes = "")
  @GetMapping(value = "/dept/list.do")
  public BasicResponse cateList(@ModelAttribute ComParam param) throws Exception {

    LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    boolean result = true;
    String msg = "";
    List<Dept> list = new ArrayList<Dept>();

    // 페이징
    param.setPageUnit(propertyService.getInt("pageUnit"));
    param.setPageSize(propertyService.getInt("pageSize"));

    PagingVO pagingVO = new PagingVO();

    pagingVO.setCurrentPageNo(param.getPageIndex());
    pagingVO.setDisplayRow(param.getPageUnit());
    pagingVO.setDisplayPage(param.getPageSize());

    param.setFirstIndex(pagingVO.getFirstRecordIndex());
    int cnt = memService.selectDeptListCnt(param);

    pagingVO.setTotalCount(cnt);
    pagingVO.setTotalPage(
        (int) Math.ceil(pagingVO.getTotalCount() / (double) pagingVO.getDisplayRow()));
    list = memService.selectDeptList(param);

    if (list == null) {
      result = false;
      msg = ResponseMessage.NO_DATA;
    }

    BasicResponse res = BasicResponse.builder().result(result).message(msg).data(list).build();

    return res;
  }

  @ApiOperation(value = "부서 등록", notes = "수정,삭제시 deftSeq 필수\n삭제시 state='D' 추가")
  @PostMapping(value = "/dept/insert.do")
  public BasicResponse cateInsert(
      @ApiParam(value = "", required = true, example = "") @RequestBody Dept req) throws Exception {

    LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    String msg = "";


    // 로그인정보
    req.setInsMemId(user.getId());
    req.setUdtMemId(user.getId());


    System.out.println("=-===========");
    System.out.println(req.toString());
    System.out.println("=-===========");

    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    Validator validator = validatorFactory.getValidator();

    Set<ConstraintViolation<Dept>> violations = validator.validate(req);

    for (ConstraintViolation<Dept> violation : violations) {
      msg = violation.getMessage();

      BasicResponse res = BasicResponse.builder().result(false).message(msg).build();

      return res;
    }


    boolean result = false;

    result = memService.insertDept(req);
    BasicResponse res = BasicResponse.builder().result(result).build();

    return res;
  }

  @ApiOperation(value = "직위 리스트", notes = "")
  @GetMapping(value = "/pos/list.do")
  public BasicResponse posList(@ModelAttribute ComParam param) throws Exception {

    LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    boolean result = true;
    String msg = "";
    List<Pos> list = new ArrayList<Pos>();

    // 페이징
    param.setPageUnit(propertyService.getInt("pageUnit"));
    param.setPageSize(propertyService.getInt("pageSize"));

    PagingVO pagingVO = new PagingVO();

    pagingVO.setCurrentPageNo(param.getPageIndex());
    pagingVO.setDisplayRow(param.getPageUnit());
    pagingVO.setDisplayPage(param.getPageSize());

    param.setFirstIndex(pagingVO.getFirstRecordIndex());
    int cnt = memService.selectPosListCnt(param);

    pagingVO.setTotalCount(cnt);
    pagingVO.setTotalPage(
        (int) Math.ceil(pagingVO.getTotalCount() / (double) pagingVO.getDisplayRow()));
    list = memService.selectPosList(param);

    if (list == null) {
      result = false;
      msg = ResponseMessage.NO_DATA;
    }

    BasicResponse res = BasicResponse.builder().result(result).message(msg).data(list).build();

    return res;
  }

  @ApiOperation(value = "직위 등록", notes = "수정,삭제시 posSeq 필수\n삭제시 state='D' 추가")
  @PostMapping(value = "/pos/insert.do")
  public BasicResponse posInsert(
      @ApiParam(value = "", required = true, example = "") @RequestBody Pos req) throws Exception {

    LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    String msg = "";

    // 로그인정보
    req.setInsMemId(user.getId());
    req.setUdtMemId(user.getId());

    System.out.println("=-===========");
    System.out.println(req.toString());
    System.out.println("=-===========");

    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    Validator validator = validatorFactory.getValidator();

    Set<ConstraintViolation<Pos>> violations = validator.validate(req);

    for (ConstraintViolation<Pos> violation : violations) {
      msg = violation.getMessage();

      BasicResponse res = BasicResponse.builder().result(false).message(msg).build();

      return res;
    }


    boolean result = false;

    result = memService.insertPos(req);
    BasicResponse res = BasicResponse.builder().result(result).build();

    return res;
  }

}
