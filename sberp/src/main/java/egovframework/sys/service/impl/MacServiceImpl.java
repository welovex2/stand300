package egovframework.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.cmm.service.ComParam;
import egovframework.sys.service.MacMapper;
import egovframework.sys.service.MacService;
import egovframework.sys.service.MachineDTO;

@Service("MacService")
public class MacServiceImpl implements MacService {

	@Autowired
	MacMapper macMapper;
	
	@Override
	public List<MachineDTO> selectList(ComParam param) {
		return macMapper.selectList(param);
	}

	@Override
	public int selectListCnt(ComParam param) {
		return macMapper.selectListCnt(param);
	}

}
