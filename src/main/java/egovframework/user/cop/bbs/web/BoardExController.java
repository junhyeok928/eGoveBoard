package egovframework.user.cop.bbs.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.cmt.service.Comment;
import egovframework.com.cop.cmt.service.CommentVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.user.cop.bbs.service.BoardCommentService;
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

	@Resource(name = "BoardCommentService")
	private BoardCommentService boardCommentService;

	// XSS ??????
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

	// ????????? ?????? ??????
	@RequestMapping("/selectBoardList.do")
	public String selectBoardList(@ModelAttribute("serachVO") BoardVO boardVO, ModelMap model) throws Exception {

		// ??????????????? ????????? ????????? ????????? ???????????? ????????????
		boardVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardVO.setPageSize(propertyService.getInt("pageSize"));

		// ????????? ?????? ?????? ??????
		PaginationInfo paginationInfo = new PaginationInfo();

		// ????????? ?????? ????????? ?????? ??????
		paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
		paginationInfo.setPageSize(boardVO.getPageSize());

		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = boardExService.selectBoardList(boardVO);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));
		List<BoardVO> noticeList = boardExService.selectNoticeBoardList(boardVO);
		paginationInfo.setTotalRecordCount(totCnt);

		// jsp??? ????????? model???
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("articleVO", boardVO);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("noticeList", noticeList);
		return "egovframework/user/cop/bbs/BBSBoardList";
	}

	// ????????? ?????????
	@GetMapping("/selectBoardDetail.do")
	public String selectBoardDetail(HttpServletRequest request, @ModelAttribute BoardVO boardVO,
			@ModelAttribute CommentVO commentVO, ModelMap model) throws Exception {
		BoardVO vo = null;
		CommentVO cvo = null;
		if (RequestContextUtils.getInputFlashMap(request) == null) {
			vo = boardExService.selectBoardDetail(boardVO);
			cvo = commentVO;
		} else {
			System.out.println(RequestContextUtils.getInputFlashMap(request));
			Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
			BoardVO revo = (BoardVO) flashMap.get("boardVO");
			CommentVO covo = (CommentVO) flashMap.get("commentVO");
			vo = boardExService.selectBoardDetail(revo);
			cvo = covo;
		}

		CommentVO articleCommentVO = new CommentVO();
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		if (cvo.isModified()) {
			cvo.setCommentNo("");
			cvo.setCommentCn("");
		}
		model.addAttribute("sessionUniqId", user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));

		cvo.setWrterNm(user == null ? "" : EgovStringUtil.isNullToString(user.getName()));

//		commentVO.setSubPageUnit(propertyService.getInt("pageUnit"));
//		commentVO.setSubPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(cvo.getSubPageIndex());
		paginationInfo.setRecordCountPerPage(cvo.getSubPageUnit());
		paginationInfo.setPageSize(cvo.getSubPageSize());

		cvo.setSubFirstIndex(paginationInfo.getFirstRecordIndex());
		cvo.setSubLastIndex(paginationInfo.getLastRecordIndex());
		cvo.setSubRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = boardCommentService.selectBoardCommentList(cvo);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("type", "body"); // ?????? ????????? body import???

		model.addAttribute("articleCommentVO", articleCommentVO); // validator ??????

		cvo.setCommentCn(""); // ?????? ??? ?????? ?????? ??????

		model.addAttribute("result", vo);
		return "egovframework/user/cop/bbs/BBSBoardDetail";
	}

	// ?????? ??????
	@RequestMapping("/insertBoardComment.do")
	public String insertBoardComment(@ModelAttribute("searchVO") CommentVO commentVO,
			@ModelAttribute("comment") Comment comment, @ModelAttribute BoardVO boardVO, BindingResult bindingResult,
			ModelMap model, @RequestParam HashMap<String, String> map, RedirectAttributes re) throws Exception {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		comment.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		comment.setWrterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		comment.setWrterNm(user == null ? "" : EgovStringUtil.isNullToString(user.getName()));

		boardCommentService.insertBoardComment(comment);
		commentVO.setCommentCn("");
		commentVO.setCommentNo("");

		return "redirect:/user/cop/bbs/selectBoardDetail.do?nttId=" + boardVO.getNttId();
	}

	// ?????? ??????
	@RequestMapping("/deleteBoardComment.do")
	public String deleteBoardComment(@ModelAttribute("searchVO") CommentVO commentVO,
			@ModelAttribute("comment") Comment comment, ModelMap model, @RequestParam HashMap<String, String> map)
			throws Exception {
		@SuppressWarnings("unused")
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		boardCommentService.deleteBoardComment(commentVO);
		commentVO.setCommentCn("");
		commentVO.setCommentNo("");
		return "redirect:/user/cop/bbs/selectBoardDetail.do?nttId=" + commentVO.getNttId();
	}

	// ?????? ?????? ???
	@RequestMapping("/updateBoardCommentView.do")
	public String updateBoardCommentView(@ModelAttribute("searchVO") CommentVO commentVO, ModelMap model, @ModelAttribute("searchVO1") BoardVO boardVO)
			throws Exception {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		CommentVO articleCommentVO = new CommentVO();
		
		BoardVO vo = boardExService.selectBoardDetail(boardVO);
		
		commentVO.setWrterNm(user == null ? "" : EgovStringUtil.isNullToString(user.getName()));

		commentVO.setSubPageUnit(propertyService.getInt("pageUnit"));
		commentVO.setSubPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(commentVO.getSubPageIndex());
		paginationInfo.setRecordCountPerPage(commentVO.getSubPageUnit());
		paginationInfo.setPageSize(commentVO.getSubPageSize());

		commentVO.setSubFirstIndex(paginationInfo.getFirstRecordIndex());
		commentVO.setSubLastIndex(paginationInfo.getLastRecordIndex());
		commentVO.setSubRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = boardCommentService.selectBoardCommentList(commentVO);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("type", "body"); // body import
		model.addAttribute("articleCommentVO", articleCommentVO); // validator ??????
		articleCommentVO = boardCommentService.selectBoardCommentDetail(commentVO);
		model.addAttribute("articleCommentVO", articleCommentVO);
		model.addAttribute("result", vo);
		return "egovframework/user/cop/bbs/BBSBoardCommentList";
	}

	// ?????? ??????
	@RequestMapping("/updateBoardComment.do")
	public String updateArticleComment(@ModelAttribute("searchVO") CommentVO commentVO,
			@ModelAttribute("comment") Comment comment, BindingResult bindingResult, ModelMap model) throws Exception {

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		comment.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));

		boardCommentService.updateBoardComment(comment);

		commentVO.setCommentCn("");
		commentVO.setCommentNo("");

		return "redirect:/user/cop/bbs/selectBoardDetail.do?nttId=" + commentVO.getNttId();
	}

	// ????????? ?????? ???
	@RequestMapping("/insertBoardView.do")
	public String insertBoardView(@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) throws Exception {
		model.addAttribute("articleVO", boardVO);

		return "egovframework/user/cop/bbs/BBSBoardRegist";
	}

	// ????????? ??????
	@RequestMapping("/insertBoard.do")
	public String insertBoard(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("board") BoardVO board,
			BindingResult bindingResult, ModelMap model) throws Exception {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		List<FileVO> result = null;
		String atchFileId = "";

		final List<MultipartFile> files = multiRequest.getFiles("file_1");
		if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, "BBS_", 0, "", "");
			atchFileId = fileMngService.insertFileInfs(result);
		}
		board.setNtcrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		board.setNtcrNm((user == null || user.getName() == null) ? "" : user.getName());
		board.setAtchFileId(atchFileId);
		board.setFrstRegisterId("usertest");
		board.setNttCn(StringEscapeUtils.unescapeHtml(board.getNttCn()));
		board.setNttCn(unscript(board.getNttCn()));
		boardExService.insertBoard(board);

		return "redirect:/user/cop/bbs/selectBoardList.do";
	}

	// ????????? ?????? ???
	@RequestMapping("/updateBoardView.do")
	public String updateArticleView(@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("board") BoardVO vo,
			ModelMap model) throws Exception {
		BoardVO bdvo = new BoardVO();
		bdvo = boardExService.selectBoardDetail(boardVO);
		model.addAttribute("articleVO", bdvo);
//		StringEscapeUtils.unescapeHtml(bdvo.getNttCn()) ;
		return "egovframework/user/cop/bbs/BBSBoardUpdt";
	}

	// ????????? ??????
	@RequestMapping("/updateBoard.do")
	public String updateBoard(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("board") BoardVO board,
			BindingResult bindingResult, ModelMap model) throws Exception {
		String atchFileId = boardVO.getAtchFileId();
		final List<MultipartFile> files = multiRequest.getFiles("file_1");
		if (!files.isEmpty()) {
			if (atchFileId == null || "".equals(atchFileId)) {
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
		board.setNttCn(StringEscapeUtils.unescapeHtml(board.getNttCn()));
		board.setNttCn(unscript(board.getNttCn()));
		boardExService.updateBoard(board);

		return "redirect:/user/cop/bbs/selectBoardList.do";
	}

	// ????????? ??????
	@RequestMapping("/deleteBoard")
	public String deleteBoard(HttpServletRequest request, @ModelAttribute("searchVO") BoardVO boardVO,
			@ModelAttribute("board") Board board, ModelMap model) throws Exception {
		boardExService.deleteBoard(board);
		return "redirect:/user/cop/bbs/selectBoardList.do";
	}

	// ?????? ?????? ???
	@RequestMapping("/replyBoardView.do")
	public String addReplyBoard(@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) throws Exception {

		BoardVO articleVO = new BoardVO();
		boardVO = boardExService.selectBoardDetail(boardVO);
		model.addAttribute("result", boardVO);
		model.addAttribute("articleVO", articleVO);
		return "egovframework/user/cop/bbs/BBSBoardReply";
	}

	// ?????? ??????
	@RequestMapping("/replyBoard.do")
	public String replyBoard(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("board") BoardVO board,
			BindingResult bindingResult, ModelMap model) throws Exception {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		final List<MultipartFile> files = multiRequest.getFiles("file_1");
		String atchFileId = "";

		if (!files.isEmpty()) {
			List<FileVO> result = fileUtil.parseFileInf(files, "BBS_", 0, "", "");
			atchFileId = fileMngService.insertFileInfs(result);
		}
		board.setAtchFileId(atchFileId);
		board.setReplyAt("Y");
		board.setParnts(Long.toString(boardVO.getNttId()));
		board.setSortOrdr(boardVO.getSortOrdr());
		board.setReplyLc(Integer.toString(Integer.parseInt(boardVO.getReplyLc()) + 1));
		board.setNtcrId((user == null || user.getId() == null) ? "" : user.getId());
		board.setNtcrNm((user == null || user.getName() == null) ? "" : user.getName());

		board.setNttCn(unscript(board.getNttCn()));
		boardExService.insertBoard(board);
		return "redirect:/user/cop/bbs/selectBoardList.do";
	}

}
