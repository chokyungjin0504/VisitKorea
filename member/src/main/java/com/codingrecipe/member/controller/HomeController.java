package com.codingrecipe.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    //기본 페이지 요청 메서드
    @GetMapping("/")  //이런 기본주소 요청이 오면
    public String index() {
        //이 부분이 호출됨 -> Spring 기반이기 때문에 이런 기본 코드로 가능
        return "index"; //인덱스라는 HTML 파일을 return => templetes 폴더의 index.html을 찾아감.
    }
}
