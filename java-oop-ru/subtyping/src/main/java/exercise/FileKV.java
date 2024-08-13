package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage{
    private final String FILE_NAME;

    private void writeMapToFile(Map<String, String> data) {
        String newSerializedData = Utils.serialize(data);
        Utils.writeFile(FILE_NAME, newSerializedData);
    }

    private HashMap<String, String> readMapFromFile() {
        String serializedData = Utils.readFile(FILE_NAME);
        return new HashMap<String, String>(Utils.deserialize(serializedData));
    }

    public FileKV(String fileName, Map<String, String> data) {
        FILE_NAME = fileName;
        writeMapToFile(data);
    }

    @Override
    public void set(String key, String value) {
        HashMap<String, String> data = readMapFromFile();
        data.put(key, value);
        writeMapToFile(data);
    }

    @Override
    public void unset(String key) {
        HashMap<String, String> data = readMapFromFile();
        data.remove(key);
        writeMapToFile(data);
    }

    @Override
    public String get(String key, String defaultValue) {
        HashMap<String, String> data = readMapFromFile();
        return data.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return readMapFromFile();
    }
}
// END
