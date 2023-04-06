package egovframework.cmm.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.quo.service.QuoDTO;
import egovframework.sbk.service.SbkDTO;
import egovframework.sls.service.BillDTO;
import egovframework.sls.service.PayDTO;
import egovframework.sls.service.SlsDTO;
import egovframework.tst.service.TestDTO;

@RestController
public class SwaggerModel {

	@GetMapping(value="/QuoDTO.Res")
	public QuoDTO.Res quoList() throws Exception{
		QuoDTO.Res result = new QuoDTO.Res();
		return result; 
	}
	
	@GetMapping(value="/BillDTO.Res")
	public BillDTO.Res billList() throws Exception{
		BillDTO.Res result = new BillDTO.Res();
		return result; 
	}
	
	@GetMapping(value="/PayDTO.Res")
	public PayDTO.Res payList() throws Exception{
		PayDTO.Res result = new PayDTO.Res();
		return result; 
	}
	
	@GetMapping(value="/SlsDTO.Res")
	public SlsDTO.Res slsList() throws Exception{
		SlsDTO.Res result = new SlsDTO.Res();
		return result; 
	}

	@GetMapping(value="/SbkDTO.Res")
	public SbkDTO.Res sbkList() throws Exception{
		SbkDTO.Res result = new SbkDTO.Res();
		return result; 
	}
	
	@GetMapping(value="/TestDTO.Res")
	public TestDTO.Res tstList() throws Exception{
		TestDTO.Res result = new TestDTO.Res();
		return result; 
	}
	
}
