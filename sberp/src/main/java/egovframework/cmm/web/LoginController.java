package egovframework.cmm.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import egovframework.cmm.service.BasicResponse;
import egovframework.cmm.service.EgovLoginService;
import egovframework.cmm.service.LoginVO;
import egovframework.cmm.service.ResponseMessage;
import egovframework.cmm.util.EgovFileScrty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"로그인"})
@RestController
@RequestMapping("/user/login")
public class LoginController {
	
	/** EgovLoginService */
	@Resource(name = "loginService")
	private EgovLoginService loginService;
	

	public static void main(String[] args) {

		try {
			String enpassword = EgovFileScrty.encryptPassword("akstp!", "system");
			System.out.println(enpassword);
			enpassword = EgovFileScrty.encryptPassword("vv", "v");
            System.out.println("a>> "+enpassword);
            enpassword = EgovFileScrty.encryptPassword("ww", "w");
            System.out.println("a>> "+enpassword);
            enpassword = EgovFileScrty.encryptPassword("xx", "x");
            System.out.println("a>> "+enpassword);
            enpassword = EgovFileScrty.encryptPassword("yy", "y");
            System.out.println("a>> "+enpassword);
            enpassword = EgovFileScrty.encryptPassword("zz", "z");
            System.out.println("a>> "+enpassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	@ApiOperation(value = "로그인")
	@PostMapping(value = "/login.do")
	public BasicResponse actionLogin(@RequestBody LoginVO loginVO, HttpServletRequest request) throws Exception {
		
		boolean result = true;
		String msg = "";
		
		// 1. 일반 로그인 처리
		loginVO.setLastIp(getClientIP(request));
		LoginVO resultVO = loginService.actionLogin(loginVO);

		boolean loginPolicyYn = true;

		if (resultVO != null && resultVO.getId() != null && !resultVO.getId().equals("") && loginPolicyYn) {

			System.out.println("=================================");
			System.out.println("세션아이디");
			System.out.println("=================================");
			System.out.println(request.getSession().getId());
			System.out.println("=================================");
			request.getSession().setAttribute("LoginVO", resultVO);
			msg = ResponseMessage.LOGIN_SUCCESS;
			result = true;
		} else {
			
			msg = ResponseMessage.LOGIN_FAIL;
			result = false;
		}

    	BasicResponse res = BasicResponse.builder().result(result)
    			.message(msg)
				.build();
    	
        return res;  
	}
	
	/**
	 * 로그아웃한다.
	 * @return String
	 * @exception Exception
	 */
	@GetMapping(value = "/logout.do")
	public boolean actionLogout(HttpServletRequest request, ModelMap model) throws Exception {

		RequestContextHolder.getRequestAttributes().removeAttribute("LoginVO", RequestAttributes.SCOPE_SESSION);

		return true;
	}
	
	@ApiOperation(value = "로그인사용자정보")
	@GetMapping(value = "/info.do")
	public BasicResponse userInfo(HttpServletRequest request) throws Exception {
		
		boolean result = true;
    	String msg = "";
    	
		System.out.println("=================================");
		System.out.println("세션아이디");
		System.out.println("=================================");
		System.out.println(request.getSession().getId());
		System.out.println("=================================");
		
		LoginVO resultVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		
		if (resultVO == null) {
			result = false;
			msg = ResponseMessage.NO_LOGIN;
		}
		
    	BasicResponse res = BasicResponse.builder().result(result)
    			.message(msg)
    			.data(resultVO)
				.build();
    	
        return res;  
	}
	
	public static String getClientIP(HttpServletRequest request) {
	    String ip = request.getHeader("X-Forwarded-For");
	    System.out.println("> X-FORWARDED-FOR : " + ip);

	    if (ip == null) {
	        ip = request.getHeader("Proxy-Client-IP");
	        System.out.println("> Proxy-Client-IP : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	        System.out.println(">  WL-Proxy-Client-IP : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	        System.out.println("> HTTP_CLIENT_IP : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	        System.out.println("> HTTP_X_FORWARDED_FOR : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getRemoteAddr();
	        System.out.println("> getRemoteAddr : "+ip);
	    }
	    System.out.println("> Result : IP Address : "+ip);

	    return ip;
	}
}
