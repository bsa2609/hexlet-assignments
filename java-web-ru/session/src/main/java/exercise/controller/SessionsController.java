package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import io.javalin.http.Context;

public class SessionsController {

    // BEGIN
    public static void index(Context ctx) {
        var currentUser = ctx.sessionAttribute("currentUser");
        var mainPage = new MainPage(currentUser);

        ctx.render("index.jte", model("mainPage", mainPage));
    }

    public static void build(Context ctx) {
        var loginPage = new LoginPage("", "");
        ctx.render("build.jte", model("loginPage", loginPage));
    }

    public static void login(Context ctx) {
        try {
            var nickname = ctx.formParam("name");
            var user = UsersRepository.findByName(nickname.trim().toLowerCase())
                    .orElseThrow(() -> new Exception("Wrong username or password"));

            var password = ctx.formParam("password");
            if (!encrypt(password).equals(user.getPassword())) {
                throw new Exception("Wrong username or password");
            }

            ctx.sessionAttribute("currentUser", nickname);
            ctx.redirect(NamedRoutes.rootPath());
        } catch (Exception e) {
            var loginPage = new LoginPage(ctx.formParam("name"), e.getMessage());
            ctx.render("build.jte", model("loginPage", loginPage));
        }
    }

    public static void logout(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect(NamedRoutes.rootPath());
    }
    // END
}
