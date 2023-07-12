package com.sparta.gunwooklv4.controller;

import com.sparta.gunwooklv4.dto.LikeResponseDto;
import com.sparta.gunwooklv4.security.UserDetailsImpl;
import com.sparta.gunwooklv4.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LikeController {
    private final LikeService likeService;

    // 게시글 좋아요 API
    @PostMapping("/like/post/{id}")
    public LikeResponseDto likePost(@PathVariable Long id,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likeService.likePost(id, userDetails.getUser());
    }


    // 댓글 좋아요 API
    @PostMapping("/like/comment/{id}")
    public LikeResponseDto likeComment(@PathVariable Long id,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likeService.likeComment(id, userDetails.getUser());
    }

}
