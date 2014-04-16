/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.bank;

/**
 *
 * @author lor1an
 */
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {

    static Random rand = new Random();

    public static void bankTransfers(Bank bank) {
        List<Account> accs = bank.getAccounts();
        Account from, to;
        from = accs.get((rand.nextInt(accs.size() - 1)));
        to = accs.get((rand.nextInt(accs.size() - 1)));
        int money = (int) (rand.nextDouble() * from.getBalance());
        bank.transfer(from, to, money);
    }

    public static void main(String[] args) {
        final Bank bank = new Bank();
        int id = 0;

        List<Account> accounts = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 1000; i++) {
            accounts.add(new Account(rand.nextInt(100000), ++id));
        }
        bank.setAccounts(accounts);

        System.out.println("All money before transfers: " + bank.countAllMoney());

        ExecutorService service = Executors.newFixedThreadPool(10000);
        for (int i = 0; i < 10000; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    bankTransfers(bank);
                }
            });
        }
        service.shutdown();   

        System.out.println("All money after transfers:  " + bank.countAllMoney());

    }
}
