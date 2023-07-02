package com.codinghub.board.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.codinghub.board.entity.Board;
import com.codinghub.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceIMPL implements BoardService {
	
    private final BoardRepository boardRepository;


	@Override
	public Optional<Board> selectBoard(Long idx) {
		
		Optional <Board> board = this.boardRepository.findById(idx);
		
		return board;
	}

	@Override
	public void createBoard(String subject, String content) {
		Board board = new Board();
		board.setContent(content);
		board.setSubject(subject);
		board.setCreateDate(LocalDateTime.now());
		this.boardRepository.save(board);
	}

	@Override
	public void delete(Long idx) {
		this.boardRepository.deleteById(idx);
	}

	@Override
	public List<Board> relationBoard(Board board) {
		List<Board> relatedboard = relatedBoard(board);
		return relatedboard;
	}

	//단어 출력 및 연관 게시판을 출력하는 메소드 시작
	public List<String> boardWord() {
	    List<Board> _board = this.boardRepository.findAll();
	    HashMap<String, Integer> boardWord = new HashMap<>();
	    for (Board board : _board) {
	        String[] getboardWord = board.getContent().replaceAll("[^\\p{L}\\p{N}]+", " ").split("\\s+");
	        for (String getword : getboardWord) {
	            boardWord.putIfAbsent(getword, 0);
	            boardWord.put(getword, boardWord.get(getword) + 1);
	        }
	    }
	    
	    List<String> relationWords = new ArrayList<>();
	    int cutsize = (int) (boardWord.size() * 0.6);

	    for (Map.Entry<String, Integer> entry : boardWord.entrySet()) {
	        if (entry.getValue() <= cutsize) {
	        	relationWords.add(entry.getKey());
	        }
	    }
	    Set<Map.Entry<String, Integer>> entries = boardWord.entrySet();
	    for (Map.Entry<String, Integer> entry : entries) {
	        String key = entry.getKey();
	        Integer value = entry.getValue();
	    }

	    return relationWords;
	}
	
	public List<Board> relatedBoard(Board board){
	    List<String> allBoardWord = boardWord();
	    String[] optionalBoard = board.getContent().replaceAll("[^\\p{L}\\p{N}]+", " ").split("\\s+");
	    List<String> relatedWords = new ArrayList<>();
	    List<Board> allBoards = this.boardRepository.findAll();
	    List<Board> relatedBoards = new ArrayList<>();
	    int count = 0;
	    for (String boardWord : optionalBoard) { 
	        for (String allWord : allBoardWord) {
	            if (boardWord.equals(allWord)) {
	                count++;
	                if (count >= 2) {
	                    relatedWords.add(boardWord);
	                    count = 0;
	                    break;
	                }
	            }
	        }
	    }
	    for (Board _board : allBoards) {
	        int wordcount = 0;
	        for (String relatedWord : relatedWords) {
	            if (_board.getContent().contains(relatedWord)) {
	                wordcount++;
	                if (wordcount >= 2) {
	                    relatedBoards.add(_board);
	                    break;
	                }
	            }
	        }
	    }
		return relatedBoards;
	}

	@Override
	public List<Board> AllBoard() {
		List<Board> board = this.boardRepository.findAll();
		return board;
	}

	
}
