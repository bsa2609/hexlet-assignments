package exercise.daytime;
import jakarta.annotation.PostConstruct;

public class Day implements Daytime {
    private String name = "day";

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
