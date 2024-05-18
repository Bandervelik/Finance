package org.example.finance;

public class Expense {
    private int idexpense;
    private int idusers;
    private String type_e;
    private double amount_e;
    private String date_e;

    public Expense(int idexpense, int idusers, String type_e, double amount_e, String date_e) {
        this.idexpense = idexpense;
        this.idusers = idusers;
        this.type_e = type_e;
        this.amount_e = amount_e;
        this.date_e = date_e;
    }

    public Expense(Expense other) {
        this.idexpense = other.getidexpense();
        this.idusers = other.getidusers();
        this.type_e = other.gettype_e();
        this.amount_e = other.getamount_e();
        this.date_e = other.getdate_e();
    }

    public int getidexpense() {
        return idexpense;
    }

    public void setidexpense(int idexpense) {
        this.idexpense = idexpense;
    }

    public int getidusers() {
        return idusers;
    }

    public void setidusers(int idusers) {
        this.idusers = idusers;
    }

    public String gettype_e() {
        return type_e;
    }

    public void settype_e(String type_e) {
        this.type_e = type_e;
    }

    public double getamount_e() {
        return amount_e;
    }

    public void setamount_e(double amount_e) {
        this.amount_e = amount_e;
    }

    public String getdate_e() {
        return date_e;
    }

    public void setdate_e(String date_e) {
        this.date_e = date_e;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "idexpense=" + idexpense +
                ", idusers=" + idusers +
                ", type_e='" + type_e + '\'' +
                ", amount_e=" + amount_e +
                ", date_e='" + date_e + '\'' +
                '}';
    }
}
