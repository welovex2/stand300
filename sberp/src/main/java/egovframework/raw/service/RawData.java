package egovframework.raw.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description = "로데이터")
@Getter @Setter @ToString
public class RawData {
	
	@ApiModelProperty(value="시험번호+RD+001 ", example = "")
	@Column
	@NotNull
	private int rawSeq;

	@ApiModelProperty(value="신청서번호 ", example = "")
	@Column
	@NotNull
	private String sbkId;

	@ApiModelProperty(value="신청서별 로데이터 번호 ", example = "")
	@Column
	@NotNull
	private int sbkNo;
	
	@ApiModelProperty(value="시험번호 ", example = "")
	@Column
	@NotNull
	private int testSeq;
	


	@ApiModelProperty(value="신청인 ", example = "드림에스앤아이")
	@Column
	private String aplcn;


	@ApiModelProperty(value="기자재명칭 ", example = "네트워크 고해상도 카메라(영상감시장치)")
	@Column
	private String eqpmn;


	@ApiModelProperty(value="모델명 ", example = "XNB-52812R")
	@Column
	private String model;


	@ApiModelProperty(value="제조번호 ", example = "N/A")
	@Column
	private String mnfctSerial;


	@ApiModelProperty(value="제조사 ", example = "드림에스앤아이")
	@Column
	private String mnfctCmpny;


	@ApiModelProperty(value="제조국가 ", example = "한국")
	@Column
	private String mnfctCntry;


	@ApiModelProperty(value="입수일 ", example = "2023-01-10")
	@Column
	private String rcptDt;


	@ApiModelProperty(value="성적서발급번호 ", example = "STB23-0158")
	@Column
	private String reportNo;


	@ApiModelProperty(value="시험시작 ", example = "2023-01-23")
	@Column
	private String testSDt;


	@ApiModelProperty(value="시험완료 ", example = "2023-01-24")
	@Column
	private String testEDt;


	@ApiModelProperty(value="시험자 ", example = "김재희")
	@Column
	private String testBy;


	@ApiModelProperty(value="시험자서명url ", example = "")
	@Column
	private String testSignUrl;


	@ApiModelProperty(value="기술책임자 ", example = "나승주")
	@Column
	private String revBy;


	@ApiModelProperty(value="기술책임자서서명url ", example = "")
	@Column
	private String revSignUrl;


	@ApiModelProperty(value="시험요구_KC_EMC ", example = "1")
	@Column
	private int tbrKcEmcYn;


	@ApiModelProperty(value="시험요구_KC_RF_EMC ", example = "0")
	@Column
	private int tbrKcRfEmcYn;


	@ApiModelProperty(value="시험요구_EMCD ", example = "0")
	@Column
	private int tbrEmcdYn;


	@ApiModelProperty(value="시험요구_FCC ", example = "0")
	@Column
	private int tbrFccYn;


	@ApiModelProperty(value="시험요구_PSE ", example = "0")
	@Column
	private int tbrPseYn;


	@ApiModelProperty(value="시험요구_KT ", example = "0")
	@Column
	private int tbrKtYn;


	@ApiModelProperty(value="시험요구_ETC ", example = "시험요구_ETC")
	@Column
	private String tbrEtc;

	
	@ApiModelProperty(value="시험요구_ETC ", example = "0")
	@Column
	private int tbrEtcYn;


	@ApiModelProperty(value="CLASSA ", example = "1")
	@Column
	private int classAYn;


	@ApiModelProperty(value="CLASSB ", example = "0")
	@Column
	private int classBYn;


	@ApiModelProperty(value="CLASS_ETC_MEMO ", example = "CLASS_ETC_MEMO")
	@Column
	private String classEtc;


	@ApiModelProperty(value="CLASS_ETC_MEMO ", example = "0")
	@Column
	private int classEtcYn;


	@ApiModelProperty(value="METHOD_CE ", example = "1")
	@Column
	private int methodCeYn;


	@ApiModelProperty(value="METHOD_RE(Below 1㎓) ", example = "1")
	@Column
	private int methodRebYn;


	@ApiModelProperty(value="METHOD_RE(Above 1㎓) ", example = "1")
	@Column
	private int methodReaYn;


	@ApiModelProperty(value="METHOD_DP ", example = "0")
	@Column
	private int methodDpYn;


	@ApiModelProperty(value="METHOD_CLICK ", example = "0")
	@Column
	private int methodClickYn;


	@ApiModelProperty(value="METHOD_HAR ", example = "0")
	@Column
	private int methodHarYn;


	@ApiModelProperty(value="METHOD_FLICKER ", example = "0")
	@Column
	private int methodFlickerYn;


	@ApiModelProperty(value="METHOD_ETC1 ", example = "METHOD_ETC1")
	@Column
	private String methodEtc1;


	@ApiModelProperty(value="METHOD_ETC1 ", example = "0")
	@Column
	private int methodEtc1Yn;


	@ApiModelProperty(value="METHOD_ETC2 ", example = "METHOD_ETC2")
	@Column
	private String methodEtc2;


	@ApiModelProperty(value="METHOD_ETC2 ", example = "0")
	@Column
	private int methodEtc2Yn;


	@ApiModelProperty(value="시험규격_ETC1 ", example = "KS C 9832:2019")
	@Column
	private String testStndrEtc1;


	@ApiModelProperty(value="시험규격_ETC2 ", example = "KS C 9832:2019")
	@Column
	private String testStndrEtc2;


	@ApiModelProperty(value="시험규격_ETC3 ", example = "KS C 9832:2019")
	@Column
	private String testStndrEtc3;


	@ApiModelProperty(value="시험규격_ETC4 ", example = "시험규격_ETC4")
	@Column
	private String testStndrEtc4;


	@ApiModelProperty(value="시험규격_ETC5 ", example = "시험규격_ETC5")
	@Column
	private String testStndrEtc5;


	@ApiModelProperty(value="시험규격_ETC6 ", example = "시험규격_ETC6")
	@Column
	private String testStndrEtc6;


	@ApiModelProperty(value="시험규격_ETC7 ", example = "시험규격_ETC7")
	@Column
	private String testStndrEtc7;


	@ApiModelProperty(value="시험규격_ETC8 ", example = "시험규격_ETC8")
	@Column
	private String testStndrEtc8;


	@ApiModelProperty(value="시험규격_ETC9 ", example = "시험규격_ETC9")
	@Column
	private String testStndrEtc9;


	@ApiModelProperty(value="TEST_STNDR_ETC1_YN ", example = "1")
	@Column
	private int testStndrEtc1Yn;


	@ApiModelProperty(value="TEST_STNDR_ETC2_YN ", example = "1")
	@Column
	private int testStndrEtc2Yn;


	@ApiModelProperty(value="TEST_STNDR_ETC3_YN ", example = "1")
	@Column
	private int testStndrEtc3Yn;


	@ApiModelProperty(value="TEST_STNDR_ETC4_YN ", example = "0")
	@Column
	private int testStndrEtc4Yn;


	@ApiModelProperty(value="TEST_STNDR_ETC5_YN ", example = "0")
	@Column
	private int testStndrEtc5Yn;


	@ApiModelProperty(value="TEST_STNDR_ETC6_YN ", example = "0")
	@Column
	private int testStndrEtc6Yn;


	@ApiModelProperty(value="TEST_STNDR_ETC7_YN ", example = "0")
	@Column
	private int testStndrEtc7Yn;


	@ApiModelProperty(value="TEST_STNDR_ETC8_YN ", example = "0")
	@Column
	private int testStndrEtc8Yn;


	@ApiModelProperty(value="TEST_STNDR_ETC9_YN ", example = "0")
	@Column
	private int testStndrEtc9Yn;


	@ApiModelProperty(value="시험항목_ESD ", example = "1")
	@Column
	private int testItemEsdYn;


	@ApiModelProperty(value="시험항목_RS ", example = "1")
	@Column
	private int testItemRsYn;


	@ApiModelProperty(value="시험항목_EFT ", example = "1")
	@Column
	private int testItemEftYn;


	@ApiModelProperty(value="시험항목_SUR ", example = "1")
	@Column
	private int testItemSurYn;


	@ApiModelProperty(value="시험항목_CS ", example = "1")
	@Column
	private int testItemCsYn;


	@ApiModelProperty(value="시험항목_M-Field ", example = "0")
	@Column
	private int testItemMfdYn;


	@ApiModelProperty(value="시험항목_VDP ", example = "1")
	@Column
	private int testItemVdpYn;


	@ApiModelProperty(value="시험항목_CTI ", example = "0")
	@Column
	private int testItemCtiYn;


	@ApiModelProperty(value="시험항목_ETC ", example = "시험항목_ETC")
	@Column
	private String testItemEtc;


	@ApiModelProperty(value="시험항목_ETC ", example = "0")
	@Column
	private int testItemEtcYn;


	@ApiModelProperty(value="시험규격_KSC1 ", example = "1")
	@Column
	private int testStndr2Ksc1Yn;


	@ApiModelProperty(value="시험규격_KSC2 ", example = "1")
	@Column
	private int testStndr2Ksc2Yn;


	@ApiModelProperty(value="시험규격_KSC3 ", example = "1")
	@Column
	private int testStndr2Ksc3Yn;


	@ApiModelProperty(value="시험규격_KSC4 ", example = "1")
	@Column
	private int testStndr2Ksc4Yn;


	@ApiModelProperty(value="시험규격_KSC5 ", example = "1")
	@Column
	private int testStndr2Ksc5Yn;


	@ApiModelProperty(value="시험규격_KSC6 ", example = "0")
	@Column
	private int testStndr2Ksc6Yn;


	@ApiModelProperty(value="시험규격_KSC7 ", example = "1")
	@Column
	private int testStndr2Ksc7Yn;


	@ApiModelProperty(value="시험규격_KSR ", example = "0")
	@Column
	private int testStndr2KsrYn;


	@ApiModelProperty(value="시험규격_KS_ETC ", example = "시험규격_KS_ETC")
	@Column
	private String testStndr2Etc;


	@ApiModelProperty(value="시험규격_KS_ETC ", example = "0")
	@Column
	private int testStndr2EtcYn;


	@ApiModelProperty(value="시험규격_KS_ETC1 ", example = "시험규격_KS_ETC1")
	@Column
	private String testStndr2KsEtc1;


	@ApiModelProperty(value="시험규격_KS_ETC2 ", example = "시험규격_KS_ETC2")
	@Column
	private String testStndr2KsEtc2;


	@ApiModelProperty(value="시험규격_KS_ETC3 ", example = "시험규격_KS_ETC3")
	@Column
	private String testStndr2KsEtc3;


	@ApiModelProperty(value="시험규격_KS_ETC4 ", example = "시험규격_KS_ETC4")
	@Column
	private String testStndr2KsEtc4;


	@ApiModelProperty(value="시험규격_KS_ETC5 ", example = "시험규격_KS_ETC5")
	@Column
	private String testStndr2KsEtc5;


	@ApiModelProperty(value="시험규격_KS_ETC6 ", example = "시험규격_KS_ETC6")
	@Column
	private String testStndr2KsEtc6;


	@ApiModelProperty(value="시험규격_KS_ETC7 ", example = "시험규격_KS_ETC7")
	@Column
	private String testStndr2KsEtc7;


	@ApiModelProperty(value="시험규격_KS_ETC8 ", example = "시험규격_KS_ETC8")
	@Column
	private String testStndr2KsEtc8;


	@ApiModelProperty(value="시험규격_KS_ETC9 ", example = "시험규격_KS_ETC9")
	@Column
	private String testStndr2KsEtc9;


	@ApiModelProperty(value="시험규격_KS_ETC1 ", example = "0")
	@Column
	private int testStndr2KsEtc1Yn;


	@ApiModelProperty(value="시험규격_KS_ETC2 ", example = "0")
	@Column
	private int testStndr2KsEtc2Yn;


	@ApiModelProperty(value="시험규격_KS_ETC3 ", example = "0")
	@Column
	private int testStndr2KsEtc3Yn;


	@ApiModelProperty(value="시험규격_KS_ETC4 ", example = "0")
	@Column
	private int testStndr2KsEtc4Yn;


	@ApiModelProperty(value="시험규격_KS_ETC5 ", example = "0")
	@Column
	private int testStndr2KsEtc5Yn;


	@ApiModelProperty(value="시험규격_KS_ETC6 ", example = "0")
	@Column
	private int testStndr2KsEtc6Yn;


	@ApiModelProperty(value="시험규격_KS_ETC7 ", example = "0")
	@Column
	private int testStndr2KsEtc7Yn;


	@ApiModelProperty(value="시험규격_KS_ETC8 ", example = "0")
	@Column
	private int testStndr2KsEtc8Yn;


	@ApiModelProperty(value="시험규격_KS_ETC9 ", example = "0")
	@Column
	private int testStndr2KsEtc9Yn;


	@ApiModelProperty(value="특이사항 ", example = "- B급 기기의 방송수신기 튜너포트 자동전압 전도성 방해 시험: 피시험기기는 방송수신기 튜너포트가 없으므로 시험을 제외함")
	@Column
	private String memo;


	@ApiModelProperty(value="제품설명 ", example = "감시를 위해 배치되는 디지털 비디오 카메라")
	@Column
	private String prdExpln;


	@ApiModelProperty(value="제품용도 ", example = "보안용 카메라")
	@Column
	private String prdUses;


	@ApiModelProperty(value="클럭수파수 ", example = "148MHz")
	@Column
	private String clockFrqnc;


	@ApiModelProperty(value="정격전원 ", example = "-DC 12 V (직류전원장치)")
	@Column
	private String ratedPower;


	@ApiModelProperty(value="시험전원 ", example = "- AC 220 V, 60 Hz (직류전원장치)")
	@Column
	private String testPower;


	@ApiModelProperty(value="사용자포트 ", example = "LAN (RJ-45)_PoE, DC In")
	@Column
	private String userPort;


	@ApiModelProperty(value="관리자포트 ", example = "해당 없음")
	@Column
	private String adminPort;


	@ApiModelProperty(value="제품기능 ", example = "감시를 위해 배치되는 디저털 비디오 카메라")
	@Column
	private String prdFnc;


	@ApiModelProperty(value="무선기능 ", example = "해당 없음")
	@Column
	private String wrlFnc;


	@ApiModelProperty(value="구성품 ", example = "네트워크 고해상도 카메라(영상감시장치)")
	@Column
	private String cmp;


	@ApiModelProperty(value="기타 ", example = "해당 없음")
	@Column
	private String etc;


	@ApiModelProperty(value="인증사용여부 ", example = "0")
	@Column
	private int whtUseCrtYn;


	@ApiModelProperty(value="인증번호 ", example = "인증번호")
	@Column
	private String crtNbr;


	@ApiModelProperty(value="인증특이사항 ", example = "해당 없음")
	@Column
	private String crtMemo;


	@ApiModelProperty(value="보완사항 ", example = "해당 없음")
	@Column
	private String modMemo;


	@ApiModelProperty(value="보완사항_URL ", example = "")
	@Column
	private String modUrl;


	@ApiModelProperty(value="보완확인1 ", example = "1")
	@Column
	private int modCheck1Yn;


	@ApiModelProperty(value="보완확인2 ", example = "1")
	@Column
	private int modCheck2Yn;


	@ApiModelProperty(value="시험배치_FLOOR ", example = "0")
	@Column
	private int testFloorYn;


	@ApiModelProperty(value="시험배치_TABLE ", example = "1")
	@Column
	private int testTableYn;


	@ApiModelProperty(value="시험배치_FSTT ", example = "0")
	@Column
	private int testFsttYn;


	@ApiModelProperty(value="시험배치_ETC ", example = "0")
	@Column
	private int testEtcYn;


	@ApiModelProperty(value="시험기자재의동작상태 ", example = "Adapter Mode")
	@Column
	private String oprCnd;

	
	@ApiModelProperty(value="Test Set-up Configuraiotn for EUT ", example = "")
	@Column
	private String setupUrl;
	
	
	@ApiModelProperty(value="규격적용 특이사항 ", example = "추가시험 요건")
	@Column
	private String stdMemo;


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

	
	@ApiModelProperty(value="Technical Requirements (기술적 요구항목) ", example = "")
	@Column
	private List<RawTchn> rawTchnList;

	@ApiModelProperty(value="Assistance Device and Cable(시험기기 전체구성) ", example = "")
	@Column
	private List<RawAsstn> rawAsstnList;

	@ApiModelProperty(value="System Configuration (시스템구성) ", example = "")
	@Column
	private List<RawSys> rawSysList;

	@ApiModelProperty(value="Type of Cable Used (접속 케이블) ", example = "")
	@Column
	private List<RawCable> rawCableList;
}
