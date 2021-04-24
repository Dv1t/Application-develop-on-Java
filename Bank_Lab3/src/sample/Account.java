package sample;

import java.io.*;
import java.util.ArrayList;

public class Account implements Serializable {
    public User user;
    private int money;
    private int id;
    private ArrayList<String> history = new ArrayList<String>();


    public Account(User user, int money, int id) {
        this.user = user;
        this.money = money;
        this.id = id;
    }

    public Account(int money, int id) {
        this.money = money;
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public String getUserName() {
        return user.getName();
    }

    public String getUserSurname() {
        return user.getSurname();
    }

    public int getMoney() {
        return money;
    }

    public int getId() {
        return id;
    }

    public void addMoney(int money) {
        this.money += money;
        this.history.add("Added " + Integer.toString(money));
    }

    public void takeMoney(int money) {
        if (this.money < money) {
            System.out.println("Error, you haven't enough money :( ");
        } else {
            this.money -= money;
            this.history.add("Taken " + String.valueOf(money));
        }
    }

    public void transferFromTo(Account accFrom, Account accTo, int money) {
        if (accFrom.money < money) {
            System.out.println("Error, you haven't enough money :( ");
        } else {
            accFrom.money -= money;
            accTo.money += money;
            System.out.println("Transaction completed successfully!");
            this.history.add("Transfered from " + String.valueOf(accFrom.getId()) + " to " + String.valueOf(accTo.getId()) + ": " + String.valueOf(money));
        }
    }

    public void getInfo(User user) {
        System.out.println("User name: " + user.getName() + "\n User surname: " + user.getSurname());
    }

    public void getHistory() {
        System.out.println("History of account " + this.getId());
        for (int i = 0; i < history.size(); i++) {
            System.out.println(history.get(i));
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "user='" + this.getUser() + '\'' +
                ", id='" + this.getId() + '\'' +
                ", money='" + this.getMoney() +
                '}';
    }
}