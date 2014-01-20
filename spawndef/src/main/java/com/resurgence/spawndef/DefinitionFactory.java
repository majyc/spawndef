package com.resurgence.spawndef;

import java.util.Scanner;

public class DefinitionFactory {

	/**
	 * static factory to create an appropriate DefInstance based on the type and the string representation to initialize the type
	 * @param type
	 * @param constructorString
	 * @return
	 */
	public static Definition newInstance(String type, String constructorString) {
		switch(type) {
		case "Pos":
			return Pos.newInstance(constructorString);
		case "Property":
			return Property.newInstance(constructorString);
		case "PYR":
			break;
		}
		return null;
	}
	
	// cannot instantiate
	private DefinitionFactory() {}

	// could this be generic?
	public static Definition newInstance(String type, Scanner scanner) {
		String constructorLine = null;
		switch(type) {
		case "Pos":
			constructorLine = "NaN NaN NaN"; // default to something obviously wrong
			if (scanner.hasNextLine()) {
				constructorLine = scanner.nextLine();
			}
			return Pos.newInstance(type + " " + constructorLine);
		case "Group":
		case "Def":	
			String name = scanner.next();
			constructorLine = type + " " + name;
			while(scanner.hasNextLine()) {
				String nextLine = scanner.nextLine().trim();
				if (nextLine.length() == 0) continue;
				constructorLine += "\n" + nextLine;
				if (nextLine.equalsIgnoreCase(Group.GROUP_END)) {
					break;
				}
			}
			return new Group(constructorLine);
		case "Property":
			constructorLine = "\"MISSING DATA\" \"This shouldn't happen\" 0"; //default to something obviously wrong
			if (scanner.hasNextLine()) {
				constructorLine = scanner.nextLine();
			}
			return Property.newInstance(type + " " + constructorLine);
		case "PYR":
			if (scanner.hasNextLine()) {
				constructorLine = scanner.nextLine();
			}
			return Pyr.newInstance(type + " " + constructorLine);
		}
		return null;
	}

}
