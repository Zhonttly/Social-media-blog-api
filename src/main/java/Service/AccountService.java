package Service;

import Model.Account; 
import DAO.AccountDAO;

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


        //Check if the account is valid and unique
        if(account.getUsername().length() > 0
            && account.getUsername().length() < 256
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
