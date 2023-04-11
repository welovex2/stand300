package egovframework.raw.service;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description = "로데이터 > EFT / B U R S T")
@Getter @Setter @ToString
public class MethodEft {
	
	@ApiModelProperty(value="EFT_SEQ ", example = "")
	@Column
	@NotNull
	private int eftSeq;


	@ApiModelProperty(value="RAW_SEQ ", example = "")
	@Column
	@NotNull
	private int rawSeq;


	@ApiModelProperty(value="VLT_AC_YN ", example = "")
	@Column
	private int vltAcYn;


	@ApiModelProperty(value="VLT_ETC ", example = "")
	@Column
	private String vltEtc;


	@ApiModelProperty(value="VLT_ETC_YN ", example = "")
	@Column
	private int vltEtcYn;


	@ApiModelProperty(value="가변유무_무_YN ", example = "")
	@Column
	private int varNYn;


	@ApiModelProperty(value="가변유무_유_YN ", example = "")
	@Column
	private int varYYn;


	@ApiModelProperty(value="ROOM1_YN ", example = "")
	@Column
	private int room1Yn;


	@ApiModelProperty(value="ROMM2_YN ", example = "")
	@Column
	private int romm2Yn;


	@ApiModelProperty(value="ROOM_ETC ", example = "")
	@Column
	private String roomEtc;


	@ApiModelProperty(value="ROOM_ETC_YN ", example = "")
	@Column
	private int roomEtcYn;


	@ApiModelProperty(value="온도 ", example = "")
	@Column
	private String temp;


	@ApiModelProperty(value="온도 ", example = "")
	@Column
	private String tempPlus;


	@ApiModelProperty(value="습도 ", example = "")
	@Column
	private String hmdt;


	@ApiModelProperty(value="습도 ", example = "")
	@Column
	private String hmdtPlus;


	@ApiModelProperty(value="기압 ", example = "")
	@Column
	private String brmt;


	@ApiModelProperty(value="기압 ", example = "")
	@Column
	private String brmtPlus;


	@ApiModelProperty(value="교류전원 ", example = "")
	@Column
	private String acPower;


	@ApiModelProperty(value="직류전원 ", example = "")
	@Column
	private String dcPower;


	@ApiModelProperty(value="통신단자 ", example = "")
	@Column
	private String commTml;


	@ApiModelProperty(value="성능평가기준A_YN ", example = "")
	@Column
	private int evltnAYn;


	@ApiModelProperty(value="성능평가기준B_YN ", example = "")
	@Column
	private int evltnBYn;


	@ApiModelProperty(value="성능평가기준C_YN ", example = "")
	@Column
	private int evltnCYn;


	@ApiModelProperty(value="반복율_5_YN ", example = "")
	@Column
	private int rpttn5Yn;


	@ApiModelProperty(value="반복율_100_YN ", example = "")
	@Column
	private int rpttn100Yn;


	@ApiModelProperty(value="인가시간_1_YN ", example = "")
	@Column
	private int acrdt1Yn;


	@ApiModelProperty(value="인가시간_2_YN ", example = "")
	@Column
	private int acrdt2Yn;


	@ApiModelProperty(value="인가방법_단자_YN ", example = "")
	@Column
	private int acrdtTrmYn;


	@ApiModelProperty(value="인가방법_단자외_YN ", example = "")
	@Column
	private int acrdtTrmEtcYn;


	@ApiModelProperty(value="측정년 ", example = "")
	@Column
	private int msrmnYear;


	@ApiModelProperty(value="측정월 ", example = "")
	@Column
	private int msrmnMon;


	@ApiModelProperty(value="측정일 ", example = "")
	@Column
	private int msrmnDay;


	@ApiModelProperty(value="측정시 ", example = "")
	@Column
	private int msrmnHour;


	@ApiModelProperty(value="측정분 ", example = "")
	@Column
	private int msrmnMin;


	@ApiModelProperty(value="서명 ", example = "")
	@Column
	private String sign;


	@ApiModelProperty(value="서명_URL ", example = "")
	@Column
	private String signUrl;


	@ApiModelProperty(value="시험결과_CODE ", example = "")
	@Column
	private String resultCode;


	@ApiModelProperty(value="교류전원_L_YN ", example = "")
	@Column
	private int powerLYn;


	@ApiModelProperty(value="교류전원_N_YN ", example = "")
	@Column
	private int powerNYn;


	@ApiModelProperty(value="교류전원_PE_YN ", example = "")
	@Column
	private int powerPeYn;


	@ApiModelProperty(value="교류전원_ETC ", example = "")
	@Column
	private String powerEtc;


	@ApiModelProperty(value="교류전원_ETC ", example = "")
	@Column
	private int powerEtcYn;


	@ApiModelProperty(value="교류전원_LN_YN ", example = "")
	@Column
	private int powerLnYn;


	@ApiModelProperty(value="교류전원_LPE_YN ", example = "")
	@Column
	private int powerLpeYn;


	@ApiModelProperty(value="교류전원_NPE_YN ", example = "")
	@Column
	private int powerNpeYn;


	@ApiModelProperty(value="교류전원_LNPE ", example = "")
	@Column
	private int powerLnpeYn;


	@ApiModelProperty(value="교류전원_ETC1 ", example = "")
	@Column
	private String powerEtc1;


	@ApiModelProperty(value="교류전원_ETC2 ", example = "")
	@Column
	private String powerEtc2;


	@ApiModelProperty(value="직류전원_P_YN ", example = "")
	@Column
	private int dcPowerPYn;


	@ApiModelProperty(value="직류전원_N_YN ", example = "")
	@Column
	private int dcPowerNYn;


	@ApiModelProperty(value="직류전원_PN_YN ", example = "")
	@Column
	private int dcPowerPnYn;


	@ApiModelProperty(value="신호선_ETC1 ", example = "")
	@Column
	private String sgnlEtc1;


	@ApiModelProperty(value="신호선_ETC1 ", example = "")
	@Column
	private int sgnlEtc1Yn;


	@ApiModelProperty(value="신호선_ETC2 ", example = "")
	@Column
	private String sgnlEtc2;


	@ApiModelProperty(value="신호선_ETC2 ", example = "")
	@Column
	private int sgnlEtc2Yn;


	@ApiModelProperty(value="신호선_ETC3 ", example = "")
	@Column
	private String sgnlEtc3;


	@ApiModelProperty(value="신호선_ETC3 ", example = "")
	@Column
	private int sgnlEtc3Yn;


	@ApiModelProperty(value="교류전원_L_CODE ", example = "")
	@Column
	private String powerLCode;


	@ApiModelProperty(value="교류전원_LN_CODE ", example = "")
	@Column
	private String powerLnCode;


	@ApiModelProperty(value="교류전원_LNPE_CODE ", example = "")
	@Column
	private String powerLnpeCode;


	@ApiModelProperty(value="교류전원_ETC1_CODE ", example = "")
	@Column
	private String powerEtc1Code;


	@ApiModelProperty(value="교류전원_ETC2_CODE ", example = "")
	@Column
	private String powerEtc2Code;


	@ApiModelProperty(value="직류전원_P_YN_CODE ", example = "")
	@Column
	private String dcPowerPCode;


	@ApiModelProperty(value="신호선_ETC1_CODE ", example = "")
	@Column
	private String sgnEtc1Code;


	@ApiModelProperty(value="신호선_ETC2_CODE ", example = "")
	@Column
	private String sgnEtc2Code;


	@ApiModelProperty(value="신호선_ETC3_CODE ", example = "")
	@Column
	private String sgnEtc3Code;


	@ApiModelProperty(value="시험자의견A_YN ", example = "")
	@Column
	private int cmntAYn;


	@ApiModelProperty(value="시험자의견_ETC ", example = "")
	@Column
	private String cmntEtc;


	@ApiModelProperty(value="시험자의견_ETC ", example = "")
	@Column
	private int cmntEtcYn;


	@ApiModelProperty(value="등록자 아이디 ", example = "", hidden = true)
	@Column
	private String insMemId;


	@ApiModelProperty(value="등록 날짜시간 ", example = "", hidden = true)
	@Column
	private LocalDateTime insDt;


	@ApiModelProperty(value="수정자 아이디 ", example = "", hidden = true)
	@Column
	private String udtMemId;


	@ApiModelProperty(value="수정 날짜시간 ", example = "", hidden = true)
	@Column
	private LocalDateTime udtDt;


	@ApiModelProperty(value="상태(I,U,D) ", example = "", hidden = true)
	@Column
	private String state;


}
