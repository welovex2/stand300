package egovframework.cmm.service;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("LoginMapper")
public interface LoginMapper {

	/**
	 * 일반 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
	public LoginVO actionLogin(LoginVO vo) throws Exception;

	/**
     * 마지막 로그인 정보를 저장한다
     * @param vo LoginVO
     * @return LoginVO
     * @exception Exception
     */
    public void updateLogin(LoginVO vo) throws Exception;
    
	/**
	 * 아이디를 찾는다.
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
	public LoginVO searchId(LoginVO vo) throws Exception;

	/**
	 * 비밀번호를 찾는다.
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
	public LoginVO searchPassword(LoginVO vo) throws Exception;

	/**
	 * 변경된 비밀번호를 저장한다.
	 * @param vo LoginVO
	 * @exception Exception
	 */
	public void updatePassword(LoginVO vo) throws Exception;
}
