package sample;

import java.io.*;
import java.util.ArrayList;

public class User implements Serializable {
    private int id;
    private String name;
    private String surname;
    public ArrayList<Account> accounts = new ArrayList<Account>();

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void addAccount(Account acc) {
        accounts.add(acc);
        acc.user = this;
    }

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
        id = (name+surname).hashCode();
    }

    public void getAccountsInfo() {
        System.out.println("Accounts info of user " + this.getName() + this.getSurname());
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i + 1) + ")");
            System.out.println("Account ID: " + accounts.get(i).getId());
            System.out.println("Amount of money: " + accounts.get(i).getMoney());
        }
    }

    public String toString() {
        return "User{" +
                ", name='" + this.getName() + '\'' +
                ", surname='" + this.getSurname() +
                '}';
    }

    public int getId() {
        return id;
    }
}