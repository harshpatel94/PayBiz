package view;



/**
 * @author hpatel111
 * implementation AdminController which handles all the features of the application. In this class mentioned 
 * methods to open different FXML files.
 */

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdminController {
	
	@FXML
	private Button btnCustReg;
	
	@FXML
	private Button btnCustUpdate;
	
	@FXML
	private Button btnVenReg;
	
	@FXML
	private Button btnVenUpdate;
	
	@FXML
	private Button btnBillPayment;
	
	@FXML
	private Button btnCustomerData;
	
	public void CustomerRegistrationTab(ActionEvent event) throws Exception{
		
		setStage(event,"Customer Registration","/view/CustomerRegistration.fxml",800,700);
		}
	
	public void CustomerUpdationTab(ActionEvent event) throws Exception{
		
		setStage(event,"Customer Updation","/view/CustomerUpdate.fxml",800,700);
	}
	
	public void VendorRegistrationTab(ActionEvent event) throws Exception{
		
		setStage(event,"Vendor Registration","/view/VendorRegistration.fxml",900,700);
	}
	
	public void VendorUpdationTab(ActionEvent event) throws Exception{
		
		setStage(event,"Vendor Updation","/view/VendorUpdate.fxml",800,700);
	}
	
	public void BillPaymentTab(ActionEvent event) throws Exception{
		
		setStage(event,"Bill Payment","/view/BillPayment.fxml",800,700);
	}
	
	public void ViewCustomerData(ActionEvent event) throws Exception{
		
		setStage(event,"Customer View/Delete","/view/CustomerAdmin.fxml",700,600);
	}
	
	public void InsertVendorType(ActionEvent event) throws Exception{
		setStage(event,"Vendor Amount Adding View","/view/Vendor.fxml",600,500);
	}
	
	public void ViewVendorData(ActionEvent event) throws Exception{
		
		setStage(event,"Vendor View/Delete","/view/VendorAdmin.fxml",900,500);
	}
	
		public void logOut(ActionEvent event){
		setStage(event,"Welcome User","/view/LoginView.fxml",500,500);
	}
	
	
	public void setStage(ActionEvent event, String title,String file,int width,int hight){
		FXMLLoader loader = new FXMLLoader();
		((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		Pane root;
		try {
			root = loader.load(getClass().getResource(file).openStream());
			Scene scene = new Scene(root,width,hight);	
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setTitle(title);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("Something went wrong, Can't open window. Try Later.");
			alert.showAndWait();
			System.out.println(e.getMessage());
		}
		
	}
	
	
}

	

