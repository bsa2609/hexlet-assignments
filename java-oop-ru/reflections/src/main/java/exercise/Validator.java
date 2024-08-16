package exercise;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
// BEGIN
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validator {

    public static List<String> validate(Object address) {
        List<String> listNotValidateFields = new ArrayList<String>();

        Class<?> addressClass = address.getClass();

        Field[] fields = addressClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                try {
                    if (field.get(address) == null) {
                        listNotValidateFields.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return listNotValidateFields;
    }

    public static Map<String, List<String>> advancedValidate(Object address) {
        Map<String, List<String>> mapNotValidateFields = new HashMap<>();

        Class<?> addressClass = address.getClass();

        Field[] fields = addressClass.getDeclaredFields();
        for (Field field : fields) {
            List<String> listErrorMessages = new ArrayList<String>();

            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                try {
                    if (field.get(address) == null) {
                        listErrorMessages.add("can not be null");
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

            if (field.isAnnotationPresent(MinLength.class)) {
                field.setAccessible(true);
                try {
                    String fieldValue = (String) field.get(address);
                    MinLength minLength = field.getAnnotation(MinLength.class);
                    if (fieldValue == null || fieldValue.length() < minLength.minLength()) {
                        listErrorMessages.add(String.format("length less than %s", minLength.minLength()));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

            if (!listErrorMessages.isEmpty()) {
                mapNotValidateFields.put(field.getName(), listErrorMessages);
            }
        }

        return mapNotValidateFields;
    }
}
// END
