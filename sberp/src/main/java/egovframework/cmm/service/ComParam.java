package egovframework.cmm.service;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "검색파라메터")
public class ComParam extends ComPaging {
    
	@ApiModelProperty(value="검색시작날짜 ", example = "2023-02-02", required = true)
	private String startDate;
	
	@ApiModelProperty(value="검색종료날짜 ", example = "2023-05-30", required = true)
	private String endDate;
	
	@ApiModelProperty(value="검색종류(00:전체, 01:고객유형, 02:NO)", example = "00", required = true)
	private String searchCode;
	
	@ApiModelProperty(value="검색어", example = "")
	private String searchWord;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSearchCode() {
		return searchCode;
	}

	public void setSearchCode(String searchCode) {
		this.searchCode = searchCode;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	
}
