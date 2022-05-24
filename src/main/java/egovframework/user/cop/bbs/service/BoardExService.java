package egovframework.user.cop.bbs.service;

import java.util.Map;

import egovframework.com.cop.bbs.service.BoardVO;

public interface BoardExService {
	Map<String, Object> selectBoardList(BoardVO boardVO);

	BoardVO selectBoardDetail(BoardVO boardVO);
}
