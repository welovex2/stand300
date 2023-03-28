package egovframework.cmm.service;

public class ResponseMessage {
    public static final String LOGIN_SUCCESS = "로그인 성공";
    public static final String LOGIN_FAIL = "로그인 실패";
    public static final String NO_LOGIN = "로그인정보가 없습니다";
    public static final String UNAUTHORIZED = "권한이 없습니다";
    
    public static final String READ_USER = "회원 정보 조회 성공";
    public static final String NOT_FOUND_USER = "회원을 찾을 수 없습니다.";
    public static final String CREATED_USER = "회원 가입 성공";
    public static final String UPDATE_USER = "회원 정보 수정 성공";
    public static final String DELETE_USER = "회원 탈퇴 성공";
    
    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String DB_ERROR = "데이터베이스 에러";
    
    public static final String NO_DATA = "데이터가 없습니다";
    
    public static final String DUPLICATE_SLS = "이미등록된 매출데이터가 있습니다";
    public static final String DUPLICATE_QUO = "이미등록된 견적서가 있습니다";
    public static final String DUPLICATE_SBK = "이미등록된 신청서가 있습니다";
    public static final String DUPLICATE_CNS = "이미등록된 상담서가 있습니다";
    public static final String DUPLICATE_TEST = "이미등록된 시험이 있습니다";
    
}
