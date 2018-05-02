package controllers;


/**
 * @author hpatel111
 * implementation class VendorTypeAddController which handles the Adding Vendor Type to database module where different javaFx elements are used
 * getting values from them and sending to the DAO through DTO and accessing data to database. And displaying to screen and handled exception as well.
 */

import java.io.IOException;

import exceptions.BpsDataBaseException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.dao.VendorTypeDAO;
import model.dto.VendorTypeDTO;

public class VendorTypeAddController {
	
	
	@FXML
	private TextField vendorType;
	
	@FXML
	private TextField vendorAmount;
	
	@FXML
	private Button btnDetails;
	
	
	public void addVendorType(ActionEvent event){
		String venType = vendorType.getText();
		String venAmount = vendorAmount.getText();
		if(venType.equals("") || venAmount.equals("")){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("Enter Details first.");
			alert.showAndWait();
		}else{
	
			
		try{
			VendorTypeDTO venDTO = new VendorTypeDTO(venType,venAmount);
			VendorTypeDAO venDAO = new VendorTypeDAO();
			venDAO.insertVendorInfo(venDTO);
			
			if(venDTO.isStatus()){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success Dialog");
				alert.setHeaderText("Success");
				alert.setContentText("Vendor Type and Amount Added");
				alert.showAndWait();
				vendorType.clear();
				vendorAmount.clear();
			}else{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Error:");
				alert.setContentText("Vendor Type and Amount is not added. Try Again !!");
				alert.showAndWait();
			}
		}catch(BpsDataBaseException e){
			System.out.println("going 2");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("Something went wrong.");
			alert.showAndWait();
		}
		}
	}
	
	public void homePage(MouseEvent event){
		FXMLLoader loader = new FXMLLoader();
		try {
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Pane root = loader.load(getClass().getResource("/view/Main.fxml").openStream());
			Scene scene = new Scene(root,800,700);		
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setTitle("Welcome Admin");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
