package egovframework.user.cop.main.service;

import java.util.HashMap;
import java.util.Map;

public interface MainExService {
	// 로그인
	public Map<String, Object> selectUserId(HashMap<String, Object> login) throws Exception;

	// 회원가입
	public void regster(HashMap<String, Object> reg) throws Exception;

	// 회원정보 수정
	public void update(Map<String, Object> upt) throws Exception;
	
	// 회원정보 삭제
	public void delete(Map<String, Object> del) throws Exception;
}
