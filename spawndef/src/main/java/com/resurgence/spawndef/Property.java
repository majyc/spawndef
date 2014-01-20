package com.resurgence.spawndef;

import java.util.Scanner;

public class Property extends Definition {
	
	public static String PROPERTY = "Property";
	private String propName;
	private String propValue;
	private String extra;
	
	public Property(String constructorString) {
		parse(constructorString);
	}
	
	
	private void parse(String constructorString) {
		try (Scanner scanner = new Scanner(constructorString)) {
			name = scanner.next();
			propName = scanner.next();
			propValue = scanner.next();
			extra = scanner.next();
		}
	}

	public static Property newInstance(String constructorString) {
		return new Property(constructorString);
	}
	
	public Property(Property p) {
		parse(p.toString());
	}


	@Override
	protected String dataToString() {
		String s = String.format("   %s   %s   %s", propName, propValue, extra );
		return s;
	}


	public String getPropName() {
		return propName;
	}


	public void setPropName(String propName) {
		this.propName = propName;
	}


	public String getPropValue() {
		return propValue;
	}


	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}


	public String getExtra() {
		return extra;
	}


	public void setExtra(String extra) {
		this.extra = extra;
	}



}
