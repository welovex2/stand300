package egovframework.cnf.service;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import egovframework.cmm.service.ComParam;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("MemMapper")
public interface MemMapper {

  List<Member> selectList(ComParam param);

  int selectListCnt(ComParam param);

  boolean insert(Member req);

  Member detail(int cmpySeq);

}
