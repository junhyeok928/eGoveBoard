package egovframework.user.cop.bbs.service;

import java.util.List;
import java.util.Map;

import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.rte.fdl.cmmn.exception.FdlException;

public interface BoardExService {
	Map<String, Object> selectBoardList(BoardVO boardVO);

	List<BoardVO> selectNoticeBoardList(BoardVO boardVO);

	BoardVO selectBoardDetail(BoardVO boardVO);

	void insertBoard(Board board) throws FdlException;

	void updateBoard(Board board) throws Exception;

	void deleteBoard(Board board) throws Exception;
}
