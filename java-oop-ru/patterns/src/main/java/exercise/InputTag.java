package exercise;

// BEGIN
public class InputTag implements TagInterface {

    private String type;
    private String value;

    public InputTag(String type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String render() {
        StringBuilder tagBuilder = new StringBuilder("<input type=\"");
        tagBuilder.append(type);
        tagBuilder.append("\" value=\"");
        tagBuilder.append(value);
        tagBuilder.append("\">");

        return tagBuilder.toString();
    }
}
// END
