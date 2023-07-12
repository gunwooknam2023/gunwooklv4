package com.sparta.gunwooklv4.service;

import com.sparta.gunwooklv4.dto.PostRequestDto;
import com.sparta.gunwooklv4.dto.PostResponseDto;
import com.sparta.gunwooklv4.dto.StatusResult;
import com.sparta.gunwooklv4.entity.Post;
import com.sparta.gunwooklv4.entity.User;
import com.sparta.gunwooklv4.entity.UserRoleEnum;
import com.sparta.gunwooklv4.jwt.JwtUtil;
import com.sparta.gunwooklv4.repository.PostRepository;
import com.sparta.gunwooklv4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PostSerivce {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;

    // 게시글 전체 조회 API
    @Transactional(readOnly = true)
    public List<PostResponseDto> getPostList() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostResponseDto> postResponseDto = new ArrayList<>();

        for(Post post : posts){
            postResponseDto.add(new PostResponseDto(post));
        }

        return postResponseDto;
    }

    // 게시글 선택 조회 API
    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long id) {

        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("선택한 게시물이 존재하지 않습니다.")
        );

        return new PostResponseDto(post);
    }


    // 게시글 작성 API
    public PostResponseDto createPost(PostRequestDto postRequestDto, User user) {

        // 유저정보가 없을시
        if(user == null){
            throw new IllegalArgumentException("허가되지 않은 사용자입니다.");
        }

        Post post = new Post(postRequestDto, user);
        postRepository.save(post);

        return new PostResponseDto(post);
    }


    // 게시글 수정 API
    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto, User user) {

        // 게시글 가져오기
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 글이 존재하지 않습니다.")
        );

        // 유저 일치여부, admin 권한 확인
        if(post.getUser().getId().equals(user.getId()) || user.getRole().equals(UserRoleEnum.ADMIN)){
            post.update(postRequestDto);
        } else {
            throw new IllegalArgumentException("작성자만 수정이 가능합니다.");
        }

        return new PostResponseDto(post);
    }


    // 게시글 삭제 API
    @Transactional
    public StatusResult deletePost(Long id, User user) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 글이 존재하지 않습니다.")
        );

        if(post.getUser().getId().equals(user.getId()) || user.getRole().equals(UserRoleEnum.ADMIN)){
            postRepository.delete(post);
        } else {
            return new StatusResult("작성자만 삭제가 가능합니다.", 400);
        }

        return new StatusResult("삭제가 완료되었습니다.", 200);
    }
}
