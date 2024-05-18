
package org.example.finance;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void signUpUser(User user) {
        String insert = "INSERT INTO " + Const.USER_TABLE + " (" + Const.USERS_FIRSTNAME + "," +
                Const.USERS_LASTNAME + "," + Const.USERS_USERNAME + "," +
                Const.USERS_PASSWORD + ")" +
                "VALUES(?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getFirstName());
            prSt.setString(2, user.getLastName());
            prSt.setString(3, user.getUsername());
            prSt.setString(4, user.getPassword());

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getUser(User user){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USERS_USERNAME + "=? AND "
                + Const.USERS_PASSWORD + "=?";


        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getUsername());
            prSt.setString(2, user.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resSet;
    }

    public void addIncome(Income income) {
        String insert = "INSERT INTO " + Const.INCOME_TABLE + "(" + Const.INCOME_USER_ID + ","
                + Const.INCOME_TYPE + "," + Const.INCOME_AMOUNT + "," + Const.INCOME_DATE + ")"
                + "VALUES(?,?,?,?)";

        try (PreparedStatement prSt = getDbConnection().prepareStatement(insert)) {
            prSt.setInt(1, income.getidusers());
            prSt.setString(2, income.gettype_i());
            prSt.setDouble(3, income.getamount_i());
            prSt.setDate(4, Date.valueOf(income.getdate_i()));
            prSt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addExpense(Expense expense) {
        String insert = "INSERT INTO " + Const.EXPENSE_TABLE + "(" + Const.EXPENSE_USER_ID + ","
                + Const.EXPENSE_TYPE + "," + Const.EXPENSE_AMOUNT + "," + Const.EXPENSE_DATE + ")"
                + "VALUES(?,?,?,?)";

        try (PreparedStatement prSt = getDbConnection().prepareStatement(insert)) {
            prSt.setInt(1, expense.getidusers());
            prSt.setString(2, expense.gettype_e());
            prSt.setDouble(3, expense.getamount_e());
            prSt.setDate(4, Date.valueOf(expense.getdate_e()));
            prSt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void addGoal(Goal goal) throws SQLException {
        String insert = "INSERT INTO " + Const.GOALS_TABLE + "(" + Const.GOALS_USER_ID + ","
                + Const.GOAL_TYPE_COLUMN + "," + Const.GOAL_AMOUNT_COLUMN + ")"
                + "VALUES(?,?,?)";

        try (PreparedStatement prSt = getDbConnection().prepareStatement(insert)) {
            prSt.setInt(1, goal.getidusers());
            prSt.setString(2, goal.gettype_g());
            prSt.setBigDecimal(3, BigDecimal.valueOf(goal.getamount_g()));
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getIncome() {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + Const.INCOME_TABLE;

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getExpense(int idusers) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + Const.EXPENSE_TABLE + " WHERE " + Const.EXPENSE_USER_ID + "=?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setInt(1, idusers);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

}
