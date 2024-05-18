package org.example.finance;

public class Goal {
    private int idusers;
    private String type_g;
    private double amount_g;

    public Goal(int idusers, String type_g, double amount_g) {
        this.idusers = idusers;
        this.type_g = type_g;
        this.amount_g = amount_g;
    }

    public Goal(Goal other) {
        this.idusers = other.getidusers();
        this.type_g = other.gettype_g();
        this.amount_g = other.getamount_g();
    }

    public int getidusers() {
        return idusers;
    }

    public void setidusers(int idusers) {
        this.idusers = idusers;
    }

    public String gettype_g() {
        return type_g;
    }

    public void settype_g(String type_g) {
        this.type_g = type_g;
    }

    public double getamount_g() {
        return amount_g;
    }

    public void setamount_g(double amount_g) {
        this.amount_g = amount_g;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "idusers=" + idusers +
                ", type_g='" + type_g + '\'' +
                ", amount_g=" + amount_g +
                '}';
    }
}
