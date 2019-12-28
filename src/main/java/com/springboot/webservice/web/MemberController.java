package com.springboot.webservice.web;

import com.springboot.webservice.member.dto.MemberDto;
import com.springboot.webservice.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class MemberController {

    private MemberService memberService;

    //  회원가입 처리
    @PostMapping("/user/insert")
    @ResponseBody
    public Long userInsert(@RequestBody MemberDto memberDto) {

        return memberService.joinUser(memberDto);
    }

    //  로그인 성공시
    @GetMapping("/user/login/result")
    public String dispLoginResult() {

        return "redirect:/";
    }

    //  접근 거부 페이지
    @GetMapping("/user/error")
    public String dispDenied() {
        return "redirect:/";
    }

    //  아이디 중복 체크
    @GetMapping("/user/checkid/{checkid}")
    @ResponseBody
    public String checkId(@PathVariable("checkid") String checkid){

        MemberDto memberDto = memberService.getUserid(checkid);

        if(memberDto.getUserid() == null){
            return "ok";
        }

        return "no";
    }
}
