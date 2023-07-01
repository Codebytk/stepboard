package com.codinghub.board;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codinghub.board.entity.Board;
import com.codinghub.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@SpringBootTest
class StepboardApplicationTests {

	@Autowired
	BoardRepository boardRepository;
	
	@Test
	void contextLoads() {
		Board board;
		for(int i = 0; i<100; i++) {
			board =  new Board();
			board.setContent("안녕 나는 이태경이고 오늘은 자바 유틸에 대한 공부를 하는 중이야 이건 테스트 용이고 이 단어들은 상위 40%내로 들어와야 할테니깐 들어오지 않겠지 그렇게 될거라고 생각해");
			board.setSubject("테스트용 제목"+i);
			board.setCreateDate(LocalDateTime.now());
			this.boardRepository.save(board);
		}
	
	}

}
