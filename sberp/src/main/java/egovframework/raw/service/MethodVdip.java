package egovframework.raw.service;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MethodVdip {

  @ApiModelProperty(value = "VDIP_SEQ ", example = "")
  @Column
  @NotNull
  private int vdipSeq;


  @ApiModelProperty(value = "RAW_SEQ ", example = "")
  @Column
  @NotNull
  private int rawSeq;


  @ApiModelProperty(value = "시험전압_AC_YN ", example = "")
  @Column
  private int vltAcYn;


  @ApiModelProperty(value = "시험전압_기타_YN ", example = "")
  @Column
  private String vltEtc;


  @ApiModelProperty(value = "시험전압_기타_YN ", example = "")
  @Column
  private int vltEtcYn;


  @ApiModelProperty(value = "가변유무_무_YN ", example = "")
  @Column
  private int varNYn;


  @ApiModelProperty(value = "가변유무_유_YN ", example = "")
  @Column
  private int varYYn;


  @ApiModelProperty(value = "ROOM1_YN ", example = "")
  @Column
  private int room1Yn;


  @ApiModelProperty(value = "ROOM2_YN ", example = "")
  @Column
  private int room2Yn;


  @ApiModelProperty(value = "ROOM_ETC ", example = "")
  @Column
  private String roomEtc;


  @ApiModelProperty(value = "ROOM_ETC ", example = "")
  @Column
  private int roomEtcYn;


  @ApiModelProperty(value = "온도 ", example = "")
  @Column
  private String temp;


  @ApiModelProperty(value = "온도 ", example = "")
  @Column
  private String tempPlus;


  @ApiModelProperty(value = "습도 ", example = "")
  @Column
  private String hmdt;


  @ApiModelProperty(value = "습도 ", example = "")
  @Column
  private String hmdtPlus;


  @ApiModelProperty(value = "기압 ", example = "")
  @Column
  private String brmt;


  @ApiModelProperty(value = "기압 ", example = "")
  @Column
  private String brmtPlus;


  @ApiModelProperty(value = "DIPS_RDC1 ", example = "")
  @Column
  private int dipsRdc1Yn;


  @ApiModelProperty(value = "DIPS_RDC1 ", example = "")
  @Column
  private String dipsRdc1Text;


  @ApiModelProperty(value = "DIPS_RDC1_05_YN ", example = "")
  @Column
  private int dipsRdc105Yn;


  @ApiModelProperty(value = "DIPS_RDC1_5_YN ", example = "")
  @Column
  private int dipsRdc15Yn;


  @ApiModelProperty(value = "DIPS_RDC1_25_YN ", example = "")
  @Column
  private int dipsRdc125Yn;


  @ApiModelProperty(value = "DIPS_RDC1_30_YN ", example = "")
  @Column
  private int dipsRdc130Yn;


  @ApiModelProperty(value = "DIPS_RDC1_50_YN ", example = "")
  @Column
  private int dipsRdc150Yn;


  @ApiModelProperty(value = "DIPS_RDC1_ETC ", example = "")
  @Column
  private String dipsRdc1Etc;


  @ApiModelProperty(value = "DIPS_RDC1_ETC ", example = "")
  @Column
  private int dipsRdc1EtcYn;


  @ApiModelProperty(value = "DIPS_RDC1_성능평가_CODE ", example = "")
  @Column
  private String dipsRdc1Code;


  @ApiModelProperty(value = "DIPS_RDC2 ", example = "")
  @Column
  private int dipsRdc2Yn;


  @ApiModelProperty(value = "DIPS_RDC2 ", example = "")
  @Column
  private String dipsRdc2Text;


  @ApiModelProperty(value = "DIPS_RDC2_05_YN ", example = "")
  @Column
  private int dipsRdc205Yn;


  @ApiModelProperty(value = "DIPS_RDC2_5_YN ", example = "")
  @Column
  private int dipsRdc25Yn;


  @ApiModelProperty(value = "DIPS_RDC2_25_YN ", example = "")
  @Column
  private int dipsRdc225Yn;


  @ApiModelProperty(value = "DIPS_RDC2_30_YN ", example = "")
  @Column
  private int dipsRdc230Yn;


  @ApiModelProperty(value = "DIPS_RDC2_50_YN ", example = "")
  @Column
  private int dipsRdc250Yn;


  @ApiModelProperty(value = "DIPS_RDC2_ETC ", example = "")
  @Column
  private String dipsRdc2Etc;


  @ApiModelProperty(value = "DIPS_RDC2_ETC ", example = "")
  @Column
  private int dipsRdc2EtcYn;


  @ApiModelProperty(value = "DIPS_RDC2_성능평가_CODE ", example = "")
  @Column
  private String dipsRdc2Code;


  @ApiModelProperty(value = "DIPS_RDC3 ", example = "")
  @Column
  private int dipsRdc3Yn;


  @ApiModelProperty(value = "DIPS_RDC3 ", example = "")
  @Column
  private String dipsRdc3Text;


  @ApiModelProperty(value = "DIPS_RDC2_ETC ", example = "")
  @Column
  private String dipsRdc3Etc;


  @ApiModelProperty(value = "DIPS_RDC2_ETC ", example = "")
  @Column
  private int dipsRdc3EtcYn;


  @ApiModelProperty(value = "DIPS_RDC2_성능평가_CODE ", example = "")
  @Column
  private String dipsRdc3Code;


  @ApiModelProperty(value = "DIPS_RDC4 ", example = "")
  @Column
  private int dipsRdc4Yn;


  @ApiModelProperty(value = "DIPS_RDC4 ", example = "")
  @Column
  private String dipsRdc4Text;


  @ApiModelProperty(value = "DIPS_RDC4_ETC ", example = "")
  @Column
  private String dipsRdc4Etc;


  @ApiModelProperty(value = "DIPS_RDC4_ETC ", example = "")
  @Column
  private int dipsRdc4EtcYn;


  @ApiModelProperty(value = "DIPS_RDC4_성능평가_CODE ", example = "")
  @Column
  private String dipsRdc4Code;


  @ApiModelProperty(value = "INTRP_RDC1 ", example = "")
  @Column
  private int intrpRdc1Yn;


  @ApiModelProperty(value = "INTRP_RDC1 ", example = "")
  @Column
  private String intrpRdc1Text;


  @ApiModelProperty(value = "INTRP_RDC1_05_YN ", example = "")
  @Column
  private int intrpRdc105Yn;


  @ApiModelProperty(value = "INTRP_RDC1_250_YN ", example = "")
  @Column
  private int intrpRdc1250Yn;


  @ApiModelProperty(value = "INTRP_RDC1_300_YN ", example = "")
  @Column
  private int intrpRdc1300Yn;


  @ApiModelProperty(value = "INTRP_RDC1_ETC ", example = "")
  @Column
  private String intrpRdc1Etc;


  @ApiModelProperty(value = "INTRP_RDC1_ETC ", example = "")
  @Column
  private int intrpRdc1EtcYn;


  @ApiModelProperty(value = "INTRP_RDC1_성능평가_CODE ", example = "")
  @Column
  private String intrpRdc1Code;


  @ApiModelProperty(value = "INTRP_RDC2 ", example = "")
  @Column
  private int intrpRdc2Yn;


  @ApiModelProperty(value = "INTRP_RDC2 ", example = "")
  @Column
  private String intrpRdc2Text;


  @ApiModelProperty(value = "INTRP_RDC2_ETC ", example = "")
  @Column
  private String intrpRdc2Etc;


  @ApiModelProperty(value = "INTRP_RDC2_ETC ", example = "")
  @Column
  private int intrpRdc2EtcYn;


  @ApiModelProperty(value = "INTRP_RDC2_성능평가_CODE ", example = "")
  @Column
  private String intrpRdc2Code;


  @ApiModelProperty(value = "INTRP_RDC3 ", example = "")
  @Column
  private int intrpRdc3Yn;


  @ApiModelProperty(value = "INTRP_RDC3 ", example = "")
  @Column
  private String intrpRdc3Text;


  @ApiModelProperty(value = "INTRP_RDC3_ETC ", example = "")
  @Column
  private String intrpRdc3Etc;


  @ApiModelProperty(value = "INTRP_RDC3_ETC ", example = "")
  @Column
  private int intrpRdc3EtcYn;


  @ApiModelProperty(value = "INTRP_RDC3_성능평가_CODE ", example = "")
  @Column
  private String intrpRdc3Code;


  @ApiModelProperty(value = "INTRP_RDC4 ", example = "")
  @Column
  private int intrpRdc4Yn;


  @ApiModelProperty(value = "INTRP_RDC4 ", example = "")
  @Column
  private String intrpRdc4Text;


  @ApiModelProperty(value = "INTRP_RDC4_ETC ", example = "")
  @Column
  private String intrpRdc4Etc;


  @ApiModelProperty(value = "INTRP_RDC4_ETC_YN ", example = "")
  @Column
  private int intrpRdc4EtcYn;


  @ApiModelProperty(value = "INTRP_RDC4_성능평가_CODE ", example = "")
  @Column
  private String intrpRdc4Code;


  @ApiModelProperty(value = "Voltage Fluctuation VNOM + 10 % ", example = "")
  @Column
  private int flcttP10Yn;


  @ApiModelProperty(value = "Voltage Fluctuation VNOM - 10 % ", example = "")
  @Column
  private int flcttM10Yn;


  @ApiModelProperty(value = "Voltage Fluctuation VNOM - 15 % ", example = "")
  @Column
  private int flcttM15Yn;


  @ApiModelProperty(value = "측정년 ", example = "")
  @Column
  private int msrmnYear;


  @ApiModelProperty(value = "측정월 ", example = "")
  @Column
  private int msrmnMon;


  @ApiModelProperty(value = "측정일 ", example = "")
  @Column
  private int msrmnDay;


  @ApiModelProperty(value = "츨정시 ", example = "")
  @Column
  private int msrmnHour;


  @ApiModelProperty(value = "측정분 ", example = "")
  @Column
  private int msrmnMin;


  @ApiModelProperty(value = "서명 ", example = "")
  @Column
  private String sign;


  @ApiModelProperty(value = "서명_URL ", example = "")
  @Column
  private String signUrl;


  @ApiModelProperty(value = "시험결과_CODE ", example = "")
  @Column
  private String resultCode;


  @ApiModelProperty(value = "시험구분1 ", example = "")
  @Column
  private String test1;


  @ApiModelProperty(value = "시험구분1_성능평가_CODE ", example = "")
  @Column
  private String test1Code;


  @ApiModelProperty(value = "시험구분1_의견 ", example = "")
  @Column
  private String test1Cmnt;


  @ApiModelProperty(value = "시험구분2 ", example = "")
  @Column
  private String test2;


  @ApiModelProperty(value = "시험구분2_성능평가_CODE ", example = "")
  @Column
  private String test2Code;


  @ApiModelProperty(value = "시험구분2_의견 ", example = "")
  @Column
  private String test2Cmnt;


  @ApiModelProperty(value = "시험구분3 ", example = "")
  @Column
  private String test3;


  @ApiModelProperty(value = "시험구분3_성능평가_CODE ", example = "")
  @Column
  private String test3Code;


  @ApiModelProperty(value = "시험구분3_의견 ", example = "")
  @Column
  private String test3Cmnt;


  @ApiModelProperty(value = "특이사항 기재 ", example = "")
  @Column
  private int memoYn;


  @ApiModelProperty(value = "특이사항 기재 ", example = "")
  @Column
  private String memo;


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

  @ApiModelProperty(value = "95 % 초과 (전압강하) > 성능평가결과 ", example = "")
  @Column
  private String vol1ResultCode;


  @ApiModelProperty(value = "95 % 초과 (전압강하) > 시험자의견 코드 (공통코드 RV)", example = "")
  @Column
  private String vol1CmntCode;


  @ApiModelProperty(value = "95 % 초과 (전압강하) > 시험자의견 ", example = "")
  @Column
  private String vol1Cmnt;


  @ApiModelProperty(value = "30 % (전압강하) > 성능평가결과 ", example = "")
  @Column
  private String vol2ResultCode;


  @ApiModelProperty(value = "30 % (전압강하) > 시험자의견 코드 (공통코드 RV) ", example = "")
  @Column
  private String vol2CmntCode;


  @ApiModelProperty(value = "30 % (전압강하) > 시험자의견 ", example = "")
  @Column
  private String vol2Cmnt;


  @ApiModelProperty(value = "95 % 초과 (순간정전) > 성능평가결과 ", example = "")
  @Column
  private String vol3ResultCode;


  @ApiModelProperty(value = "95 % 초과 (순간정전) > 시험자의견 코드 (공통코드 RV) ", example = "")
  @Column
  private String vol3CmntCode;


  @ApiModelProperty(value = "95 % 초과 (순간정전) > 시험자의견 ", example = "")
  @Column
  private String vol3Cmnt;

}
