package com.sparta.gunwooklv4.service;

import com.sparta.gunwooklv4.dto.CommentRequestDto;
import com.sparta.gunwooklv4.dto.CommentResponseDto;
import com.sparta.gunwooklv4.dto.StatusResult;
import com.sparta.gunwooklv4.entity.Comment;
import com.sparta.gunwooklv4.entity.Post;
import com.sparta.gunwooklv4.entity.User;
import com.sparta.gunwooklv4.entity.UserRoleEnum;
import com.sparta.gunwooklv4.jwt.JwtUtil;
import com.sparta.gunwooklv4.repository.CommentRepository;
import com.sparta.gunwooklv4.repository.PostRepository;
import com.sparta.gunwooklv4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final JwtUtil jwtUtil;

    // 댓글 작성 API
    @Transactional
    public CommentResponseDto createComment(Long id, CommentRequestDto commentRequestDto, User user) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글을 작성할 게시글이 존재하지 않습니다.")
        );

        Comment comment = new Comment(commentRequestDto, post, user);
        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }


    // 댓글 수정 API
    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto commentRequestDto, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("수정하려는 댓글이 존재하지 않습니다.")
        );

        // 작성자 확인, 권한 체크
        if(comment.getUser().getUsername().equals(user.getUsername()) || user.getRole().equals(UserRoleEnum.ADMIN)){
            comment.update(commentRequestDto);
            return new CommentResponseDto(comment);
        } else {
            throw new IllegalArgumentException("댓글 작성자만 수정이 가능합니다.");
        }
    }


    // 댓글 삭제 API
    public StatusResult deleteComment(Long id, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("삭제하려는 댓글이 존재하지 않습니다.")
        );

        if(comment.getUser().getUsername().equals(user.getUsername()) || user.getRole().equals(UserRoleEnum.ADMIN)){
            commentRepository.delete(comment);
            return new StatusResult("삭제가 완료되었습니다.", 200);
        } else {
            return new StatusResult("댓글 작성자만 삭제가 가능합니다.", 400);
        }
    }


}
