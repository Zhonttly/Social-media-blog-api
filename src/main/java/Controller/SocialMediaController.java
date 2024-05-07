package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {

        //Set up Javalin
        Javalin app = Javalin.create();

        //Create endpoints
        app.post("/register", this::postNewAccountHandler);
        app.post("/login", this::postLoginHandler);
        app.post("/message", this::postMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/accounts/{account_id}/messages", this::getMessageByIDHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByID);
        app.patch("/messages/{message_id}", this::patchMessageByID);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByID);

        //Establish connection at port
        app.start(8080);

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

    }

    /**
     * Handler to verify/post a login. 
     * 
     * @param context the context object handles information HTTP requests and generates responses within Javalin.
     * @throws JsonProcessingException
     */
    private void postLoginHandler(Context context) throws JsonProcessingException {

    }

    /**
     * Handler to post a message. 
     * 
     * @param context the context object handles information HTTP requests and generates responses within Javalin.
     * @throws JsonProcessingException
     */
    private void postMessageHandler(Context context) throws JsonProcessingException {

    }

    /**
     * Handler to retrieve ALL messages
     * @param context the context object handles information HTTP requests and generates responses within Javalin. 
     */
    private void getAllMessagesHandler(Context context) {

    }

    /**
     * Handler to retrieve a message by its ID
     * @param context the context object handles information HTTP requests and generates responses within Javalin. 
     */
    private void getMessageByIDHandler(Context context) {

    }

    /**
     * Handler to delete a message by its ID
     * @param context the context object handles information HTTP requests and generates responses within Javalin. 
     */
    private void deleteMessageByID(Context context) {

    }

    /**
     * Handler to update/patch a message by its ID
     * @param context the context object handles information HTTP requests and generates responses within Javalin. 
     */
    private void patchMessageByID(Context context) {

    }

    /**
     * Handler to retrieve ALL messages from an account ID
     * @param context the context object handles information HTTP requests and generates responses within Javalin. 
     */
    private void getAllMessagesByID(Context context) {

    }



}