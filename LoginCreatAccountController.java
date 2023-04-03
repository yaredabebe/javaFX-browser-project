package application;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginCreatAccountController {

	  @FXML
	    private AnchorPane LogIn;

	    @FXML
	    private TextField liEmailField;

	    @FXML
	    private PasswordField liPassword;

	    @FXML
	    private Button loginBut;

	    @FXML
	    private Hyperlink liLink;

	    @FXML
	    private Text lgErrorEmail;

	    @FXML
	    private Text lgErrorPassword;

	    @FXML
	    private Text lgErrorLoginFailed;

	    @FXML
	    private AnchorPane CreateAccount;

	    @FXML
	    private TextField caEmailField;

	    @FXML
	    private PasswordField caPassword;

	    @FXML
	    private Button creatAccountBut;

	    @FXML
	    private Hyperlink caLink;

	    @FXML
	    private TextField caFristNameField;

	    @FXML
	    private TextField caLastNameField;

	    @FXML
	    private Text caErrorEmailAddress;

	    @FXML
	    private Text caErrorFristName;

	    @FXML
	    private Text caErrorLastName;

	    @FXML
	    private Text caErrorPassword;

	    @FXML
	    private Text caSuccses;
	    
	    @FXML
	    private Text caErrorEmailExist;
	    

	    private Stage newWindow=new Stage();

    @FXML
    void changePage(ActionEvent event) {
    	if(event.getSource()==caLink) {
    		LogIn.setVisible(true);
    		CreateAccount.setVisible(false);
    	}else if(event.getSource()==liLink) {
    		LogIn.setVisible(false);
    		CreateAccount.setVisible(true);
    	}
    }

    @FXML
    void creatAccount(ActionEvent event) {
    	if(validCreatForm()) {
    		String Sql="INSERT INTO `user`( `fName`, `lName`, `email`, `password`) VALUES (?,?,?,?)";
        	String Sql2="SELECT `ID`, `fName`, `lName`, `email`, `password` FROM `user` WHERE email=?";
    		try {
    			Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/webbrowser","root","");
    			PreparedStatement ps=con.prepareStatement(Sql);
    			PreparedStatement ps2=con.prepareStatement(Sql2);
    			ps2.setString(1,  caEmailField.getText().toUpperCase());
    			ResultSet rs = ps2.executeQuery();
    			if(rs.next()) {
    				caSuccses.setVisible(false);
    				caErrorEmailExist.setVisible(true);
    			}
    			else {
    				ps.setString(1, caFristNameField.getText().toUpperCase());
        			ps.setString(2, caLastNameField.getText().toUpperCase());
        			ps.setString(3, caEmailField.getText().toUpperCase());
        			ps.setString(4, caPassword.getText().toUpperCase());
        			ps.executeUpdate();

    				caErrorEmailExist.setVisible(false);
        			caSuccses.setVisible(true);
    			}
    		} catch (Exception e) {
    			System.out.println(e.getMessage());
    			e.printStackTrace();
    			
    		}
    	}
    	

    }
    private boolean validCreatForm() {
    	int c=0;
    	if(caFristNameField.getText().isEmpty()){
    		caErrorFristName.setText("Enter Frist Name");
    		caErrorFristName.setVisible(true);
    		c++;
    	}else {
    		if(caFristNameField.getText().contains("!")||
    				caFristNameField.getText().contains("@")||
    				caFristNameField.getText().contains("#")||
    				caFristNameField.getText().contains("$")||
    				caFristNameField.getText().contains("%")||
    				caFristNameField.getText().contains("^")||
    				caFristNameField.getText().contains("&")||
    				caFristNameField.getText().contains("*")||
    				caFristNameField.getText().contains("(")||
    				caFristNameField.getText().contains(")")||
    				caFristNameField.getText().contains("_")||
    				caFristNameField.getText().contains("-")||
    				caFristNameField.getText().contains("+")||
    				caFristNameField.getText().contains("=")||
    				caFristNameField.getText().contains("{")||
    				caFristNameField.getText().contains("[")||
    				caFristNameField.getText().contains("}")||
    				caFristNameField.getText().contains("]")||
    				caFristNameField.getText().contains("|")||
    				caFristNameField.getText().contains("'")||
    				caFristNameField.getText().contains("\"")||
    				caFristNameField.getText().contains("\\")||
    				caFristNameField.getText().contains(";")||
    				caFristNameField.getText().contains(":")||
    				caFristNameField.getText().contains(".")||
    				caFristNameField.getText().contains(">")||
    				caFristNameField.getText().contains("/")||
    				caFristNameField.getText().contains("?")||
    				caFristNameField.getText().contains("<")||
    				caFristNameField.getText().contains(",")){
    			caErrorFristName.setText("Enter Correct Frist Name");
        		caErrorFristName.setVisible(true);
        		c++;
    		}else if(caFristNameField.getText().length()<4) {
    			caErrorFristName.setText("Enter Correct Frist Name");
        		caErrorFristName.setVisible(true);
    		}
    		else {    			
    			caErrorFristName.setVisible(false);
    		}
    	}
    	if(caLastNameField.getText().isEmpty()) {
    		caErrorLastName.setText("Enter Last Name");
    		caErrorLastName.setVisible(true);c++;
    	}else {
    		if(caLastNameField.getText().contains("!")||
    				caLastNameField.getText().contains("@")||
    				caLastNameField.getText().contains("#")||
    				caLastNameField.getText().contains("$")||
    				caLastNameField.getText().contains("%")||
    				caLastNameField.getText().contains("^")||
    				caLastNameField.getText().contains("&")||
    				caLastNameField.getText().contains("*")||
    				caLastNameField.getText().contains("(")||
    				caLastNameField.getText().contains(")")||
    				caLastNameField.getText().contains("_")||
    				caLastNameField.getText().contains("-")||
    				caLastNameField.getText().contains("+")||
    				caLastNameField.getText().contains("=")||
    				caLastNameField.getText().contains("{")||
    				caLastNameField.getText().contains("[")||
    				caLastNameField.getText().contains("}")||
    				caLastNameField.getText().contains("]")||
    				caLastNameField.getText().contains("|")||
    				caLastNameField.getText().contains("'")||
    				caLastNameField.getText().contains("\"")||
    				caLastNameField.getText().contains("\\")||
    				caLastNameField.getText().contains(";")||
    				caLastNameField.getText().contains(":")||
    				caLastNameField.getText().contains(".")||
    				caLastNameField.getText().contains(">")||
    				caLastNameField.getText().contains("/")||
    				caLastNameField.getText().contains("?")||
    				caLastNameField.getText().contains("<")||
    				caLastNameField.getText().contains(",")){
    			caErrorLastName.setText("Enter Correct Frist Name");
    			caErrorLastName.setVisible(true);c++;
    		}else if(caLastNameField.getText().length()<4) {
    			caErrorLastName.setText("Enter Correct Last Name");
    			caErrorLastName.setVisible(true);
    		}
    		else {    			
    			caErrorLastName.setVisible(false);
    		}
    	} if(caEmailField.getText().isEmpty()){
    		caErrorEmailAddress.setVisible(true);c++;
    	}else {
    		caErrorEmailAddress.setVisible(false);
    	}if(caPassword.getText().isEmpty()){
    		caErrorPassword.setVisible(true);c++;
    	}else {
    		if(caPassword.getText().length()<5) {
    			caErrorPassword.setText("Password Must Be Greater Than 5");
    			caErrorPassword.setVisible(true);c++;
    		}else {    			
    			caErrorPassword.setVisible(false);
    			
    		}

    	}
    	if(c==0) {
    		
    		return true;
    	}
    	return false;
    }
    private boolean validLoginForm() {
    	int c=0;
    	if(liEmailField.getText().isEmpty()) {
    		lgErrorEmail.setText("Enter Email Address");
    		lgErrorEmail.setVisible(true);c++;
    	}else {
    		lgErrorEmail.setVisible(false);
    	}
    	if(liPassword.getText().isEmpty()) {
    		lgErrorPassword.setText("Enter Email Address");
    		lgErrorPassword.setVisible(true);c++;
    	}else {
    		lgErrorPassword.setVisible(false);
    	}
    	if(c==0) {
    		return true;
    	}
    	return false;
    }
    @FXML
    void login(ActionEvent event) {
    	if(validLoginForm()) {
    		String Sql2="SELECT `ID`, `fName`, `lName`, `email`, `password` FROM `user` WHERE email=?";
    		
    		try {
    			String fName,lName,email,password;
        		int id;
    			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webbrowser","root","");
    			PreparedStatement ps2=con.prepareStatement(Sql2);
    			ps2.setString(1,  liEmailField.getText().toUpperCase());
    			ResultSet rs = ps2.executeQuery();
    			if(rs.next()) {
    				id=rs.getInt("ID");
    				fName=rs.getString("fName");
    				lName=rs.getString("lName");
    				email=rs.getString("email");
    				password=rs.getString("password");
    				
    				if(liPassword.getText().contentEquals(password)) {
    					Seation.setSeation(id, fName, lName, email, password);
    					lgErrorLoginFailed.setVisible(false);
    					
    					try {
    						Stage s=(Stage)loginBut.getScene().getWindow();
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
    					lgErrorLoginFailed.setText("Inccorect Password or Email ");
    					lgErrorLoginFailed.setVisible(true);
    				}
    			}else{
    				lgErrorLoginFailed.setText("Account Not Exist");
    				lgErrorLoginFailed.setVisible(true);
    			}
    		} catch (Exception e) {
    			System.out.println(e.getMessage());
    			e.printStackTrace();
    			
    		}
    	}
    }

    @FXML
    void initialize() {
    	
       
    }
}