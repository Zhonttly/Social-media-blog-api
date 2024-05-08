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
     */
    public Message postMessage(Message message){

        Message postedMessage = messageDAO.insertMessage(message);

        if (postedMessage != null && message.getMessage_text().length() > 0) {
            return postedMessage;
        }

        return null;
    }

}
