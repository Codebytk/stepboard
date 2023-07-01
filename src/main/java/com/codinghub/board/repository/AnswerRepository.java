package com.codinghub.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codinghub.board.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
