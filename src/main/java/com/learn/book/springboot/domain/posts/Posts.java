package com.learn.book.springboot.domain.posts;

import com.learn.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본 생성자 자동추가
@Entity // 테이블과 링크될 클래스 선언, 카멜케이스 매칭 (ex. SalesManager.java -> sales_manager table
public class Posts extends BaseTimeEntity {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙, IDENTITY 옵션이 추가되어야만 auto_increment
    private Long id;

    @Column(length = 500, nullable = false) // 컬럼 선언, 기본값 외 필요한 옵션 변경시 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 빌더 패턴 클래스 생성
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update (String title, String content) {
        this.title = title;
        this.content = content;
    }
}