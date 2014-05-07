/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Anatolii_Hlazkov
 */
public class Bank {

    private List<Account> accounts = new ArrayList<>();

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @SuppressWarnings("empty-statement")
    public void transh(Account from, Account to, int amount) {
        boolean flag = (from.ID >= to.ID);
        if (flag) {
            while (!_transfer(from, to, amount));
        } else {
            while (!_transfer(to, from, -amount));
        }
    }

    private boolean _transfer(Account from, Account to, int amount) {
        if (from.getLock().tryLock()) {
            try {
                if (to.getLock().tryLock()) {
                    try {
                        from.changeBalance(-amount);
                        to.changeBalance(amount);
                        return true;
                    } finally {
                        to.getLock().unlock();
                    }
                }
            } finally {
                from.getLock().unlock();
            }
        }
        return false;
    }

    public int countAllMoney() {
        int allMoney = 0;
        for (Account account : accounts) {
            while (true) {
                if (!account.getLock().isLocked()) {
                    try {
                        account.getLock().lock();
                        allMoney += account.getBalance();
                        break;
                    } finally {
                        account.getLock().unlock();
                    }
                }
            }
        }
        return allMoney;
    }

}
