package egovframework.sbk.service;

import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import egovframework.tst.service.TestItemDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class SbkDTO{

	@Getter @Setter @ToString(callSuper = true)
	@ApiModel(value="SbkDTO.Req", description = "신청서 작성")
	public static class Req extends Sbk {
		@ApiModelProperty(value="견적서번호", example = " ")
		@Column
		private String quoId;
		
		@ApiModelProperty(value="신청서번호", example = " ")
		@Column
		private String sbkId;
		
		@ApiModelProperty(value="회사 고유번호", example = "1")
		@Column
		private int cmpySeq;
		
		@ApiModelProperty(value="담당자 이름 ", example = "김담당")
		@Column
		private String mngName;
		
		@ApiModelProperty(value="담당자 전화번호 ", example = "02-354-9855")
		@Column
		@Pattern(regexp = "^[0-9-]*$", message = "연락처는 10 ~ 11 자리의 숫자만 입력 가능합니다.") 
		@Size(min=0, max=13, message = "연락처는 10 ~ 11 자리의 숫자만 입력 가능합니다.")
		private String mngPhone;

		@Pattern(regexp = "^[0-9-]*$", message = "연락처는 10 ~ 11 자리의 숫자만 입력 가능합니다.") 
		@Size(min=0, max=13, message = "연락처는 10 ~ 11 자리의 숫자만 입력 가능합니다.")
		@ApiModelProperty(value="담당자팩스 ", example = "031-5252-4555")
		@Column
		private String mngFax;
		
		@ApiModelProperty(value="업체명 ", example = " ")
		@Column
		private String cmpyName;
		
		@ApiModelProperty(value="제품명 ", example = "블루투스 수신기")
		@Column
		private String prdctName;
		
		@ApiModelProperty(value="모델명 ", example = "dong-gle")
		@Column
		private String modelName;
		
	}

	@Getter @Setter
	@ApiModel(value="SbkDTO.Res", description = "신청서 조회")
	public static class Res extends Sbk {
		
		@ApiModelProperty(value="게시글번호", example = "1")
		@Column
		private int no;
		
		@ApiModelProperty(value="신청서번호", example = "SB23-G0005")
		@Column
		private String sbkId;
		
		@ApiModelProperty(value="견적서번호", example = " ")
		@Column
		private String quoId;

		@ApiModelProperty(value="상담서번호", example = " ")
		@Column
		private String cnsSeq;
		
		@ApiModelProperty(value="시험항목리스트", example = "[]")
		@Column
		private List<TestItemDTO> items;
		
		@ApiModelProperty(value="회사 고유번호", example = "1")
		@Column
		private int cmpySeq;
		
		@ApiModelProperty(value="담당자 이름 ", example = "김담당")
		@Column
		private String mngName;

		@ApiModelProperty(value="전화번호 ", example = "02-354-9855")
		@Column
		private String mngPhone;
		
		@ApiModelProperty(value="담당자팩스 ", example = "031-5252-4555")
		@Column
		private String mngFax;
		
		@ApiModelProperty(value="제품명 ", example = "블루투스 수신기")
		@Column
		private String prdctName;

		@ApiModelProperty(value="업체명 ", example = "")
		@Column
		private String cmpyName;
		
		@ApiModelProperty(value="팩스 ", example = "02-415-5633")
		@Column
		private String cmpyFax;
		
		@ApiModelProperty(value="모델명 ", example = "dong-gle")
		@Column
		private String modelName;
		
		@ApiModelProperty(value="작성일자", example = "2023-03-03")
		@Column
		private String insDtStr;
		
		@ApiModelProperty(value="고객유형", example = "컨설팅 or 직고객")
		@Column
		private String cmpyType;
		
		@ApiModelProperty(value="고객유형 0001 컨설팅 0002 직고객 0003 없음 ", example = "")
		@Column
		private String cmpyCode;
		
		@ApiModelProperty(value="컨설팅명 ", example = "")
		@Column
		private String cmpyTitle;
		
		@ApiModelProperty(value="작성자", example = "김정미")
		@Column
		private String memName;
		
		@ApiModelProperty(value="시료반입일 ", example = "2023-03-21")
		@Column
		private String carryInDt;
		
		@ApiModelProperty(value="고객 상담내용", example = "어쩌구저쩌구")
		@Column
		private String lastMemo;
		
		@ApiModelProperty(value="신청구분", example = "신규, 동일기자재")
		@Column
		private String sgText;
		
		@ApiModelProperty(value="부가가치세여부 ", example = "1")
		@Column
		private int vatYn;
		
		@ApiModelProperty(value="청구액총합 ", example = "3000000")
		@Column
		private String CostTotal;
		
		@ApiModelProperty(value="VAT포함 총합계 ", example = "3300000")
		@Column
		private String TotalVat;

	}
	
}
