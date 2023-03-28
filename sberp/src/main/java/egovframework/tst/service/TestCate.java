package egovframework.tst.service;

import java.time.LocalDateTime;

import javax.persistence.Column;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class TestCate {

	@ApiModelProperty(value="TEST_CATE_SEQ ", example = "", required = true)
	@Column
	@NotNull
	private int testCateSeq;


	@ApiModelProperty(value="DEPTH ", example = "", required = true)
	@Column
	private int depth;


	@ApiModelProperty(value="NAME ", example = "", required = true)
	@Column
	private String name;


	@ApiModelProperty(value="상위카테고리 SEQ ", example = "", required = true)
	@Column
	private int topDepthSeq;


	@ApiModelProperty(value="사용여부 ", example = "", hidden = true)
	@Column
	private int useYn;


	@ApiModelProperty(value="INS_MEM_ID ", example = "", hidden = true)
	@Column
	private String insMemId;


	@ApiModelProperty(value="INS_DT ", example = "", hidden = true)
	@Column
	private LocalDateTime insDt;


	@ApiModelProperty(value="UDT_MEM_ID ", example = "", hidden = true)
	@Column
	private String udtMemId;


	@ApiModelProperty(value="UDT_DT ", example = "", hidden = true)
	@Column
	private LocalDateTime udtDt;


	@ApiModelProperty(value="상태(I,U,D) ", example = "", required = true)
	@Column
	private String state;


	public int getTestCateSeq() {
		return testCateSeq;
	}


	public void setTestCateSeq(int testCateSeq) {
		this.testCateSeq = testCateSeq;
	}


	public int getDepth() {
		return depth;
	}


	public void setDepth(int depth) {
		this.depth = depth;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getTopDepthSeq() {
		return topDepthSeq;
	}


	public void setTopDepthSeq(int topDepthSeq) {
		this.topDepthSeq = topDepthSeq;
	}


	public int getUseYn() {
		return useYn;
	}


	public void setUseYn(int useYn) {
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
