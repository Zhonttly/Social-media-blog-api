package DAO;

import Util.ConnectionUtil;
import Model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AccountDAO {
    
    Connection connection = ConnectionUtil.getConnection();
    /**
     * Inserts a new Account into the Account table
     * @param account account to be added
     */
    public Account createAccount(Account account) {
        System.out.println("Attempting to create a new account in the DAO...");
        Connection connection = ConnectionUtil.getConnection();

        try {
            //General SQL logic
            String sql = "INSERT INTO account(username, password) VALUES(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //Preparedstatement parameters
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            
            preparedStatement.executeUpdate();

            // //Return the newly added account in the database
            // ResultSet rs = preparedStatement.getGeneratedKeys();

            // if(rs.next()){
            //     System.out.println("Checking newly created account...");
            //     int auto_account_id = (int) rs.getLong(1);
            //     return new Account(auto_account_id, account.getUsername(), account.getPassword());
            // }

            //Query for the newly added account for the generated account id
            String sql2 = "SELECT account_id FROM account WHERE username = ?";
            PreparedStatement ps2 = connection.prepareStatement(sql2);

            ps2.setString(1, account.getUsername());

            ResultSet rs2 = ps2.executeQuery();

            //Return the new account
            while (rs2.next()) {
                System.out.println("Found something! => " + rs2.getInt(1));
                account.setAccount_id(rs2.getInt(1));
                return account;
            }


        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        //If account creation was not valid
        return null;
    }

    /**
     * Retrieve an account username to see if it exists
     * @param account
     * @return the account if an account with that username exists
     */
    public Account getAccountFromUsername(String username) {
        System.out.println("Searching for dupe username...");
        Connection connection = ConnectionUtil.getConnection();

        try {
            //General SQL logic
            String sql = "SELECT * FROM account WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //Preparedstatement parameters
            preparedStatement.setString(1, username);

            //Execute Preparedstatement
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                System.out.println("Dupe found!");
                return new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password") );
            }

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        //If no username was found
        return null;

    }

}
