package egovframework.user.cop.bbs.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.cmt.service.Comment;
import egovframework.com.cop.cmt.service.CommentVO;

@Repository("BoardCommentDAO")
public class BoardCommentDAO extends EgovComAbstractDAO {

	@SuppressWarnings("deprecation")
	public List<?> selectBoardCommentList(CommentVO commentVO) {
		return list("BoardComment.selectBoardCommentList", commentVO);
	}

	public int selectBoardCommentListCnt(CommentVO commentVO) {
		return (Integer) selectOne("BoardComment.selectBoardCommentListCnt", commentVO);
	}

	public void insertBoardComment(Comment comment) {
		insert("BoardComment.insertBoardComment", comment);
	}

	public void deleteBoardComment(CommentVO commentVO) {
		update("BoardComment.deleteBoardComment", commentVO);
	}

	public void updateBoardComment(Comment comment) {
		update("BoardComment.updateBoardComment", comment);
	}

	public CommentVO selectBoardCommentDetail(CommentVO commentVO) {
		return (CommentVO) selectOne("BoardComment.selectBoardCommentDetail", commentVO);
	}
}
