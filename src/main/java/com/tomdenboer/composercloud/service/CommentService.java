package com.tomdenboer.composercloud.service;

import com.tomdenboer.composercloud.model.Comment;
import com.tomdenboer.composercloud.model.Playlist;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public interface CommentService {
    Collection<Comment> getAllComments();
    Optional<Comment> getCommentById(long id);
    long createComment(Comment comment, long id);
}
