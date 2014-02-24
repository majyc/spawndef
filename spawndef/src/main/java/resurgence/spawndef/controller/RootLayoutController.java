package resurgence.spawndef.controller;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogOptions;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooserBuilder;
import resurgence.spawndef.InvalidFormatException;


public class RootLayoutController {
	private static final String SPAWN_DEF_EXT = ".spawndef";

	private MainApp mainApp;
	
	@FXML
	private MenuItem saveItem;
	@FXML
	private MenuItem saveAsItem;
	
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
		if (mainApp.isDirty()) {
			DialogResponse response = Dialogs.showConfirmDialog(mainApp.getPrimaryStage(),
					"You have unsaved changes. Cancel if you want to save them", "Unsaved Changes", "SpawnDef has unsaved changes", DialogOptions.OK_CANCEL);
			if (response == DialogResponse.CANCEL) return;
		}
		File file = chooseFile("Open File", FileOperation.OPEN);

		if (file != null) {
			mainApp.loadSpawnDef(file);
			mainApp.showSpawnDefOverview();
			mainApp.setSpawnDefFilePath(file);
			saveItem.setDisable(false);
			saveAsItem.setDisable(false);
		}
	}

	/**
	 * @return
	 */
	private File chooseFile(String title, FileOperation fileOperation) {
		FileChooserBuilder fcb = FileChooserBuilder.create();

		File currFile = mainApp.getSpawnDefFilePath();
		String currentDir = ".";
		if (currFile == null) {
			currentDir = System.getProperty("user.dir") + File.separator;
		} else {
			currentDir = currFile.getParent();
		}
		currFile = new File(currentDir);
		FileChooser fileChooser = fcb.title(title).initialDirectory(currFile).build();
		// Set extension filter

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"SpawnDef files (*" + SPAWN_DEF_EXT + ")", "*" + SPAWN_DEF_EXT);
		fileChooser.getExtensionFilters().add(extFilter);
		File file = null;
		switch (fileOperation) {
		case OPEN:
			file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
			break;
		case SAVE:
			file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
			break;
		default:
			break;
		}
		return file;
	}

	/**
	 * Saves the file to the person file that is currently open. If there is no
	 * open file, the "save as" dialog is shown.
	 * @throws IOException 
	 */
	@FXML
	private void handleSave() throws IOException {
		if (!mainApp.isDirty()) return;
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
		File file = chooseFile("Save file as...", FileOperation.SAVE);

		if (file != null) {
			// Make sure it has the correct extension
			if (!file.getPath().endsWith(SPAWN_DEF_EXT)) {
				file = new File(file.getPath() + SPAWN_DEF_EXT);
			}
			mainApp.saveSpawnDefToFile(file);
			saveItem.setDisable(true);
		}
	}
	
	@FXML
	private void handleExit() {
		if (mainApp.isDirty()) {
			DialogResponse response = Dialogs.showConfirmDialog(mainApp.getPrimaryStage(),
					"You have unsaved changes. Cancel if you want to save them", "Unsaved Changes", "SpawnDef has unsaved changes", DialogOptions.OK_CANCEL);
			if (response == DialogResponse.CANCEL) return;
		}
		System.exit(0);
	}
	
}
