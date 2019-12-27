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

    @GetMapping("/board/list")
    public String list(Model model){
        List<BoardDto> boardList = boardService.getBoardlist();

        System.out.println(boardList.toString());

        model.addAttribute("boardList",boardList);

        return "board/list";
    }

    @PostMapping("/board/write")
    @ResponseBody
    public Long write(@RequestBody BoardDto boardDto){

        return boardService.savePost(boardDto);
    }

    // 디테일 이동
    @GetMapping("/board/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        return "board/detail";
    }

    // 업데이트
    @PutMapping("/board/update/{no}")
    @ResponseBody
    public Long update(@RequestBody BoardDto boardDto){
        return boardService.savePost(boardDto);
    }


    @DeleteMapping("/board/delete/{no}")
    @ResponseBody
    public String delete(@PathVariable("no") Long no) {
        boardService.deletePost(no);

        return "ok";
    }
}
