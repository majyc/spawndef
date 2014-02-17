package resurgence.spawndef.controller;

import javafx.beans.property.SimpleStringProperty;

public class SpawnDefProperty {
	private final SimpleStringProperty name;
	private final SimpleStringProperty value;
	
	SpawnDefProperty(String name, String value) {
		this.name = new SimpleStringProperty(name);
		this.value = new SimpleStringProperty(value);
	}
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public String getValue() {
		return value.get();
	}
	public void setValue(String value) {
		this.value.set(value);
	}
	public String getDialog() {
		return value.get();
	}
	public void setDialog(String dialog) {
		this.value.set(dialog);
	}

}
