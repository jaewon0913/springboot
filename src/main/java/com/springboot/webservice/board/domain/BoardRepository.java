package com.springboot.webservice.board.domain;

import com.springboot.webservice.board.Entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    Optional<BoardEntity> findById(Long id);
}
