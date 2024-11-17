package memory.consistency;

public class MemoryConsistencyErrors {

    public static void main(String[] args) {
        Counter counter = new Counter();
        new Thread(counter).start();
        new Thread(counter).start();
        new Thread(counter).start();

        SynchronizedCounter synchronizedCounter = new SynchronizedCounter();
        new Thread(synchronizedCounter).start();
        new Thread(synchronizedCounter).start();
        new Thread(synchronizedCounter).start();
    }

    private static class Counter implements Runnable {

        private int count;

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            for (int i = 0; i < 1_000_000; i++) {
                count = count + 1;
            }
            System.out.println(threadName + " counted up to: " + count);
        }
    }

    private static class SynchronizedCounter implements Runnable {

        private int count;

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            for (int i = 0; i < 1_000_000; i++) {
                synchronized (this) {
                    count = count + 1;
                }
            }
            System.out.println(threadName + " synchronized counted up to: " + count);
        }
    }
}
