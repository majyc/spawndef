package com.resurgence.spawndef;

import java.util.ArrayList;
import java.util.Scanner;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

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
		boolean needToTrimEnd = false;  // if the very last piece of data is a GROUP_END we don't want it to have a trailing newline
		for (Definition element : data) {
			needToTrimEnd = false;
			String lines[] = element.toString().split("\n");
			String temp = Joiner.on("\n\t").join(lines);
			s += "\t" + temp;
			if (lines[lines.length-1].equalsIgnoreCase(GROUP_END)) {
				s += "\n";			  // make sure that GROUP_END is followed by new line
				needToTrimEnd = true; // except the final piece of data in the list
			}
		}
		if (needToTrimEnd) s = s.replaceAll("\\s+$", ""); //trim only trailing whitespace... we carefully added leading space
		return s;
	}

}
