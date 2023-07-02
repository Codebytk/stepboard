package com.codinghub.board.service;

import java.util.List;
import java.util.Optional;

import com.codinghub.board.dto.BoardCreateForm;
import com.codinghub.board.entity.Board;

public interface BoardService {

	public List<Board> AllBoard();
	
	public Optional<Board> selectBoard(Long idx);
	
	public void createBoard(String subject, String content);
	
	public void delete(Long idx);
	
	public List<Board> relationBoard(Board board);
	
}
