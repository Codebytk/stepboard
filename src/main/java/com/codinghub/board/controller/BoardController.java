package com.codinghub.board.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.codinghub.board.dto.BoardCreateForm;
import com.codinghub.board.entity.Board;
import com.codinghub.board.service.BoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	
	@GetMapping("/")
	private String mainpage(Model model) {
		List<Board> board = this.boardService.AllBoard();
		model.addAttribute("board",board);
		return "boardList";
	}
	@GetMapping("/board/create")
	private String createForm(BoardCreateForm boardCreateForm) {
		return "createForm";
	}
	@PostMapping("/board/create")
	private String createForm(@Valid BoardCreateForm boardCreateForm,BindingResult bindingresult) {
		if(bindingresult.hasErrors()) {
			return "createForm";
		}
		try {
			this.boardService.createBoard(boardCreateForm.getSubject(), boardCreateForm.getContent());
		}catch (Exception e) {
			e.printStackTrace();
			return "createForm";
		}
		return "redirect:/";
	}
	
	@GetMapping(value="/board/detail/{idx}")
	private String boardDetail(@PathVariable Long idx,Model model) {
		Optional<Board> _board = this.boardService.selectBoard(idx);
		model.addAttribute("board", _board.get());
		List<Board> board = this.boardService.relationBoard(_board.get());
		model.addAttribute("relatedBoard", board);
		return "boardDetail";
	}
}