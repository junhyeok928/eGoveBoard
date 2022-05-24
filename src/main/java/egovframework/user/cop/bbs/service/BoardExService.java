package egovframework.user.cop.bbs.service;

import java.util.Map;

import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.rte.fdl.cmmn.exception.FdlException;

public interface BoardExService {
	Map<String, Object> selectBoardList(BoardVO boardVO);

	BoardVO selectBoardDetail(BoardVO boardVO);
	
	void insertBoard(Board board) throws FdlException;
	
	void updateBoard(Board board) throws Exception;
}
