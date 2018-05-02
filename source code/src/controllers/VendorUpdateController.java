package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;

import java.util.ResourceBundle;

import bo.RegVendorBO;

import exceptions.BpsDataBaseException;
import model.dao.CustomerRegistrationDAO;
import model.dao.VendorDAO;

import model.dto.VendorDTO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author hpatel111 controller for updating vendor details
 */
public class VendorUpdateController implements Initializable{

	@FXML
	private TextField vendorId;
	
	@FXML
	private Button find;
	
	@FXML
	private TextField companyRegNumber;
	
	@FXML
	private TextField vendorName;
	
	@FXML
	private ComboBox<String> vendorType;
	private ObservableList<String> vendorData = FXCollections.observableArrayList();

	
	@FXML
	private TextArea vendorAddress;
	
	@FXML
	private ComboBox<String> vendorCountry;
	private ObservableList<String> countryData = FXCollections.observableArrayList();

	@FXML
	private ComboBox<String> vendorState;
	private ObservableList<String> stateData = FXCollections.observableArrayList();
	
	@FXML
	private TextField vendorEmail;
	
	@FXML
	private TextField vendorContactNumber;
	
	@FXML
	private TextField vendorWebsite;
	
	@FXML
	private DatePicker certiIssuedDate;
	
	@FXML
	private DatePicker certiValidityDate;
	
	@FXML
	private TextField employeeCount;
	
	@FXML
	private TextField customerCount;
	
	@FXML
	private TextField yearOfEstablishment;
	
	@FXML
	private Button update;
	
	CustomerRegistrationDAO custDAO = new CustomerRegistrationDAO();
	
	
	public void selectVendor(ActionEvent event){
		
		String venId = vendorId.getText();
		if(venId.equals("")){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("Enter VendorId first");
			alert.showAndWait();
		}else{
		VendorDTO vendorDTO = new VendorDTO();
		try{
			vendorDTO.setVendorId(venId);
			VendorDAO vendorDAO = new VendorDAO();
			vendorDAO.fetchData(vendorDTO);
		}catch(BpsDataBaseException e){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("System is not working due to:" + e.getMessage());
			alert.showAndWait();
			vendorId.clear();
		}catch(Exception e){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("Reason: "+ e.getMessage());
			alert.showAndWait();
			vendorId.clear();
		}
		
		if(vendorDTO.isStatus() == true){
			companyRegNumber.setText(vendorDTO.getCompanyRegNo());
			vendorName.setText(vendorDTO.getVendorName());
			vendorType.setValue(vendorDTO.getVendorType());
			certiIssuedDate.setValue(LocalDate.parse(vendorDTO.getCertificateIssuedDate()));
			certiValidityDate.setValue(LocalDate.parse(vendorDTO.getCertificateValidityDate()));
			yearOfEstablishment.setText(Integer.toString(vendorDTO.getYearOfEstablishment()));
			vendorCountry.setValue(vendorDTO.getCountry());
			vendorState.setValue(vendorDTO.getState());
			vendorAddress.setText(vendorDTO.getAddress());
			vendorContactNumber.setText(vendorDTO.getContactNumber());
			vendorEmail.setText(vendorDTO.getEmail());
			vendorWebsite.setText(vendorDTO.getWebsite());
			employeeCount.setText(Integer.toString(vendorDTO.getEmployeeCount()));
			customerCount.setText(Integer.toString(vendorDTO.getCustomerCount()));
			companyRegNumber.setEditable(false);
		}else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("Reason: No vendor found on this Vendor Id. Try Again");
			alert.showAndWait();
		}	
		}
	}
	
	public void updateVendor(ActionEvent event){
		
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setVendorId(vendorId.getText());
		vendorDTO.setCompanyRegNo(companyRegNumber.getText());
		vendorDTO.setAddress(vendorAddress.getText());
		vendorDTO.setEmail(vendorEmail.getText());
		vendorDTO.setContactNumber(vendorContactNumber.getText());
		vendorDTO.setWebsite(vendorWebsite.getText());
		LocalDate certiIssue = certiIssuedDate.getValue();
		String venCertiIssueDate = certiIssue.toString();
		vendorDTO.setCertificateIssuedDate(venCertiIssueDate);
		LocalDate certiValidity = certiValidityDate.getValue();
		String venCertiValidityDate = certiValidity.toString();
		vendorDTO.setCertificateValidityDate(venCertiValidityDate);
		vendorDTO.setEmployeeCount(Integer.parseInt(employeeCount.getText()));
		vendorDTO.setCustomerCount(Integer.parseInt(customerCount.getText()));
		vendorDTO.setYearOfEstablishment(Integer.parseInt(yearOfEstablishment.getText()));
		vendorDTO.setCountry(vendorCountry.getValue());
		vendorDTO.setState(vendorState.getValue());
		vendorDTO.setVendorName(vendorName.getText());
		vendorDTO.setVendorType(vendorType.getValue());
		
		
		RegVendorBO vendorBO = new RegVendorBO();
		vendorDTO = vendorBO.generateCertificate(vendorDTO);
		
		System.out.println(vendorDTO.toString());
		
		VendorDAO vendorDAO = new VendorDAO();
		try{
			vendorDAO.updateData(vendorDTO);
		}catch(BpsDataBaseException e){
			System.out.println("bpsSERV ");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("Reason: "+ e.getMessage());
			alert.showAndWait();
			System.out.println(e.getMessage());
			selectVendor(event);
		}catch(Exception e){
			System.out.println("bpsSERVex ");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("Reason: "+ e.getMessage());
			alert.showAndWait();			
			selectVendor(event);
		}
		
		if(vendorDTO.isStatus()){
			System.out.println("inside true ");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setHeaderText("Success");
			alert.setContentText("Vendor Updated Successfully.");
			alert.showAndWait();
			FXMLLoader loader = new FXMLLoader();
			setStage("/view/Vendor.fxml",event,loader,"Welcome Vendor.");
		}else{
			System.out.println("bpsSERVelse ");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("Vendor not Updated! check details");
			alert.showAndWait();	
			companyRegNumber.clear();
			vendorName.clear();
			vendorType.setPromptText("Vendor Type");
			vendorAddress.clear();
			vendorCountry.setPromptText("Country");
			vendorState.setPromptText("State");
			vendorEmail.clear();
			vendorContactNumber.clear();
			vendorWebsite.clear();
			certiIssuedDate.getEditor().clear();
			certiValidityDate.getEditor().clear();
			employeeCount.clear();
			customerCount.clear();
			yearOfEstablishment.clear();
			vendorId.clear();
			
		}
		
	}
	
public void setStage(String file,ActionEvent event,FXMLLoader loader,String title){
		
		try {
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Pane root = loader.load(getClass().getResource(file).openStream());
			Scene scene = new Scene(root,700,600);		
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setTitle(title);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("Window is not opening.");
			alert.showAndWait();	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	try {
		countryData = custDAO.fillCountry();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	vendorCountry.setItems(countryData);
	try {
		vendorData = custDAO.fillVendor();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	vendorType.setItems(vendorData);
	
}

public void vendorCountry(ActionEvent event){

	stateData.clear();
	String count = vendorCountry.getValue();
	try {
		stateData = custDAO.fillState(count);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	vendorState.setItems(stateData);
	
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
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("Error:");
		alert.setContentText("Something is wrong.");
		alert.showAndWait();	
		e.printStackTrace();
	}
}

}
