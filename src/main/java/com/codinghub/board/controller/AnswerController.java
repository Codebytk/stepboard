package com.codinghub.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codinghub.board.service.AnswerService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
	
	@Autowired
	private final AnswerService answerService;

	@PostMapping(value="/create/{idx}")
	public String createanswer(@PathVariable("idx") Long boardIdx, @RequestParam("answer") String answerContent) {
		this.answerService.createAnswer(boardIdx, null, answerContent);
		System.out.println("parentIdx null");
		 return "redirect:/board/detail/" + boardIdx;
	}
	@PostMapping(value="/create/{b_idx}/{re_idx}")
	public String createreanswer(@PathVariable("b_idx") Long boardIdx, @PathVariable("re_idx") Long parentIdx, @RequestParam("answer") String answerContent) {
		this.answerService.createAnswer(boardIdx, parentIdx, answerContent);
		System.out.println(parentIdx);
		 return "redirect:/board/detail/" + boardIdx;
	}
}
