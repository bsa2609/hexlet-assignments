@import exercise.dto.BasePage
@import gg.jte.Content

@param BasePage page = null
@param Content content

<!-- BEGIN -->
<html lang="ru">
    <head>
        <meta charset="utf-8" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
              crossorigin="anonymous">
    </head>
    <body>
        @if(page != null && page.getFlash() != null)
            <div class="alert alert-success" role="alert">
                ${page.getFlash()}
            </div>
        @endif
        <div class="content">
            ${content}
        </div>
    </body>
</html>
<!-- END -->
