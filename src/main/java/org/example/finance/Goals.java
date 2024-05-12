package org.example.finance;

public class Goals {
    private Integer userId;
    private String type;
    private double amount;

    public Goals(Integer userId, String type, double amount) {
        this.userId = userId;
        this.type = type;
        this.amount = amount;
    }

    public Goals() {}

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
