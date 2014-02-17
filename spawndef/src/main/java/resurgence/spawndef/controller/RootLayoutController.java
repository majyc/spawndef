package resurgence.spawndef.controller;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import resurgence.spawndef.InvalidFormatException;

public class RootLayoutController {
	private MainApp mainApp;
	

	public RootLayoutController() {}
	
	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	
	/**
	 * Opens a FileChooser to let the user select an address book to load.
	 * @throws InvalidFormatException 
	 * @throws IOException 
	 */
	@FXML
	private void handleOpen() throws IOException, InvalidFormatException {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"SpawnDef files (*.sd)", "*.sd");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

		if (file != null) {
			mainApp.loadSpawnDef(file);
		}
	}

	/**
	 * Saves the file to the person file that is currently open. If there is no
	 * open file, the "save as" dialog is shown.
	 * @throws IOException 
	 */
	@FXML
	private void handleSave() throws IOException {
		File spawnDefFile = mainApp.getSpawnDefFilePath();
		if (spawnDefFile != null) {
			mainApp.saveSpawnDefToFile(spawnDefFile);
		} else {
			handleSaveAs();
		}
	}

	/**
	 * Opens a FileChooser to let the user select a file to save to.
	 * @throws IOException 
	 */
	@FXML
	private void handleSaveAs() throws IOException {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"SpawnDef files (*.sd)", "*.sd");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

		if (file != null) {
			// Make sure it has the correct extension
			if (!file.getPath().endsWith(".sd")) {
				file = new File(file.getPath() + ".sd");
			}
			mainApp.saveSpawnDefToFile(file);
		}
	}
	
	
}
