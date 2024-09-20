package exercise;

import java.net.URI;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> index() {
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(posts.size()))
                .body(posts);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> show(@PathVariable String id) {
        var postAsOptional = posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst();

        return ResponseEntity.of(postAsOptional);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> create(@RequestBody Post newPost) {
        Post post = new Post(newPost.getId(), newPost.getTitle(), newPost.getBody());
        posts.add(post);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newPost);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post updatedPost) {
        var postAsOptional = posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst();
        if (postAsOptional.isPresent()) {
            Post post = postAsOptional.get();
            post.setId(updatedPost.getId());
            post.setTitle(updatedPost.getTitle());
            post.setBody(updatedPost.getBody());

            return ResponseEntity.ok(updatedPost);

        } else {
            return ResponseEntity.noContent().build();
        }
    }
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
