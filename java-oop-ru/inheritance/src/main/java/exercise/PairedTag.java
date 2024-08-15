package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {

    public String body;
    public List<Tag> children;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> children) {
        super(name, attributes);
        this.body = body;
        this.children = children;
    }

    @Override
    public String toString() {
        StringBuilder tagBuilder = new StringBuilder("<");
        tagBuilder.append(name);

        attributes.forEach((key, value) -> {
            tagBuilder.append(" ");
            tagBuilder.append(key);
            tagBuilder.append("=\"");
            tagBuilder.append(value);
            tagBuilder.append("\"");
        });
        tagBuilder.append(">");

        tagBuilder.append(body);

        children.forEach(child -> {
            tagBuilder.append(child.toString());
        });

        tagBuilder.append("</");
        tagBuilder.append(name);
        tagBuilder.append(">");

        return tagBuilder.toString();
    }
}
// END
