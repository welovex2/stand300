package egovframework.sys.service;

import java.util.List;

import egovframework.cmm.service.ComParam;

public interface MacService {

  List<MachineDTO> selectList(ComParam param);

  int selectListCnt(ComParam param);

  boolean insert(List<MachineDTO> list);

  boolean update(MachineDTO req);

  boolean updateSub(String type, List<MachineDTO> list);

}
