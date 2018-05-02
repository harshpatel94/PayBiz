package controllers;


/**
 * @author hpatel111
 * implementation class CustomerController which handles the Customer Information module where different javaFx elements are used
 * getting values from them and sending to the DAO through DTO and accessing data to database. And displaying to screen and handled exception as well.
 */

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.dao.CustomerRegistrationDAO;
import model.dao.CustomerUpdationDAO;
import model.dto.Customer;
import model.dto.CustomerRegistrationDTO;
import model.dto.CustomerResponseDTO;

public class CustomerController implements Initializable {
	
	@FXML private TextField customer_id_field;
	
	@FXML private Button btnFind;
	
	@FXML private TextArea feedback;
	
	@FXML private Label showId;
	
	@FXML private TableView<Customer> table;
	@FXML private TableColumn<Customer, String> customer_id;
	@FXML private TableColumn<Customer, String> customer_name;
	@FXML private TableColumn<Customer, String> customer_address;
	@FXML private TableColumn<Customer, String> customer_emailId;
	@FXML private TableColumn<Customer, String> vendor_type;
	@FXML private TableColumn<Customer, Float> customer_balance;
	
	public ObservableList<Customer> list = FXCollections.observableArrayList();
	
	public void viewUser(ActionEvent event) throws BpsDataBaseException{
		
		
		String custId = customer_id_field.getText();
		if(custId.equals("")){
			alertError("Enter Customer ID first.");
		}else{
		CustomerRegistrationDTO customerRegistrstionDTO = new CustomerRegistrationDTO();
		customerRegistrstionDTO.setCustomerId(custId);
		CustomerUpdationDAO customerUpdDAO = new CustomerUpdationDAO();
		CustomerRegistrationDTO customerRegistrstionDTO1 = customerUpdDAO
				.customerSelectionDAO(customerRegistrstionDTO);
		
		try{
			if(customerRegistrstionDTO1 != null){
				list.add(new Customer(customerRegistrstionDTO1.getCustomerId(),
						customerRegistrstionDTO1.getCustomerName(),customerRegistrstionDTO1.getAddress(),
						customerRegistrstionDTO1.getMailid(),customerRegistrstionDTO1.getTypeOfVendor(),customerRegistrstionDTO1.getBalance()));
			}else{
				alertError("Something went wrong.");
			}
		}catch(Exception e){
			alertError("Reason: "+e.getMessage());
		}
		}
		
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		customer_id.setCellValueFactory(new PropertyValueFactory<Customer, String>("customer_id"));
		customer_name.setCellValueFactory(new PropertyValueFactory<Customer, String>("customer_name"));
		customer_address.setCellValueFactory(new PropertyValueFactory<Customer, String>("customer_address"));
		customer_emailId.setCellValueFactory(new PropertyValueFactory<Customer, String>("customer_email_id"));
		vendor_type.setCellValueFactory(new PropertyValueFactory<Customer, String>("vendor_type"));
		customer_balance.setCellValueFactory(new PropertyValueFactory<Customer, Float>("customer_balance"));
		
		table.setItems(list);
		
		table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	String custId = null;
		    	for(Customer data:table.getItems()){
					custId = data.getCustomer_id();
					System.out.println(custId);
				}
		    	
		    	Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Dialog");
				alert.setHeaderText("confirm");
				alert.setContentText("Are you sure you want to delete this record");
		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		try {
						customerDelete(custId);
					} catch (BpsDataBaseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	table.getItems().clear();
		    	}
		    	
		    }
		});
		
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
	
	public void alertError(String Message){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("Error:");
		alert.setContentText(Message);
		alert.showAndWait();
	}
	
	public void customerDelete(String custId) throws BpsDataBaseException{
		CustomerRegistrationDTO customerRegistrstionDTO = new CustomerRegistrationDTO();
		customerRegistrstionDTO.setCustomerId(custId);
		CustomerUpdationDAO customerUpdDAO = new CustomerUpdationDAO();
		CustomerResponseDTO customerResponse = customerUpdDAO
				.customerDeletionDAO(customerRegistrstionDTO);
		
		if(customerResponse.isStatus()){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setHeaderText("Success");
			alert.setContentText("Customer deleted successfully.");
			alert.showAndWait();
		}else{
			alertError("Customer couldn't delete due to system problem.");
			
		}
		
	}
	
	public void getUser(String custId){
		showId.setText(custId);
	}
	
	
	
	public void sendFeedback(ActionEvent event) throws BpsDataBaseException{
		
		String feed = feedback.getText();
		if(feed.equals("")){
			alertError("Enter feedback first");
		}
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
			alertError("Customer couldn't delete due to system problem.");
			
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
			primaryStage.setTitle("Welcome Customer");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
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
	
	
	


}
