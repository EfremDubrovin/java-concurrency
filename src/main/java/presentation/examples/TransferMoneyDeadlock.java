package presentation.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransferMoneyDeadlock {

    public void transferMoney(Account fromAccount,
                              Account toAccount,
                              Integer amount) throws InsufficientFundsException {
        synchronized (fromAccount) {
            synchronized (toAccount) {
                if (fromAccount.getBalance().compareTo(amount) < 0) {
                    throw new InsufficientFundsException();
                } else {
                    fromAccount.debit(amount);
                    toAccount.credit(amount);
                }
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Account ivan = new Account(2000);
        Account andrei = new Account(2000);
        List<Runnable> transactions = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            transactions.add(() -> {
                new TransferMoneyDeadlock().transferMoney(
                    ivan, andrei, 10
                );
            });
            transactions.add(() -> {
                new TransferMoneyDeadlock().transferMoney(
                    andrei, ivan, 10
                );
            });
        }
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        transactions.forEach(executorService::execute);
        executorService.shutdown();
    }

    private static class Account {
        private int balance;

        public Account(int balance) {
            this.balance = balance;
        }

        public Comparable<Integer> getBalance() {
            return balance;
        }

        public void debit(Integer amount) {
            balance = balance - amount;
        }

        public void credit(Integer amount) {
            balance = balance + amount;
        }
    }

    private static class InsufficientFundsException extends RuntimeException {
    }
}
