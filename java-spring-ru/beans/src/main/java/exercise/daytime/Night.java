package exercise.daytime;
import jakarta.annotation.PostConstruct;

public class Night implements Daytime {
    private String name = "night";

    public String getName() {
        return name;
    }

    // BEGIN
    @PostConstruct
    public void afterCreate() {
        System.out.printf("Bean \"%s\" was created%n", getName());
    }
    // END
}
