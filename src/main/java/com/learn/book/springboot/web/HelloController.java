package com.learn.book.springboot.web;

import com.learn.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON 반환하는 컨트롤러로 설정 ( a.k.a @ResponseBody)
public class HelloController {

    @GetMapping("/hello") // httpMethod Get의 요청을 받는 API -> @RequestMapping(method=RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
}
