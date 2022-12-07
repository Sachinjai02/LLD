import java.util.HashMap;
import java.util.Map;

public class ObjectsRegistry {
    private static Map<String, Object> objects = new HashMap<>();

    public static void put(String key, Object obj) {
        objects.put(key, obj);
    }

    public static Object get(String key) {
        return objects.get(key);
    }
}
