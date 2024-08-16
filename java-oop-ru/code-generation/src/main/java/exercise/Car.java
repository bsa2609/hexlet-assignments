package exercise;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.SneakyThrows;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

// BEGIN
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    @SneakyThrows
    public String serialize() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    @SneakyThrows
    public static Car deserialize(String jsonRepresentation) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonRepresentation, new TypeReference<Car>() {});
    }
    // END
}
