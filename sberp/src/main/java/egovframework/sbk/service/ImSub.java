package egovframework.sbk.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class ImSub {
	@ApiModelProperty(value="IM_SEQ + 01 ", example = "", required = true)
	@Column
	@NotNull
	private int imSubSeq;


	@ApiModelProperty(value="IM_SEQ ", example = "", required = true)
	@Column
	@NotNull
	private int imSeq;


	@ApiModelProperty(value="본품/구성품(공통코드:TI) 01 본품 02 구성품 ", example = "", required = true)
	@Column
	private String divCode;


	@ApiModelProperty(value="수량 ", example = "", required = true)
	@Column
	private int qty;


	@ApiModelProperty(value="모델명/설명 ", example = "", required = true)
	@Column
	private String memo;


	@ApiModelProperty(value="반입날짜 ", example = "", required = true)
	@Column
	private LocalDate carryInDate;


	@ApiModelProperty(value="반입자 ", example = "", required = true)
	@Column
	private String carryInId;


	@ApiModelProperty(value="반입형태(공통코드:TC) 01 택배 02 직접전달 ", example = "", required = true)
	@Column
	private String carryInCode;


	@ApiModelProperty(value="배송인 ", example = "", required = true)
	@Column
	private String carryInDlvryName;


	@ApiModelProperty(value="배송인연락처 ", example = "", required = true)
	@Column
	private String carryInDlvryPhone;


	@ApiModelProperty(value="송장 ", example = "", required = true)
	@Column
	private String carryInDlvryInvc;


	@ApiModelProperty(value="반입날짜 ", example = "", required = true)
	@Column
	private LocalDate carryOutDate;


	@ApiModelProperty(value="반입자 ", example = "", required = true)
	@Column
	private String carryOutId;


	@ApiModelProperty(value="반입형태(공통코드:TC) 01 택배 02 직접전달 ", example = "", required = true)
	@Column
	private String carryOutCode;


	@ApiModelProperty(value="배송인 ", example = "", required = true)
	@Column
	private String carryOutDlvryName;


	@ApiModelProperty(value="배송인연락처 ", example = "", required = true)
	@Column
	private String carryOutDlvryPhone;


	@ApiModelProperty(value="송장 ", example = "", required = true)
	@Column
	private String carryOutDlvryInvc;


	@ApiModelProperty(value="등록자 아이디 ", example = "", required = true)
	@Column
	private String insMemId;


	@ApiModelProperty(value="등록 날짜시간 ", example = "", required = true)
	@Column
	private LocalDateTime insDt;


	@ApiModelProperty(value="수정자 아이디 ", example = "", required = true)
	@Column
	private String udtMemId;


	@ApiModelProperty(value="수정 날짜시간 ", example = "", required = true)
	@Column
	private LocalDateTime udtDt;


	@ApiModelProperty(value="상태(I,U,D) ", example = "", required = true)
	@Column
	private String state;


	public int getImSubSeq() {
		return imSubSeq;
	}


	public void setImSubSeq(int imSubSeq) {
		this.imSubSeq = imSubSeq;
	}


	public int getImSeq() {
		return imSeq;
	}


	public void setImSeq(int imSeq) {
		this.imSeq = imSeq;
	}


	public String getDivCode() {
		return divCode;
	}


	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}


	public int getQty() {
		return qty;
	}


	public void setQty(int qty) {
		this.qty = qty;
	}


	public String getMemo() {
		return memo;
	}


	public void setMemo(String memo) {
		this.memo = memo;
	}


	public LocalDate getCarryInDate() {
		return carryInDate;
	}


	public void setCarryInDate(LocalDate carryInDate) {
		this.carryInDate = carryInDate;
	}


	public String getCarryInId() {
		return carryInId;
	}


	public void setCarryInId(String carryInId) {
		this.carryInId = carryInId;
	}


	public String getCarryInCode() {
		return carryInCode;
	}


	public void setCarryInCode(String carryInCode) {
		this.carryInCode = carryInCode;
	}


	public String getCarryInDlvryName() {
		return carryInDlvryName;
	}


	public void setCarryInDlvryName(String carryInDlvryName) {
		this.carryInDlvryName = carryInDlvryName;
	}


	public String getCarryInDlvryPhone() {
		return carryInDlvryPhone;
	}


	public void setCarryInDlvryPhone(String carryInDlvryPhone) {
		this.carryInDlvryPhone = carryInDlvryPhone;
	}


	public String getCarryInDlvryInvc() {
		return carryInDlvryInvc;
	}


	public void setCarryInDlvryInvc(String carryInDlvryInvc) {
		this.carryInDlvryInvc = carryInDlvryInvc;
	}


	public LocalDate getCarryOutDate() {
		return carryOutDate;
	}


	public void setCarryOutDate(LocalDate carryOutDate) {
		this.carryOutDate = carryOutDate;
	}


	public String getCarryOutId() {
		return carryOutId;
	}


	public void setCarryOutId(String carryOutId) {
		this.carryOutId = carryOutId;
	}


	public String getCarryOutCode() {
		return carryOutCode;
	}


	public void setCarryOutCode(String carryOutCode) {
		this.carryOutCode = carryOutCode;
	}


	public String getCarryOutDlvryName() {
		return carryOutDlvryName;
	}


	public void setCarryOutDlvryName(String carryOutDlvryName) {
		this.carryOutDlvryName = carryOutDlvryName;
	}


	public String getCarryOutDlvryPhone() {
		return carryOutDlvryPhone;
	}


	public void setCarryOutDlvryPhone(String carryOutDlvryPhone) {
		this.carryOutDlvryPhone = carryOutDlvryPhone;
	}


	public String getCarryOutDlvryInvc() {
		return carryOutDlvryInvc;
	}


	public void setCarryOutDlvryInvc(String carryOutDlvryInvc) {
		this.carryOutDlvryInvc = carryOutDlvryInvc;
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
