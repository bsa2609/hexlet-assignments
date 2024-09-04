@import exercise.dto.LoginPage
@import exercise.util.NamedRoutes
@param LoginPage loginPage

<html lang="ru">
    <head>
        <meta charset="utf-8">
        <title>Login</title>
    </head>
    <body>
        @if (!loginPage.getError().isBlank())
            <h5>${loginPage.getError()}</h5>
        @endif
        <form action="${NamedRoutes.loginPath()}" method="post">
            <input type="text" placeholder="Name" name="name" value="${loginPage.getName()}" />
            <input type="password" placeholder="Password" name="password" />
            <input type="submit" />
        </form>
    </body>
</html>

