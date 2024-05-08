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
                System.out.println("Found message id =>" + rs2.getInt("message_id"));
                message.setMessage_id(rs2.getInt("message_id") + 1); //Not sure what issue is with autoincrement, but adding an additional +1
                return message;
            }


        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        //If account creation was not valid
        return null;
    }
    
}
