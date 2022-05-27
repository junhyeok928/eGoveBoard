package egovframework.user.cop.bbs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cop.cmt.service.Comment;
import egovframework.com.cop.cmt.service.CommentVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.user.cop.bbs.service.BoardCommentService;

@Service("BoardCommentService")
public class BoardCommentServiceImpl extends EgovAbstractServiceImpl implements BoardCommentService {

	@Resource(name = "BoardCommentDAO")
	private BoardCommentDAO boardCommentDAO;

	@Resource(name = "egovAnswerNoGnrService")
	private EgovIdGnrService egovAnswerNoGnrService;

	@Override
	public Map<String, Object> selectBoardCommentList(CommentVO commentVO) {
		List<?> result = boardCommentDAO.selectBoardCommentList(commentVO);
		int cnt = boardCommentDAO.selectBoardCommentListCnt(commentVO);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	@Override
	public void insertBoardComment(Comment comment) throws FdlException {
		comment.setCommentNo(egovAnswerNoGnrService.getNextLongId() + "");
		boardCommentDAO.insertBoardComment(comment);
	}

	@Override
	public void deleteBoardComment(CommentVO commentVO) {
		boardCommentDAO.deleteBoardComment(commentVO);
	}
	
	@Override
	public void updateBoardComment(Comment comment) {
		boardCommentDAO.updateBoardComment(comment);
	}
	
	@Override
	public CommentVO selectBoardCommentDetail(CommentVO commentVO) {
		return boardCommentDAO.selectBoardCommentDetail(commentVO);
	}

}
