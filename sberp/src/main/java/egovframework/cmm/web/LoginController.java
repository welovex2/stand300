package egovframework.cmm.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import egovframework.cmm.service.EgovLoginService;
import egovframework.cmm.service.LoginVO;
import egovframework.cmm.util.EgovFileScrty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"로그인"})
@RestController
@RequestMapping("/user/")
public class LoginController {
	
	/** EgovLoginService */
	@Resource(name = "loginService")
	private EgovLoginService loginService;
	

	public static void main(String[] args) {

		try {
			String enpassword = EgovFileScrty.encryptPassword("jyunj91!", "jyunj91");
			System.out.println(enpassword);
			enpassword = EgovFileScrty.encryptPassword("silva86!", "silva86");
			System.out.println(enpassword);
			enpassword = EgovFileScrty.encryptPassword("jaecie17!", "jaecie17");
			System.out.println(enpassword);
			enpassword = EgovFileScrty.encryptPassword("j871010!", "j871010");
			System.out.println(enpassword);
			enpassword = EgovFileScrty.encryptPassword("admins!", "system");
			System.out.println(enpassword);
			enpassword = EgovFileScrty.encryptPassword("stb9394!@", "admin");
			System.out.println(enpassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	/**
	 * 일반 로그인을 처리한다
	 * @param vo - 아이디, 비밀번호가 담긴 LoginVO
	 * @param request - 세션처리를 위한 HttpServletRequest
	 * @return result - 로그인결과(세션정보)
	 * @exception Exception
	 */
	@PostMapping(value = "/login.do")
	public boolean actionLogin(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request) throws Exception {

		// 1. 일반 로그인 처리
		LoginVO resultVO = loginService.actionLogin(loginVO);

		boolean loginPolicyYn = true;

		if (resultVO != null && resultVO.getId() != null && !resultVO.getId().equals("") && loginPolicyYn) {

			request.getSession().setAttribute("LoginVO", resultVO);
			return true;
		} else {

//			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return false;
		}

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
	public LoginVO userInfo(HttpServletRequest request) throws Exception {

		LoginVO resultVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		
		resultVO = LoginVO.builder().id("welovex2")
			.empName("박연진")
			.position("과장")
			.lineNum("031-1588-1588")
			.phoneNum("010-1588-8851")
			.email("welovex2@standardbank.co.kr")
			.build();
		
		return resultVO;
	}
}
