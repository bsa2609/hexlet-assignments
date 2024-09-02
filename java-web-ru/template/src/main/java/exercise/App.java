package exercise;

import io.javalin.Javalin;

import java.util.Comparator;
import java.util.List;
import io.javalin.http.NotFoundResponse;
import exercise.model.User;
import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        app.get("/users", ctx -> {
            var sortedUsers = USERS.stream()
                    .sorted(Comparator.comparingLong(User::getId))
                    .toList();

            UsersPage usersPage = new UsersPage(sortedUsers, "Список пользователей");
            ctx.render("users/index.jte", model("usersPage", usersPage));
        });

        app.get("/users/{id}", ctx -> {
            final Long id;
            try {
                id = ctx.pathParamAsClass("id", Long.class).getOrDefault(0L);
            } catch (Exception e) {
                throw new NotFoundResponse("User id = " + ctx.pathParam("id") + " not Long type");
            }

            var user = USERS.stream()
                    .filter(u -> u.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new NotFoundResponse("User id = " + id + " not found"));
            var userPage = new UserPage(user);
            ctx.render("users/show.jte", model("userPage", userPage));
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
