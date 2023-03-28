package egovframework.cnf.service;

import java.time.LocalDateTime;

import javax.persistence.Column;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "시험규격")
public class TestStndr {
	@ApiModelProperty(value="TEST_STNDR_SEQ ", example = "", required = true)
	@Column
	@NotNull
	private int testStndrSeq;


	@ApiModelProperty(value="공통코드 : CN ", example = "", required = true)
	@Column
	private String countryCode;


	@ApiModelProperty(value="인증종류 ", example = "", required = true)
	@Column
	private int crtfTypeSeq;


	@ApiModelProperty(value="시험종류(시험부) ", example = "", required = true)
	@Column
	private String testTypeCode;


	@ApiModelProperty(value="시험규격 ", example = "", required = true)
	@Column
	private String testStndr;


	@ApiModelProperty(value="MEMO ", example = "", required = true)
	@Column
	private String memo;


	@ApiModelProperty(value="사용여부 ", example = "", hidden = true)
	@Column
	private String useYn;


	@ApiModelProperty(value="등록자 아이디 ", example = "", hidden = true)
	@Column
	private String insMemId;


	@ApiModelProperty(value="등록 날짜시간 ", example = "", hidden = true)
	@Column
	private LocalDateTime insDt;


	@ApiModelProperty(value="수정자 아이디 ", example = "", hidden = true)
	@Column
	private String udtMemId;


	@ApiModelProperty(value="수정 날짜시간 ", example = "", hidden = true)
	@Column
	private LocalDateTime udtDt;


	@ApiModelProperty(value="상태(I,U,D) ", example = "", hidden = true)
	@Column
	private String state;


	public int getTestStndrSeq() {
		return testStndrSeq;
	}


	public void setTestStndrSeq(int testStndrSeq) {
		this.testStndrSeq = testStndrSeq;
	}


	public String getCountryCode() {
		return countryCode;
	}


	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}


	public int getCrtfTypeSeq() {
		return crtfTypeSeq;
	}


	public void setCrtfTypeSeq(int crtfTypeSeq) {
		this.crtfTypeSeq = crtfTypeSeq;
	}


	public String getTestTypeCode() {
		return testTypeCode;
	}


	public void setTestTypeCode(String testTypeCode) {
		this.testTypeCode = testTypeCode;
	}


	public String getTestStndr() {
		return testStndr;
	}


	public void setTestStndr(String testStndr) {
		this.testStndr = testStndr;
	}


	public String getMemo() {
		return memo;
	}


	public void setMemo(String memo) {
		this.memo = memo;
	}


	public String getUseYn() {
		return useYn;
	}


	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}


	public String getInsMemId() {
		return insMemId;
	}


	public void setInsMemId(String insMemId) {
		this.insMemId = insMemId;
	}


	public LocalDateTime getInsDt() {
		return insDt;
	}


	public void setInsDt(LocalDateTime insDt) {
		this.insDt = insDt;
	}


	public String getUdtMemId() {
		return udtMemId;
	}


	public void setUdtMemId(String udtMemId) {
		this.udtMemId = udtMemId;
	}


	public LocalDateTime getUdtDt() {
		return udtDt;
	}


	public void setUdtDt(LocalDateTime udtDt) {
		this.udtDt = udtDt;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}

}
