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
		case "PYR":
			break;
		}
		return null;
	}
	
	private DefinitionFactory() {}

	// could this be generic?
	public static Definition newInstance(String type, Scanner scanner) {
		String constructorLine = null;
		switch(type) {
		case "Pos":
			if (scanner.hasNextLine()) {
				constructorLine = scanner.nextLine();
			}
			return Pos.newInstance(type + " " + constructorLine);
		case "Group":
			String name = scanner.next();
			constructorLine = "Group " + name;
			while(scanner.hasNextLine()) {
				String nextLine = scanner.nextLine();
				constructorLine += "\n" + nextLine;
				if (nextLine.equalsIgnoreCase(Group.GROUP_END)) {
					break;
				}
			}
			return new Group(constructorLine);
		case "PYR":
			break;
		}
		return null;
	}

}
