package egovframework.user.cop.bbs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cop.cmt.service.Comment;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.user.cop.bbs.service.BoardCommentService;

@Service("BoardCommentService")
public class BoardCommentServiceImpl extends EgovAbstractServiceImpl implements BoardCommentService{
	
	@Resource(name = "BoardCommentDAO")
	private BoardCommentDAO boardCommentDAO;
	
	@Resource(name = "egovAnswerNoGnrService")
    private EgovIdGnrService egovAnswerNoGnrService;
	
	@Override
	public void insertBoardComment(Comment comment) throws FdlException{
		comment.setCommentNo(egovAnswerNoGnrService.getNextLongId() + "");
		boardCommentDAO.insertBoardComment(comment);
	}
	
}
