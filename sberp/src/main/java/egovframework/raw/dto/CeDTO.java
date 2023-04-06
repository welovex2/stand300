package egovframework.raw.dto;

import java.util.List;

import javax.persistence.Column;

import egovframework.raw.service.MethodCe;
import egovframework.raw.service.RawMac;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString(callSuper = true)
public class CeDTO extends MethodCe {

	@ApiModelProperty(value="측정설비 종류(공통코드 : TM)", example = "CE")
	@Column
	String macType;
	
	@ApiModelProperty(value="측정설비 리스트", example = "")
	@Column
	List<RawMac> macList;
	
	
}
