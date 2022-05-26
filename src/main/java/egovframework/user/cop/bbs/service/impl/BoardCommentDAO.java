package egovframework.user.cop.bbs.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.cmt.service.Comment;

@Repository("BoardCommentDAO")
public class BoardCommentDAO extends EgovComAbstractDAO{
	public void insertBoardComment(Comment comment) {
		insert("BoardComment.insertBoardComment", comment);
	}
}
