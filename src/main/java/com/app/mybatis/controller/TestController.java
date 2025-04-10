package com.app.mybatis.controller;

import com.app.mybatis.domain.PostVO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
// frontController 역할
@RequestMapping("/test/*")
public class TestController {

//    /test/ex
//    Get 방식
    @GetMapping("ex")
//    Post 방식
    @PostMapping
    public String ex(){
        return "first";
//        string으로 리턴,
//        view resolve가 문자열을 들고온다.(templates경로도 가져온다)
    }

    @GetMapping("/ex01")
    public void ex01(HttpServletResponse response) throws IOException {
        response.getWriter().write("hello,World!");
    }
}
