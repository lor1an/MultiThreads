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
    private double balance;
    private final Lock lock = new ReentrantLock();
    
    
}
