package exercise;

import lombok.SneakyThrows;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {
    @SneakyThrows
    public static void save(Path path, Car car) {
        String jsonRepresentation = car.serialize();
        Files.write(path, jsonRepresentation.getBytes());
    }

    @SneakyThrows
    public static Car extract(Path path) {
        String jsonRepresentation = Files.readString(path);
        return Car.deserialize(jsonRepresentation);
    }
}
// END
