/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.bank;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Anatolii_Hlazkov
 */
public class Account {

    public final int ID;
    private int balance;
    private Lock lock = new ReentrantLock();

    public Account(int ballance, int id) {
        if (ballance < 0) {
            throw new IllegalArgumentException();
        }
        ID = id;
        this.balance = ballance;
    }

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }

    public void withdraw(int money) {
        if (balance < money) {
            throw new IllegalArgumentException();
        }
        balance -= money;
    }

    public void deposit(int money) {
        balance += money;
    }

    public int getBalance() {
        return balance;
    }

}
