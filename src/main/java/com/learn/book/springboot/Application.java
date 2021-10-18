package com.learn.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication // 스프링 부트의 자동설정, Bean 읽기와 생성을 모두 자동으로 설정하게 되는 최상단 메인 클래스 설정
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args); // 내장 WAS를 실행 -> tomcat 불필요하며 외장 WAS 사용시 설정을 맞춰야 하는 번거로움이 없어짐\
    }
}