@import exercise.dto.users.UsersPage
@param UsersPage usersPage

<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
    <title>Пользователи</title>
</head>
<body>
<h1 class="display-4">${usersPage.getTitle()}</h1>
<div class="mx-auto p-4 py-md-5">
    <main>
        @if (usersPage.getUsers().isEmpty())
            <h1 class="display-5">В списке нет пользователей</h1>
        @else
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">First</th>
                    <th scope="col">Last</th>
                    <th scope="col">E-mail</th>
                </tr>
                </thead>
                <tbody>
                    @for(var user : usersPage.getUsers())
                        <tr>
                            <th scope="row"><a href="/users/${user.getId()}">${user.getId()}</a></th>
                            <td><a href="/users/${user.getId()}">${user.getFirstName()}</a></td>
                            <td><a href="/users/${user.getId()}">${user.getLastName()}</a></td>
                            <td><a href="/users/${user.getId()}">${user.getEmail()}</a></td>
                        </tr>
                    @endfor
                </tbody>
            </table>


        @endif
    </main>
</div>
</body>
</html>
