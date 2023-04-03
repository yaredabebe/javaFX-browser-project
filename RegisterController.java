package application;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class RegisterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView profileImage;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    void haveAccount(MouseEvent event) {
    	
    }

    @FXML
    void register(ActionEvent event) {

    }
    @FXML
    void noAccount(ActionEvent event) {

    }
    

    @FXML
    void initialize() {
        assert profileImage != null : "fx:id=\"profileImage\" was not injected: check your FXML file 'Register.fxml'.";
        assert emailField != null : "fx:id=\"emailField\" was not injected: check your FXML file 'Register.fxml'.";
        assert nameField != null : "fx:id=\"nameField\" was not injected: check your FXML file 'Register.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'Register.fxml'.";
        assert registerButton != null : "fx:id=\"registerButton\" was not injected: check your FXML file 'Register.fxml'.";

    }
}
