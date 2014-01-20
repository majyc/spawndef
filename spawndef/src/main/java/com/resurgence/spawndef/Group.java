package com.resurgence.spawndef;

import java.util.ArrayList;
import java.util.Scanner;

import com.google.common.base.Joiner;

public class Group extends CompoundDefinition {

	public static final String GROUP_BEGIN = "Group";
	public static final String GROUP_END = "End";
	private ArrayList<Definition> data;

	public Group() {
		setName("Default");
		setBeginDelimiter(GROUP_BEGIN);
		data = new ArrayList<Definition>();
	}
	
	/**
	 * String constructor
	 * @param s constructor string in canonical format
	 */
	public Group(String s) {
		this();
		try (Scanner scanner = new Scanner(s)) {
			String type = scanner.next();
			parse(type, scanner);
		}
	}

	private void parse(String type, Scanner scanner) {
		setBeginDelimiter(type);
		setName(scanner.next());
		while(scanner.hasNext()) {
			String next = scanner.next();
			if (next.equalsIgnoreCase(GROUP_END)) continue;
			Definition e = (Definition) DefinitionFactory.newInstance(next, scanner);
			data.add(e);
		}		
	}

	@Override
	protected String dataToString() {
		String s = "";
		for (Definition element : data) {
			String lines[] = element.toString().split("\n");
			String temp = Joiner.on("\n\t").join(lines);
			s += "\n\t" + temp;
		}
		return s;
	}

}
