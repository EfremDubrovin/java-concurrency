package presentation.examples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class BoundedArrayList<T> {
    private final List<T> list;
    private final Semaphore sem;

    public BoundedArrayList(int bound) {
        this.list = Collections.synchronizedList(new ArrayList<>());
        sem = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        sem.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = list.add(o);
            return wasAdded;
        } finally {
            if (!wasAdded) {
                sem.release();
            }
        }
    }

    public boolean remove(Object o) {
        boolean wasRemoved = list.remove(o);
        if (wasRemoved) {
            sem.release();
        }
        return wasRemoved;
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedArrayList boundedArrayList = new BoundedArrayList(1);
        ExecutorService executor = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            executor.execute(() -> {
                try {
                    System.out.println("try " + Thread.currentThread().getName() + " Adding element");
                    boundedArrayList.add("hey");
                    Thread.sleep(250);
                    System.out.println("succeeded " + Thread.currentThread().getName() + " Adding element");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                System.out.println("try " + Thread.currentThread().getName() + " Removing element");
                boundedArrayList.remove("hey");
                System.out.println("succeeded " + Thread.currentThread().getName() + " Removing element");
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        Thread.sleep(10_000);
        executor.shutdown();
    }
}
