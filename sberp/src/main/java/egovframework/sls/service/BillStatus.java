package egovframework.sls.service;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class BillStatus {

	@ApiModelProperty(value="BILL_STATUS_SEQ ", example = "", hidden = true)
	@Column
	@NotNull
	private int billStatusSeq;


	@ApiModelProperty(value="SLS_YM ", example = "", hidden = true)
	@Column
	@NotNull
	private String slsYm;


	@ApiModelProperty(value="SLS_SEQ ", example = "", hidden = true)
	@Column
	@NotNull
	private int slsSeq;


	@ApiModelProperty(value="STATE_CODE ", example = "", required = true)
	@Column
	private String stateCode;


	@ApiModelProperty(value="BILL ", example = "", required = true)
	@Column
	@Pattern(regexp = "^[0-9]*$", message = "금액은 숫자만 입력 가능합니다.") 
	private int bill;


	@ApiModelProperty(value="MEMO ", example = "", required = true)
	@Column
	private String memo;


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


	public int getBillStatusSeq() {
		return billStatusSeq;
	}


	public void setBillStatusSeq(int billStatusSeq) {
		this.billStatusSeq = billStatusSeq;
	}


	public String getSlsYm() {
		return slsYm;
	}


	public void setSlsYm(String slsYm) {
		this.slsYm = slsYm;
	}


	public int getSlsSeq() {
		return slsSeq;
	}


	public void setSlsSeq(int slsSeq) {
		this.slsSeq = slsSeq;
	}


	public String getStateCode() {
		return stateCode;
	}


	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}


	public int getBill() {
		return bill;
	}


	public void setBill(int bill) {
		this.bill = bill;
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
