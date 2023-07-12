package com.sparta.gunwooklv4.controller;

import com.sparta.gunwooklv4.dto.CommentRequestDto;
import com.sparta.gunwooklv4.dto.CommentResponseDto;
import com.sparta.gunwooklv4.dto.StatusResult;
import com.sparta.gunwooklv4.security.UserDetailsImpl;
import com.sparta.gunwooklv4.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성 APi
    @PostMapping("/comment/{id}")
    public CommentResponseDto createComment(@PathVariable Long id,
                                            @RequestBody CommentRequestDto commentRequestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createComment(id, commentRequestDto, userDetails.getUser());
    }

    // 댓글 수정 API
    @PutMapping("/comment/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id,
                                            @RequestBody CommentRequestDto commentRequestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.updateComment(id, commentRequestDto, userDetails.getUser());
    }

    // 댓글 삭제 API
    @DeleteMapping("/comment/{id}")
    public StatusResult deleteComment(@PathVariable Long id,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.deleteComment(id, userDetails.getUser());
    }

}
