package presentation.examples;

import java.util.concurrent.ConcurrentMap;

public class NotConcurrentHashMap {

    public static String addDescription(String name, String description,
                                        ConcurrentMap<String, String> map) {
        String newDescription = map.computeIfAbsent(name, k -> description);
        System.out.printf("%s has a new description %s", name, newDescription);
        return description;
    }
}
