package Service;

import Model.Message; 
import DAO.MessageDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for message logic
 */
public class MessageService {
    

    private MessageDAO messageDAO;

    /**
     * Default constructor for MessageService
     */
    public MessageService() {
        this.messageDAO = new MessageDAO();
    }

    /**
     * MessageService constructor when MessageDAO is provided
     * @param messageDAO
     */
    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    /**
     * Posts a new message
     * A message can only be posted if:
     * Message > 0 chars
     * Message < 256 char
     * 
     * @param message Message being posted
     * @return newly posted message, null if not a valid message
     */
    public Message postMessage(Message message){

        Message postedMessage = messageDAO.insertMessage(message);

        if (postedMessage != null && message.getMessage_text().length() > 0) {
            return postedMessage;
        }

        return null;
    }

    /**
     * Retrieves all the messages from the database
     * 
     * @return List of all the messages within the database
     */
    public List<Message> retrieveMessages() {
        return messageDAO.getAllMessages();
    }

    /**
     * Retrieves a specific message by its ID
     * 
     * @param message_id the ID for the message we want to retrieve
     * @return the message
     */
    public Message getMessageByID(int message_id) {
        return messageDAO.retrieveMessageByID(message_id);

    }

    /**
     * Delete a message by its ID
     * 
     * @param message_id the ID for the message we want to delete
     * @return the deleted message if succesful, null otherwise
     */
    public Message deleteMessageByID(int message_id) {
        return messageDAO.deleteMessage(message_id);
    }
}
