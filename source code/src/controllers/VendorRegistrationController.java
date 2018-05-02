package controllers;


/**
 * @author hpatel111
 * implementation class VendorRegistrationController which handles the VendorRegisration module where different javaFx elements are used
 * getting values from them and sending to the DAO through DTO and accessing data to database. And displaying to screen and handled exception as well.
 */
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import controllers.VendorRegistrationController;

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

public class VendorRegistrationController implements Initializable {
	
	@FXML
	private TextField companyRegNumber;
	
	@FXML
	private TextField vendorName;
	
	@FXML
	private TextField vendorPassword;
	
	@FXML
	private ComboBox<String> vendorType;
	private ObservableList<String> vendorTypeData = FXCollections.observableArrayList();
	
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
	private Button register;
	
	@FXML
	private Button reset;
	
	CustomerRegistrationDAO custDAO = new CustomerRegistrationDAO();
	
	
	
	public void registerVendor(ActionEvent event){
		
		String vEmail = "";
		String companyRegNum = companyRegNumber.getText();
		String venName = vendorName.getText();
		String venPassword = vendorPassword.getText();
		String venType = vendorType.getValue();
		String venAddress = vendorAddress.getText();
		String venCountry = vendorCountry.getValue();
		String venState = vendorState.getValue();
		String venEmail = vendorEmail.getText();
		String venContactNumber = vendorContactNumber.getText();
		String venWebsite = vendorWebsite.getText();
		LocalDate certiIssue = certiIssuedDate.getValue();
		String venCertiIssueDate = certiIssue.toString();
		LocalDate certiValidity = certiValidityDate.getValue();
		String venCertiValidityDate = certiValidity.toString();
		if(companyRegNum.equals("") || venName.equals("") || venPassword.equals("") || venType.equals("") ||
				venAddress.equals("") ||venCountry.equals("") ||venState.equals("") ||venEmail.equals("") ||
				venContactNumber.equals("") || venWebsite.equals("")){
			alertError("Enter details first");
		}else{
		int venEmployeeCount = Integer.parseInt(employeeCount.getText());
		int venCustomerCount = Integer.parseInt(customerCount.getText());
		int YOE = Integer.parseInt(yearOfEstablishment.getText());
		
		try{
			RegVendorBO vendorBO = new RegVendorBO();
			int m = vendorBO.checkMail(venEmail);
			if(m==0){
				vEmail = venEmail;
			}
			VendorDTO vendor = new VendorDTO(venPassword,venName,
					companyRegNum, venType, venAddress,
					venCountry, venState, vEmail, venContactNumber, venWebsite,
					venCertiIssueDate, venCertiValidityDate,
					venEmployeeCount, venCustomerCount, YOE);
			System.out.println(vendor.toString());

			VendorDTO vendorDTO = vendorBO.generateVendorID(vendor);
			
			vendorDTO = vendorBO.generateCertificate(vendor);
			System.out.println(vendorDTO.toString());

			VendorDAO venDAO = new VendorDAO();
			venDAO.registerVendor(vendorDTO);
			
			System.out.println(vendorDTO.isStatus());
			if(vendorDTO.isStatus()){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success Dialog");
				alert.setHeaderText("Success");
				alert.setContentText("Vendor Registered Successfully with vendor id  "+vendorDTO.getVendorId()+" ");
				alert.showAndWait();
				resetDetails(event);
				FXMLLoader loader = new FXMLLoader();
				setStage("/view/Main.fxml",event,loader,"Welcome Admin");
			}else{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Error:");
				alert.setContentText("Vendor is not Registered. Try Again !!");
				alert.showAndWait();
				resetDetails(event);
				System.out.println("going 1");
			}
		}catch(BpsDataBaseException e){
			System.out.println("going 2");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("Vendor is not Registered. Try Again !!");
			alert.showAndWait();
			resetDetails(event);
		}catch(Exception e){
			System.out.println("goin 3");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("System is not working.");
			alert.showAndWait();
			resetDetails(event);
		}
		}
	}

	public void resetDetails(ActionEvent event){
		companyRegNumber.clear();
		vendorName.clear();
		vendorPassword.clear();
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
				vendorTypeData = custDAO.fillVendor();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			vendorType.setItems(vendorTypeData);
			
		
		
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
			alert.setContentText("Something went wrong.");
			alert.showAndWait();
			resetDetails(event);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("Error:");
		alert.setContentText("something went wrong.");
		alert.showAndWait();
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


	
	

}
