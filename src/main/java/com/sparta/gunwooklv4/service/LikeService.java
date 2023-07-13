package com.sparta.gunwooklv4.service;

import com.sparta.gunwooklv4.dto.StatusResult;
import com.sparta.gunwooklv4.entity.Comment;
import com.sparta.gunwooklv4.entity.Like;
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

import java.util.Optional;

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
    public StatusResult likePost(Long id, User user) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("좋아요를 누를 게시글이 존재하지 않습니다.")
        );

        Optional<Like> checkUserAndPost = likeRepository.findByUserAndPost(user, post);

        if(checkUserAndPost.isPresent()){
             return new StatusResult("이미 해당 게시글에 좋아요를 누르셨습니다.", 400);
        } else {
            likeRepository.save(new Like(user, post));
            post.increseLikeCount();
            return new StatusResult("해당 게시글을 좋아합니다.", 200);
        }

    }

    // 게시글 좋아요 취소 API
    @Transactional
    public StatusResult deleteLikePost(Long id, User user) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("좋아요를 취소할 게시글이 존재하지 않습니다.")
        );

        Optional<Like> checkUserAndPost = likeRepository.findByUserAndPost(user, post);

        if(!checkUserAndPost.isPresent()){
            return new StatusResult("해당 게시글에 취소할 좋아요가 존재하지 않습니다.", 400);
        } else {
            likeRepository.delete(checkUserAndPost.get());
            post.decreseLikeCount();
            return new StatusResult("해당 게시글에 좋아요가 취소되었습니다.", 200);
        }

    }

    // 댓글 좋아요 API
    @Transactional
    public StatusResult likeComment(Long id, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("좋아요를 누를 댓글이 존재하지 않습니다.")
        );

        Optional<Like> checkUserAndComment = likeRepository.findByUserAndComment(user, comment);

        if(checkUserAndComment.isPresent()){
            return new StatusResult("이미 해당 댓글에 좋아요를 누르셨습니다.", 400);
        } else {
            likeRepository.save(new Like(user, comment));
            comment.increseLikeCount();
            return new StatusResult("해당 댓글을 좋아합니다.", 200);
        }

    }


    // 댓글 좋아요 취소 API
    @Transactional
    public StatusResult deleteLikeComment(Long id, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("좋아요를 취소할 댓글이 존재하지 않습니다.")
        );

        Optional<Like> checkUserAndComment = likeRepository.findByUserAndComment(user, comment);

        if(!checkUserAndComment.isPresent()){
            return new StatusResult("해당 게시글에 취소할 좋아요가 존재하지 않습니다.", 400);

        } else {
            likeRepository.delete(checkUserAndComment.get());
            comment.decreseLikeCount();
            return new StatusResult("해당 댓글에 좋아요가 취소되었습니다.", 200);

        }

    }
}
