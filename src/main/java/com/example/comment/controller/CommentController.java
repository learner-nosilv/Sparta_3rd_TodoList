package com.example.comment.controller;

import com.example.comment.dto.CommentRequestDto;
import com.example.comment.dto.CommentResponseDto;
import com.example.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public CommentResponseDto save(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.save(postId, commentRequestDto);
    }
}
