package presentation.examples;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MyRecursiveAction extends RecursiveAction {

    private long workLoad = 0;

    public MyRecursiveAction(long workLoad) {
        this.workLoad = workLoad;
    }

    @Override
    protected void compute() {
        //if work is above threshold, break tasks up into smaller tasks
        if(this.workLoad > 16) {
            System.out.println("Splitting workLoad : " + this.workLoad);

            long workload1 = this.workLoad / 2;
            long workload2 = this.workLoad - workload1;

            MyRecursiveAction subtask1 = new MyRecursiveAction(workload1);
            MyRecursiveAction subtask2 = new MyRecursiveAction(workload2);

            subtask1.fork();
            subtask2.fork();

        } else {
            System.out.println(Thread.currentThread().getName() + " Doing workLoad myself: " + this.workLoad);
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(3);
        MyRecursiveAction myRecursiveAction = new MyRecursiveAction(50);
        forkJoinPool.invoke(myRecursiveAction);
        forkJoinPool.shutdown();
    }
}
