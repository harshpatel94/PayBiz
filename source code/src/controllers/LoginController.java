package controllers;

/**
 * @author hpatel111
 * implementation class LoginController which handles the Login module where different javaFx elements are used
 * getting values from them and sending to the DAO through DTO and accessing data to database. And displaying to screen and handled exception as well.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.dao.LoginDAO;
import model.dto.LoginRequestDTO;
import model.dto.LoginResponseDTO;

public class LoginController implements Initializable {
	

	LoginRequestDTO loginReqDTO = new LoginRequestDTO();
	
	@FXML
	private TextField userid;
	
	@FXML
	private TextField password;

	
	@FXML
	private ComboBox<String> userType;
	private ObservableList<String> myComboBoxData = FXCollections.observableArrayList("Admin","Customer","Vendor");

	
	
	
	/**
	 * The constructor (is called before the initialize()-method).
	 */
	
	public void Login(ActionEvent event) throws Exception{
		
		String userId = userid.getText();
		String pass = password.getText();
		String priviledge = userType.getValue();
		if(userId.equals("")||pass.equals("") || priviledge.equals("")){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("Enter Details first!");
			alert.showAndWait();
		}else{
		
		if(priviledge.equals("Admin")){
			
			boolean status = Check(userId,pass,"A");
					if(status == true){
						FXMLLoader loader = new FXMLLoader();
						setStage("/view/Main.fxml",event,loader,"Welcome Admin.");
					}else{
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Error:");
						alert.setContentText("Credentials Mis-Match. Try Again!");
						alert.showAndWait();
					}	
		}else if(priviledge.equals("Customer")){
			boolean status = Check(userId,pass,"C");
			if(status == true){
				try {
					FXMLLoader loader = new FXMLLoader();
					((Node)event.getSource()).getScene().getWindow().hide();
					Stage primaryStage = new Stage();
					Pane root = loader.load(getClass().getResource("/view/CustomerHome.fxml").openStream());
					CustomerHomeController custControl = (CustomerHomeController)loader.getController();
					custControl.getUser(userid.getText());
					Scene scene = new Scene(root,700,500);	
					primaryStage.setTitle("Welcome Customer");
					scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
					primaryStage.setScene(scene);
					primaryStage.show();
				} catch (IOException e) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Error:");
					alert.setContentText("Something went wrong!");
					alert.showAndWait();
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Error:");
				alert.setContentText("Credentials Mis-Match. Try Again!");
				alert.showAndWait();
			}
		}else{
			boolean status = Check(userId,pass,"V");
			if(status == true){
				try {
					FXMLLoader loader = new FXMLLoader();
					((Node)event.getSource()).getScene().getWindow().hide();
					Stage primaryStage = new Stage();
					Pane root = loader.load(getClass().getResource("/view/VendorHome.fxml").openStream());
					VendorHomeController custControl = (VendorHomeController)loader.getController();
					custControl.getUser(userid.getText());
					Scene scene = new Scene(root,700,500);	
					primaryStage.setTitle("Welcome Vendor");
					scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
					primaryStage.setScene(scene);
					primaryStage.show();
				} catch (IOException e) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Error:");
					alert.setContentText("Something went wrong!");
					alert.showAndWait();
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Error:");
				alert.setContentText("Credentials Mis-Match. Try Again!");
				alert.showAndWait();
			}
		}
		}
		
	}
	
	public boolean Check(String userId,String pass,String priviledge){
		boolean status = false;
		try{
			loginReqDTO = new LoginRequestDTO(userId,pass,priviledge);
			LoginDAO loginDAO = new LoginDAO();
			
			LoginResponseDTO loginResDTO = loginDAO.login(loginReqDTO);
			status = loginResDTO.isStatus();
			System.out.println(status);
		}catch(Exception e){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("Message from system:"+e.getMessage());
			alert.showAndWait();
		}
		
		return status;
	}
	
	public void setStage(String file,ActionEvent event,FXMLLoader loader,String title){
		
		try {
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Pane root = loader.load(getClass().getResource(file).openStream());
			Scene scene = new Scene(root,800,700);	
			scene.getStylesheets().add("/application/application.css");
			primaryStage.setTitle(title);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("Something went wrong!");
			alert.showAndWait();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		userType.setItems(myComboBoxData);
	}
	
	

}
