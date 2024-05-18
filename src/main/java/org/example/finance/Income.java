package org.example.finance;

public class Income {
    private int idincome;
    private int idusers;
    private String type_i;
    private double amount_i;
    private String date_i;

    public Income(int idincome, int idusers, String type_i, double amount_i, String date_i) {
        this.idincome = idincome;
        this.idusers = idusers;
        this.type_i = type_i;
        this.amount_i = amount_i;
        this.date_i = date_i;
    }

    public Income(Income other) {
        this.idincome = other.getId();
        this.idusers = other.getidusers();
        this.type_i = other.gettype_i();
        this.amount_i = other.getamount_i();
        this.date_i = other.getdate_i();
    }

    public int getId() {
        return idincome;
    }

    public void setidincome(int idincome) {
        this.idincome = idincome;
    }

    public int getidusers() {
        return idusers;
    }

    public void setidusers(int idusers) {
        this.idusers = idusers;
    }

    public String gettype_i() {
        return type_i;
    }

    public void settype_i(String type_i) {
        this.type_i = type_i;
    }

    public double getamount_i() {
        return amount_i;
    }

    public void setamount_i(double amount_i) {
        this.amount_i = amount_i;
    }

    public String getdate_i() {
        return date_i;
    }

    public void setdate_i(String date_i) {
        this.date_i = date_i;
    }

    @Override
    public String toString() {
        return "Income{" +
                "idincome=" + idincome +
                ", idusers=" + idusers +
                ", type_i='" + type_i + '\'' +
                ", amount_i_i=" + amount_i +
                ", date_i_i='" + date_i + '\'' +
                '}';
    }
}
