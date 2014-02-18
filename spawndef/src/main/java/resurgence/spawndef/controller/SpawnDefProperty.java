package resurgence.spawndef.controller;

import resurgence.spawndef.SpawnDef;
import javafx.beans.property.SimpleStringProperty;

public class SpawnDefProperty {
	private final SimpleStringProperty name;
	private final SimpleStringProperty value;
	private final SpawnDef owner;
	
	SpawnDefProperty(SpawnDef owner, String name, String value) {
		this.owner = owner;
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
		if (!this.value.equals(value)) {
			this.owner.setDirty(true);			
		}
		this.value.set(value);
	}
	public String getTip() {
		// TODO Auto-generated method stub
		return null;
	}
}
