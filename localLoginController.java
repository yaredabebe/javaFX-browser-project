package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class localLoginController {

	@FXML
    private PasswordField password;

    @FXML
    private Button logInBut;

    @FXML
    private Text name;

    @FXML
    private Text errorPasswordField;

    @FXML
    private Text incorectPassword;
    
    
    private String Password="";
    
    private Stage newWindow=new Stage();

    @FXML
    void initialize() {
    	String  fName,lName;
		fName=Seation.getSeationFname();
		lName=Seation.getSeationLname();
		Password=Seation.getSeationPassword();
		this.name.setText(fName+" "+lName);
		this.name.setTextAlignment(TextAlignment.CENTER);
    }
    
    public void localLogin(ActionEvent event) {
    	if(password.getText().isEmpty()) {
    		errorPasswordField.setText("Enter Passwor");
    		errorPasswordField.setVisible(true);
    	}else {
    		System.out.println(Password);
    		if(password.getText().contentEquals(Password)) {
    			errorPasswordField.setVisible(false);
    			try {
					Stage s=(Stage)logInBut.getScene().getWindow();
					s.close();
					Parent browser=FXMLLoader.load(getClass().getResource("ParentPage.fxml"));
					Scene secondScene = new Scene(browser);
					newWindow.setScene(secondScene);
					newWindow.setResizable(false);
					newWindow.setTitle("Parent Control");
					newWindow.show();
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
    		}else {
    			incorectPassword.setText("Incorect Password");
    			incorectPassword.setVisible(true);
    		}
    	}
    	
    }
}
