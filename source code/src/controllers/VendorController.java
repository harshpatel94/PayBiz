package controllers;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.dao.VendorDAO;
import model.dto.Vendor;
import model.dto.VendorDTO;

/**
 * @author hpatel111
 * implementation class VendorController which handles the Vendor module where different javaFx elements are used
 * getting values from them and sending to the DAO through DTO and accessing data to database. And displaying to screen and handled exception as well.
 */

public class VendorController implements Initializable{
	
	@FXML private TextField venId;
	@FXML private TableView<Vendor> table;
	@FXML private TableColumn<Vendor,String> vendor_id;
	@FXML private TableColumn<Vendor,Integer> company_reg_no;
	@FXML private TableColumn<Vendor,String> vendor_address;
	@FXML private TableColumn<Vendor,String> vendor_contact_no;
	@FXML private TableColumn<Vendor,String> vendor_website;
	@FXML private TableColumn<Vendor,String> certificate_issue_date;
	@FXML private TableColumn<Vendor,String> certificate_validity_date;
	@FXML private TableColumn<Vendor,String> certificate;
	
	public ObservableList<Vendor> list = FXCollections.observableArrayList();
	
	public void viewVendor(ActionEvent event) throws BpsDataBaseException{
		String venID = venId.getText();
		if(venID.equals("")){
			alertError("Enter VendorId first");
		}else{
		
			VendorDTO venDTO = new VendorDTO();
		try{
			venDTO.setVendorId(venID);
			VendorDAO venDAO = new VendorDAO();
			VendorDTO ven = venDAO.viewVendor(venDTO);
			if(ven != null){
				list.add(new Vendor(ven.getVendorId(),Integer.parseInt(ven.getCompanyRegNo()),ven.getAddress(),
						ven.getContactNumber(),ven.getWebsite(),ven.getCertificateIssuedDate(),ven.getCertificateValidityDate()
						,ven.getCertificate()));
			}else{
				alertError("Something went wrong.");
			}
		}catch(Exception e){
			alertError("Reason: There is no any Vendor on this ID.");
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
			alertError("Somthing went wrong.");
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
		vendor_id.setCellValueFactory(new PropertyValueFactory<Vendor,String>("vendor_id"));
		company_reg_no.setCellValueFactory(new PropertyValueFactory<Vendor, Integer>("company_reg_no"));
		vendor_address.setCellValueFactory(new PropertyValueFactory<Vendor,String>("vendor_address"));
		vendor_contact_no.setCellValueFactory(new PropertyValueFactory<Vendor,String>("vendor_contact_no"));
		vendor_website.setCellValueFactory(new PropertyValueFactory<Vendor,String>("vendor_website"));
		certificate_issue_date.setCellValueFactory(new PropertyValueFactory<Vendor,String>("certificate_issue_date"));
		certificate_validity_date.setCellValueFactory(new PropertyValueFactory<Vendor,String>("certificate_validity_date"));
		certificate.setCellValueFactory(new PropertyValueFactory<Vendor,String>("certificate"));
	
		table.setItems(list);
		
		table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	String venId = null;
		    	for(Vendor data:table.getItems()){
					venId = data.getVendorId();
					System.out.println(venId);
		    	}
		    	Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Dialog");
				alert.setHeaderText("confirm");
				alert.setContentText("Are you sure you want to delete this record");
		    	Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
					try {
						vendorDelete(venId);
					} catch (BpsDataBaseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	table.getItems().clear();
				}
		    	
		    }
		});
		}
	
	public void vendorDelete(String venId) throws BpsDataBaseException{
		VendorDTO venDTO = new VendorDTO();
		if(venId.equals("")){
			alertError("Enter Vendor Id first");
		}
		venDTO.setVendorId(venId);
	
		VendorDAO venDAO = new VendorDAO();
		VendorDTO vendor = venDAO.vendorDeleteDAO(venDTO);
		
		if(vendor.isStatus()){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setHeaderText("Success");
			alert.setContentText("Customer deleted successfully.");
			alert.showAndWait();
		}else{
			alertError("Vendor couldn't delete due to system problem. Try again after sometime.");
		}
		
	}
	
	
	

}
