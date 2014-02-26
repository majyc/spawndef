package resurgence.spawndef.controller;

import javafx.beans.property.SimpleStringProperty;
import resurgence.spawndef.Actor.Element;
import resurgence.spawndef.SpawnDef;

public class ActorProperty {
	
	private final SimpleStringProperty name;
	private final SimpleStringProperty value;
	private final SpawnDef owner;
	
	ActorProperty(SpawnDef owner, Element actorElementName, String value) {
		this.owner = owner;
		this.name = new SimpleStringProperty(actorElementName.toString());
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

}
