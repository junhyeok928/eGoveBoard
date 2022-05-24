package egovframework.user.cop.bbs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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
	public BoardVO selectBoardDetail(BoardVO boardVO) {
		int iniqireCo = boardExDAO.selectMaxInqireCo(boardVO);

		boardVO.setInqireCo(iniqireCo);
		boardExDAO.updateInqireCo(boardVO);

		return boardExDAO.selectBoardDetail(boardVO);
	}
	
	@Override
	public void insertBoard(Board board) throws FdlException{
//		if ("Y".equals(board.getReplyAt())) {
//			board.setNttId(nttIdgenService.getNextIntegerId());
//			BoardExDAO.replyArticle(board);
//		}
		board.setParnts("0");
		board.setReplyLc("0");
		board.setReplyAt("N");
		board.setNttId(nttIdgenService.getNextIntegerId());
		
		boardExDAO.insertBoard(board);
	}
	
	@Override
	public void updateBoard(Board board) {
		boardExDAO.updateBoard(board);
	}

}
