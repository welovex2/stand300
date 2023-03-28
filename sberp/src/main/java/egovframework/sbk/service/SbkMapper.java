package egovframework.sbk.service;

import java.util.List;

import egovframework.cmm.service.ComParam;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.sbk.service.SbkDTO.Req;
import egovframework.tst.service.TestItem;
import egovframework.tst.service.TestItemDTO;
import egovframework.tst.service.TestItemRej;

@Mapper("SbkMapper")
public interface SbkMapper {

	SbkDTO.Res selectDetail(Req sbk);

	boolean insert(Req sbk);

	String selectRef(Req sbk) throws Exception;
	
	boolean updateJob(Req sbk);
	
	boolean updateJobSbk(Req sbk);
	
	boolean insertJob(Req sbk);
	
	List<TestItem> selectTestItemList(Req sbk);

	boolean update(Req sbk);

	int selectListCnt(ComParam param);

	List<SbkDTO.Res> selectList(ComParam param);

	boolean updateTestItemSign(TestItemDTO req);

	List<TestItemRej> signRejectList(String testItemSeq);

	boolean signRejectInsert(TestItemRej req);
}