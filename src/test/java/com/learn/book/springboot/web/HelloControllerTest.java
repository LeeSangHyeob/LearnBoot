package com.learn.book.springboot.web;

import com.learn.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class) // 스프링부트 테스트와 JUnit 를 연결
@WebMvcTest(controllers=HelloController.class,
excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)}) // WEB에 집중할 수 있는 어노테이션
public class HelloControllerTest {

    @Autowired // BEAN 주입
    private MockMvc mvc; // WEB API 테스트할 때 사용

    @Test
    @WithMockUser(roles="USER")
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // /hello 주소로 HTTP GET 요청
                .andExpect(status().isOk()) // mvc.perform의 결과 검증 - isOk() = 200
                .andExpect(content().string(hello)); // 본문의 내용 검증
    }

    @Test
    @WithMockUser(roles="USER")
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto").param("name", name).param("amount", String.valueOf(amount))) // API 테스트 요청 파라미터 설정(Only String)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // jsonPath $ 기준으로 필드명을 명시, 필드별로 검증
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}