package resurgence.spawndef;

import java.util.Scanner;

public class Property implements IDefinition {
	
	public static String PROPERTY = "Property";
	private String propName;
	private String propValue;
	private String extra;
	private String name;
	
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
	
	public String toString() {
		return name + dataToString();
	}
	
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


	@Override
	public String getName() {
		return this.name;
	}


	@Override
	public void setName(String name) {
		this.name = name;
	}



}
