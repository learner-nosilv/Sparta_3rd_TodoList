package com.example.post.dto;

import lombok.Getter;

@Getter
// [받는 것] id 없이 제목과 내용만 받음
public class PostRequestDto {
    private String title;
    private String content;
}
