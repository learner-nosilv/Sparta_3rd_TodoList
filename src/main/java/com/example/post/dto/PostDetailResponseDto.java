package com.example.post.dto;

import com.example.comment.dto.CommentSimpleResponseDto;
import com.example.post.entity.Post;
import lombok.Getter;

import java.util.List;

@Getter
public class PostDetailResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final List<CommentSimpleResponseDto> comments;

    public PostDetailResponseDto(Long id, String title, String content, List<CommentSimpleResponseDto> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.comments = comments;
    }
}
