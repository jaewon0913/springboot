package com.springboot.webservice.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class WebController {

    @GetMapping("/")
    public String main(){

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
