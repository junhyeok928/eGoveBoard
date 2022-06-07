package egovframework.user.cop.bbs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.user.cop.bbs.service.BoardExService;

@Service("BoardExService")
public class BoardExServiceImpl extends EgovAbstractServiceImpl implements BoardExService {
	@Resource(name = "BoardExDAO")
	private BoardExDAO boardExDAO;

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	@Resource(name = "egovNttIdGnrService")
	private EgovIdGnrService nttIdgenService;

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;

	@Override
	public Map<String, Object> selectBoardList(BoardVO boardVO) {
		List<?> list = boardExDAO.selectBoardList(boardVO);
		int cnt = boardExDAO.selectBoardListCnt(boardVO);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resultList", list);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	@Override
	public List<BoardVO> selectNoticeBoardList(BoardVO boardVO) {
		return boardExDAO.selectNoticeBoardList(boardVO);
	}

	@Override
	public BoardVO selectBoardDetail(BoardVO boardVO) {
		int iniqireCo = boardExDAO.selectMaxInqireCo(boardVO);

		boardVO.setInqireCo(iniqireCo);
		boardExDAO.updateInqireCo(boardVO);

		return boardExDAO.selectBoardDetail(boardVO);
	}

	@Override
	public void insertBoard(Board board) throws FdlException {
		if ("Y".equals(board.getReplyAt())) {
			board.setNttId(nttIdgenService.getNextIntegerId());
			boardExDAO.replyBoard(board);
		} else {
			board.setParnts("0");
			board.setReplyLc("0");
			board.setReplyAt("N");
			board.setNttId(nttIdgenService.getNextIntegerId());

			boardExDAO.insertBoard(board);
		}

	}

	@Override
	public void updateBoard(Board board) {
		boardExDAO.updateBoard(board);
	}

	@Override
	public void deleteBoard(Board board) throws Exception {
		FileVO fvo = new FileVO();

		fvo.setAtchFileId(board.getAtchFileId());

		board.setNttSj("이 글은 작성자에 의해서 삭제되었습니다.");

		boardExDAO.deleteBoard(board);

		if (!"".equals(fvo.getAtchFileId()) || fvo.getAtchFileId() != null) {
			fileService.deleteAllFileInf(fvo);
		}
	}

}
