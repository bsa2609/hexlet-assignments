@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page

<html lang="ru">
    <head>
        <meta charset="utf-8">
        <title>Новая статья</title>
    </head>
    <body>
        @if(page.getErrors() != null)
            <ul>
                @for(var validator : page.getErrors().values())
                    @for(var error : validator)
                        <li>${error.getMessage()}</li>
                    @endfor
                @endfor
            </ul>
        @endif

        <form action="/articles", method="post">
            <div>
                <label>
                    Заголовок
                    <input type="text" name="title" value="${page.getTitle()}">
                </label>
            </div>
            <div>
                <div>
                    <label for="content">Содержание</label>
                </div>
                <div>
                    <textarea name="content" id="content" rows="10" cols="50">${page.getContent()}</textarea>
                </div>
            </div>
            <button type="submit">Сохранить</button>
        </form>
    </body>
</html>
