package egovframework.sls.service;

import java.util.List;
import javax.persistence.Column;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class SlsDTO {

  @Getter
  @Setter
  @ToString
  @ApiModel(value = "SlsDTO.Req", description = "매출 등록")
  public static class Req extends Sls {

    @ApiModelProperty(value = "견적서번호", example = "Q2303-G0018")
    @Column
    private String quoId;

    @ApiModelProperty(value = "견적서번호", example = "Q2303-G0018")
    @Column
    private List<String> quoIds;
    
    @ApiModelProperty(value = "취합견적서번호", example = "CH2305-001")
    @Column
    private String chqId;

    @ApiModelProperty(value = "견적서상태 :1 매출확정, 2 수정요청, 3 수정허가, 4 수정완료 ", example = "1",
        required = true)
    @Column
    private String quoStateCode;
  }

  @Getter
  @Setter
  @ApiModel(value = "SlsDTO.Res", description = "매출 조회")
  public static class Res extends Sls {

    @ApiModelProperty(value = "게시글번호", example = "1")
    @Column
    private int no;

    @ApiModelProperty(value = "매출번호", example = "M2303-0002")
    @Column
    private String slsId;

    @ApiModelProperty(value = "취합번호", example = "CH2303-001")
    @Column
    private String chqId;

    @ApiModelProperty(value = "견적서번호", example = "Q2303-G0018")
    @Column
    private String quoId;

    @ApiModelProperty(value = "매출확정일", example = "2023-03-04")
    @Column
    private String cnfrmDtStr;

    @ApiModelProperty(value = "매출확정자", example = "김정미")
    @Column
    private String cnfrmName;

    @ApiModelProperty(value = "고객유형", example = "컨설팅 or 직고객")
    @Column
    private String cmpyType;

    @ApiModelProperty(value = "컨설팅명 ", example = "", required = true)
    @Column
    private String cmpyTitle;

    @ApiModelProperty(value = "업체명 ", example = "", required = true)
    @Column
    private String cmpyName;

    @ApiModelProperty(value = "제품명 ", example = "", required = true)
    @Column
    private String prdctName;

    @ApiModelProperty(value = "접수비 ", example = "", required = true)
    @Column
    private int fee;

    @ApiModelProperty(value = "면허세 ", example = "", required = true)
    @Column
    private int lcnsTax;


    @ApiModelProperty(value = "시험비 ", example = "", required = true)
    @Column
    private int testFee;


    @ApiModelProperty(value = "청구액 ", example = "", required = true)
    @Column
    private int chrgs;


    @ApiModelProperty(value = "대납분 ", example = "", required = true)
    @Column
    private int advncPymnt;


    @ApiModelProperty(value = "특별할인 ", example = "", required = true)
    @Column
    private int spclDscnt;


    @ApiModelProperty(value = "컨설팅비 ", example = "", required = true)
    @Column
    private int cnsltFee;


    @ApiModelProperty(value = "외주비 ", example = "", required = true)
    @Column
    private int otsrcFee;


    @ApiModelProperty(value = "순매출 ", example = "", required = true)
    @Column
    private int netSales;

    @ApiModelProperty(value = "상담서 고유번호", example = "63")
    @Column
    private int cnsSeq;

    @ApiModelProperty(value = "고객 상담내용", example = "어쩌구저쩌구")
    @Column
    private String lastMemo;

    @ApiModelProperty(value = "정상총합 ", example = "3000000", required = true)
    @Column
    private String CostTotal;

    @ApiModelProperty(value = "계산서발행액총합 ", example = "3300000", required = true)
    @Column
    private String TotalVat;

    @ApiModelProperty(value = "납부상태 ", example = "", required = true)
    @Column
    private String payState;

    @ApiModelProperty(value = "납부상태(일) ", example = "", required = true)
    @Column
    private String payDtStr;

    @ApiModelProperty(value = "납부상태(금액) ", example = "", required = true)
    @Column
    private int pay;

    @ApiModelProperty(value = "게산서발행여부상태", example = "", required = true)
    @Column
    private String billState;

    @ApiModelProperty(value = "게산서발행여부(일) ", example = "", required = true)
    @Column
    private String billDtStr;

    @ApiModelProperty(value = "게산서발행여부(금액) ", example = "", required = true)
    @Column
    private int bill;

    @ApiModelProperty(value = "수정요청상태 ", example = "", required = true)
    @Column
    private String quoState;

    @ApiModelProperty(value = "수정요청상태(일) ", example = "", required = true)
    @Column
    private String prmsDtStr;

    @ApiModelProperty(value = "수정요청상태(이름) ", example = "", required = true)
    @Column
    private String prmsName;

  }
}
