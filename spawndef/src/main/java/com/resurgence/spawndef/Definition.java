package com.resurgence.spawndef;

public abstract class Definition {

	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name + dataToString();
	}
	
	protected abstract String dataToString();

}
