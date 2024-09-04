@import exercise.dto.posts.PostPage
@param PostPage postPage

@template.layout.page(
content = @`
    <h5>${postPage.getPost().getName()}</h5>
    <p>${postPage.getPost().getBody()}</p>
`
)