package com.sparta.gunwooklv4.repository;

import com.sparta.gunwooklv4.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
