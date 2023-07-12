package com.sparta.gunwooklv4.service;

import com.sparta.gunwooklv4.dto.LikeResponseDto;
import com.sparta.gunwooklv4.entity.Comment;
import com.sparta.gunwooklv4.entity.Post;
import com.sparta.gunwooklv4.entity.User;
import com.sparta.gunwooklv4.jwt.JwtUtil;
import com.sparta.gunwooklv4.repository.CommentRepository;
import com.sparta.gunwooklv4.repository.LikeRepository;
import com.sparta.gunwooklv4.repository.PostRepository;
import com.sparta.gunwooklv4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final JwtUtil jwtUtil;

    // 게시글 좋아요 API
    @Transactional
    public LikeResponseDto likePost(Long id, User user) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("좋아요를 누를 게시글이 존재하지 않습니다.")
        );

    }


    // 댓글 좋아요 API
    @Transactional
    public LikeResponseDto likeComment(Long id, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("좋아요를 누를 댓글이 존재하지 않습니다.")
        );

    }
}
