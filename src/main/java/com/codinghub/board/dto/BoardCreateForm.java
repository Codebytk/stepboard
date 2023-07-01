package com.codinghub.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardCreateForm {

	@NotBlank(message = "내용은 필수 입력사항입니다.")
	String content;
	@NotBlank(message = "제목은 필수 입력사항입니다.")
	String Subject;
}

