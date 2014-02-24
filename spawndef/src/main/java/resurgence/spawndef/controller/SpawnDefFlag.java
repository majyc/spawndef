package resurgence.spawndef.controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import resurgence.spawndef.SpawnDef;

public class SpawnDefFlag {

	private final SimpleStringProperty name;
	private final SimpleBooleanProperty value;
	private final SpawnDef owner;
	
	public SpawnDef getOwner() {
		return owner;
	}

	SpawnDefFlag(SpawnDef owner, String name, boolean value) {
		this.owner = owner;
		this.name = new SimpleStringProperty(name);
		this.value = new SimpleBooleanProperty(value);
	}
	
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public Boolean getValue() {
		return value.get();
	}
	
	public BooleanProperty valueProperty() {
        return value;
    }
	
	public void setValue(boolean value) {
		if (!this.value.equals(value)) {
			this.owner.setDirty(true);			
		}
		this.value.set(value);
	}	
	
}
