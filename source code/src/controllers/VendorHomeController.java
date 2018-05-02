package controllers;

/**
 * @author hpatel111
 * implementation class VendorHomeController which handles the Sub part of vendor Home screen module where different javaFx elements are used
 * getting values from them and sending to the DAO through DTO and accessing data to database. And displaying to screen and handled exception as well.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import exceptions.BpsDataBaseException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.dao.VendorDAO;
import model.dto.VendorDTO;
import javafx.fxml.Initializable;

public class VendorHomeController implements Initializable{


	@FXML private TextArea feedback;
	
	@FXML private Label showId;
	
	
	public void getUser(String custId){
		showId.setText(custId);
	}
	
	
	
	public void sendFeedback(ActionEvent event) throws BpsDataBaseException{
		
		String feed = feedback.getText();
		if(feed.equals("")){
			alertError("Enter Feedback first");
		}else{
		VendorDTO venDTO = new VendorDTO();
		venDTO.setFeedback(feed);
		System.out.println(showId.getText());
		venDTO.setVendorId(showId.getText());
		
		VendorDAO vDAO = new VendorDAO();
		VendorDTO vRes = vDAO
				.vendorFeedback(venDTO);
		
		if(vRes.isStatus()){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setHeaderText("Success");
			alert.setContentText("Feedback Sent successfully.");
			alert.showAndWait();
			feedback.clear();
		}else{
			alertError("Vendor couldn't post feedback due to system problem.");
			
		}
		}
		
	}

	
	public void logOut(ActionEvent event){
		
		setStage("/view/LoginView.fxml",event,"Welcome User",500,500);
	}
	
	public void updateForm(ActionEvent event){
		setStage("/view/VendorUpdate.fxml",event,"Vendor Updation",800,700);
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
	
	
public void setStage(String file,ActionEvent event,String title,int width,int height){
		
		try {
			FXMLLoader loader = new FXMLLoader();
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Pane root = loader.load(getClass().getResource(file).openStream());
			Scene scene = new Scene(root,width,height);	
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
}
