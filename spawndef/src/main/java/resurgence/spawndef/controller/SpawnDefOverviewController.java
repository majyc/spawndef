package resurgence.spawndef.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTableCell;
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
	private TableView<SpawnDefFlag> flagTable;
	@FXML
	private TableColumn<SpawnDefFlag, String> flagNameColumn;
	@FXML
	private TableColumn<SpawnDefFlag, Boolean> flagValueColumn;
	
	
	@FXML
	private Label treeLabel;

	@FXML
	private TabPane tabPane;
	
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

		flagNameColumn.setCellValueFactory(new PropertyValueFactory<SpawnDefFlag, String>("name"));

		
		flagValueColumn.setCellValueFactory(new PropertyValueFactory<SpawnDefFlag, Boolean>("value"));
		flagValueColumn.setCellFactory(CheckBoxTableCell.<SpawnDefFlag>forTableColumn(flagValueColumn));

		//Use the setRoot method to set the root TreeItem
		sdTree.setRoot(sdRoot);

		//Set a ChangeListener to handle events that occur with a TreeItem
		//is selected
		sdTree.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<TreeItem <String>>() {
					public void changed(ObservableValue<? extends TreeItem<String>> observableValue, 
							TreeItem<String> oldItem, TreeItem<String> newItem) {
						String selected = newItem.getValue();
						treeLabel.setText(treeLabelText + selected);
						selected = selected.toLowerCase();
						if (selected.startsWith("flag")) {
							flagTable.toFront();
						} else if (selected.startsWith("prop")) {
							propertyTable.toFront();
						}
					}
				});

		//Set a ChangeLister to handle when the tab selection changes
		tabPane.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
		        mainApp.changedTab(oldValue, newValue);
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
		flagTable.setItems(mainApp.getFlagData());
	}

}
