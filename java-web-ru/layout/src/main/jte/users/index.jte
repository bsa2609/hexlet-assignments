@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
content = @`
    <table class="table">
        <thead>
        <tr>
            <th scope="col">id</th>
            <th scope="col">First</th>
            <th scope="col">Last</th>
            <th scope="col">E-mail</th>
        </tr>
        </thead>
        <tbody>
        @for(var user : page.getUsers())
            <tr>
                <td><a href="users/${user.getId()}">${user.getId()}</a></td>
                <td><a href="users/${user.getId()}">${user.getFirstName()}</a></td>
                <td><a href="users/${user.getId()}">${user.getLastName()}</a></td>
                <td><a href="users/${user.getId()}">${user.getEmail()}</a></td>
            </tr>
        @endfor
        </tbody>
    </table>


`
)
