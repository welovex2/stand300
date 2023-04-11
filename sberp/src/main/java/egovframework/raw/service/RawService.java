package egovframework.raw.service;

import egovframework.raw.dto.CeDTO;
import egovframework.raw.dto.EftDTO;
import egovframework.raw.dto.EsdDTO;
import egovframework.raw.dto.ReDTO;
import egovframework.raw.dto.RsDTO;

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

}