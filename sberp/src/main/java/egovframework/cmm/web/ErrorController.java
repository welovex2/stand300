package egovframework.cmm.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.cmm.service.BasicResponse;

@RestController
@RequestMapping("/err")
public class ErrorController {

	@PostMapping(value="/error.do")
	public BasicResponse error(HttpServletRequest request, Exception ex) throws Exception {
		boolean result = false;
		String msg;
		
		String error_code = request.getAttribute("javax.servlet.error.status_code").toString();
		
		try {
            int status_code = Integer.parseInt(error_code);
            switch (status_code) {
            case 400: msg = "잘못된 요청입니다."; break;
            case 403: msg = "접근이 금지되었습니다."; break;
            case 404: msg = "페이지를 찾을 수 없습니다."; break;
            case 405: msg = "요청된 메소드가 허용되지 않습니다."; break;
            case 500: msg = "서버에 오류가 발생하였습니다."; break;
            case 503: msg = "서비스를 사용할 수 없습니다."; break;
            default: msg = "알 수 없는 오류가 발생하였습니다."; break;
            }
        } catch(Exception e) {
            msg = "기타 오류가 발생하였습니다.";
        } 
		
		BasicResponse res = BasicResponse.builder().result(result)
				.message(msg)
				.build();

		return res;
	}
}
