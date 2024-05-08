package DAO;


import Util.ConnectionUtil;
import Model.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The DAO for Message
 */
public class MessageDAO {

    Connection connection = ConnectionUtil.getConnection();

    /**
     * Adds a new message into the database
     * 
     * @param message the message to be added
     * @return message added, along with updated message id
     */
    public Message insertMessage(Message message) {

        System.out.println("Attempting to post a new message in the DAO...");
        Connection connection = ConnectionUtil.getConnection();

        try {
            //General SQL logic
            String sql = "INSERT INTO message(posted_by, message_text, time_posted_epoch) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //Preparedstatement parameters
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());
            
            preparedStatement.executeUpdate();

            //Get the newly added message with the new message id
            String sql2 = "SELECT message_id FROM message WHERE posted_by = ?";
            PreparedStatement ps2 = connection.prepareStatement(sql2);

            ps2.setInt(1, message.getPosted_by());

            ResultSet rs2 = ps2.executeQuery();

            //Return the new account
            while (rs2.next()) {
                message.setMessage_id(rs2.getInt("message_id") + 1); //Not sure what the issue is with autoincrement, but adding an additional +1
                return message;
            }


        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        //If account creation was not valid
        return null;
    }

    /**
     * Retrieves ALL messages from the database
     * 
     * @return List representing all of the messages
     */
    public List<Message> getAllMessages() {

        System.out.println("Getting all messages...");
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<Message>();

        try {

            //General SQL Logic to retrieve messages
            String sql = "SELECT * FROM message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            //Add messages to list
            while(rs.next()) {
                Message message = new Message(
                    rs.getInt("posted_by"), 
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch"));

                message.setMessage_id(rs.getInt("message_id"));

                messages.add(message);
            }

        } catch(SQLException e) {
            System.out.println(e.getMessage()); 
        }

        return messages;
    }


    /**
     * Retrieve a specific message from its ID
     * 
     * @param message_id ID of the message to be retrieved
     * @return the message
     */
    public Message retrieveMessageByID(int message_id) {

        Connection connection = ConnectionUtil.getConnection();

        try {

            //SQL Logic for selecting the message by its ID
            String sql = "SELECT * FROM message WHERE message_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, message_id);

            ResultSet rs = ps.executeQuery();

            System.out.println("so far so good");
            //Return the message
            while(rs.next()) {
                return new Message(
                    message_id, 
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch"));
            }

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        //If no message was found
        return null;

    }

    /**
     * Deletes a message specified by its ID
     * 
     * @param message_id the ID of the message we want to delete
     * @return the deleted message, null if there was no message deleted
     */
    public Message deleteMessage(int message_id) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            //SQL logic to detect if message exists
            Message delMessage = this.retrieveMessageByID(message_id);

            //SQL logic to delete message
            if (delMessage != null) {
                String sql = "DELETE FROM message WHERE message_id = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, message_id);

                ps.executeUpdate();

                //Return deleted message
                return delMessage;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;

    }
    
}
