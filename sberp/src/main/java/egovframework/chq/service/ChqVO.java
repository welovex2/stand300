package egovframework.chq.service;

import java.time.LocalDateTime;

import javax.persistence.Column;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class ChqVO {
	@ApiModelProperty(value="CHQ_SEQ ", example = "", required = true)
	@Column
	@NotNull
	private int chqSeq;


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


	public int getChqSeq() {
		return chqSeq;
	}


	public void setChqSeq(int chqSeq) {
		this.chqSeq = chqSeq;
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
