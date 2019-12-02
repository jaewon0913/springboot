package com.springboot.webservice.web;

import com.springboot.webservice.board.dto.BoardDto;
import com.springboot.webservice.board.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class BoardController {
    private BoardService boardService;

    @GetMapping("/list")
    public String list(Model model){
        List<BoardDto> boardList = boardService.getBoardlist();

        model.addAttribute("boardList",boardList);

        return "board/list";
    }

    @GetMapping("/write")
    public String write(){
        return "board/write";
    }

    @PostMapping("/write")
    public String write(BoardDto boardDto){
        boardService.savePost(boardDto);

        return "redirect:/list";
    }

    // 디테일 이동
    @GetMapping("/board/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        return "board/detail";
    }

    // 업데이트 이동
    @GetMapping("/board/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        return "board/update";
    }

    // 업데이트
    @PutMapping("/board/edit/{no}")
    public String update(BoardDto boardDTO) {
        boardService.savePost(boardDTO);

        return "redirect:/list";
    }

    @DeleteMapping("/board/{no}")
    public String delete(@PathVariable("no") Long no) {
        boardService.deletePost(no);

        return "redirect:/list";
    }
}
