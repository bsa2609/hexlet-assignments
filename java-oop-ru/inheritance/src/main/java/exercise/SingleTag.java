package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag {

    public SingleTag(String name, Map<String, String> attributes) {
        super(name, attributes);
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

        return tagBuilder.toString();
    }
}
// END
