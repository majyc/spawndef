package com.resurgence.spawndef;

import java.util.EnumMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class Pos extends Definition {
	private static final String POS = "Pos";
	private EnumMap<COORD, String> data;
	
	public static Pos newInstance(String s) {
		return new Pos(s);
	}
	
	public Pos() {
		name = POS;
		data= new EnumMap<COORD, String>(COORD.class);
		// initialize array
		data.put(COORD.X, "0");
		data.put(COORD.Y, "0");
		data.put(COORD.Z, "0");
	}

	public Pos(String x, String y, String z) {
		this();
		setX(x);
		setY(y);
		setZ(z);
	}

	public Pos(Definition p) {
		this(p.toString());
	}

	public Pos(String string) {
		this();
		parse(string);
	}

	private void parse(String string) {
		try (Scanner scanner = new Scanner(string)) {
			setName(scanner.next());
			setX(scanner.nextDouble());
			setY(scanner.nextDouble());
			setZ(scanner.nextDouble());
		}
	}
	
	public void setX(String x) {
		setValue(COORD.X, x);
	}
	public void setX(double x) {
		setValue(COORD.X, x);
	}

	public void setY(String y) {
		setValue(COORD.Y, y);	
	}
	public void setY(double y) {
		setValue(COORD.Y, y);	
	}
	
	public void setZ(String z) {
		setValue(COORD.Z, z);
	}
	public void setZ(double z) {
		setValue(COORD.Z, z);
	}
	
	public String dataToString() {
		String s = "";
		if (data != null) {
			for (Entry<COORD, String> iterable_element : data.entrySet()) {
				s += " " + iterable_element.getValue();
			}
		}
		return s;
	}

	public void setValue(COORD c, String s) {
		setValue(c, Double.parseDouble(s));
	}

	public void setValue(COORD pos, double d) {
		String s;
		// zeroes don't include decimal -- don't know if that's required or just a funny bit about the way DEMORECORD produces output
		if (d == 0) {
			s = "0";
		} else {
			s = String.format("%1$.6f", d);
		}
		data.put(pos, s);
	}
	
	public String getValue(COORD pos) {
		return data.get(pos);
	}

	public String get(COORD pos) {
		return data.get(pos);
	}

	public void put(COORD pos, String s) {
		data.put(pos, s);
	}

	public void move(COORD pos, double distance) {
		double d = Double.parseDouble(getValue(pos));
		d += distance;
		setValue(pos, d);
	}

	public void move(double xDistance, double yDistance, double zDistance) {
		COORD[] values = COORD.values();
		double[] distances = {xDistance, yDistance, zDistance};
		for (int i = 0; i < values.length; i++) {
			COORD c = values[i];
			move(c, distances[i]);
		}
	}

	/**
	 * moveDirection - moves the Pos in a compass direction by a number of feet; use @moveAtAngle to move freely in different directions
	 * @param dir  compass direction, including UP and DOWN
	 * @param feet number of feet to move in a direction; negative feet will move in the opposite direction (negative move WEST is 
	 * same as positive move EAST)
	 */
	public void moveDirection(COMPASS dir, double feet) {
		switch(dir) {
		case NORTH:
			move(COORD.Y, -feet);
			break;
		case NORTHEAST:
			moveAtAngle(45, feet);
			break;
		case EAST:
			move(COORD.X, -feet);
			break;
		case SOUTHEAST:
			moveAtAngle(135, feet);
			break;
		case SOUTH:
			move(COORD.Y, feet);
			break;
		case SOUTHWEST:
			moveAtAngle(225, feet);
			break;
		case WEST:
			move(COORD.X, feet);
			break;
		case NORTHWEST:
			moveAtAngle(315, feet);
			break;
		case UP:
			move(COORD.Z, feet);
			break;
		case DOWN:
			move(COORD.Z, -feet);
			break;
		default:
			break;
		}		
	}

	/**
	 * moveAtAngle moves a number of feet at an arbitrary angle 
	 * @param angle measured in degrees starting from 0 straight North and going clockwise
	 * @param feet  distance to move
	 */
	public void moveAtAngle(double angle, double feet) {
		move(Math.sin(Math.toRadians(angle)) * -feet,  Math.cos(Math.toRadians(angle)) * -feet, 0);
	}

	/**
	 * moveAtAngle moves a number of feet along an arbitrary angle with regard to the Z-axis 
	 * @param angle measured in degrees starting from 0 in the current Z plane and 90 straight up
	 * @param feet  distance to move
	 * @param fixedAxis which axis to regard as fixed (e.g. Y means that moving up at an angle will change only the Z and X coords)
	 * @throws InvalidFixedAxisException 
	 */
	public void moveAtZAngle(double angle, double feet, COORD fixedAxis) throws InvalidFixedAxisException {
		// -feet for X and Y axis because their interpretation is backwards from the usual Cartesian plane,
		// growing as you move to the left
		// Z axis fortunately remains the same
		switch(fixedAxis) {
		case X:
			move(0, Math.cos(Math.toRadians(angle)) * -feet,  Math.sin(Math.toRadians(angle)) * feet);	
			break;
		case Y:
			move(Math.cos(Math.toRadians(angle)) * -feet, 0,  Math.sin(Math.toRadians(angle)) * feet);	
			break;
		case Z:
			// degenerate case, you've asked to move vertically while holding Z steady.
			throw new InvalidFixedAxisException("Cannot fix Z axis when moving at Z angle");
		default:
			throw new InvalidFixedAxisException("Unrecognized COORD [" + fixedAxis + "]");
		}
		
		if (fixedAxis == COORD.Y) {
					
		} else {
			
		}
	}

	public double getX() {
		return Double.parseDouble(getValue(COORD.X));
	}
	public double getY() {
		return Double.parseDouble(getValue(COORD.Y));
	}
	public double getZ() {
		return Double.parseDouble(getValue(COORD.Z));
	}
	
	
}