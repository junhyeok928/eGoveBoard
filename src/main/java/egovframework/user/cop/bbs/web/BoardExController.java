package egovframework.user.cop.bbs.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
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

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;

	protected String unscript(String data) {
		if (data == null || data.trim().equals("")) {
			return "";
		}

		String ret = data;

		ret = ret.replaceAll("<(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;script");
		ret = ret.replaceAll("</(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;/script");

		ret = ret.replaceAll("<(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;object");
		ret = ret.replaceAll("</(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;/object");

		ret = ret.replaceAll("<(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;applet");
		ret = ret.replaceAll("</(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;/applet");

		ret = ret.replaceAll("<(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
		ret = ret.replaceAll("</(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");

		ret = ret.replaceAll("<(F|f)(O|o)(R|r)(M|m)", "&lt;form");
		ret = ret.replaceAll("</(F|f)(O|o)(R|r)(M|m)", "&lt;form");

		return ret;
	}

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

	@RequestMapping("/insertBoardView.do")
	public String insertBoardView(@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) throws Exception {
		model.addAttribute("articleVO", boardVO);

		return "egovframework/user/cop/bbs/BBSBoardRegist";
	}

	@RequestMapping("/insertBoard.do")
	public String insertBoard(final MultipartHttpServletRequest multiRequest, 
			@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("board") BoardVO board,
			BindingResult bindingResult, ModelMap model) throws Exception {
		List<FileVO> result = null;
		String atchFileId = "";

		final List<MultipartFile> files = multiRequest.getFiles("file_1");
		if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, "BBS_", 0, "", "");
			atchFileId = fileMngService.insertFileInfs(result);
		}
		board.setAtchFileId(atchFileId);
		board.setFrstRegisterId("usertest");
		board.setNttCn(unscript(board.getNttCn()));
		boardExService.insertBoard(board);
		
		return "forward:/user/cop/bbs/selectBoardList.do";
	}
	@RequestMapping("/updateBoardView.do")
	public String updateArticleView(@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("board") BoardVO vo,
			ModelMap model) throws Exception {
		BoardVO bdvo = new BoardVO();
		model.addAttribute("articleVO", bdvo);
		return "egovframework/user/cop/bbs/BBSBoardUpdt";
	}
	
	@RequestMapping("/updateBoard.do")
	public String updateBoard(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("board") BoardVO board,
			BindingResult bindingResult, ModelMap model) throws Exception {
		String atchFileId = boardVO.getAtchFileId();
		final List<MultipartFile> files = multiRequest.getFiles("file_1");
		if (!files.isEmpty()) {
			if( atchFileId == null || "".equals(atchFileId)) {
				List<FileVO> result = fileUtil.parseFileInf(files, "BBS_", 0, atchFileId, "");
				atchFileId = fileMngService.insertFileInfs(result);
				board.setAtchFileId(atchFileId);
			} else {
				FileVO fvo = new FileVO();
				fvo.setAtchFileId(atchFileId);
				int cnt = fileMngService.getMaxFileSN(fvo);
				List<FileVO> _result = fileUtil.parseFileInf(files, "BBS_", cnt, atchFileId, "");
				fileMngService.updateFileInfs(_result);
			}
		}
		boardExService.updateBoard(board);
		
		return "forward:/user/cop/bbs/selectBoardList.do";
	}
}
