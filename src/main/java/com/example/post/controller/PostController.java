package com.example.post.controller;

import com.example.post.dto.PostRequestDto;
import com.example.post.dto.PostSimpleResponseDto;
import com.example.post.entity.Post;
import com.example.post.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class PostController {
    private final PostService postService;

    @PostMapping("/posts")
    public PostSimpleResponseDto save(@RequestBody PostRequestDto postRequestDto) {
        return postService.save(postRequestDto);
    }

    @GetMapping("/posts")
    public List<PostSimpleResponseDto> findAll() {
        return postService.findAll();
    }

    @GetMapping("/posts/{postId}")
    public PostSimpleResponseDto findAll(@PathVariable Long postId) {
        return postService.findById(postId);
    }
}
