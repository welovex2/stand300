package egovframework.raw.dto;

import javax.persistence.Column;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class InfoDTO {
	
	@ApiModelProperty(value="신청인", example = "")
	@Column
	private String cmpyName;
	
	@ApiModelProperty(value="기자재명칭", example = "")
	@Column
	private String modelName;
	
	@ApiModelProperty(value="모델명", example = "")
	@Column
	private String mnfctCmpny;
	
	@ApiModelProperty(value="제조자", example = "")
	@Column
	private String mnfctCntry;
	
	@ApiModelProperty(value="구성품", example = "")
	@Column
	private String mdlIdntf;
	
	@ApiModelProperty(value="입수일", example = "")
	@Column
	private String receiptDt;
	
	@ApiModelProperty(value="시험시작일", example = "")
	@Column
	private String testStartDt;
	
}
