package resurgence.spawndef;

import java.util.Scanner;

/**
 *  Pyr - sets the Pitch, Yaw, and Roll of the entity
    The PYR command is followed by a set of three numerical map coordinates in Pitch Yaw Roll format. 
    
    These values are expressed in radians, with values ranging from -3.14159 to 3.14159 (-pi to +pi). 
    A total of 2pi (6.28319) represents a complete rotation of 360 degrees for any of the three axes.
    The Pitch (or "P") value determines the front-to-back tilt of an NPC, object, or other entity -- whether an entity is leaning to the front or to the back.
    The Yaw (or "Y") value determines the left-right facing of an NPC, object, or other entity -- whether an entity is facing to the right or left, rotating around its vertical axis.
    The Roll (or "R") value determines the side-to-side tilt of an NPC, object, or other entity -- whether an entity is leaning/rotated to the left or to the right.
    
    Note that the axis of rotation for most models is at the base of the model and not through their apparent center.
    
    This version will actually accept just about any strings as the Pitch, Yaw, and Roll, because it just stores them,
    and doesn't really manipulate them. (Unlike the Pos command, which has a bunch of move methods)

 * @author Joshua
 *
 */
public class Pyr implements IDefinition {
	private static final String PYR = "PYR";
	private static final double TWO_PI = Math.PI * 2;
	private String p;
	private String y;
	private String r;
	private String name;
	
	public Pyr() {
		name = PYR;
		p = "0";
		y = "0";
		r = "0";			
	}
	
	public boolean isRadians() {
		return TWO_PI >= Double.parseDouble(p) && TWO_PI >= Double.parseDouble(y) && TWO_PI >= Double.parseDouble(r);
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

	public Pyr(String p, String y, String r) {
		this();
		this.p = p;
		this.y = y;
		this.r = r;
	}

	public Pyr(String constructorString) {
		this();
		parse(constructorString);
	}

	public Pyr(Pyr p) {
		this(p.toString());
	}

	private void parse(String constructorString) {
		try (Scanner scanner = new Scanner(constructorString)) {
			name = scanner.next();
			p = scanner.next();
			y = scanner.next();
			r = scanner.next();
		}
		
	}
	
	public String toString() {
		return name + dataToString();
	}
	
	protected String dataToString() {
		String s = String.format(" %s %s %s", p, y, r );
		return s;
	}

	public static IDefinition newInstance(String constructorString) {
		return new Pyr(constructorString);
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
