package com.codinghub.board.service;

public interface AnswerService {
	
	public void createAnswer(Long boardIdx, Long parentIdx, String content);
}
