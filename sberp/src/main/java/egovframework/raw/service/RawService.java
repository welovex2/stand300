package egovframework.raw.service;

import java.util.List;

import egovframework.raw.dto.CeDTO;
import egovframework.raw.dto.EftDTO;
import egovframework.raw.dto.EsdDTO;
import egovframework.raw.dto.RawDTO;
import egovframework.raw.dto.RawSearchDTO;
import egovframework.raw.dto.ReDTO;
import egovframework.raw.dto.RsDTO;
import egovframework.raw.dto.SurgeDTO;

public interface RawService {

	boolean insert(RawData req);

	boolean update(RawData req);

	CeDTO ceDetail(int rawSeq);
	
	boolean insertCe(CeDTO req);

	ReDTO reDetail(int rawSeq);

	boolean insertRe(ReDTO req);

	boolean insertEsd(EsdDTO req);
	
	EsdDTO esdDetail(int rawSeq);

	RawData detail(int testSeq);

	RsDTO rsDetail(int rawSeq);

	boolean insertRs(RsDTO req);

	EftDTO eftDetail(int rawSeq);

	boolean insertEft(EftDTO req);

	List<RawTchn> tchnList(int rawSeq);

	List<RawSpec> specList(int rawSeq);

	List<RawAsstn> asstnList(int rawSeq);

	List<RawSys> sysList(int rawSeq);

	List<RawCable> cableList(int rawSeq);

	List<RawMet> methodList(int rawSeq);

	List<RawMac> macList(String machineType, int rawSeq);

	List<MethodEsdSub> esdSubList(int esdSeq);

	SurgeDTO surgeDetail(int rawSeq);
	
	boolean insertSurge(SurgeDTO req);

}