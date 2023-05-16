package egovframework.sys.service;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestStndr {

  @ApiModelProperty(value = "TEST_STNDR_SEQ ", example = "")
  @Column
  @NotNull
  private int testStndrSeq;


  @ApiModelProperty(value = "공통코드 : CN ", example = "")
  @Column
  private String countryCode;


  @ApiModelProperty(value = "인증종류 ", example = "")
  @Column
  private int crtfTypeSeq;


  @ApiModelProperty(value = "시험종류(시험부) ", example = "")
  @Column
  private String testTypeCode;


  @ApiModelProperty(value = "시험규격 ", example = "")
  @Column
  private String testStndr;


  @ApiModelProperty(value = "MEMO ", example = "")
  @Column
  private String memo;


  @ApiModelProperty(value = "사용여부 ", example = "", hidden = true)
  @Column
  private String useYn;


  @ApiModelProperty(value = "등록자 아이디 ", example = "", hidden = true)
  @Column
  private String insMemId;


  @ApiModelProperty(value = "등록 날짜시간 ", example = "", hidden = true)
  @Column
  private LocalDateTime insDt;


  @ApiModelProperty(value = "수정자 아이디 ", example = "", hidden = true)
  @Column
  private String udtMemId;


  @ApiModelProperty(value = "수정 날짜시간 ", example = "", hidden = true)
  @Column
  private LocalDateTime udtDt;


  @ApiModelProperty(value = "상태(I,U,D) ", example = "", hidden = true)
  @Column
  private String state;


}
