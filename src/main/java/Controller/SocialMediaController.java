package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;

import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;

    /**
     * Default constructor for the controller
     */
    public SocialMediaController() {
        accountService = new AccountService();
        messageService = new MessageService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        System.out.println("Starting Javalin...");

        //Set up Javalin
        Javalin app = Javalin.create();

        //Create endpoints
        app.post("/register", this::postNewAccountHandler);
        app.post("/login", this::postLoginHandler);
        app.post("/messages", this::postMessagesHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/accounts/messages/{message_id}", this::getMessageByIDHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIDHandler);
        app.patch("/messages/{message_id}", this::patchMessageByIDHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByIDHandler);

        //Return Javalin object
        return app; 
    }


    /**
     * Handler to create/post a new account. 
     * 
     * @param context the context object handles information HTTP requests and generates responses within Javalin.
     * @throws JsonProcessingException
     */
    private void postNewAccountHandler(Context context) throws JsonProcessingException {

        System.out.println("PostNewAccountHandler was invoked!");

        //Create a mapper to convert the JSON representation of the message to an account object
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);

        //Add account info to DAO
        Account newAccount = accountService.addAccount(account);

        //Return the account, including account ID. Returns an error if the account registration was NOT successful
        if(newAccount!=null){
            context.json(mapper.writeValueAsString(newAccount));
        }else{
            context.status(400);
        }
    }


    /**
     * Handler to verify/post a login. 
     * 
     * @param context the context object handles information HTTP requests and generates responses within Javalin.
     * @throws JsonProcessingException
     */
    private void postLoginHandler(Context context) throws JsonProcessingException {
        System.out.println("PostLoginHandler was invoked!");

        //Get account from user input
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);

        //Check if account information exists in database
        Account verifiedAccount = accountService.verifyLogin(account);

        //Return the account if successful, otherwise returns a 401 error
        if(verifiedAccount != null){
            context.json(mapper.writeValueAsString(verifiedAccount));
        }else{
            context.status(401);
        }
    }

    /**
     * Handler to post a message. 
     * 
     * @param context the context object handles information HTTP requests and generates responses within Javalin.
     * @throws JsonProcessingException
     */
    private void postMessagesHandler(Context context) throws JsonProcessingException {
        
        //Create a mapper to convert the JSON representation of the message to a message object
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);

        //Submit the message to the social media page through the service class to the DAO
        Message postedMessage = messageService.postMessage(message);

        //Return the body of the message and message ID. Returns an error if the message was NULL
        if(postedMessage != null){
            context.json(mapper.writeValueAsString(postedMessage));
        }else{
            context.status(400);
        }



    }

    /**
     * Handler to retrieve ALL messages
     * 
     * @param context the context object handles information HTTP requests and generates responses within Javalin. 
     */
    private void getAllMessagesHandler(Context context) {

    }

    /**
     * Handler to retrieve a message by its ID
     * 
     * @param context the context object handles information HTTP requests and generates responses within Javalin. 
     */
    private void getMessageByIDHandler(Context context) {

    }

    /**
     * Handler to delete a message by its ID
     * 
     * @param context the context object handles information HTTP requests and generates responses within Javalin. 
     */
    private void deleteMessageByIDHandler(Context context) {

    }

    /**
     * Handler to update/patch a message by its ID
     * 
     * @param context the context object handles information HTTP requests and generates responses within Javalin. 
     */
    private void patchMessageByIDHandler(Context context) {

    }

    /**
     * Handler to retrieve ALL messages from an account ID
     * 
     * @param context the context object handles information HTTP requests and generates responses within Javalin. 
     */
    private void getAllMessagesByIDHandler(Context context) {

    }



}