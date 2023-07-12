package com.sparta.gunwooklv4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.gunwooklv4.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonManagedReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    @JsonIgnore
    private Post post;

    public Comment(CommentRequestDto commentRequestDto, Post post, User user){
        this.user = user;
        this.post = post;
        this.comment = commentRequestDto.getComment();
    }

    public void update(CommentRequestDto commentRequestDto){
        this.comment = commentRequestDto.getComment();
    }
}
