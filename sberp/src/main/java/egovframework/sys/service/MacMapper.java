package egovframework.sys.service;

import java.util.List;

import egovframework.cmm.service.ComParam;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("MacMapper")
public interface MacMapper {
	
	List<MachineDTO> selectList(ComParam param);

	int selectListCnt(ComParam param);
}
