package egovframework.user.cop.bbs.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardVO;

@Repository("BoardExDAO")
public class BoardExDAO extends EgovComAbstractDAO {
	@SuppressWarnings("deprecation")
	public List<?> selectBoardList(BoardVO boardVO) {
		return list("BBSBoard.selectBoardList", boardVO);
	}

	public int selectBoardListCnt(BoardVO boardVO) {
		return (Integer) selectOne("BBSBoard.selectBoardListCnt", boardVO);
	}

	public BoardVO selectBoardDetail(BoardVO boardVO) {
		return (BoardVO) selectOne("BBSBoard.selectBoardDetail", boardVO);
	}

	public int selectMaxInqireCo(BoardVO boardVO) {
		return (Integer) selectOne("BBSBoard.selectMaxInqireCo", boardVO);
	}

	public void updateInqireCo(BoardVO boardVO) {
		update("BBSBoard.updateInqireCo", boardVO);
	}
	
	public void insertBoard(Board board) {
		insert("BBSBoard.insertBoard", board);
	}
	public void updateBoard(Board board) {
		update("BBSBoard.updateBoard", board);
	}
	public void deleteBoard(Board board) {
		update("BBSBoard.deleteBoard", board);
	}
	public void replyBoard(Board board) {
		insert("BBSBoard.replyBoard", board);
	}
}
