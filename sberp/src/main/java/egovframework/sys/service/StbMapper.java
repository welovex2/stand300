package egovframework.sys.service;

import java.util.List;

import egovframework.cmm.service.ComParam;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("StbMapper")
public interface StbMapper {
	
	List<TestStndrDTO> selectList(ComParam param);

	int selectListCnt(ComParam param);
}
