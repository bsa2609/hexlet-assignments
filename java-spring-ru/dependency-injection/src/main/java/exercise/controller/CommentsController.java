package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/comments")
public class CommentsController {
    private final String MESSAGE_COMMENT_NOT_FOUND = "Comment with id %s not found";

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> index() {
        return commentRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment show(@PathVariable Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(MESSAGE_COMMENT_NOT_FOUND, id)
                ));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@RequestBody Comment data) {
        Comment comment = new Comment();
        comment.setPostId(data.getPostId());
        comment.setBody(data.getBody());

        commentRepository.save(comment);

        return  comment;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment update(@PathVariable Long id, @RequestBody Comment data) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(MESSAGE_COMMENT_NOT_FOUND, id)
                ));

        comment.setPostId(data.getPostId());
        comment.setBody(data.getBody());

        commentRepository.save(comment);

        return  comment;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(MESSAGE_COMMENT_NOT_FOUND, id)
                ));
        commentRepository.delete(comment);
    }
}
// END
