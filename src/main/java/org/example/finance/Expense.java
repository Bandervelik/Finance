package org.example.finance;

public class Expense {
    private int id;
    private int userId;
    private String type;
    private Double amount;
    private String date;

    public Expense(int userId, String type, Double amount, String date) {
        this.userId = userId;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }
    public Expense() {}

    public String gettype() {
        return type;
    }

    public void settype(String type) {
        this.type = type;
    }

    public Double getamount() {
        return amount;
    }

    public void setamount(Double amount) {
        this.amount = amount;
    }

    public String getdate() {
        return date;
    }

    public void setdate(String date) {
        this.date = date;
    }

    public Integer getid() {
        return id;
    }

    public void setid(Integer password) {
        this.id = id;}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
