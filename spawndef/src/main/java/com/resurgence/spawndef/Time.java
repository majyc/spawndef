package com.resurgence.spawndef;

import java.util.ArrayList;
import java.util.HashMap;

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
	public Time(String time) {
		super();
		name = TIME_COMMAND;
		data = new ArrayList<String>();
		// enforce formatting
		double timeD = Double.parseDouble(time); 
		time = String.format("%1$.2f", timeD);
		data.add(time);
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

}
