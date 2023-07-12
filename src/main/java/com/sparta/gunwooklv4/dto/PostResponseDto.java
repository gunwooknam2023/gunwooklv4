package com.sparta.gunwooklv4.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.gunwooklv4.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter

// 댓글이 존재하지않을시 사용하기 위한 애너테이션. NULL값인 속성은 JSON 직렬화과정에서 제외.
@JsonInclude(JsonInclude.Include.NON_NULL)

public class PostResponseDto {
    private Long id;
    private String username;
    private String title;
    private String contents;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> commentList = new ArrayList<>(); // 댓글목록을 받아옴

    public PostResponseDto(Post post){
        this.id = post.getId();
        this.username = post.getUsername();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.createAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }

    // 게시글 조회
    public PostResponseDto(Post post, List<CommentResponseDto> commentList){
        this.id = post.getId();
        this.username = post.getUsername();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.createAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.commentList = commentList;
    }

}
