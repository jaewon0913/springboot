package com.springboot.webservice.board.service;

import com.springboot.webservice.board.domain.BoardRepository;
import com.springboot.webservice.board.domain.entity.BoardEntity;
import com.springboot.webservice.board.dto.BoardDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BoardService {
    private BoardRepository boardRepository;

    //  페이징 추가로 기능 사용X
//    @Transactional
//    public List<BoardDto> getBoardlist() {
//        List<BoardEntity> boardEntities = boardRepository.findAll();
//        List<BoardDto> boardDtoList = new ArrayList<>();
//
//        for ( BoardEntity boardEntity : boardEntities) {
//            BoardDto boardDTO = BoardDto.builder()
//                    .id(boardEntity.getId())
//                    .title(boardEntity.getTitle())
//                    .content(boardEntity.getContent())
//                    .writer(boardEntity.getWriter())
//                    .modifiedDate(boardEntity.getModifiedDate())
//                    .build();
//
//            boardDtoList.add(boardDTO);
//        }
//
//        return boardDtoList;
//    }

    @Transactional
    public BoardDto getPost(Long num) {
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(num);
        BoardEntity boardEntity = boardEntityWrapper.get();

        BoardDto boardDTO = BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .writer(boardEntity.getWriter())
                .modifiedDate(boardEntity.getModifiedDate())
                .build();

        return boardDTO;
    }

    @Transactional
    public Long savePost(BoardDto boardDto) {

        System.out.println(boardDto.getId());
        System.out.println(boardDto.getTitle());
        System.out.println(boardDto.getContent());
        System.out.println(boardDto.getWriter());
        System.out.println(boardDto.toString());

        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public void deletePost(Long num) {
        boardRepository.deleteById(num);
    }

    //  페이징 List
    public Page<BoardEntity> getBoardList(Pageable pageable){
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, new Sort(Sort.Direction.DESC, "id"));

        return boardRepository.findAll(pageable);
    }
}
