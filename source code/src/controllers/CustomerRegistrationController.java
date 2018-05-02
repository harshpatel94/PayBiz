package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;



import controllers.CustomerRegistrationController;
import exceptions.BpsDataBaseException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.dao.CustomerRegistrationDAO;
import model.dto.CustomerRegistrationDTO;
import model.dto.CustomerResponseDTO;

/**
 * @author hpatel111 controller for registering customer
 */

public class CustomerRegistrationController implements Initializable {


	Date date = new Date();

	@FXML
	private TextField customerName;
	@FXML
	private TextField customerPassword;
	@FXML
	private TextArea customerAddress;
	@FXML
	private TextField customerContactNumber;
	
	@FXML
	private TextField customerEmail;

	@FXML
	private ComboBox<String> customerCountry;
	private ObservableList<String> countryData = FXCollections.observableArrayList();
	
	@FXML
	private ComboBox<String> customerState;
	private ObservableList<String> stateData = FXCollections.observableArrayList();
	
	@FXML
	private ComboBox<String> customerDocument;
	private ObservableList<String> documentData = FXCollections.observableArrayList("Voter Id","Pass Port","Driving License","Pan Card","SSN");
	
	@FXML
	private TextField customerDocNumber;
	
	@FXML
	private Label customerRegDate;
	
	@FXML
	private ComboBox<String> vendorType;
	private ObservableList<String> vendorTypeData = FXCollections.observableArrayList();

	@FXML
	private TextField customerCardNumber;
	
	@FXML
	private TextField customerBalance;
	
	@FXML
	private Button btnSubmit;
	
	@FXML
	private Button btnReset;
	
	@FXML
	private Button btnHome;

	CustomerRegistrationDAO custDAO = new CustomerRegistrationDAO();
	
	
	public void Submit(ActionEvent event){
		Float balance = null;
		String name = customerName.getText();
		String pass = customerPassword.getText();
		String address = customerAddress.getText();
		String email = customerEmail.getText();
		String contact = customerContactNumber.getText();
		System.out.println(contact);
		String country = customerCountry.getValue();
		String state = customerState.getValue();
		String document = customerDocument.getValue();
		String docNumber =  customerDocNumber.getText();
		Format formatter = new SimpleDateFormat("MM/dd/yyyy");
		String regDate = formatter.format(date);
		String typeVendor = vendorType.getValue();
		String cardNumber = customerCardNumber.getText();
		String bal = customerBalance.getText();
		if(bal.equals("")){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("Enter balance");
			alert.showAndWait();
			resetDetails(event);
			resetDetails(event);
		}else{
			balance = Float.parseFloat(bal);
		}
		if(name.equals("") || pass.equals("") || address.equals("") || email.equals("") || contact.equals("") || country.equals("") || 
				state.equals("") || document.equals("") || docNumber.equals("") || typeVendor.equals("")
				|| cardNumber.equals("")){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("Enter detials first.");
			alert.showAndWait();
			resetDetails(event);
			resetDetails(event);
		}else{
		
		try {
			CustomerRegistrationDTO customerDTO = new CustomerRegistrationDTO(name,pass,address,
					contact,country,state,email,document,docNumber,regDate,typeVendor,
					cardNumber,balance);
			
			CustomerRegistrationDAO customerRegisDAO = new CustomerRegistrationDAO();
			CustomerResponseDTO custRegiDTO = customerRegisDAO.customerRegisDAO(customerDTO);
			if(custRegiDTO.isStatus() == true){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");
				alert.setHeaderText("Message:");
				alert.setContentText("Customer Registered Successfully! Customer Id is :" +custRegiDTO.getCustomer_id());
				alert.showAndWait();
				FXMLLoader loader = new FXMLLoader();
				setStage("/view/Main.fxml",event,loader,"Welcome Admin.");
			}else{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Error:");
				alert.setContentText("Customer failed to register.");
				alert.showAndWait();
				resetDetails(event);
				resetDetails(event);
			}
		} catch (BpsDataBaseException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("Check details. ");
			alert.showAndWait();
			resetDetails(event);
			e.printStackTrace();
		}catch(Exception e){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("Reason :"+ e.getMessage());
			alert.showAndWait();
			resetDetails(event);
		}
		}
		
		
	}
	
	public void resetDetails(ActionEvent event){
		
		customerName.clear();
		customerPassword.clear();
		customerAddress.clear();
		customerContactNumber.clear();
		customerEmail.clear();
		customerCountry.setPromptText("Country");
		customerState.setPromptText("State");
		customerDocument.setPromptText("Idetification Document");
		customerDocNumber.clear();
		vendorType.setPromptText("Vendor Type");
		customerCardNumber.clear();
		customerBalance.clear();
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Format formatter = new SimpleDateFormat("MM/dd/yyyy");
		String regDate = formatter.format(date);
		customerRegDate.setText(regDate);
		try {
			countryData = custDAO.fillCountry();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		customerCountry.setItems(countryData);
		customerDocument.setItems(documentData);
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
			Scene scene = new Scene(root,800,700);		
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

public void customerCountry(ActionEvent event){

	stateData.clear();
	String count = customerCountry.getValue();
	try {
		stateData = custDAO.fillState(count);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	customerState.setItems(stateData);
	
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
		alert.setContentText("Somthing went wrong.");
		alert.showAndWait();
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


}
