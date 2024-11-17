package presentation.examples;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    protected BlockingQueue queue = null;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " consumed " + queue.take());
            System.out.println(Thread.currentThread().getName() + " consumed " + queue.take());
            System.out.println(Thread.currentThread().getName() + " consumed " + queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}