package com.hebut.triptogether.UI.PushList;

/**
 * Created by Zhang_Rui on 2018/6/11.
 */

public class JD  {
    private Long id;
    private String name;
    private int balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public JD(Long id, String name, int balance) {
        super();
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public JD(String name, int balance) {
        super();
        this.name = name;
        this.balance = balance;
    }

    public JD() {
        super();
    }
}