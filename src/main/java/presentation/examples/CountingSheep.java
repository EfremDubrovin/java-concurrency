package presentation.examples;

public class CountingSheep {
    volatile boolean asleep;

    public void count () {
        while (!asleep) {
            System.out.println("counting...");
        }
    }
}
