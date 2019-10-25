package com.springboot.webservice.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//  @RestController 는 @ResponseBody 를 모든 메소드에서 적용해준다.
@RestController
public class WebRestController {

    @GetMapping("/hello")
    public String hello(){
        return "HelloWorld";
    }
}
