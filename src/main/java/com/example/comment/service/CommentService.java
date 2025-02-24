package com.example.comment.service;

import com.example.comment.dto.CommentRequestDto;
import com.example.comment.dto.CommentResponseDto;
import com.example.comment.entity.Comment;
import com.example.comment.repository.CommentRepository;
import com.example.post.entity.Post;
import com.example.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentResponseDto save(Long postId, CommentRequestDto commentRequestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalStateException("Post(게시글)이 없습니다.")
        );
        Comment comment = new Comment(post, commentRequestDto.getContent());

        Comment saved = commentRepository.save(comment);

        return new CommentResponseDto(saved.getId(), post.getId(), saved.getContent());
    }
}
