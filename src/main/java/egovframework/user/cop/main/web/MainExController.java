package egovframework.user.cop.main.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.utl.utl.ScriptUtils;
import egovframework.user.cop.main.service.MainExService;

@Controller
public class MainExController {

	@Resource(name = "mainExService")
	private MainExService mainExService;

	// 로그인 폼
	@RequestMapping(value = "/user/main/MainEx.do")
	public String loginform() {
		return "egovframework/user/MainEx";
	}

	// 로그인 액션
	@RequestMapping(value = "/user/main/MainExAction.do", method = RequestMethod.POST)
	public String login(@RequestParam("userId") String userId, @RequestParam("userPassword") String userPassword,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		HashMap<String, Object> loginfo = new HashMap<>();
		loginfo.put("userId", userId);
		loginfo.put("userPassword", userPassword);

		Map<String, Object> userinfo = mainExService.selectUserId(loginfo);

		if (userinfo != null) {

			request.getSession().setAttribute("userId", userinfo);
			System.out.println(request.getSession().getAttribute("userId"));
			return "egovframework/user/MyPage";
		} else {

			ScriptUtils.alertAndMovePage(response, "로그인실패.", "/user/main/MainEx.do");

			return null;

		}

	}

	// 회원가입
	@RequestMapping(value = "/user/main/RegPage.do")
	public String regform() {
		return "egovframework/user/RegPage";
	}

	// 회원가입 액션
	@RequestMapping(value = "/user/main/RegAction.do")
	public String reg(@RequestParam("userId") String userId, @RequestParam("userPassword") String userPassword)
			throws Exception {
		HashMap<String, Object> reginfo = new HashMap<>();
		reginfo.put("userId", userId);
		reginfo.put("userPassword", userPassword);

		mainExService.regster(reginfo);
		System.out.println("성공");

		return "redirect:/user/main/MainEx.do";
	}

	// 회원정보 수정
	@RequestMapping(value = "/user/main/updateInfo.do")
	public String update(HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
		Map<String, String[]> reqmap = request.getParameterMap();
		Map<String, Object> map = new HashMap<String, Object>();
		for (String key : reqmap.keySet()) {
			map.put(key, reqmap.get(key)[0]);
		}

		HashMap<String, Object> loginfo = new HashMap<>();
		loginfo.put("userId", map.get("userid"));
		loginfo.put("userPassword", map.get("userpassword"));

		mainExService.update(map);
		Map<String, Object> userinfo = mainExService.selectUserId(loginfo);
		request.getSession().setAttribute("userId", userinfo);
		System.out.println(request.getSession().getAttribute("userId") + "test");
		return "redirect:/user/main/MyPage.do";
	}

	// 마이페이지 폼
	@RequestMapping(value = "/user/main/MyPage.do")
	public String MyPage(HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
		return "egovframework/user/MyPage";
	}

	// 회원정보 삭제
	@RequestMapping(value = "/user/main/deleteInfo.do")
	public String delete(HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
		Map<String, String[]> reqmap = request.getParameterMap();
		Map<String, Object> map = new HashMap<String, Object>();
		for (String key : reqmap.keySet()) {
			map.put(key, reqmap.get(key)[0]);
		}
		System.out.println(map);
		mainExService.delete(map);
		return "redirect:/user/main/MainEx.do";
	}

	// 로그아웃
	@RequestMapping(value = "/user/main/logout.do")
	public String logout(HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
		request.getSession().removeAttribute("userId");
		System.out.println("세션값: " + request.getSession().getAttribute("userId") + " 로그아웃 테스트입니다");
		return "redirect:/user/main/MainEx.do";
	}
}
