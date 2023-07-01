package com.codinghub.board.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codinghub.board.entity.Answer;
import com.codinghub.board.entity.Board;
import com.codinghub.board.repository.AnswerRepository;
import com.codinghub.board.repository.BoardRepository;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerServiceImpl implements AnswerService {

	private final AnswerRepository answerRepository;
	private final BoardRepository boardRepository;
	
	@Override
	public void createAnswer(Long boardIdx, Long parentIdx, String content){
		 Optional<Board> board = this.boardRepository.findById(boardIdx);
		 Answer answer = new Answer();
	     answer.setContent(content);
	     answer.setBoard(board.get()); // boardIdx를 매개변수로 받아 Board 생성자에 전달합니다 (사용 중인 Board 클래스에 맞게 변경해 주세요)
	     if (parentIdx != null) {
	    	 try {
	    		 Answer parentAnswer = this.answerRepository.findById(parentIdx).orElseThrow(() -> new NotFoundException("부모 답변을 찾을 수 없습니다."));
	    		 answer.setParent(parentAnswer);
	    	 }catch(NotFoundException e) {
	    		 e.printStackTrace();
	    	 }
	     }
	     this.answerRepository.save(answer);
	}

}
