package hu.unideb.inf.abdullah.chatapplication;

import hu.unideb.inf.abdullah.chatapplication.sockets.ChatClient;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;

public class HelloController {

    static private Model model = new Model();


    public void setModel(Model model) {
        HelloController.model = model;
    }

    //Profile elements
    @FXML
    private Label labelProfileFirstName = new Label();


    @FXML
    private Label labelProfileLastName = new Label();

    @FXML
    private Label phonenumberProfileLabel = new Label();

    @FXML
    private Label usernameProfileLabel = new Label();

    //Sign up elements

    @FXML
    private TextField firstNameTextSignUpTextField;

    @FXML
    private TextField lastNameTextSignUpTextField;

    @FXML
    private TextField passwordTextSignUpTextField;

    @FXML
    private TextField phoneNumberTextSignUpTextField;

    @FXML
    private TextField userNameTextSignUpTextField;


    //Login fxml elements
    @FXML
    private TextField UserNameTextField;

    @FXML
    private TextField passwordTextfield;

//Messaging fxml elements
    @FXML
    static private TextArea historyTextBox = new TextArea();

    @FXML
    private Button sendingButton;

    @FXML
    private TextArea sendingTextBox;

    //profile fxml elements
    @FXML
    private Text firstName;

    @FXML
    private Text firstNameText;

    @FXML
    private Text lastNAmeText;

    @FXML
    private Text phoneNumberText;

    //ini method
    @FXML
    public void initialize() {
        labelProfileFirstName.textProperty().bind(model.getProfile().getFirstName());
        labelProfileLastName.setText(model.getProfile().getLastName());
        phonenumberProfileLabel.setText(model.getProfile().getPhoneNumber());
        usernameProfileLabel.setText(model.getProfile().getUserName());
        historyTextBox.appendText(model.getProfile().getChatHistory());
    }

    @FXML
    private void handleSendButtonPressed(){
       if (!sendingTextBox.getText().isBlank()){
           Platform.runLater(() -> {
               System.out.println("meow");
               System.out.println(model.getProfile().getFirstName().getValue() + " : " +  sendingTextBox.getText()  + "\n");
               historyTextBox.appendText(model.getProfile().getFirstName().getValue() + " : " +  sendingTextBox.getText()  + "\n" );
           });
           model.getProfile().client.sendMessage(sendingTextBox.getText());
           model.getProfile().appendChatHistory(historyTextBox.getText());
           historyTextBox.appendText(model.getProfile().getChatHistory());
        }
        sendingTextBox.clear();
    }

    @FXML
    void handleClearButtonPressed() {
        sendingTextBox.clear();
    }
    //show the Setting window
    @FXML
    void handleSettingButtonPressed() {
        Platform.runLater(() -> {
            // code to update UI
            try {
                App.setRoot("Setting.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
    //show the Messaging Window
    @FXML
    static void handleMessagesButtonPressed() throws IOException {
        App.setRoot("Messaging.fxml");
        if (!model.getProfile().getChatHistory().isBlank()){
            historyTextBox.appendText(model.getProfile().getChatHistory());
        }

    }
    //Show the Profile window
    @FXML
    void handleProfileButtonPressed() throws IOException {
        App.setRoot("profile.fxml");
    }
    @FXML
    void handleLoginButtonPressed( ) {

    }

    @FXML
    void handleSignUpButtonPressed( ) throws IOException {
        App.setRoot("SignUp.fxml");
    }
//still need to work on to prevent from inserting letters in phone number field
    @FXML
    void handleCreateAccountButton() throws IOException  {
        if (!(firstNameTextSignUpTextField.getText().isBlank())&&!(lastNameTextSignUpTextField.getText().isBlank())&&!(userNameTextSignUpTextField.getText().isBlank())&&!(phoneNumberTextSignUpTextField.getText().isBlank())&&!(passwordTextSignUpTextField.getText().isBlank())){
            model.getProfile().setFirstName(new SimpleStringProperty(firstNameTextSignUpTextField.getText()));
            model.getProfile().setLastName(lastNameTextSignUpTextField.getText());
            model.getProfile().setUserName(userNameTextSignUpTextField.getText());
            model.getProfile().setPassword(passwordTextSignUpTextField.getText());
            model.getProfile().setPhoneNumber(phoneNumberTextSignUpTextField.getText());
            try {
                model.getProfile().client = new ChatClient();
                Thread thread = new Thread(model.getProfile().client);
                thread.start();
            }catch (Exception e){
                System.err.println("Error: " + e);
            }
            App.setRoot("Messaging.fxml");
        }
    }

     /*public static void updateHistoryBox(String message) throws IOException {

         Platform.runLater(() -> {
             System.out.println(message);
             model.getProfile().appendChatHistory(message);
             System.out.println(model.getProfile().getChatHistory());
             try {
                 handleMessagesButtonPressed();
             } catch (IOException e) {
                 throw new RuntimeException(e);
             }
             historyTextBox.appendText(model.getProfile().getChatHistory());         });
     }

      */

}
