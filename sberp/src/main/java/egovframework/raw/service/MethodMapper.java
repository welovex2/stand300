package egovframework.raw.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import egovframework.raw.dto.CeDTO;
import egovframework.raw.dto.CsDTO;
import egovframework.raw.dto.CtiDTO;
import egovframework.raw.dto.EftDTO;
import egovframework.raw.dto.EsdDTO;
import egovframework.raw.dto.MfDTO;
import egovframework.raw.dto.ReDTO;
import egovframework.raw.dto.RsDTO;
import egovframework.raw.dto.SurgeDTO;
import egovframework.raw.dto.VdipDTO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("MethodMapper")
public interface MethodMapper {

	public CeDTO ceDetail(int rawSeq);
	
	public void insertCe(CeDTO req);

	public void insertMac(@Param("rawSeq") int rawSeq, @Param("machineType") String machineType, @Param("macList") List<RawMac> macList);
	
	public void updateCe(CeDTO req);

	public ReDTO reDetail(int rawSeq);
	
	public void insertRe(ReDTO req);
	
	public void updateRe(ReDTO req);

	public void insertEsd(EsdDTO req);

	public EsdDTO esdDetail(int rawSeq);

	public void insertEsdSub(@Param("esdSeq") int esdSeq, @Param("MethodEsdSubList") List<MethodEsdSub> sIItems);

	public void updateEsdSub(@Param("esdSeq") int esdSeq, @Param("MethodEsdSubList") List<MethodEsdSub> sUItems);

	public void deleteEsdSub(@Param("esdSeq") int esdSeq, @Param("MethodEsdSubList") List<MethodEsdSub> sDItems);

	public RsDTO rsDetail(int rawSeq);

	public void insertRs(RsDTO req);
	
	EftDTO eftDetail(int rawSeq);

	boolean insertEft(EftDTO req);

	public List<RawMac> macList(@Param("machineType") String machineType, @Param("rawSeq") int rawSeq);

	public List<MethodEsdSub> esdSubList(int esdSeq);

	public SurgeDTO surgeDetail(int rawSeq);

	public boolean insertSurge(SurgeDTO req);
	
	public CsDTO csDetail(int rawSeq);

	public boolean insertCs(CsDTO req);

	public MfDTO mfDetail(int rawSeq);

	public boolean insertMf(MfDTO req);

	public VdipDTO vdipDetail(int rawSeq);

	public boolean insertVdip(VdipDTO req);
	
	public CtiDTO ctiDetail(int rawSeq);

	public  boolean insertCti(CtiDTO req);

	public void insertCtiSub(@Param("ctiSeq") int ctiSeq, @Param("ctiSubList") List<MethodCtiSub> sIItems);

	public List<MethodCtiSub> ctiSubList(int ctiSeq);
	
}
