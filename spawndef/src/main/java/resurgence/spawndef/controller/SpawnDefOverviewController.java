package resurgence.spawndef.controller;

import java.util.List;

import resurgence.spawndef.SpawnDef;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SpawnDefOverviewController {
	
	@FXML
	private TreeView<String> sdTree = new TreeView<String>();
	
	@FXML
	private TableView<SpawnDefProperty> propertyTable;
	@FXML
	private TableColumn<SpawnDefProperty, String> nameColumn;
	@FXML
	private TableColumn<SpawnDefProperty, String> valueColumn;
	  
	
	@SuppressWarnings("unused")
	private MainApp mainApp;
	
	private SpawnDef spawnDef;
	
	public SpawnDefOverviewController() {
		
	}
	
	@SuppressWarnings("unchecked")
	@FXML
	private void initialize() {
	    //Create a TreeItem that will act as the root item of the TreeView
	    TreeItem<String> sdRoot = new TreeItem<String>("A Spawn Def"); 
	    //Add TreeItems to the root
	    sdRoot.getChildren().addAll(
	        new TreeItem<String>("Properties"),
	        new TreeItem<String>("Flags"),
	        new TreeItem<String>("Actors"));

	    nameColumn.setCellValueFactory(new PropertyValueFactory<SpawnDefProperty, String>("name"));
	    valueColumn.setCellValueFactory(new PropertyValueFactory<SpawnDefProperty, String>("value"));
	  	    
	    //Use the setRoot method to set the root TreeItem
	    sdTree.setRoot(sdRoot);			
	
	}
	
	  /**
	  * Is called by the main application to give a reference back to itself.
	  * 
	  * @param mainApp
	  */
	  public void setMainApp(MainApp mainApp) {
	      this.mainApp = mainApp;
	      
	      // Add observable list data to the table
	      propertyTable.setItems(mainApp.getPropertyData());	      
	  }

		/**
		 * @param propertyData 
		 * 
		 */
	  void populateProperties(ObservableList<SpawnDefProperty> propertyData) {
		 if (spawnDef == null) return;
		 for (String spawnDefPropertyName : SpawnDef.PROPERTIES_NAMES) {
			 propertyData.add(new SpawnDefProperty(spawnDefPropertyName, spawnDef.getPropertyByName(spawnDefPropertyName)));			
		 }
	  }

		public SpawnDef getSpawnDef() {
			return spawnDef;
		}

		public void setSpawnDef(SpawnDef spawnDef) {
			this.spawnDef = spawnDef;
		}	


}
