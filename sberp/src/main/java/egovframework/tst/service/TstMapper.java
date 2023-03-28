package egovframework.tst.service;

import java.util.List;

import egovframework.cmm.service.ComParam;
import egovframework.cnf.service.TestStndr;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.tst.service.TestDTO.Req;
import egovframework.tst.service.TestDTO.Res;

@Mapper("TstMapper")
public interface TstMapper {

	public List<TestCate> selectCrtfList(int topCode);

	public List<TestStndr> selectStndrList(TstParam param);

	public Test selectDetail(Req req);

	public boolean insert(Req req);

	public List<Res> selectList(ComParam param);

	public int selectListCnt(ComParam param);

	public boolean testMemInsert(Req req);

	public List<Res> testMemList(String testSeq);

	boolean testStateInsert(Req req);

	List<Res> testStateList(String testSeq);
	
	boolean testBoardInsert(Req req);

	List<Res> testBoardList(String testSeq);
}
