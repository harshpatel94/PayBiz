package controllers;

/**
 * @author hpatel111
 * implementation class CustomerHomeController which handles the Main Home screen of the customer where different javaFx elements are used
 * getting values from them and sending to the DAO through DTO and accessing data to database. And displaying to screen and handled exception as well.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import exceptions.BpsDataBaseException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.dao.CustomerRegistrationDAO;
import model.dto.CustomerRegistrationDTO;
import model.dto.CustomerResponseDTO;

public class CustomerHomeController implements Initializable {

	
	@FXML private TextArea feedback;
	
	@FXML private Label showId;
	
	
	public void getUser(String custId){
		showId.setText(custId);
	}
	
	
	
	public void sendFeedback(ActionEvent event) throws BpsDataBaseException{
		
		String feed = feedback.getText();
		if(feed.equals("")){
			alertError("Enter feedback first");
		}else{
			
		CustomerRegistrationDTO customerRegistrstionDTO = new CustomerRegistrationDTO();
		customerRegistrstionDTO.setFeedback(feed);
		System.out.println(showId.getText());
		customerRegistrstionDTO.setCustomerId(showId.getText());
		
		CustomerRegistrationDAO customerUpdDAO = new CustomerRegistrationDAO();
		CustomerResponseDTO custRes = customerUpdDAO
				.customerFeedBack(customerRegistrstionDTO);
		
		if(custRes.isStatus()){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setHeaderText("Success");
			alert.setContentText("Feedback Sent successfully.");
			alert.showAndWait();
			feedback.clear();
		}else{
			alertError("Customer couldn't post feedback due to system problem.");
			
		}
		}
		
	}
	
	public void goBillPayment(ActionEvent event){
		FXMLLoader loader = new FXMLLoader();
		try {
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Pane root = loader.load(getClass().getResource("/view/BillPayment.fxml").openStream());
			Scene scene = new Scene(root,800,700);		
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setTitle("Welcome User");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			
			System.out.println(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void logOut(ActionEvent event){
		FXMLLoader loader = new FXMLLoader();
		try {
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Pane root = loader.load(getClass().getResource("/view/LoginView.fxml").openStream());
			Scene scene = new Scene(root,500,500);		
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setTitle("Welcome User");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void goToUpdation(ActionEvent event){
		FXMLLoader loader = new FXMLLoader();
		try {
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Pane root = loader.load(getClass().getResource("/view/CustomerUpdate.fxml").openStream());
			Scene scene = new Scene(root,800,700);		
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setTitle("Customer Updation");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void alertError(String Message){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("Error:");
		alert.setContentText(Message);
		alert.showAndWait();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
