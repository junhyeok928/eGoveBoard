package egovframework.user.cop.main.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository("MainExDAO")
public class MainExDAO extends EgovComAbstractDAO {
	// 로그인
	public Map<String, Object> selectUserId(HashMap<String, Object> login) throws Exception {
		return selectOne("MainExDAO.selectUserId", login);
	}

	// 회원가입
	public void regster(HashMap<String, Object> reg) throws Exception {
		insert("MainExDAO.register", reg);
	}

	// 회원정보 수정
	public void update(Map<String, Object> upt) throws Exception {
		update("MainExDAO.update", upt);
	}

	// 회원삭제
	public void delete(Map<String, Object> del) throws Exception {
		delete("MainExDAO.delete", del);
	}
}
