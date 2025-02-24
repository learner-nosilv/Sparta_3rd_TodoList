package com.example.post.dto;

import lombok.Getter;

@Getter
// [반환하는 것] id, 제목, 내용
public class PostSimpleResponseDto {
    private final Long id;
    private final String title;
    private final String content;

    public PostSimpleResponseDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
