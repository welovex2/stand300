package egovframework.sys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import egovframework.cmm.service.ComParam;
import egovframework.sys.service.StbMapper;
import egovframework.sys.service.StbService;
import egovframework.sys.service.TestStndrDTO;

@Service("StbService")
public class StbServiceImpl implements StbService {

	@Autowired
	StbMapper stbMapper;
	
	@Override
	public List<TestStndrDTO> selectList(ComParam param) {
		return stbMapper.selectList(param);
	}

	@Override
	public int selectListCnt(ComParam param) {
		return stbMapper.selectListCnt(param);
	}

}
