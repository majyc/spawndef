package com.resurgence.spawndef;

import java.util.ArrayList;
import java.util.Scanner;

import com.google.common.base.Joiner;

public class Group implements IDefinition {

	public static final String GROUP_BEGIN = "Group";
	public static final String GROUP_END = "End";
	private ArrayList<IDefinition> data;
	private String name;
	private String endDelimiter;
	private String beginDelimiter;

	public Group() {
		setName("Default");
		setBeginDelimiter(GROUP_BEGIN);
		setEndDelimiter(GROUP_END);
		data = new ArrayList<IDefinition>();
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
			IDefinition e = (IDefinition) DefinitionFactory.newInstance(next, scanner);
			data.add(e);
		}		
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(beginDelimiter).append(" ").append(name).append(dataToString()).append("\n").append(endDelimiter).append("\n");
		return sb.toString();
	}
	
	protected String dataToString() {
		String s = "";
		for (IDefinition element : data) {
			String lines[] = element.toString().split("\n");
			String temp = Joiner.on("\n\t").join(lines);
			s += "\n\t" + temp;
		}
		return s;
	}

	public String getBeginDelimiter() {
		return beginDelimiter;
	}

	public void setBeginDelimiter(String beginDelimiter) {
		this.beginDelimiter = beginDelimiter;
	}

	public String getEndDelimiter() {
		return endDelimiter;
	}

	public void setEndDelimiter(String endDelimiter) {
		this.endDelimiter = endDelimiter;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
