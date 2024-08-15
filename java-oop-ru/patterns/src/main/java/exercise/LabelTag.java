package exercise;

// BEGIN
public class LabelTag implements TagInterface {

    public String label;
    public TagInterface otherTag;

    public LabelTag(String label, TagInterface otherTag) {
        this.label = label;
        this.otherTag = otherTag;
    }

    @Override
    public String render() {
        StringBuilder tagBuilder = new StringBuilder("<label>");
        tagBuilder.append(label);
        tagBuilder.append(otherTag.render());
        tagBuilder.append("</label>");

        return tagBuilder.toString();
    }
}
// END
