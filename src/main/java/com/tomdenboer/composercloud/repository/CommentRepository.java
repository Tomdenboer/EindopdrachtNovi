package com.tomdenboer.composercloud.repository;

import com.tomdenboer.composercloud.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
