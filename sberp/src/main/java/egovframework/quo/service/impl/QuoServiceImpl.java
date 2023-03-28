package egovframework.quo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import egovframework.cmm.service.ComParam;
import egovframework.quo.service.QuoDTO.Req;
import egovframework.quo.service.QuoDTO.Res;
import egovframework.quo.service.QuoMapper;
import egovframework.quo.service.QuoModDTO;
import egovframework.quo.service.QuoService;
import egovframework.tst.service.TestItem;

@Service("QuoService")
public class QuoServiceImpl implements QuoService {

	@Autowired
	QuoMapper quoMapper;
	
	@Override
	public String selectRef(Req quo) throws Exception {
		return quoMapper.selectRef(quo);
	}

	@Override
	@Transactional
	public boolean insert(Req quo) throws Exception {
		boolean result = true;

		// 견적서 기본정보
		quoMapper.insert(quo);
		quo.setQuoId(quoMapper.selectRef(quo));
		
		// 견적서 시험항목
		if (!ObjectUtils.isEmpty(quo.getTestItems())) {
			quoMapper.insertTestItem(quo.getQuoId(), quo.getInsMemId(), quo.getTestItems());
		}
		
		// 업무서 공통 정보
		if (StringUtils.isEmpty(quo.getSbkId()))
			quoMapper.insertJob(quo);
		else 
			quoMapper.updateJobQuo(quo);
		
		return result;
	}

	@Override
	public Res selectDetail(Req quo) throws Exception {
		Res detail = quoMapper.selectDetail(quo);
		return detail;
	}

	@Override
	public List<Res> selectList(ComParam param) throws Exception {
		List<Res> list = quoMapper.selectList(param);
		return list;
	}

	@Override
	public int selectListCnt(ComParam param) {
		return quoMapper.selectListCnt(param);
	}

	@Override
	@Transactional
	public boolean update(Req quo) throws Exception {
		boolean result = true;

		// 견적서 기본정보
		quoMapper.update(quo);
		// 견적서 시험항목
		List<TestItem> iItems = quo.getTestItems().stream().filter(t->"I".equals(t.getState())).collect(Collectors.toList());
		System.out.println(iItems.toString());
		if (!ObjectUtils.isEmpty(iItems))
			quoMapper.insertTestItem(quo.getQuoId(), quo.getInsMemId(), iItems);
		
		List<TestItem> uItems = quo.getTestItems().stream().filter(t->"U".equals(t.getState())).collect(Collectors.toList());
		if (!ObjectUtils.isEmpty(uItems))
			quoMapper.updateTestItem(quo.getQuoId(), quo.getInsMemId(), uItems);
		
		List<TestItem> dItems = quo.getTestItems().stream().filter(t->"D".equals(t.getState())).collect(Collectors.toList());
		if (!ObjectUtils.isEmpty(dItems))
			quoMapper.deleteTestItem(quo.getQuoId(), quo.getInsMemId(), dItems);
		
		// 업무서 공통 정보
		quoMapper.updateJob(quo);
		
		return result;
	}

	@Override
	@Transactional
	public boolean updateStatus(QuoModDTO.Req req) {
		boolean result = true;
		
		// 견적서 상태 변경
		quoMapper.updateQuoState(req);
		
		if ("2".equals(req.getStateCode())) {
			// 수정 히스토리 등록
			quoMapper.insertState(req);
		} else {
			System.out.println("stateCode="+req.getStateCode());
			// 수정 히스토리 수정
			quoMapper.updateState(req);
		}
		
		return result;
	}

	@Override
	public List<QuoModDTO.Res> selectStatusList(String quoSeq) {
		return quoMapper.selectStatusList(quoSeq);
	}
}