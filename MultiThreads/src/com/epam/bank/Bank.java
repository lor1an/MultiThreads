/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.bank;

import java.util.ArrayList;
import java.util.List;

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

    public void transfer(Account from, Account to, int amount) {
        boolean flag = (from.ID >= to.ID);
        if (flag) {
            if (from.getLock().tryLock()) {
                try {
                    if (to.getLock().tryLock()) {
                        try {
                            from.withdraw(amount);
                            to.deposit(amount);
                        } finally {
                            to.getLock().unlock();
                        }
                    } else {
                        from.getLock().unlock();
                        transfer(from, to, amount);
                    }
                } finally {
                    from.getLock().unlock();
                }
            } else {
                transfer(from, to, amount);
            }
        } else {
            if (to.getLock().tryLock()) {
                try {
                    if (from.getLock().tryLock()) {
                        try {
                            from.withdraw(amount);
                            to.deposit(amount);
                        } finally {
                            from.getLock().unlock();
                        }
                    } else {
                        from.getLock().unlock();
                        transfer(from, to, amount);
                    }
                } finally {
                    to.getLock().unlock();
                }
            } else {
                transfer(from, to, amount);
            }
        }
    }

    public int countAllMoney() {
        int allMoney = 0;
        for (Account account : accounts) {
            allMoney += account.getBalance();
        }
        return allMoney;
    }

}
