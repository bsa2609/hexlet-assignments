package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;

// BEGIN
import io.javalin.http.NotFoundResponse;
// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        app.get("companies/{id}", ctx -> {
            Map<String, String> foundCompany = null;

            String id = ctx.pathParam("id");

            for (Map<String, String> company : COMPANIES) {
                if (company.get("id").equals(id)) {
                    foundCompany = company;
                    break;
                }
            }

            if (foundCompany == null) {
                throw new NotFoundResponse("Company not found");
            } else {
                ctx.json(foundCompany);
            }

        });
        // END

        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });

        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
