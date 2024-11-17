package presentation.examples;

public class ThisNoEscape {
    private final EventListener listener;
    private ThisNoEscape(){
        this.listener = () -> {
            // listening
        };
    }
    public static ThisNoEscape getInstance(EventSource source) {
        ThisNoEscape noEscape = new ThisNoEscape();
        source.publishListener(noEscape.listener);
        return noEscape;
    }
}