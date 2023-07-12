package com.sparta.gunwooklv4.repository;

import com.sparta.gunwooklv4.entity.Comment;
import com.sparta.gunwooklv4.entity.Like;
import com.sparta.gunwooklv4.entity.Post;
import com.sparta.gunwooklv4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndPost(User user, Post post);
    Optional<Like> findByUserAndComment(User user, Comment comment);
}
