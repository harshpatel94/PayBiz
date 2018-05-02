package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Date;
import java.util.ResourceBundle;


import model.dao.AfterBillPaymentUpdateDAO;
import model.dao.BillDAO;
import model.dao.CustomerRegistrationDAO;
import model.dto.AfterBillPaymentUpdateDTO;
import model.dto.AfterBillPaymentUpdateResponse;
import model.dto.BillRequestDTO;
import model.dto.BillResponseDTO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author hpatel111
 * implementation class BillController which handles the Bill payment module where different javaFx elements are used
 * getting values from them and sending to the DAO through DTO and accessing data to database. And displaying to screen and handled exception as well.
 */
public class BillController implements Initializable {

	public BillController(){
		super();
	}
	
	@FXML
	private TextField customerId;
	
	@FXML
	private ComboBox<String> vendorType;
	private ObservableList<String> vendorData = FXCollections.observableArrayList();

	@FXML
	private ComboBox<String> vendorName;
	private ObservableList<String> vendorNameData = FXCollections.observableArrayList();

	@FXML
	private Label cust_id;
	
	@FXML
	private Label pendingAmount;
	
	@FXML
	private Label amountDueCustomer;

	@FXML
	private Label paymentDate;
	
	@FXML
	private TextField amountToPay;
	
	@FXML
	private Label cardNumber;
	
	@FXML
	private TextField confirmCardNumber;
	
	@FXML
	private TextField cardMonth;

	@FXML
	private TextField cardYear;
	
	@FXML
	private TextField CVV;
	
	@FXML
	private Button btnShowDetails;
	
	@FXML
	private Button btnMakePayment;
	
	@FXML
	private Pane paneView;
	
	Date date = new Date();
	CustomerRegistrationDAO custDAO = new CustomerRegistrationDAO();
	
	public void showDetails(ActionEvent event){
		String customerID = customerId.getText();
		String venName = vendorName.getValue();
		String venType = vendorType.getValue();
		if(customerID.equals("") || venName.equals("") || venType.equals("")){
			alert();
		}else{
		try{
			BillRequestDTO billReqDTO = new BillRequestDTO(customerID, venType,
					venName);
			BillDAO billDAO = new BillDAO();
			BillResponseDTO billResDTO = billDAO.billpayment(billReqDTO);
			
			System.out.println(billResDTO.toString());
			
			if(billResDTO.isStatus() == true){
				//paneView.setVisible(true);
				cust_id.setText(billReqDTO.getCusid());
				pendingAmount.setText(Float.toString(billResDTO.getPendingAmount()));
				cardNumber.setText(billResDTO.getCardNumber());
				amountDueCustomer.setText(Float.toString(billResDTO.getCustomerAmount()));
			
			}else{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Error:");
				alert.setContentText("Invalid details. Check the entered values.!");
				alert.showAndWait();
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("Somethings wrong with the system. !");
			alert.showAndWait();
		}	
		}
	}
	
	public void makePayment(ActionEvent event) throws Exception{
		String cusid = cust_id.getText();
		String pending_amount = pendingAmount.getText();
		String card_no = confirmCardNumber.getText();
		String amount_pay = amountToPay.getText();
		if(cusid.equals("") || pending_amount.equals("") || card_no.equals("") || amount_pay.equals("") ){
			alert();
		}
		int month = Integer.parseInt(cardMonth.getText());
		int year = Integer.parseInt(cardYear.getText());
		if(month==0 || year==0){
			alert();
		}
		String card_validity = YearMonth.of(year, month).toString();
		System.out.println(YearMonth.of(year, month));
		String cvv = CVV.getText();
		
		if(card_validity.equals("") || cvv.equals("")){
			alert();
		}
		AfterBillPaymentUpdateDTO updateReqDTO = new AfterBillPaymentUpdateDTO(Integer.parseInt(cusid),
				Float.parseFloat(pending_amount),card_no,amount_pay,
				card_validity,cvv);
		AfterBillPaymentUpdateDAO updateDAO = new AfterBillPaymentUpdateDAO();
		AfterBillPaymentUpdateResponse updateResDTO = updateDAO.updatePayment(updateReqDTO);
		
		if(updateResDTO.isStatus() == true){
			showDetails(event);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setHeaderText("Message:");
			alert.setContentText("Bills are paid successfully!");
			alert.showAndWait();
			FXMLLoader loader = new FXMLLoader();
			setStage("/view/Main.fxml",event,loader,"Welcome Admin.");
		}else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("Transaction Failed.Check the amount to pay or Card number entered.");
			alert.showAndWait();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
		Format formatter = new SimpleDateFormat("MM/dd/yyyy");
		String regDate = formatter.format(date);
		paymentDate.setText(regDate);
		try {
			vendorData = custDAO.fillVendor();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vendorType.setItems(vendorData);
		
	}
	
public void setStage(String file,ActionEvent event,FXMLLoader loader,String title) throws Exception{
		
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Pane root = loader.load(getClass().getResource(file).openStream());
			Scene scene = new Scene(root,700,600);		
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setTitle(title);
			primaryStage.setScene(scene);
			primaryStage.show();	
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

public void vendorName(ActionEvent event){

	vendorNameData.clear();
	String count = vendorType.getValue();
	try {
		vendorNameData = custDAO.fillVendorName(count);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	vendorName.setItems(vendorNameData);
	
}

public void alert(){
	Alert alert = new Alert(AlertType.ERROR);
	alert.setTitle("Error Dialog");
	alert.setHeaderText("Error:");
	alert.setContentText("Enter Details.");
	alert.showAndWait();
}
	
	
	
}


