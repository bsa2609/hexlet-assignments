@import exercise.dto.users.UsersPage
@import gg.jte.Content

@param UsersPage usersPage
@param Content content

@template.layout.page(
content = @`

    <form action="/users" method="get">
        <input type="search" name="term" value="${usersPage.getTerm()}" />
        <input type="submit" value="Search" />
    </form>

    @for(var user : usersPage.getUsers())
        <div>${user.getFirstName() + " " + user.getLastName()}</div>
    @endfor
`
)
