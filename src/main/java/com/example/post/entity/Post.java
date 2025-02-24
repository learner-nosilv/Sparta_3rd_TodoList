package com.example.post.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Post {

    // Id는 자동 생성
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;       // 글 제목
    private String content;     // 글 내용

    public Post(String title, String content){
        this.title = title;
        this.content = content;
    }
}
