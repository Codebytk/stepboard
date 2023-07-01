package com.codinghub.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codinghub.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
