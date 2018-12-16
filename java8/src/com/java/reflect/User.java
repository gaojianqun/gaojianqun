package com.java.reflect;

import java.io.Serializable;

/**
 * Created by gaojianqun on 2018/11/20.
 */
public class User implements Serializable{

    private String id;

    private String userName;

    private Float money;

    public User() {
    }

    public User(String id,String userName,Float money){
        this.id = id;
        this.userName = userName;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }



}
