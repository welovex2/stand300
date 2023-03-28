package egovframework.tst.service;

import javax.persistence.Column;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@ApiModel(description = "시험항목 및 비용")
public class TestItemDTO extends TestItem {
	
	@ApiModelProperty(value="인증종류1", example = "", required = true)
	@Column
	private String crtfc1;

	@ApiModelProperty(value="인증종류2", example = "", required = true)
	@Column
	private String crtfc2;
	
	@ApiModelProperty(value="시험종류코드", example = "", required = true)
	@Column
	private String testTypeCode;
	
	@ApiModelProperty(value="시험항목", example = "", required = true)
	@Column
	private String testCate;
	
	@ApiModelProperty(value="시험부서", example = "", required = true)
	@Column
	private String testType;
	
	@ApiModelProperty(value="전송타입", example = "", required = true)
	@Column
	private String sendType;
	
}
