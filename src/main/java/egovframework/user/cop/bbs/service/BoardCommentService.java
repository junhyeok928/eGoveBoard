package egovframework.user.cop.bbs.service;

import egovframework.com.cop.cmt.service.Comment;
import egovframework.rte.fdl.cmmn.exception.FdlException;

public interface BoardCommentService {
	void insertBoardComment(Comment comment) throws FdlException;
}
