package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void index(Context ctx) {
        int pageNumber = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        var posts = PostRepository.findAll(pageNumber, 5);

        if (posts.isEmpty()) {
            throw new NotFoundResponse("Page number " + pageNumber + " not found");
        }

        int postsCount = PostRepository.getEntities().size();
        int pagesCount = (int) postsCount / 5;
        if (postsCount % 5 != 0) {
            pagesCount = pagesCount + 1;
        }

        var postsPage = new PostsPage(posts, pageNumber, pagesCount);
        ctx.render("posts/index.jte", model("postsPage", postsPage));
    }

    public static void show(Context ctx) {
        final Long id;

        try {
            id = ctx.pathParamAsClass("id", Long.class).getOrDefault(0L);
        } catch (Exception e) {
            throw new NotFoundResponse("Post id = " + ctx.pathParam("id") + " not Long type, post not found");
        }

        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Post id = " + id + "not found"));

        var postPage = new PostPage(post);
        ctx.render("posts/show.jte", model("postPage", postPage));
    }
    // END
}
