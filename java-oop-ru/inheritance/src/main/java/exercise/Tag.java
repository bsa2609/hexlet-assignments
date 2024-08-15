package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public class Tag {
    public String name;
    public Map<String, String> attributes;
//    public Tag child;
//    public String body;

    public Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }
}
// END
