package resurgence.spawndef.controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import resurgence.spawndef.InvalidFormatException;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	  /**
	  * The data as an observable list of SpawnDefProperties.
	  */
	private ObservableList<SpawnDefProperty> propertyData = FXCollections.observableArrayList();
	
	
	public MainApp() {
	}


	@Override
	public void start(Stage primaryStage) throws InvalidFormatException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("SpawnDef Editor");
		try {
			// Load the root layout from the fxml file
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("../view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			Scene scene = new Scene(rootLayout);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
		}
	    showSpawnDefOverview();
	}
	  /**
	  * Returns the main stage.
	  * @return
	  */
	  public Stage getPrimaryStage() {
	      return primaryStage;
	  }
	  
	  /**
	  * Shows the person overview scene.
	 * @throws InvalidFormatException 
	  */
	  public void showSpawnDefOverview() throws InvalidFormatException {
	      try {
	          // Load the fxml file and set into the center of the main layout
	          FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("../view/SpawnDefOverview.fxml"));
	          AnchorPane overviewPage = (AnchorPane) loader.load();
	          rootLayout.setCenter(overviewPage);
	          
	          SpawnDefOverviewController controller = loader.getController();
	          controller.setMainApp(this);
		      // Add some sample data. Eventually this will be done as part of loading a new SpawnDef
	          // Wrapper might go away to be replace by direct manipulation of the model...
	          SpawnDefWrapper sdw = new SpawnDefWrapper();
	          controller.setSpawnDef(sdw.getSpawnDef());
		      controller.populateProperties(propertyData);
	      } catch (IOException e) {
	          // Exception gets thrown if the fxml file could not be loaded
	          e.printStackTrace();
	      }
	  }
	  


	/**
	   * Returns the data as an observable list of Persons. 
	   * @return
	   */
	   public ObservableList<SpawnDefProperty> getPropertyData() {
	       return propertyData;
	   }
	  
	  
	public static void main(String[] args) {
		launch(args);
	}
}
