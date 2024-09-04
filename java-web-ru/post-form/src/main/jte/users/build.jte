
<html lang="ru">
    <head>
        <meta charset="utf-8">
        <title>Создание пользователя</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
              crossorigin="anonymous">
    </head>
    <body>
        <form action="/users" method="post">
            <div class="mb-3" style="margin: 10px">
                <label for="firstName" class="form-label" style="border-left = 10px">Имя</label>
                <input type="text" class="form-control" id="firstName" name="firstName">
            </div>
            <div class="mb-3" style="margin: 10px">
                <label for="lastName" class="form-label">Фамилия</label>
                <input type="text" class="form-control" id="lastName" name="lastName">
            </div>
            <div class="mb-3" style="margin: 10px">
                <label for="email" class="form-label">Адрес электронной почты</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="name@example.com">
            </div>
            <div class="mb-3" style="margin: 10px">
                <label for="password" class="form-label">Парорль</label>
                <input type="password" class="form-control" id="password" name="password">
            </div>
            <button type="submit" class="btn btn-primary" style="margin: 10px">Submit</button>
        </form>
    </body>
</html>
