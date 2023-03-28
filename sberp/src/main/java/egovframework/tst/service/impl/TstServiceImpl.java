package egovframework.tst.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.cmm.service.ComParam;
import egovframework.cnf.service.TestStndr;
import egovframework.sbk.service.SbkDTO;
import egovframework.tst.service.Test;
import egovframework.tst.service.TestCate;
import egovframework.tst.service.TestDTO.Req;
import egovframework.tst.service.TestDTO.Res;
import egovframework.tst.service.TstMapper;
import egovframework.tst.service.TstParam;
import egovframework.tst.service.TstService;

@Service("TstService")
public class TstServiceImpl implements TstService {

	@Autowired
	TstMapper tstMapper;
	
	@Override
	public List<TestCate> selectCrtfList(int topCode) {
		return tstMapper.selectCrtfList(topCode);
	}

	@Override
	public List<TestStndr> selectStndrList(TstParam param) {
		return tstMapper.selectStndrList(param);
	}

	@Override
	public Test selectDetail(Req req) {
		return tstMapper.selectDetail(req);
	}

	@Override
	public boolean insert(Req req) {
		return tstMapper.insert(req);
	}

	@Override
	public int selectListCnt(ComParam param) {
		return tstMapper.selectListCnt(param);
	}

	@Override
	public List<Res> selectList(ComParam param) {
		return tstMapper.selectList(param);
	}

	@Override
	public boolean testMemInsert(Req req) {
		return tstMapper.testMemInsert(req);
	}

	@Override
	public List<Res> testMemList(String testSeq) {
		return tstMapper.testMemList(testSeq);
	}

	@Override
	public boolean testStateInsert(Req req) {
		return tstMapper.testStateInsert(req);
	}

	@Override
	public List<Res> testStateList(String testSeq) {
		return tstMapper.testStateList(testSeq);
	}

	@Override
	public boolean testBoardInsert(Req req) {
		return tstMapper.testBoardInsert(req);
	}

	@Override
	public List<Res> testBoardList(String testSeq) {
		return tstMapper.testBoardList(testSeq);
	}

	@Override
	public SbkDTO.Res testBoardAppDetail(String sbkId) {
		return tstMapper.testBoardAppDetail(sbkId);
	}

}
