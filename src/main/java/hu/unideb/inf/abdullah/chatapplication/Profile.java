package hu.unideb.inf.abdullah.chatapplication;

import hu.unideb.inf.abdullah.chatapplication.sockets.ChatClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Profile {

    private StringProperty firstName = new SimpleStringProperty();
    private String lastName;
    private String phoneNumber;
    private String userName;
    private String password;
    public ChatClient client;

    private String chatHistory = new String("");

    public Profile() {
    }

    public Profile(StringProperty firstName, String lastName, String phoneNumber, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.password = password;
    }

    public StringProperty getFirstName() {
        return firstName;
    }

    public void setFirstName(StringProperty firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {return phoneNumber;}

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void appendChatHistory(String string){
        this.chatHistory = string;
    }

    public String getChatHistory() {
        return chatHistory;
    }

}
