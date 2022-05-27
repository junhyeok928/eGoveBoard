package egovframework.user.cop.bbs.service;

import java.util.Map;

import egovframework.com.cop.cmt.service.Comment;
import egovframework.com.cop.cmt.service.CommentVO;
import egovframework.rte.fdl.cmmn.exception.FdlException;

public interface BoardCommentService {
	Map<String, Object> selectBoardCommentList(CommentVO commentVO);

	void insertBoardComment(Comment comment) throws FdlException;

	void deleteBoardComment(CommentVO commentVO);

	void updateBoardComment(Comment comment);
	
	CommentVO selectBoardCommentDetail(CommentVO commentVO);
}
