package exercise.controller;

import exercise.repository.CommentRepository;
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

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {
    private final String MESSAGE_POST_NOT_FOUND = "Post with id %s not found";

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> index() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post show(@PathVariable Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(MESSAGE_POST_NOT_FOUND, id)
                ));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post data) {
        Post post = new Post();
        post.setTitle(data.getTitle());
        post.setBody(data.getBody());

        postRepository.save(post);

        return post;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post update(@PathVariable Long id, @RequestBody Post data) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(MESSAGE_POST_NOT_FOUND, id)
                ));

        post.setTitle(data.getTitle());
        post.setBody(data.getBody());

        postRepository.save(post);

        return post;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        commentRepository.deleteByPostId(id);

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(MESSAGE_POST_NOT_FOUND, id)
                ));
        postRepository.delete(post);
    }
}
// END
