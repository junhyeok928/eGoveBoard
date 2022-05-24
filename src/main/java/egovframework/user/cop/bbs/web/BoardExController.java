package egovframework.user.cop.bbs.web;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.user.cop.bbs.service.BoardExService;

@Controller
@RequestMapping(value = "/user/cop/bbs")
public class BoardExController {

	@Resource(name = "BoardExService")
	private BoardExService boardExService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	@RequestMapping("/selectBoardList.do")
	public String selectBoardList(@ModelAttribute("serachVO") BoardVO boardVO, ModelMap model) throws Exception {

		// 프로퍼티의 페이지 유닛와 페이지 사이즈를 가져온다
		boardVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardVO.setPageSize(propertyService.getInt("pageSize"));

		// 페이징 처리 객체 생성
		PaginationInfo paginationInfo = new PaginationInfo();

		// 페이징 처리 객체의 값을 지정
		paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
		paginationInfo.setPageSize(boardVO.getPageSize());

		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = boardExService.selectBoardList(boardVO);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		// jsp로 넘어갈 model값
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("articleVO", boardVO);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/user/cop/bbs/BBSBoardList";
	}

	@RequestMapping("/selectBoardDetail.do")
	public String selectBoardDetail(@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) throws Exception {
		BoardVO vo = boardExService.selectBoardDetail(boardVO);
		model.addAttribute("result", vo);
		return "egovframework/user/cop/bbs/BBSBoardDetail";
	}
}
