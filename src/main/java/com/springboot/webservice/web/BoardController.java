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

    // 모달창 사용으로 사용X
//    @GetMapping("/write")
//    public String write(){
//        return "board/write";
//    }

    @PostMapping("/write")
    @ResponseBody
    public Long write(@RequestBody BoardDto boardDto){
        //boardService.savePost(boardDto);

        return boardService.savePost(boardDto);
        //return "redirect:/list";
    }

    // 디테일 이동
    @GetMapping("/board/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        return "board/detail";
    }

    // 업데이트 이동(모달창 사용으로 사용X)
//    @GetMapping("/board/edit/{no}")
//    public String edit(@PathVariable("no") Long no, Model model) {
//        BoardDto boardDTO = boardService.getPost(no);
//
//        model.addAttribute("boardDto", boardDTO);
//        return "board/update";
//    }

    // 업데이트
    @PutMapping("/board/update/{no}")
    @ResponseBody
    public Long update(@RequestBody BoardDto boardDto){
        return boardService.savePost(boardDto);
    }

//    @PutMapping("/board/edit/{no}")
//    public String update2(BoardDto boardDTO) {
//        System.out.println(boardDTO.toString());
//        boardService.savePost(boardDTO);
//
//        return "redirect:/list";
//    }

    @DeleteMapping("/board/delete/{no}")
    @ResponseBody
    public String delete(@PathVariable("no") Long no) {
        boardService.deletePost(no);

        return "ok";
    }

}
