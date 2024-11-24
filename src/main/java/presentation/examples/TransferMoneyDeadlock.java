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
        Account fromAccount = new Account(200);
        Account toAccount = new Account(20);
        List<Runnable> transactions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            transactions.add(() -> {
                new TransferMoneyDeadlock().transferMoney(
                    fromAccount, toAccount, 10
                );
            });
        }
        for (int i = 0; i < 10; i++) {
            transactions.add(() -> {
                new TransferMoneyDeadlock().transferMoney(
                    toAccount, fromAccount, 10
                );
            });
        }
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        transactions.forEach(executorService::execute);
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
