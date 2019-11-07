package com.springboot.webservice.web;

import com.springboot.webservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class WebController {

    private PostsService postsService;

//    @GetMapping("/")
//    public String main(Model model){
//        model.addAttribute("posts",postsService.findAllDesc());
//        return "main";
//    }

    @GetMapping("/")
    public String main_blog(Model model){
        model.addAttribute("message","안녕하세요");
        return "index";
    }

    @GetMapping("/final_pdf")
    public String final_pdf_view(){
        return "pdf/final_pdf";
    }
    @GetMapping("/semi_pdf")
    public String semi_pdf_view(){
        return "pdf/semi_pdf";
    }
    @GetMapping("/portfolio_pdf")
    public String portfolio_pdf_view(){
        return "pdf/portfolio_pdf";
    }
}
