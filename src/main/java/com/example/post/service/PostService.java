package com.example.post.service;

import com.example.post.dto.PostRequestDto;
import com.example.post.dto.PostSimpleResponseDto;
import com.example.post.entity.Post;
import com.example.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;        // 포스트
    private final CommentRepository commentRepository;  // 댓글

    @Transactional
    public PostSimpleResponseDto save(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto.getTitle(), postRequestDto.getContent());   // 포스트 엔티티 생성
        Post saved = postRepository.save(post);                                         // 포스트 엔티티 -> 레포층에 저장
        return new PostSimpleResponseDto(saved.getId(), saved.getTitle(), saved.getContent());
    }


    // 다건조회 (댓글포함 x -> Simple Dto 사용)
    @Transactional  // (readOnly = true) 정의되어 있지 않다고 오류남
    public List<PostSimpleResponseDto> findAll() {
        List<Post> posts = postRepository.findAll();                            // 엔티티 형태
        List<PostSimpleResponseDto> postSimpleResponseDtos = new ArrayList<>(); // DTO 형태
        for (Post post : posts) {
            postSimpleResponseDtos.add(new PostSimpleResponseDto(
                    post.getId(),
                    post.getTitle(),
                    post.getContent()
            ));
        }
        return postSimpleResponseDtos;      // 응답 Dto 형태로 리턴
    }

    // 단건조회 (댓글포함O -> Detail Dto 사용)
    @Transactional
    public PostSimpleResponseDto findById(Long postid) {    // 댓글과 겹칠 수 있으므로
        Post post = postRepository.findById(postid).orElseThrow(
                () -> new IllegalStateException("해당 Post(게시글)가 없습니다.")
        );

        List<Comment> comments = commentRepository.findByPost(post);
        List<CommmentSimpleResponseDto> commentDtos = new ArrayList<>();


    }
}
