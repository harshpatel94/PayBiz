package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import exceptions.BpsDataBaseException;
import model.dao.CustomerRegistrationDAO;
import model.dao.CustomerUpdationDAO;
import model.dto.CustomerRegistrationDTO;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author hpatel111 controller for updating customer details
 * 
 */
public class CustomerUpdateController implements Initializable{

	@FXML
	private Button btnFind;
	
	@FXML
	private Button btnUpdate;
	@FXML
	private TextField customerId;
	
	@FXML
	private TextField customerName;

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
	private ComboBox<String> vendorType;
	private ObservableList<String> vendorTypeData = FXCollections.observableArrayList("Phone Purchase","Electricity","MobilePhone");

	@FXML
	private TextField customerCardNumber;
	
	@FXML
	private TextField customerBalance;
	
	@FXML
	private Button btnHome;
	
	@FXML
	private TextField customer_id_field;
		
	CustomerRegistrationDAO custDAO = new CustomerRegistrationDAO();
	
	
	public void selectUser(ActionEvent event){
		try{

			String custId = customerId.getText();
			if(custId.equals("")){
				alertError("Enter customer id first.");
			}else{
			System.out.println(custId);
			CustomerRegistrationDTO customerRegistrstionDTO = new CustomerRegistrationDTO();
			customerRegistrstionDTO.setCustomerId(custId);
			
			/*
			 * Sending to CustomerUpdDAO to fetch customer Details
			 */
			CustomerUpdationDAO customerUpdDAO = new CustomerUpdationDAO();
			CustomerRegistrationDTO customerRegistrstionDTO1 = customerUpdDAO
					.customerSelectionDAO(customerRegistrstionDTO);
		
			if( customerRegistrstionDTO1 == null){
				//message.setText("ID NOT FOUND \n Enter Valid ID:");
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Error:");
				alert.setContentText("ID NOT FOUND. Enter Valid ID:");
				alert.showAndWait();
			}else{
				
				customerId.setText(customerRegistrstionDTO1.getCustomerId());
				customerName.setText(customerRegistrstionDTO1.getCustomerName());
				customerAddress.setText(customerRegistrstionDTO1.getAddress());
				customerContactNumber.setText(customerRegistrstionDTO1.getContactNumber());
				customerCountry.setValue(customerRegistrstionDTO1.getCountry());
				customerState.setValue(customerRegistrstionDTO1.getState());
				customerEmail.setText(customerRegistrstionDTO1.getMailid());
				customerDocument.setValue(customerRegistrstionDTO1.getidentificationDocument());
				customerDocNumber.setText(customerRegistrstionDTO1.getDocDetNum());
				vendorType.setValue(customerRegistrstionDTO1.getTypeOfVendor());
				customerCardNumber.setText(customerRegistrstionDTO1.getCardNumber());
				customerBalance.setText(Float.toString(customerRegistrstionDTO1.getBalance()));
			}
			}
		}catch(BpsDataBaseException e){
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("Reason:"+e.getMessage());
			alert.showAndWait();
		}catch(Exception e){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("Reason: "+ e.getMessage());
			alert.showAndWait();

		}
		
				
	}
	
	public void updateDetails(ActionEvent event){
		
		Float bal = null;
			String custId = customerId.getText();
			String custName = customerName.getText();
			String custAddress = customerAddress.getText();
			String custContactNumber = customerContactNumber.getText();
			String custCountry = customerCountry.getValue();
			String custState = customerState.getValue();
			String custEmail = customerEmail.getText();
			String custDocument = customerDocument.getValue();
			String custDocNumber = customerDocNumber.getText();
			String vendortype = vendorType.getValue();
			Date date = new Date();
			Format formatter = new SimpleDateFormat("MM/dd/yyyy");
			String date1 = formatter.format(date);
			System.out.println(date1);
			String custCardNumber = customerCardNumber.getText();
			String custBalance = customerBalance.getText();
			if(custId.equals("") || custName.equals("") || custAddress.equals("") || custContactNumber.equals("") || custCountry.equals("")
					|| custState.equals("") || custEmail.equals("") || custDocument.equals("") || custDocNumber.equals("") ||
					vendortype.equals("") || custCardNumber.equals("") || custBalance.equals("")){
				alertError("Enter Details first.");
			}else{
				bal = Float.parseFloat(custBalance);	
			
			CustomerRegistrationDTO customerRegistrstionDTO = new CustomerRegistrationDTO(
					custId, custName, custAddress, custContactNumber, custCountry,
					custState, custEmail, custDocument, custDocNumber,vendortype, custCardNumber, bal);
			System.out.println(customerRegistrstionDTO.toString());
		
			/*
			 * Updating the existing data in the
			 */
			try {
				CustomerUpdationDAO customerUpdDAO = new CustomerUpdationDAO();
				model.dto.CustomerResponseDTO customerRespDTO = customerUpdDAO
						.customerUpdateDAO(customerRegistrstionDTO);
				
				System.out.println(customerRespDTO.isStatus());
				if (customerRespDTO.isStatus() == true) 
				{
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Success");
						alert.setHeaderText("Message:");
						alert.setContentText("Customer Updated Successfully");
						alert.showAndWait();
						FXMLLoader loader = new FXMLLoader();
						setStage("/view/Main.fxml",event,loader,"Home");
				}
				else{
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Error:");
						alert.setContentText("Customer details not updated. Try Again !!");
						alert.showAndWait();
						customerId.clear();
						customerName.clear();
						customerAddress.clear();
						customerContactNumber.clear();
						customerCountry.setPromptText(customerRegistrstionDTO.getCountry());
						customerState.setPromptText(customerRegistrstionDTO.getState());
						customerEmail.clear();
						customerDocument.setPromptText(customerRegistrstionDTO.getidentificationDocument());
						customerDocNumber.clear();
						vendorType.setPromptText(customerRegistrstionDTO.getTypeOfVendor());
						customerCardNumber.clear();
						customerBalance.clear();
				}
			}catch(BpsDataBaseException e){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Error:");
				alert.setContentText("Reason: "+e.getMessage());
				alert.showAndWait();
				customerId.clear();
				
			}catch(Exception e){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Error:");
				alert.setContentText("Reason: "+ e.getMessage());
				alert.showAndWait();
				
			}
			}
	}
	
	
	


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		customerDocument.setItems(documentData);
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
			alert.setContentText("Something went wrong");
			alert.showAndWait();
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("Error:");
		alert.setContentText("Something went wrong.");
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
