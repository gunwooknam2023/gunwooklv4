package com.sparta.gunwooklv4.dto;

import com.sparta.gunwooklv4.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter

public class PostResponseDto {
    private Long id;
    private String username;
    private String title;
    private String contents;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> commentList;

    public PostResponseDto(Post post){
        this.id = post.getId();
        this.username = post.getUser().getUsername();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.createAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.commentList = post.getCommentList().stream()
                .map(CommentResponseDto::new).collect(Collectors.toList());
    }

}
