package egovframework.cmm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.Column;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @Class Name : LoginVO.java
 * @Description : Login VO class
 * @Modification Information
 * @
 * @  수정일         수정자                   수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.03.03    박지욱          최초 생성
 *
 *  @author 공통서비스 개발팀 박지욱
 *  @since 2009.03.03
 *  @version 1.0
 *  @see
 *  
 */
@Getter @Setter
@Builder
@AllArgsConstructor
@ApiModel(value="LoginVO", description = "로그인정보")
public class LoginVO {
	
	public LoginVO() {
		Random ran = new Random();
		List<String> ids = new ArrayList<String>();
		ids.add("d");
		ids.add("g");
		ids.add("h");
		
		id = ids.get(ran.nextInt(3));
	}
	
	@ApiModelProperty(value="사원번호", example = "0001", hidden = true)
	@Column
	private String empNo;
	
	@ApiModelProperty(value="아이디 ", example = "system", required = true)
	@Column
	private String id;
	
	@ApiModelProperty(value="이름", example = "문동은")
	@Column
	private String empName;
	
	@ApiModelProperty(value="이메일주소", example = "welovex2@standardbank.co.kr", hidden = true)
	@Column
	private String email;
	
	@ApiModelProperty(value="비밀번호", example = "akstp!", required = true, hidden = true)
	@Column
	private String password;
	
	@ApiModelProperty(value="비밀번호 힌트", example = "1", hidden = true)
	@Column
	private String passwordHint;
	
	@ApiModelProperty(value="비밀번호 정답 ", example = "1", hidden = true)
	@Column
	private String passwordCnsr;

	@ApiModelProperty(value="부서코드 ", example = "", hidden = true)
	private String deptCode;
	
	@ApiModelProperty(value="부서명 ", example = "", hidden = true)
	private String deptName;
	
	@ApiModelProperty(value="입사일 ", example = "", hidden = true)
	private String hireDt;

	@ApiModelProperty(value="직위코드", example = "1", hidden = true)
	@Column
	private String positionCode;
	
	@ApiModelProperty(value="직위", example = "1", hidden = true)
	@Column
	private String position;
	
	@ApiModelProperty(value="연락처", example = "1", hidden = true)
	@Column
	private String phoneNum;
	
	@ApiModelProperty(value="유선번호", example = "1", hidden = true)
	@Column
	private String lineNum;
	
	@ApiModelProperty(value="권한코드", example = "1", hidden = true)
	@Column
	private String authCode;
	
	@ApiModelProperty(value="권한", example = "1", hidden = true)
	@Column
	private String auth;

	@ApiModelProperty(value="재직상태코드", example = "1", hidden = true)
	@Column
	private String statusCode;
	
	@ApiModelProperty(value="재직상태", example = "1", hidden = true)
	@Column
	private String status;

	@ApiModelProperty(value="휴직 시작기간", example = "1", hidden = true)
	@Column
	private String leaveStartDt;

	@ApiModelProperty(value="휴직 종료기간", example = "1", hidden = true)
	@Column
	private String leaveEndtDt;
	
	@ApiModelProperty(value="로그인 후 이동할 페이지", example = "1", hidden = true)
	@Column
	private String url;
	
	@ApiModelProperty(value="사용자 IP정보", example = "1")
	@Column
	private String lastIp;

    @ApiModelProperty(value="마지막 로그인 날짜", example = "1")
    @Column
    private String lastLoginDt;
}
