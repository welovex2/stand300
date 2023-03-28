package egovframework.tst.service;

import javax.persistence.Column;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class TestDTO {

	@Getter @Setter @ToString
	@ApiModel(value="TestDTO.Req", description = "시험 등록")
	public static class Req extends Test {
		@ApiModelProperty(value="메모 ", example = "바빠서..")
		@Column
		private String memo;
		
		@ApiModelProperty(value="시험상태 ", example = "1")
		@Column
		private String stateCode;
	}
	
	@Getter @Setter
	@ApiModel(value="TestDTO.Res", description = "시험 조회")
	public static class Res extends Test {
		
	}
}
