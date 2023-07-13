package com.sparta.gunwooklv4.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public Like(User user, Post post){
        setUser(user);
        setPost(post);
    }

    public Like(User user, Comment comment){
        setUser(user);
        setComment(comment);
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setComment(Comment comment){
        this.comment = comment;
    }
}
