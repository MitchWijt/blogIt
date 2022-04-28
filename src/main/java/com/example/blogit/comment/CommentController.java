package com.example.blogit.comment;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/blog/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/create")
    public Comment createComment(@Valid @RequestBody Comment comment) {
        return commentService.createComment(comment);
    }

    @PutMapping("/upvote/{uuid}")
    public Comment upvoteComment(@PathVariable("uuid") UUID commentUUID) throws Exception {
        return commentService.upvoteComment(commentUUID);
    }

    @GetMapping("/{blogUUID}")
    public List<Comment> getComments(@PathVariable("blogUUID") UUID blogUUID) {
        return commentService.getComments(blogUUID);
    }
}
