@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@param MainPage mainPage

@template.layout.page(
content = @`
    @if(mainPage.getName() == null)
        <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
    @else
        <h5>${String.valueOf(mainPage.getName())}</h5>
        <form action="${NamedRoutes.logoutPath()}" method="post">
            <button type="submit">Выйти</button>
        </form>
    @endif
`)
