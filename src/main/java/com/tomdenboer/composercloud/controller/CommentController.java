package com.tomdenboer.composercloud.controller;

import com.tomdenboer.composercloud.model.Comment;
import com.tomdenboer.composercloud.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("")
    public ResponseEntity<Object> getAllComments() {
        return ResponseEntity.ok().body(commentService.getAllComments());
    }

    @GetMapping(value = "/{id}")
        public ResponseEntity<Object> getCommentById (@PathVariable("id") long id)  {
        return ResponseEntity.ok().body(commentService.getCommentById(id));
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Object> createComment(@RequestBody Comment comment, @PathVariable long id){
        long newId = commentService.createComment(comment, id);

        return ResponseEntity.ok().body("Comment met id: "+ newId + " aangemaakt.");
    }


}
