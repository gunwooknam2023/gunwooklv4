package com.sparta.gunwooklv4.controller;

import com.sparta.gunwooklv4.dto.LikeResponseDto;
import com.sparta.gunwooklv4.dto.StatusResult;
import com.sparta.gunwooklv4.security.UserDetailsImpl;
import com.sparta.gunwooklv4.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LikeController {
    private final LikeService likeService;

    // 게시글 좋아요 API
    @PostMapping("/like/post/{id}")
    public StatusResult likePost(@PathVariable Long id,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likeService.likePost(id, userDetails.getUser());
    }

    // 게시글 좋아요 취소 API
    @DeleteMapping("/like/post/{id}")
    public StatusResult deleteLikePost(@PathVariable Long id,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likeService.deleteLikePost(id, userDetails.getUser());
    }


    // 댓글 좋아요 API
    @PostMapping("/like/comment/{id}")
    public StatusResult likeComment(@PathVariable Long id,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likeService.likeComment(id, userDetails.getUser());
    }

    // 댓글 좋아요 취소 API
    @DeleteMapping("/like/comment/{id}")
    public StatusResult deleteLikeComment(@PathVariable Long id,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likeService.deleteLikeComment(id, userDetails.getUser());
    }


}
