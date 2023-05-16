package egovframework.chq.service;

import java.util.List;
import javax.persistence.Column;
import egovframework.tst.service.TestItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@ApiModel(value = "ChqDTO", description = "취합견적서")
public class ChqDTO extends Chq {

  @ApiModelProperty(value = "취합견적서 아이디 리스트", example = "[\"Q2304-G0098\",\"Q2303-G0089\"]",
      hidden = true)
  private List<String> quoIds;

  @ApiModelProperty(value = "취합견적서 아이디", example = "")
  private String chqId;

  @Getter
  @Setter
  @ApiModel(value = "ChqDTO.Res", description = "취합견적서 조회")
  public static class Res extends Chq {
    
    @ApiModelProperty(value = "거래처명", example = "")
    @Column
    private String cmpyTitle;
    
    @ApiModelProperty(value = "견적일자", example = "")
    @Column
    private String insDtStr;
    
    @ApiModelProperty(value = "매출확정일", example = "")
    @Column
    private String cnfrmDtStr;
    
    @ApiModelProperty(value = "담당자", example = "")
    @Column
    private String memName;
    
    @ApiModelProperty(value = "열 합계", example = "")
    @Column
    private int cnt;
    
    @ApiModelProperty(value="접수비총합 ", example = "3000000")
    @Column
    private String fee;
    
    @ApiModelProperty(value="면허세총합 ", example = "3000000")
    @Column
    private String lcnsTax;
    
    @ApiModelProperty(value="시험비총합 ", example = "3000000")
    @Column
    private String testFee;
    
    @ApiModelProperty(value="청구액총합 ", example = "3000000")
    @Column
    private String CostTotal;
    
    @ApiModelProperty(value="VAT포함 총합계 ", example = "3300000")
    @Column
    private String TotalVat;
    
    @ApiModelProperty(value = "견적리스트", example = "")
    @Column
    private List<Sub> sub;
    
  }
  
  @Getter
  @Setter
  @ApiModel(value = "ChqDTO.Sub", description = "취합견적서 조회")
  public static class Sub {
    @ApiModelProperty(value = "견적서번호", example = "Q2303-G0018")
    @Column
    private String quoId;
    
    @ApiModelProperty(value = "신청서번호", example = " ")
    @Column
    private String sbkId;
    
    @ApiModelProperty(value = "컨설팅명 ", example = "")
    @Column
    private String cmpyTitle;

    @ApiModelProperty(value = "업체명 ", example = "")
    @Column
    private String cmpyName;
    
    @ApiModelProperty(value = "제품명 ", example = "")
    @Column
    private String prdctName;

    @ApiModelProperty(value = "시험항목리스트", example = "[]")
    @Column
    private List<TestItem> items;
  }
}