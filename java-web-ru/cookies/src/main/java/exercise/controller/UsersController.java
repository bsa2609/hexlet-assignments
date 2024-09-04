package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void root(Context ctx) {
        ctx.redirect(NamedRoutes.buildUserPath());
    }

    public static void create(Context ctx) {
        var firstName = ctx.formParam("firstName");
        var lastName = ctx.formParam("lastName");
        var email = ctx.formParam("email");
        var password = ctx.formParam("password");

        var user = new User(firstName, lastName, email, Security.encrypt(password), Security.generateToken());

        UserRepository.save(user);

        ctx.cookie("token", user.getToken());
        ctx.redirect(NamedRoutes.userPath(user.getId()));
    }

    public static void show(Context ctx) {
        var token = ctx.cookie("token");
        var id = ctx.pathParamAsClass("id", Long.class).getOrDefault(0L);

        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("User id = " + id + " not found"));

        if (user.getToken().equals(token)) {
            var page = new UserPage(user);
            ctx.render("users/show.jte", model("page", page));
        } else {
            ctx.status(302);
            ctx.redirect(NamedRoutes.buildUserPath());
        }
    }
    // END
}
