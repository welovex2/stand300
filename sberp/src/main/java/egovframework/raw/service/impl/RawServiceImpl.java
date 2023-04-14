package egovframework.raw.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import egovframework.raw.dto.CeDTO;
import egovframework.raw.dto.EftDTO;
import egovframework.raw.dto.EsdDTO;
import egovframework.raw.dto.ReDTO;
import egovframework.raw.dto.RsDTO;
import egovframework.raw.dto.SurgeDTO;
import egovframework.raw.service.MethodEsdSub;
import egovframework.raw.service.MethodMapper;
import egovframework.raw.service.RawAsstn;
import egovframework.raw.service.RawCable;
import egovframework.raw.service.RawData;
import egovframework.raw.service.RawMac;
import egovframework.raw.service.RawMapper;
import egovframework.raw.service.RawMet;
import egovframework.raw.service.RawService;
import egovframework.raw.service.RawSpec;
import egovframework.raw.service.RawSys;
import egovframework.raw.service.RawTchn;

@Service("RawService")
public class RawServiceImpl implements RawService {

	@Autowired
	RawMapper rawMapper;

	@Autowired
	MethodMapper methodMapper;
	
	@Override
	@Transactional
	public boolean insert(RawData req) {
		boolean result = true;
		
		req.setSbkId(rawMapper.getSbkId(req.getTestSeq()));
		rawMapper.insert(req);

		// 4-2. method (시험방법), 고정리스트
		rawMapper.insertMethod(req.getRawSeq(), req.getMethodList());
		
		// 기술제원
		List<RawSpec> pIItems = req.getRawSpecList().stream().filter(t->"I".equals(t.getState())).collect(Collectors.toList());
		if (!ObjectUtils.isEmpty(pIItems))
			rawMapper.insertSpec(req.getRawSeq(), pIItems);
		
		// 기술적 요구항목
		List<RawTchn> tIItems = req.getRawTchnList().stream().filter(t->"I".equals(t.getState())).collect(Collectors.toList());
		if (!ObjectUtils.isEmpty(tIItems))
			rawMapper.insertTchn(req.getRawSeq(), tIItems);
		
		// 시험기기 전체구성
		List<RawAsstn> aIItems = req.getRawAsstnList().stream().filter(t->"I".equals(t.getState())).collect(Collectors.toList());
		if (!ObjectUtils.isEmpty(aIItems))
			rawMapper.insertAsstn(req.getRawSeq(), aIItems);
		
		// 시스템구성
		List<RawSys> sIItems = req.getRawSysList().stream().filter(t->"I".equals(t.getState())).collect(Collectors.toList());
		if (!ObjectUtils.isEmpty(sIItems))
			rawMapper.insertSys(req.getRawSeq(), sIItems);
		
		// 접속케이블
		List<RawCable> cIItems = req.getRawCableList().stream().filter(t->"I".equals(t.getState())).collect(Collectors.toList());
		if (!ObjectUtils.isEmpty(cIItems))
			rawMapper.insertCable(req.getRawSeq(), cIItems);
		
		return result; 
	}

	@Override
	@Transactional
	public boolean update(RawData req) {
		boolean result = true;
		
		rawMapper.update(req);

		// 4-2. method (시험방법), 고정리스트
		rawMapper.insertMethod(req.getRawSeq(), req.getMethodList());
				
		// 기술제원
		List<RawSpec> pIItems = req.getRawSpecList().stream().filter(t->"I".equals(t.getState())).collect(Collectors.toList());
		if (!ObjectUtils.isEmpty(pIItems))
			rawMapper.insertSpec(req.getRawSeq(), pIItems);
		
		List<RawSpec> pUItems = req.getRawSpecList().stream().filter(t->"U".equals(t.getState())).collect(Collectors.toList());
		if (!ObjectUtils.isEmpty(pUItems))
			rawMapper.updateSpec(req.getRawSeq(), pUItems);
		
		List<RawSpec> pDItems = req.getRawSpecList().stream().filter(t->"D".equals(t.getState())).collect(Collectors.toList());
		if (!ObjectUtils.isEmpty(pDItems))
			rawMapper.deleteSpec(req.getRawSeq(), pDItems);
		
		// 기술적 요구항목
		List<RawTchn> tIItems = req.getRawTchnList().stream().filter(t->"I".equals(t.getState())).collect(Collectors.toList());
		if (!ObjectUtils.isEmpty(tIItems))
			rawMapper.insertTchn(req.getRawSeq(), tIItems);
		
		List<RawTchn> tUItems = req.getRawTchnList().stream().filter(t->"U".equals(t.getState())).collect(Collectors.toList());
		if (!ObjectUtils.isEmpty(tUItems))
			rawMapper.updateTchn(req.getRawSeq(), tUItems);
		
		List<RawTchn> tDItems = req.getRawTchnList().stream().filter(t->"D".equals(t.getState())).collect(Collectors.toList());
		if (!ObjectUtils.isEmpty(tDItems))
			rawMapper.deleteTchn(req.getRawSeq(), tDItems);
		
		// 시험기기 전체구성
		List<RawAsstn> aIItems = req.getRawAsstnList().stream().filter(t->"I".equals(t.getState())).collect(Collectors.toList());
		if (!ObjectUtils.isEmpty(aIItems))
			rawMapper.insertAsstn(req.getRawSeq(), aIItems);
		
		List<RawAsstn> aUItems = req.getRawAsstnList().stream().filter(t->"U".equals(t.getState())).collect(Collectors.toList());
		if (!ObjectUtils.isEmpty(aUItems))
			rawMapper.updateAsstn(req.getRawSeq(), aUItems);
		
		List<RawAsstn> aDItems = req.getRawAsstnList().stream().filter(t->"D".equals(t.getState())).collect(Collectors.toList());
		if (!ObjectUtils.isEmpty(aDItems))
			rawMapper.deleteAsstn(req.getRawSeq(), aDItems);
		
		// 시스템구성
		List<RawSys> sIItems = req.getRawSysList().stream().filter(t->"I".equals(t.getState())).collect(Collectors.toList());
		if (!ObjectUtils.isEmpty(sIItems))
			rawMapper.insertSys(req.getRawSeq(), sIItems);
		
		List<RawSys> sUItems = req.getRawSysList().stream().filter(t->"U".equals(t.getState())).collect(Collectors.toList());
		if (!ObjectUtils.isEmpty(sUItems))
			rawMapper.updateSys(req.getRawSeq(), sUItems);
		
		List<RawSys> sDItems = req.getRawSysList().stream().filter(t->"D".equals(t.getState())).collect(Collectors.toList());
		if (!ObjectUtils.isEmpty(sDItems))
			rawMapper.deleteSys(req.getRawSeq(), sDItems);
		
		// 접속케이블
		List<RawCable> cIItems = req.getRawCableList().stream().filter(t->"I".equals(t.getState())).collect(Collectors.toList());
		if (!ObjectUtils.isEmpty(cIItems))
			rawMapper.insertCable(req.getRawSeq(), cIItems);
		
		List<RawCable> cUItems = req.getRawCableList().stream().filter(t->"U".equals(t.getState())).collect(Collectors.toList());
		if (!ObjectUtils.isEmpty(cUItems))
			rawMapper.updateCable(req.getRawSeq(), cUItems);
		
		List<RawCable> cDItems = req.getRawCableList().stream().filter(t->"D".equals(t.getState())).collect(Collectors.toList());
		if (!ObjectUtils.isEmpty(cDItems))
			rawMapper.deleteCable(req.getRawSeq(), cDItems);
		
		return result;
	}
	
	@Override
	@Transactional
	public boolean insertCe(CeDTO req) {
		boolean result = true;
		
		// 기본로데이터가 없을때 로데이터 먼저 등록
		if(req.getRawSeq() == 0)
			req.setRawSeq(beforeRawInsert(req.getTestSeq(), req.getInsMemId()));
		
		methodMapper.insertCe(req);
		
		// 측정설비
		if (!ObjectUtils.isEmpty(req.getMacList()))
			methodMapper.insertMac(req.getRawSeq(), req.getMacType(), req.getMacList());
		
		return result;
	}

	@Override
	public CeDTO ceDetail(int rawSeq) {
		return methodMapper.ceDetail(rawSeq);
	}

	@Override
	public ReDTO reDetail(int rawSeq) {
		return methodMapper.reDetail(rawSeq);
	}

	@Override
	public boolean insertRe(ReDTO req) {
		boolean result = true;
		
		// 기본로데이터가 없을때 로데이터 먼저 등록
		if(req.getRawSeq() == 0)
			req.setRawSeq(beforeRawInsert(req.getTestSeq(), req.getInsMemId()));
		
		methodMapper.insertRe(req);
		
		// 측정설비
		if (!ObjectUtils.isEmpty(req.getMacList()))
			methodMapper.insertMac(req.getRawSeq(), req.getMacType(), req.getMacList());
		
		return result;
	}

	@Override
	@Transactional
	public boolean insertEsd(EsdDTO req) {
		boolean result = true;
		
		// 기본로데이터가 없을때 로데이터 먼저 등록
		if(req.getRawSeq() == 0)
			req.setRawSeq(beforeRawInsert(req.getTestSeq(), req.getInsMemId()));
		
		methodMapper.insertEsd(req);
		
		// 측정설비
		if (!ObjectUtils.isEmpty(req.getMacList()))
			methodMapper.insertMac(req.getRawSeq(), req.getMacType(), req.getMacList());
		
		// 시험결과 > 직접인가
		List<MethodEsdSub> cIItems = req.getSubList().stream().filter(t->"I".equals(t.getState())).collect(Collectors.toList());
		if (!ObjectUtils.isEmpty(cIItems))
			methodMapper.insertEsdSub(req.getEsdSeq(), cIItems);
		
		List<MethodEsdSub> cUItems = req.getSubList().stream().filter(t->"U".equals(t.getState())).collect(Collectors.toList());
		if (!ObjectUtils.isEmpty(cUItems))
			methodMapper.updateEsdSub(req.getEsdSeq(), cUItems);
		
		List<MethodEsdSub> cDItems = req.getSubList().stream().filter(t->"D".equals(t.getState())).collect(Collectors.toList());
		if (!ObjectUtils.isEmpty(cDItems))
			methodMapper.deleteEsdSub(req.getEsdSeq(), cDItems);
		
		return result;
	}

	@Override
	public EsdDTO esdDetail(int rawSeq) {
		return methodMapper.esdDetail(rawSeq);
	}

	@Override
	public RawData detail(int testSeq) {
		return rawMapper.detail(testSeq); 
	}

	@Override
	public RsDTO rsDetail(int rawSeq) {
		return methodMapper.rsDetail(rawSeq);
	}

	@Override
	@Transactional
	public boolean insertRs(RsDTO req) {
		boolean result = true;
		
		// 기본로데이터가 없을때 로데이터 먼저 등록
		if(req.getRawSeq() == 0)
			req.setRawSeq(beforeRawInsert(req.getTestSeq(), req.getInsMemId()));
		
		methodMapper.insertRs(req);
		
		// 측정설비
		if (!ObjectUtils.isEmpty(req.getMacList()))
			methodMapper.insertMac(req.getRawSeq(), req.getMacType(), req.getMacList());
		
		return result;
	}

	@Override
	public EftDTO eftDetail(int rawSeq) {
		return methodMapper.eftDetail(rawSeq);
	}

	@Override
	@Transactional
	public boolean insertEft(EftDTO req) {
		boolean result = true;
		
		// 기본로데이터가 없을때 로데이터 먼저 등록
		if(req.getRawSeq() == 0)
			req.setRawSeq(beforeRawInsert(req.getTestSeq(), req.getInsMemId()));
		
		methodMapper.insertEft(req);
		
		// 측정설비
		if (!ObjectUtils.isEmpty(req.getMacList()))
			methodMapper.insertMac(req.getRawSeq(), req.getMacType(), req.getMacList());
		
		return result;
	}

	public int beforeRawInsert(int testSeq, String insMemId) {
		
		RawData req = new RawData();
		req.setTestSeq(testSeq);
		req.setInsMemId(insMemId);
		req.setUdtMemId(insMemId);
		req.setSbkId(rawMapper.getSbkId(testSeq));
		rawMapper.insert(req);
		
		return req.getRawSeq();
	}

	@Override
	public List<RawTchn> tchnList(int rawSeq) {
		return rawMapper.tchnList(rawSeq);
	}

	@Override
	public List<RawSpec> specList(int rawSeq) {
		return rawMapper.specList(rawSeq);
	}

	@Override
	public List<RawAsstn> asstnList(int rawSeq) {
		return rawMapper.asstnList(rawSeq);
	}

	@Override
	public List<RawSys> sysList(int rawSeq) {
		return rawMapper.sysList(rawSeq);
	}

	@Override
	public List<RawCable> cableList(int rawSeq) {
		return rawMapper.cableList(rawSeq);
	}

	@Override
	public List<RawMet> methodList(int rawSeq) {
		return rawMapper.methodList(rawSeq);
	}

	@Override
	public List<RawMac> macList(String machineType, int rawSeq) {
		return methodMapper.macList(machineType, rawSeq);
	}
	
	@Override
	public List<MethodEsdSub> esdSubList(int esdSeq) {
		return methodMapper.esdSubList(esdSeq);
	}

	@Override
	public SurgeDTO surgeDetail(int rawSeq) {
		return methodMapper.surgeDetail(rawSeq);
	}

	@Override
	public boolean insertSurge(SurgeDTO req) {
		
		boolean result = true;
		
		// 기본로데이터가 없을때 로데이터 먼저 등록
		if(req.getRawSeq() == 0)
			req.setRawSeq(beforeRawInsert(req.getTestSeq(), req.getInsMemId()));
		
		methodMapper.insertSurge(req);
		
		// 측정설비
		if (!ObjectUtils.isEmpty(req.getMacList()))
			methodMapper.insertMac(req.getRawSeq(), req.getMacType(), req.getMacList());
		
		return result;
		
	}

}
