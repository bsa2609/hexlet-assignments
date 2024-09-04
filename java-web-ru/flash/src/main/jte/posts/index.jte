@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(
page = page,
content = @`
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">id</th>
            <th scope="col">name</th>
        </tr>
        </thead>
        <tbody>
        @for(var post : page.getPosts())
            <tr>
                <td><a href="${NamedRoutes.postPath(post.getId())}">${post.getId()}</a></td>
                <td><a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a></td>
            </tr>
        @endfor
        </tbody>
    </table>
`
)