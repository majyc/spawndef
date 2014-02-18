package resurgence.spawndef.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class SpawnDefOverviewController {

	@FXML
	private TreeView<String> sdTree = new TreeView<String>();

	@FXML
	private TableView<SpawnDefProperty> propertyTable;
	@FXML
	private TableColumn<SpawnDefProperty, String> propNameColumn;
	@FXML
	private TableColumn<SpawnDefProperty, String> propValueColumn;

	@FXML
	private Label treeLabel;

	@SuppressWarnings("unused")
	private MainApp mainApp;

	public SpawnDefOverviewController() {

	}

	@SuppressWarnings("unchecked")
	@FXML
	private void initialize() {

		final String treeLabelText = "Selected Tree Item From SD Tree: ";
		//Create labels to highlight the selected items from the TreeViews
		//treeLabel = new Label(treeLabelText);

		//Create a TreeItem that will act as the root item of the TreeView
		TreeItem<String> sdRoot = new TreeItem<String>("A Spawn Def"); 
		//Add TreeItems to the root
		sdRoot.getChildren().addAll(
				new TreeItem<String>("Properties"),
				new TreeItem<String>("Flags"),
				new TreeItem<String>("Actors"));
		
		propNameColumn.setCellValueFactory(new PropertyValueFactory<SpawnDefProperty, String>("name"));

		propValueColumn.setCellValueFactory(new PropertyValueFactory<SpawnDefProperty, String>("value"));
		propValueColumn.setCellFactory(TextFieldTableCell.<SpawnDefProperty>forTableColumn());
		propValueColumn.setOnEditCommit(
				new EventHandler<CellEditEvent<SpawnDefProperty, String>>() {
					@Override
					public void handle(CellEditEvent<SpawnDefProperty, String> t) {
						((SpawnDefProperty) t.getTableView().getItems().get(
								t.getTablePosition().getRow())
								).setValue(t.getNewValue());
					}
				}
				);


		//Use the setRoot method to set the root TreeItem
		sdTree.setRoot(sdRoot);

		//Set a ChangeListener to handle events that occur with a TreeItem
		//is selected
		sdTree.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<TreeItem <String>>() {
					public void changed(ObservableValue<? extends TreeItem<String>> observableValue, 
							TreeItem<String> oldItem, TreeItem<String> newItem) {
						treeLabel.setText(treeLabelText + newItem.getValue());
					}
				});

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

}
