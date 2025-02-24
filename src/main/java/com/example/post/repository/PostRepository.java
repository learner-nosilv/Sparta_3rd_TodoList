package com.example.post.repository;

import com.example.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

// @repository 가 되어있는 JpaRepo 를 상속받으므로 해당 어노테이션 사용 X
public interface PostRepository extends JpaRepository<Post, Long> {
}
