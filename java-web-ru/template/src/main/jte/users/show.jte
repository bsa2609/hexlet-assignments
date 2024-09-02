@import exercise.dto.users.UserPage
@param UserPage userPage

<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
    <title>Пользователь ${userPage.getUser().getId()}</title>
</head>
<body>
<div class="mx-auto p-4 py-md-5">
    <main>
        <div class="card" style="width: 20rem;">
            <div class="card-header">
                Пользователь # ${userPage.getUser().getId()}
            </div>
            <div class="card-body">
                <h5 class="card-title">${userPage.getUser().getLastName()}</h5>
                <h5 class="card-title">${userPage.getUser().getFirstName()}</h5>
                <p class="card-text">${userPage.getUser().getEmail()}</p>
            </div>
        </div>

    </main>
</div>
</body>
</html>
