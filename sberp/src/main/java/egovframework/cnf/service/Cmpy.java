package egovframework.cnf.service;

import java.time.LocalDateTime;

import javax.persistence.Column;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "회사")
public class Cmpy {

	@ApiModelProperty(value="CMPY_SEQ ", example = "", required = true)
	@Column
	@NotNull
	private int cmpySeq;


	@ApiModelProperty(value="회사타입 0000 협력사(컨설팅) 1000 직접고객 ", example = "", required = true)
	@Column
	private String cmpyCode;


	@ApiModelProperty(value="업체명 ", example = "", required = true)
	@Column
	@NotNull
	private String cmpyName;


	@ApiModelProperty(value="영문회사명 ", example = "", required = true)
	@Column
	private String engName;


	@ApiModelProperty(value="회사종류(공통코드:PK) 01 컨설팅 02 시험소 03 협력사 ", example = "", required = true)
	@Column
	private String typeCode;


	@ApiModelProperty(value="신청서종류 ", example = "", required = true)
	@Column
	private String applType;


	@ApiModelProperty(value="전화번호 ", example = "", required = true)
	@Column
	private String cmpyPhone;


	@ApiModelProperty(value="팩스번호 ", example = "", required = true)
	@Column
	private String cmpyFax;


	@ApiModelProperty(value="회사식별부호 ", example = "", required = true)
	@Column
	private String cmpnyIdntf;


	@ApiModelProperty(value="기타아이디 ", example = "", required = true)
	@Column
	private String etcId;


	@ApiModelProperty(value="사업자등록번호 ", example = "", required = true)
	@Column
	private String bsnsRgnmb;


	@ApiModelProperty(value="법인번호 ", example = "", required = true)
	@Column
	private String crprtRgnmb;


	@ApiModelProperty(value="국가_CODE(공통코드:PC) 01 한국 02 중국 03 대만 04 미국 05 일본 06 브라질 07 이탈리아 ", example = "", required = true)
	@Column
	private String cntryCode;


	@ApiModelProperty(value="홈페이지주소 ", example = "", required = true)
	@Column
	private String homepage;


	@ApiModelProperty(value="대표이사명 ", example = "", required = true)
	@Column
	private String rprsn;


	@ApiModelProperty(value="영문대표이사명 ", example = "", required = true)
	@Column
	private String engRprsn;


	@ApiModelProperty(value="대표이사주민번호 ", example = "", required = true)
	@Column
	private String rsdntRgnmb;


	@ApiModelProperty(value="주소 ", example = "", required = true)
	@Column
	private String addr;


	@ApiModelProperty(value="영문주소 ", example = "", required = true)
	@Column
	private String engAddr;


	@ApiModelProperty(value="기타 ", example = "", required = true)
	@Column
	private String memo;


	@ApiModelProperty(value="등록자 아이디 ", example = "", required = true)
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


	public int getCmpySeq() {
		return cmpySeq;
	}


	public void setCmpySeq(int cmpySeq) {
		this.cmpySeq = cmpySeq;
	}


	public String getCmpyCode() {
		return cmpyCode;
	}


	public void setCmpyCode(String cmpyCode) {
		this.cmpyCode = cmpyCode;
	}


	public String getCmpyName() {
		return cmpyName;
	}


	public void setCmpyName(String cmpyName) {
		this.cmpyName = cmpyName;
	}


	public String getEngName() {
		return engName;
	}


	public void setEngName(String engName) {
		this.engName = engName;
	}


	public String getTypeCode() {
		return typeCode;
	}


	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}


	public String getApplType() {
		return applType;
	}


	public void setApplType(String applType) {
		this.applType = applType;
	}


	public String getCmpyPhone() {
		return cmpyPhone;
	}


	public void setCmpyPhone(String cmpyPhone) {
		this.cmpyPhone = cmpyPhone;
	}


	public String getCmpyFax() {
		return cmpyFax;
	}


	public void setCmpyFax(String cmpyFax) {
		this.cmpyFax = cmpyFax;
	}


	public String getCmpnyIdntf() {
		return cmpnyIdntf;
	}


	public void setCmpnyIdntf(String cmpnyIdntf) {
		this.cmpnyIdntf = cmpnyIdntf;
	}


	public String getEtcId() {
		return etcId;
	}


	public void setEtcId(String etcId) {
		this.etcId = etcId;
	}


	public String getBsnsRgnmb() {
		return bsnsRgnmb;
	}


	public void setBsnsRgnmb(String bsnsRgnmb) {
		this.bsnsRgnmb = bsnsRgnmb;
	}


	public String getCrprtRgnmb() {
		return crprtRgnmb;
	}


	public void setCrprtRgnmb(String crprtRgnmb) {
		this.crprtRgnmb = crprtRgnmb;
	}


	public String getCntryCode() {
		return cntryCode;
	}


	public void setCntryCode(String cntryCode) {
		this.cntryCode = cntryCode;
	}


	public String getHomepage() {
		return homepage;
	}


	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}


	public String getRprsn() {
		return rprsn;
	}


	public void setRprsn(String rprsn) {
		this.rprsn = rprsn;
	}


	public String getEngRprsn() {
		return engRprsn;
	}


	public void setEngRprsn(String engRprsn) {
		this.engRprsn = engRprsn;
	}


	public String getRsdntRgnmb() {
		return rsdntRgnmb;
	}


	public void setRsdntRgnmb(String rsdntRgnmb) {
		this.rsdntRgnmb = rsdntRgnmb;
	}


	public String getAddr() {
		return addr;
	}


	public void setAddr(String addr) {
		this.addr = addr;
	}


	public String getEngAddr() {
		return engAddr;
	}


	public void setEngAddr(String engAddr) {
		this.engAddr = engAddr;
	}


	public String getMemo() {
		return memo;
	}


	public void setMemo(String memo) {
		this.memo = memo;
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
