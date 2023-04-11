package egovframework.cmm.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import egovframework.cmm.service.BasicResponse;
import egovframework.cmm.service.EgovFileMngService;
import egovframework.cmm.service.FileVO;
import egovframework.cmm.util.EgovFileMngUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"서명테스트"})
@RestController
@RequestMapping("/sign")
public class SignController {

    @Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;
    
    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;
    
    @ApiOperation(value = "서명 테스트")
	@PostMapping(value="/insert.do")
	public BasicResponse insert(MultipartFile signFile) throws Exception {
    	
    	boolean result = true;
    	
    	List<FileVO> FileResult = null;
    	FileVO oneFile = null;
    	String atchFileId = "";
		
    	// 시험자 서명
	    if (!ObjectUtils.isEmpty(signFile)) {
	    	oneFile = fileUtil.parseFile(signFile, "SIGN", 0, "", "");
    		atchFileId = fileMngService.insertFileInf(oneFile);
	    }
	    
		BasicResponse res = BasicResponse.builder().result(result)
				.message("")
				.data("/file/getImage.do?atchFileId=".concat(atchFileId).concat("&fileSn=0"))
				.build();
		
		return res;   	
    }
}
