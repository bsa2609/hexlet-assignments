@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage postsPage

@template.layout.page(
content = @`
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">id</th>
            <th scope="col">name</th>
            <th scope="col">description</th>
        </tr>
        </thead>
        <tbody>
        @for(var post : postsPage.getPosts())
            <tr>
                <td><a href="${NamedRoutes.postPath(post.getId())}">${post.getId()}</a></td>
                <td><a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a></td>
            </tr>
        @endfor
        </tbody>
    </table>

    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item ${postsPage.getPageNumber() <= 1 ? "disabled" : ""}"><a class="page-link" href="${NamedRoutes.postsPath()}?page=${postsPage.getPageNumber() - 1}">Previous</a></li>
            <li class="page-item ${postsPage.getPageNumber() >= postsPage.getPagesCount() ? "disabled" : ""}"><a class="page-link" href="${NamedRoutes.postsPath()}?page=${postsPage.getPageNumber() + 1}">Next</a></li>
        </ul>
    </nav>

`
)