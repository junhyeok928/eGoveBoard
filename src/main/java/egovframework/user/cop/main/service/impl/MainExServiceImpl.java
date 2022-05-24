package egovframework.user.cop.main.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.user.cop.main.service.MainExService;

@Service("mainExService")
public class MainExServiceImpl extends EgovAbstractServiceImpl implements MainExService {
	@Resource(name = "MainExDAO")
	private MainExDAO mainexDAO;

	// 로그인
	@Override
	public Map<String, Object> selectUserId(HashMap<String, Object> login) throws Exception {
		return mainexDAO.selectUserId(login);
	}

	// 회원가입
	@Override
	public void regster(HashMap<String, Object> reg) throws Exception {
		mainexDAO.regster(reg);
	}

	// 회원정보 수정
	@Override
	public void update(Map<String, Object> upt) throws Exception {
		mainexDAO.update(upt);
	}

	// 회원정보 삭제
	@Override
	public void delete(Map<String, Object> del) throws Exception {
		mainexDAO.delete(del);
	}
}
