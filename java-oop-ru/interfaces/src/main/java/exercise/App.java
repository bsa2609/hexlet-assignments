package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> apartments, int firstApartmentsCount) {
        return apartments.stream()
                .sorted(Home::CompareTo)
                .map(Home::toString)
                .limit(firstApartmentsCount)
                .collect(Collectors.toList());
    }
}
// END
