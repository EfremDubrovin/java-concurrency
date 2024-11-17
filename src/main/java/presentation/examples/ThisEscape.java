package presentation.examples;

public class ThisEscape {
    public ThisEscape(EventSource source){
        source.publishListener(() -> {
            // listening...
        });
    }
}

interface EventListener {
    void listen();
}