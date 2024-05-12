package org.example.finance;

public class Expense {
    private int id;
    private int userId;
    private String type1;
    private Double amount1;
    private String date1;

    public Expense(int userId, String type1, Double amount1, String date1) {
        this.userId = userId;
        this.type1 = type1;
        this.amount1 = amount1;
        this.date1 = date1;
    }
    public Expense() {}

    public String gettype1() {
        return type1;
    }

    public void settype1(String type1) {
        this.type1 = type1;
    }

    public Double getamount1() {
        return amount1;
    }

    public void setamount1(Double amount1) {
        this.amount1 = amount1;
    }

    public String getdate1() {
        return date1;
    }

    public void setdate1(String date1) {
        this.date1 = date1;
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
