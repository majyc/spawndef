package resurgence.spawndef.controller;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Dialogs;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import resurgence.spawndef.InvalidFormatException;
import resurgence.spawndef.SpawnDef;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	/**
	 * The data as an observable list of SpawnDefProperties.
	 */
	private ObservableList<SpawnDefProperty> propertyData = FXCollections.observableArrayList();
	private ObservableList<SpawnDefFlag> flagData = FXCollections.observableArrayList();
	
	private SpawnDef spawnDef;

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
			RootLayoutController rlController = loader.getController();
			rlController.setMainApp(this);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			Dialogs.showErrorDialog(primaryStage,
					"Could not load RootLayout file:\n" + "../view/RootLayout.fxml",
					"Could not start up", "Error", e);
		}
//		showSpawnDefOverview();
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

			SpawnDefOverviewController sdoController = loader.getController();
			sdoController.setMainApp(this);
		} catch (IOException e) {
			Dialogs.showErrorDialog(primaryStage,
					"Could not load SpawnDefOverview file:\n" + "../view/SpawnDefOverview.fxml",
					"Could not start up", "Error", e);
		}
	}


	/**
	 * populateProperties - fill the ObservableList with data from the SpawnDef this controls for properties
	 * @param propertyData 
	 * 
	 */
	void populateProperties(ObservableList<SpawnDefProperty> propertyData) {
		if (spawnDef == null) return;
		for (String spawnDefPropertyName : SpawnDef.PROPERTIES_NAMES) {
			propertyData.add(new SpawnDefProperty(spawnDef, spawnDefPropertyName, spawnDef.getPropertyByName(spawnDefPropertyName)));			
		}
	}

	/**
	 * populateFlags - fill the ObservableList with data from the SpawnDef this controls for flags
	 * @param flagData 
	 * 
	 */
	void populateFlags(ObservableList<SpawnDefFlag> flagData) {
		if (spawnDef == null) return;
		for (String spawnFlagName : SpawnDef.FLAG_NAMES) {
			flagData.add(new SpawnDefFlag(spawnDef, spawnFlagName, spawnDef.getFlag(spawnFlagName)));
		}
		for (final SpawnDefFlag sdf : flagData) {
			sdf.valueProperty().addListener(new ChangeListener<Object>(){
		        @Override 
		        public void changed(ObservableValue<?> o,Object oldVal, 
		                 Object newVal){
		        	// if changed is called, it's automatically dirty, regardless of old vs. new value
		        	sdf.getOwner().setDirty(true);
		        }
		      });
		}
	}


	/**
	 * Returns the data as an observable list of SpawnDefProperties. 
	 * @return
	 */
	public ObservableList<SpawnDefProperty> getPropertyData() {
		return propertyData;
	}
		
	/**
	 * Returns the data as an observable list of SpawnDefFlags. 
	 * @return
	 */
	public ObservableList<SpawnDefFlag> getFlagData() {
		return flagData;
	}
		

	
	/**
	 * Returns the SpawnDef file preference, i.e. the file that was last opened.
	 * The preference is read from the OS specific registry. If no such
	 * preference can be found, null is returned.
	 * 
	 * @return
	 */
	public File getSpawnDefFilePath() {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		String filePath = prefs.get("filePath", null);
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}

	/**
	 * Sets the file path of the currently loaded file.
	 * The path is persisted in the OS specific registry.
	 * 
	 * @param file the file or null to remove the path
	 */
	public void setSpawnDefFilePath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());

			// Update the stage title
			primaryStage.setTitle("SpawnDef Editor - " + file.getName());
		} else {
			prefs.remove("filePath");

			// Update the stage title
			primaryStage.setTitle("SpawnDef Editor");
		}
	}	


	/**
	 * saveSpawnDef - write the SpawnDef back to the file
	 * @param propertyData 
	 */
	void saveSpawnDefToFile(File file) {
		saveProperties(propertyData);
		saveFlags(flagData);
		String content = getSpawnDefString();
		try {
			if (file.exists()) {
				FileUtil.saveFileWithBackup(file, content);
			}
			FileUtil.saveFile(file, content);
			setDirty(false);
		} catch (IOException e) {
			Dialogs.showErrorDialog(primaryStage,
					"Could not save data to file:\n" + file.getPath(),
					"Could not save data", "Error", e);
		}
	}

	private String getSpawnDefString() {
		if (spawnDef != null) {
			return spawnDef.toString();
		} else {
			return "";			
		}			
	}
	
	
	/**
	 * @param file
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	void loadSpawnDef(File file) throws IOException, InvalidFormatException {
		String content = FileUtil.readFile(file);
		spawnDef = new SpawnDef(content);
		setDirty(false);
		populateProperties(propertyData);
		populateFlags(flagData);
	};
	
	boolean isDirty() {
		return spawnDef != null && spawnDef.isDirty();
	}


	/**
	 * saveProperties - save the property data from the ObvervableList into the SpawnDef this controls
	 * @param propertyData 
	 * 
	 */
	void saveProperties(ObservableList<SpawnDefProperty> propertyData) {
		if (spawnDef == null) return;
		for (SpawnDefProperty property : propertyData) {
			String name = property.getName();
			String value = property.getValue();
			spawnDef.setPropertyByName(name, value);
		}
	}

	/**
	 * saveFlags - save the flag data from the ObvervableList into the SpawnDef this controls
	 * @param flagData 
	 * @throws InvalidFormatException 
	 * 
	 */
	void saveFlags(ObservableList<SpawnDefFlag> flagData) {
		if (spawnDef == null) return;
		try {
			for (SpawnDefFlag flag : flagData) {
				String name = flag.getName();
				boolean value = flag.getValue();
				spawnDef.setFlag(name, value);
			}
		} catch (InvalidFormatException e) {
			Dialogs.showErrorDialog(primaryStage,
					"Error saving flags",
					"Could not save flags:\n", "Error", e);
		}
	}


	
	public SpawnDef getSpawnDef() {
		return spawnDef;
	}

	public void setSpawnDef(SpawnDef spawnDef) {
		this.spawnDef = spawnDef;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void setDirty(boolean b) {
		spawnDef.setDirty(b);
	}


	public void changedTab(Number oldValue, Number newValue) {
		// TODO Auto-generated method stub
		
	}
}
