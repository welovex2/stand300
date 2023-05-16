package egovframework.sys.service;

import java.util.List;

import egovframework.cmm.service.ComParam;

public interface StbService {

	List<TestStndrDTO> selectList(ComParam param);

	int selectListCnt(ComParam param);

}
