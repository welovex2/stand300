package egovframework.raw.dto;

import java.util.List;

import javax.persistence.Column;

import egovframework.raw.service.MethodSurge;
import egovframework.raw.service.RawMac;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString(callSuper = true)
public class SurgeDTO extends MethodSurge {
	
	@ApiModelProperty(value="시험고유번호", example = "15", hidden = true)
	@Column
	int testSeq;
	
	@ApiModelProperty(value="측정설비 종류(공통코드 : TM)", example = "SG")
	@Column
	String macType;
	
	@ApiModelProperty(value="측정설비 리스트", example = "")
	@Column
	List<RawMac> macList;
}
