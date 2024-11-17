package presentation.examples;

import java.util.ArrayList;
import java.util.List;

public class UnsafePublishing {
    private final List<String> names = new ArrayList<>();

    public List<String> getNames() {
        return names;
    }
}
