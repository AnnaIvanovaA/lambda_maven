package com.jet.threads;

public class MoneyTransfer {
    public void transferMoney(Integer acc1, Integer acc2, double amount){
        Integer firstLocked = acc1 > acc2 ? acc2 : acc1;
        Integer secondLocked = acc1 > acc2 ? acc1 : acc2;


        synchronized (firstLocked){
            synchronized (secondLocked){
                //...transfer money
            }
        }
    }

    public static void main(String[] args) {
        MoneyTransfer mt = new MoneyTransfer();
        Integer first = 100;
        Integer second = 101;

        //deadlock
        mt.transferMoney(first, second, 54.23);   //acquire first, wait for second --> acquire first
        mt.transferMoney(second, first, 66.99);    //acquire second, wait for first --> wait
    }
}
