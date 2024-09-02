@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
content = @`
    <div>${page.getUser().getFirstName()}</div>
    <div>${page.getUser().getLastName()}</div>
    <div>${page.getUser().getEmail()}</div>
`
)
