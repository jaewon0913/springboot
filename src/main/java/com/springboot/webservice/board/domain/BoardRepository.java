package com.springboot.webservice.board.domain;

import com.springboot.webservice.board.Entity.BoardEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    Optional<BoardEntity> findById(Long id);

//    @Modifying
//    @Query(" update board set title= :title, content= :content, modifiedDate = 'now()' where id =:id  ")
//    int updateBoard(@Param("title") String title, @Param("content") String content, @Param("id") Long id);
}
