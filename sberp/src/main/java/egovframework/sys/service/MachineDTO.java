package egovframework.sys.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString(callSuper = true)
public class MachineDTO extends Machine {

	private String reformDtStr;
}
