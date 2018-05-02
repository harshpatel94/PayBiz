package application;

/**
 * @author hpatel111
 * this is the mail class starting from here
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage)  throws Exception{
			setUserAgentStylesheet(STYLESHEET_MODENA);
			Parent root = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
			Scene scene = new Scene(root,500,500);		
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Bill Payment System");
			primaryStage.setScene(scene);
			primaryStage.show();
			
	}
	
	public static void main(String[] args) {
	
		//Printing author
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        System.out.println("\n\nCur dt=" + timeStamp + "\nProgrammed by Harsh Patel\n");
		
		
		launch(args);
	}
}
