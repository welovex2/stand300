package egovframework.raw.service;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MethodCS {
	
	@ApiModelProperty(value="CS_SEQ ", example = "")
	@Column
	private int csSeq;


	@ApiModelProperty(value="RAW_SEQ ", example = "")
	@Column
	private int rawSeq;


	@ApiModelProperty(value="시험전압_AC_YN ", example = "")
	@Column
	private int vltAcYn;


	@ApiModelProperty(value="시험전압_ETC ", example = "")
	@Column
	private String vltEtc;


	@ApiModelProperty(value="시험전압_ETC ", example = "")
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


	@ApiModelProperty(value="ROOM2_YN ", example = "")
	@Column
	private int room2Yn;


	@ApiModelProperty(value="ROOM_ETC ", example = "")
	@Column
	private String roomEtc;


	@ApiModelProperty(value="ROOM_ETC ", example = "")
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


	@ApiModelProperty(value="전계강도_1V_YN ", example = "")
	@Column
	private int strn1vYn;


	@ApiModelProperty(value="전계강도_3V_YN ", example = "")
	@Column
	private int strn3vYn;


	@ApiModelProperty(value="전계강도_10V_YN ", example = "")
	@Column
	private int strn10vYn;


	@ApiModelProperty(value="전계강도_ETC ", example = "")
	@Column
	private String strnEtc;


	@ApiModelProperty(value="전계강도_ETC ", example = "")
	@Column
	private int strnEtcYn;


	@ApiModelProperty(value="스위프율15_YN ", example = "")
	@Column
	private int swep15Yn;


	@ApiModelProperty(value="스위프율1_YN ", example = "")
	@Column
	private int swep1Yn;


	@ApiModelProperty(value="변조_80_YN ", example = "")
	@Column
	private int mdlt80Yn;


	@ApiModelProperty(value="변조_ETC ", example = "")
	@Column
	private String mdltEtc;


	@ApiModelProperty(value="변조_ETC ", example = "")
	@Column
	private int mdltEtcYn;


	@ApiModelProperty(value="성능평가기준_A_YN ", example = "")
	@Column
	private int evltnAYn;


	@ApiModelProperty(value="성능평가기준_B_YN ", example = "")
	@Column
	private int evltnBYn;


	@ApiModelProperty(value="성능평가기준_C_YN ", example = "")
	@Column
	private int evltnCYn;


	@ApiModelProperty(value="주파수범위 ", example = "")
	@Column
	private String frqncArea;


	@ApiModelProperty(value="특정주파수 ", example = "")
	@Column
	private String spcFrqnc;


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


	@ApiModelProperty(value="인가부위_교류전원_YN ", example = "")
	@Column
	private int applPowerYn;


	@ApiModelProperty(value="인가부위_입력_YN ", example = "")
	@Column
	private int applInYn;


	@ApiModelProperty(value="인가부위_출력_YN ", example = "")
	@Column
	private int applOutYn;


	@ApiModelProperty(value="인가부위_직류전원_YN ", example = "")
	@Column
	private int applDbPowerYn;


	@ApiModelProperty(value="인가방법1_M2_YN ", example = "")
	@Column
	private int appl1M2Yn;


	@ApiModelProperty(value="인가방법1_M3_YN ", example = "")
	@Column
	private int appl1M3Yn;


	@ApiModelProperty(value="인가방법1_EM_YN ", example = "")
	@Column
	private int appl1EmYn;


	@ApiModelProperty(value="인가방법1_ETC ", example = "")
	@Column
	private String appl1Etc;


	@ApiModelProperty(value="인가방법1_ETC ", example = "")
	@Column
	private int appl1EtcYn;


	@ApiModelProperty(value="인가방법2_M2_YN ", example = "")
	@Column
	private int appl2M2Yn;


	@ApiModelProperty(value="인가방법2_M3_YN ", example = "")
	@Column
	private int appl2M3Yn;


	@ApiModelProperty(value="인가방법2_EM_YN ", example = "")
	@Column
	private int appl2EmYn;


	@ApiModelProperty(value="인가방법2_ETC ", example = "")
	@Column
	private String appl2Etc;


	@ApiModelProperty(value="인가방법2_ETC ", example = "")
	@Column
	private int appl2EtcYn;


	@ApiModelProperty(value="인가방법3_M2_YN ", example = "")
	@Column
	private int appl3M2Yn;


	@ApiModelProperty(value="인가방법3_M3_YN ", example = "")
	@Column
	private int appl3M3Yn;


	@ApiModelProperty(value="인가방법3_EM_YN ", example = "")
	@Column
	private int appl3EmYn;


	@ApiModelProperty(value="인가방법3_ETC ", example = "")
	@Column
	private String appl3Etc;


	@ApiModelProperty(value="인가방법3_ETC ", example = "")
	@Column
	private int appl3EtcYn;


	@ApiModelProperty(value="인가방법1_성능평가_CODE ", example = "")
	@Column
	private String appl1Code;


	@ApiModelProperty(value="인가방법2_성능평가_CODE ", example = "")
	@Column
	private String appl2Code;


	@ApiModelProperty(value="인가방법3_성능평가_CODE ", example = "")
	@Column
	private String appl3Code;


	@ApiModelProperty(value="인가방법1_MEMO ", example = "")
	@Column
	private String appl1Memo;


	@ApiModelProperty(value="인가방법2_MEMO ", example = "")
	@Column
	private String appl2Memo;


	@ApiModelProperty(value="인가방법3_MEMO ", example = "")
	@Column
	private String appl3Memo;


	@ApiModelProperty(value="포트1 ", example = "")
	@Column
	private String port1;


	@ApiModelProperty(value="포트2 ", example = "")
	@Column
	private String port2;


	@ApiModelProperty(value="포트1_인가방법_M2_YN ", example = "")
	@Column
	private int port1AcrdtM2Yn;


	@ApiModelProperty(value="포트1_인가방법_M3_YN ", example = "")
	@Column
	private int port1AcrdtM3Yn;


	@ApiModelProperty(value="포트1_인가방법_M3_YN ", example = "")
	@Column
	private int port1AcrdtEmYn;


	@ApiModelProperty(value="포트1_인가방법_ETC ", example = "")
	@Column
	private String port1AcrdtEtc;


	@ApiModelProperty(value="포트1_인가방법_ETC ", example = "")
	@Column
	private int port1AcrdtEtcYn;


	@ApiModelProperty(value="포트1_인가방법_성능평가_CODE ", example = "")
	@Column
	private String port1AcrdtCode;


	@ApiModelProperty(value="포트1_인가방법_MEMO ", example = "")
	@Column
	private String port1AcrdtMemo;


	@ApiModelProperty(value="포트2_인가방법_M2_YN ", example = "")
	@Column
	private int port2AcrdtM2Yn;


	@ApiModelProperty(value="포트2_인가방법_M3_YN ", example = "")
	@Column
	private int port2AcrdtM3Yn;


	@ApiModelProperty(value="포트2_인가방법_M3_YN ", example = "")
	@Column
	private int port2AcrdtEmYn;


	@ApiModelProperty(value="포트2_인가방법_ETC ", example = "")
	@Column
	private String port2AcrdtEtc;


	@ApiModelProperty(value="포트2_인가방법_ETC ", example = "")
	@Column
	private int port2AcrdtEtcYn;


	@ApiModelProperty(value="포트2_인가방법_성능평가_CODE ", example = "")
	@Column
	private String port2AcrdtCode;


	@ApiModelProperty(value="포트2_인가방법_MEMO ", example = "")
	@Column
	private String port2AcrdtMemo;


	@ApiModelProperty(value="오디오_전기적_YN ", example = "")
	@Column
	private int audioElctYn;


	@ApiModelProperty(value="오디오_음향적_YN ", example = "")
	@Column
	private int audioAcstYn;


	@ApiModelProperty(value="오디오_온이어_YN ", example = "")
	@Column
	private int audioNearYn;


	@ApiModelProperty(value="오디오_오프이어_YN ", example = "")
	@Column
	private int audioFearYn;


	@ApiModelProperty(value="오디오_M2_YN ", example = "")
	@Column
	private int audioM2Yn;


	@ApiModelProperty(value="오디오_M3_YN ", example = "")
	@Column
	private int audioM3Yn;


	@ApiModelProperty(value="오디오_M3_YN ", example = "")
	@Column
	private int audioEmYn;


	@ApiModelProperty(value="오디오_ETC ", example = "")
	@Column
	private String audioEtc;


	@ApiModelProperty(value="오디오_ETC ", example = "")
	@Column
	private int audioEtcYn;


	@ApiModelProperty(value="오디오_성능평가_CODE ", example = "")
	@Column
	private String audioCode;


	@ApiModelProperty(value="오디오_MEMO ", example = "")
	@Column
	private String audioMemo;


	@ApiModelProperty(value="스피커_전기적_YN ", example = "")
	@Column
	private int spkrElctYn;


	@ApiModelProperty(value="스피커_음향적_YN ", example = "")
	@Column
	private int spkrAcstYn;


	@ApiModelProperty(value="스피커_온이어_YN ", example = "")
	@Column
	private int spkrNearYn;


	@ApiModelProperty(value="스피커_오프이어_YN ", example = "")
	@Column
	private int spkrFearYn;


	@ApiModelProperty(value="스피커_M2_YN ", example = "")
	@Column
	private int spkrM2Yn;


	@ApiModelProperty(value="스피커_M3_YN ", example = "")
	@Column
	private int spkrM3Yn;


	@ApiModelProperty(value="스피커_M3_YN ", example = "")
	@Column
	private int spkrEmYn;


	@ApiModelProperty(value="스피커_ETC ", example = "")
	@Column
	private String spkrEtc;


	@ApiModelProperty(value="스피커_ETC ", example = "")
	@Column
	private int spkrEtcYn;


	@ApiModelProperty(value="스피커_성능평가_CODE ", example = "")
	@Column
	private String spkrCode;


	@ApiModelProperty(value="스피커_MEMO ", example = "")
	@Column
	private String spkrMemo;


	@ApiModelProperty(value="통신1_잡음전력_YN ", example = "")
	@Column
	private int comm1NoiseYn;


	@ApiModelProperty(value="통신1_음향시험_YN ", example = "")
	@Column
	private int comm1AcstYn;


	@ApiModelProperty(value="통신1_M2_YN ", example = "")
	@Column
	private int comm1M2Yn;


	@ApiModelProperty(value="통신1_M3_YN ", example = "")
	@Column
	private int comm1M3Yn;


	@ApiModelProperty(value="통신1_M3_YN ", example = "")
	@Column
	private int comm1EmYn;


	@ApiModelProperty(value="통신1_ETC ", example = "")
	@Column
	private String comm1Etc;


	@ApiModelProperty(value="통신1_ETC ", example = "")
	@Column
	private int comm1EtcYn;


	@ApiModelProperty(value="통신1_성능평가_CODE ", example = "")
	@Column
	private String comm1Code;


	@ApiModelProperty(value="통신1_MEMO ", example = "")
	@Column
	private String comm1Memo;


	@ApiModelProperty(value="통신2_잡음전력_YN ", example = "")
	@Column
	private int comm2NoiseYn;


	@ApiModelProperty(value="통신2_음향시험_YN ", example = "")
	@Column
	private int comm2AcstYn;


	@ApiModelProperty(value="통신2_M2_YN ", example = "")
	@Column
	private int comm2M2Yn;


	@ApiModelProperty(value="통신2_M3_YN ", example = "")
	@Column
	private int comm2M3Yn;


	@ApiModelProperty(value="통신2_M3_YN ", example = "")
	@Column
	private int comm2EmYn;


	@ApiModelProperty(value="통신2_ETC ", example = "")
	@Column
	private String comm2Etc;


	@ApiModelProperty(value="통신2_ETC ", example = "")
	@Column
	private int comm2EtcYn;


	@ApiModelProperty(value="통신2_성능평가_CODE ", example = "")
	@Column
	private String comm2Code;


	@ApiModelProperty(value="통신2_MEMO ", example = "")
	@Column
	private String comm2Memo;


	@ApiModelProperty(value="시험자의견_A_YN ", example = "")
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
