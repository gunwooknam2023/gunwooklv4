package com.sparta.gunwooklv4.controller;

import com.sparta.gunwooklv4.dto.PostRequestDto;
import com.sparta.gunwooklv4.dto.PostResponseDto;
import com.sparta.gunwooklv4.dto.StatusResult;
import com.sparta.gunwooklv4.security.UserDetailsImpl;
import com.sparta.gunwooklv4.service.PostSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostSerivce postSerivce;

    // 게시글 전체 조회 API
    @GetMapping("/posts")
    public List<PostResponseDto> getPostList(){
        return postSerivce.getPostList();
    }

    // 게시글 선택 조회 API
    @GetMapping("/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id){
        return postSerivce.getPost(id);
    }

    // 게시글 작성 API
    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postSerivce.createPost(postRequestDto, userDetails.getUser());
    }

    // 게시글 수정 API
    @PutMapping("/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id,
                                                      @RequestBody PostRequestDto postRequestDto,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postSerivce.updatePost(id, postRequestDto, userDetails.getUser());
    }

    // 게시글 삭제 API
    @DeleteMapping("/post/{id}")
    public StatusResult deletePost(@PathVariable Long id,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postSerivce.deletePost(id, userDetails.getUser());
    }
}
