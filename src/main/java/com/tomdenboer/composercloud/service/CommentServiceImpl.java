package com.tomdenboer.composercloud.service;

import com.tomdenboer.composercloud.exceptions.CommentNotFoundException;
import com.tomdenboer.composercloud.exceptions.SongNotFoundException;
import com.tomdenboer.composercloud.model.Comment;
import com.tomdenboer.composercloud.model.Song;
import com.tomdenboer.composercloud.model.User;
import com.tomdenboer.composercloud.repository.CommentRepository;
import com.tomdenboer.composercloud.util.PrincipalHelper;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private SongService songService;

    @Autowired
    private final PrincipalHelper principalHelper;

    public CommentServiceImpl () {
        this.principalHelper = new PrincipalHelper();
    }

    @Override
    public Collection<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> getCommentById(long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);

        if (optionalComment.isEmpty()) {
            throw new CommentNotFoundException();
        } else {
            return optionalComment;
        }
    }

    @Override
    public long createComment(Comment comment, long id)  {
        Optional<Song> optionalSong = songService.getSongDataById(id);

        if (optionalSong.isEmpty()) {
            throw new SongNotFoundException(id);
        } else {
            User currentUser = principalHelper.getCurrentUser();
            comment.setUser(currentUser);
            comment.setSong(optionalSong.get());

            Comment newComment = commentRepository.save(comment);
            return newComment.getId();
        }
    }
}
