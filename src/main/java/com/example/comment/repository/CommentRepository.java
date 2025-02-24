package com.example.comment.repository;

import com.example.comment.entity.Comment;
import com.example.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}