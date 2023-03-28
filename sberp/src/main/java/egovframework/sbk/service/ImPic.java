package egovframework.sbk.service;

import java.time.LocalDateTime;

import javax.persistence.Column;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class ImPic {

	@ApiModelProperty(value="IM_PIC_SEQ ", example = "", required = true)
	@Column
	@NotNull
	private int imPicSeq;


	@ApiModelProperty(value="IM_SEQ ", example = "", required = true)
	@Column
	@NotNull
	private int imSeq;


	@ApiModelProperty(value="사진위치 ", example = "", required = true)
	@Column
	private String url;


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


	public int getImPicSeq() {
		return imPicSeq;
	}


	public void setImPicSeq(int imPicSeq) {
		this.imPicSeq = imPicSeq;
	}


	public int getImSeq() {
		return imSeq;
	}


	public void setImSeq(int imSeq) {
		this.imSeq = imSeq;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
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
