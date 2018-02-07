package model;

import java.io.Serializable;

public class User implements Serializable{

    private static final long serialVersionUID = -5809782578272943999L;

    private String id;

    private double deposit;

    private int age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
