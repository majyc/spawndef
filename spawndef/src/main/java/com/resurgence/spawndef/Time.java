package com.resurgence.spawndef;

import java.util.ArrayList;
import java.util.Scanner;

/*
 *    
    """Time - command to set the time of day in the demo
    >>> t = Time(10.00)
    >>> print(t)
    0   0   TIME 10.00
    
    """
    def __init__(self, time):
        super().__init__()
        self.data = ["{:.2f}".format(time)]
        self.name = "TIME"
 */
public class Time extends Command {
	public static final String TIME_COMMAND = "TIME";
	private ArrayList<String> data;
	public Time() {
		super();
		name = TIME_COMMAND;
		data = new ArrayList<String>();
		data.add("0");
	}
	
	/**
	 * Constructor accepts a single time value, sets everything else to 0
	 * 
	 * @param time
	 */
	public Time(double time) {
		this();
		setTime(time);
	}
	
	
	/**
	 * String constructor - expects a string like
	 * "0   33   TIME 11.00"
	 * from the demorecord format with the values for
	 * timeincrement_ticks entity TIME time_double
	 * 
	 * @param s
	 */
	public Time(String s) {
		this();
		parse(s);
	}
	
	/**
	 * copy Constructor
	 * @param t
	 */
	public Time(Time t) {
		this(t.toString());
	}
	
	protected void parse(String string) {
		try (Scanner scanner = new Scanner(string)) {
			timeIncrement = scanner.nextInt();
			entity = scanner.next();
			name = scanner.next();
			setTime(scanner.nextDouble());
		}
	}
	
	
	@Override
	public String dataToString() {
		String s = "";
		if (data != null) {
			for (Object iterable_element : data) {
				s += " " + iterable_element.toString();
			}
		}
		return s;
	}

	public String getTime() {
		return data.get(0);
	}
	public void setTime(String time) {
		data.remove(0);
		// enforce formatting
		double timeD = Double.parseDouble(time); 
		time = String.format("%1$.2f", timeD);
		data.add(time);
	}
	public void setTime(double time) {
		data.remove(0);
		// enforce formatting
		String timeS = String.format("%1$.2f", time);
		data.add(timeS);
	}

}
