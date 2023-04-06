package egovframework.raw.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("RawMapper")
public interface RawMapper {

	public boolean insert(RawData req);

	public void insertTchn(@Param("rawSeq") int rawSeq, @Param("rawTchnList") List<RawTchn> rawTchnList);
	
	public void updateTchn(@Param("rawSeq") int rawSeq, @Param("rawTchnList") List<RawTchn> rawTchnList);
	
	public void deleteTchn(@Param("rawSeq") int rawSeq, @Param("rawTchnList") List<RawTchn> rawTchnList);

	public void insertAsstn(@Param("rawSeq") int rawSeq, @Param("rawAsstnList") List<RawAsstn> rawAsstnList);
	
	public void updateAsstn(@Param("rawSeq") int rawSeq, @Param("rawAsstnList") List<RawAsstn> rawAsstnList);
	
	public void deleteAsstn(@Param("rawSeq") int rawSeq, @Param("rawAsstnList") List<RawAsstn> rawAsstnList);

	public void insertSys(@Param("rawSeq") int rawSeq, @Param("rawSysList") List<RawSys> rawSysList);
	
	public void updateSys(@Param("rawSeq") int rawSeq, @Param("rawSysList") List<RawSys> rawSysList);
	
	public void deleteSys(@Param("rawSeq") int rawSeq, @Param("rawSysList") List<RawSys> rawSysList);

	public void insertCable(@Param("rawSeq") int rawSeq, @Param("rawCableList") List<RawCable> rawCableList);
	
	public void updateCable(@Param("rawSeq") int rawSeq, @Param("rawCableList") List<RawCable> rawCableList);
	
	public void deleteCable(@Param("rawSeq") int rawSeq, @Param("rawCableList") List<RawCable> rawCableList);
	
	public boolean update(RawData req);

	public RawData detail(int testSeq);

}
