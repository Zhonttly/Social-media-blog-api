package Service;

import Model.Account; 
import DAO.AccountDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for account logic
 */
public class AccountService {
    private AccountDAO accountDAO;

    /**
     * Default constructor for AccountService
     */
    public AccountService() {
        this.accountDAO = new AccountDAO(); 
    }

    /**
     * AccountService constructor when AccountDAO is provided
     * @param accountDAO
     */
    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    /**
     * Adds a new Account if legal. Does not create a new account if 
     * username is NULL
     * username already exists
     * password is less than 4 characters
     */
    public Account addAccount(Account account) {

        System.out.println("addAccount from the Service class invoked!");

        //Check if the account is valid and unique
        if(account.getUsername().length() > 0
            && accountDAO.getAccountFromUsername(account.getUsername()) == null 
            && account.getPassword().length() >= 4) {

            return accountDAO.createAccount(account);
        }
        
        return null;
    }

    /**
     * Checks if the username and password matches an account's respective user and 
     * password in the database
     * 
     * @param account Account to be verified for login credentials
     * @return True if login credentials match existing account, false otherwise
     */
    public Account verifyLogin(Account account) {

        Account searchAccount = accountDAO.getAccountFromUsername(account.getUsername());

        if (searchAccount != null && searchAccount.getPassword().equals(account.getPassword())) {
            return searchAccount;
        }

        return null;

    }

}
