package egovframework.chq.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"취합견적서"})
@RestController
public class ChqController {
	
	@ApiOperation(value = "취합견적서 리스트")
	@GetMapping(value="/chqList.do")
	public boolean chqList() throws Exception{
    	return true;
    }
	
	@ApiOperation(value = "취합견적서 등록")
	@GetMapping(value="/chqInsert.do")
	public boolean chqInsert() throws Exception{
    	return true;
    }
	
	@ApiOperation(value = "취합견적서 상세보기")
	@GetMapping(value="/chqDetail.do")
	public boolean chqDetail() throws Exception{
    	return true;
    }
	
	@ApiOperation(value = "취합견적서 매출확정")
	@GetMapping(value="/chqSlsInsert.do")
	public boolean chqSlsInsert() throws Exception{
    	return true;
    }
	
	@ApiOperation(value = "취합견적서 삭제")
	@GetMapping(value="/chqDelete.do")
	public boolean chqDelete() throws Exception{
    	return true;
    }
}
			