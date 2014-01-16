package com.resurgence.spawndef;

import java.util.EnumMap;
import java.util.Map.Entry;

/*
 * 
 *  """
    >>> p = Pos("CAM", -679.100287, 220.000000, -678.694545)
    >>> print(p)
    0   CAM   POS -679.100287 220.000000 -678.694545
    >>> p = Pos(33, 133.326782, 168.000000, -646.427490)
    >>> print(p)
    0   33   POS 133.326782 168.000000 -646.427490
    >>> p2 = p + (10, 20, -30)
    >>> print(p2)
    0   33   POS 143.326782 188.000000 -676.427490
    >>> p3 = Pos("33", 10, 20, -30)
    >>> print(p + p3)
    0   33   POS 143.326782 188.000000 -676.427490
    >>> print(p + North(10))  # North is lower Y
    0   33   POS 133.326782 158.000000 -646.427490
    >>> print(p + South(10))  # South is higher Y
    0   33   POS 133.326782 178.000000 -646.427490
    >>> print(p + West(10))   # West is higher X
    0   33   POS 143.326782 168.000000 -646.427490
    >>> print(p + East(10))   # East is lower X
    0   33   POS 123.326782 168.000000 -646.427490
    >>> print(p + NorthEast(10, 90))
    0   33   POS 123.326782 168.000000 -646.427490
    
    
    """
    def __init__(self, entity, X, Y, Z, timeIncrement=None):
        super().__init__(timeIncrement)
        self.entity = entity
        self.name = "POS"
        self.data = [X, Y, Z]
    def __str__(self):
        value = '{0}   {1}'.format(self.timeIncrement, self.entity)
        if self.name:
            value = value + '   ' + self.name
        for item in self.data:
            if item == 0:
                value = value + ' 0'
            else:
                value = value + ' ' + "{:.6f}".format(item)
        return value        
    def setX(self,  X):
        self.data[0] = X
    def setY(self,  Y):
        self.data[1] = Y
    def setZ(self,  Z):
        self.data[2] = Z
    def setTimeIncrement(self,  T):
        self.timeIncrement = T
    def getX(self):
        return self.data[0]
    def getY(self):
        return self.data[1]
    def getZ(self):
        return self.data[2]
    def getTimeIncrement(self):
        return self.timeIncrement
    def __add__(self,  r):
        try:
            temp = Pos(self.entity,  self.getX() + r.getX(), self.getY() + r.getY(),  self.getZ() + r.getZ(),  self.getTimeIncrement())
        except AttributeError:
            temp = Pos(self.entity,  self.getX() + r[0], self.getY() + r[1],  self.getZ() + r[2],  self.getTimeIncrement())
        return temp
 */

enum COORD {
	X,
	Y,
	Z
}

/**
 * Pos - Position Command
 * @author Joshua
 * Pos - sets the X, Y, Z position of the entity
 *  The POS command is followed by a set of three numerical map coordinates in X Z Y format. 
 *   
 *  0   1   POS -1140 102 4592.5
 *  For a given map, the X value corresponds to its East-West position of the entity, the Z value corresponds to altitude or Up-Down position of the entity, 
 *  and the Y value corresponds to the North-South position. Larger values of X are further to the West, larger values of Z increase altitude, 
 *  and larger values of Y are further to the South. Note that, for X & Y, this may be the reverse of what is intuitive for many.
 *   
 *  The values for POS are expressed in feet. So changing an Y value from 1.0 to 2.0 will move an entity one foot to the South. 
 *  Changing a Y value from 1.0 to 2641.0 will move an entity a half mile to the South.
 *
 */
public class Pos extends Command {

	public static final String POS_COMMAND = "POS";
	private EnumMap<COORD, String> data;
	

	public Pos() {
		super();
	}

	public Pos(String entity, String X, String Y, String Z, String timeIncrement) {
		super(Integer.parseInt(timeIncrement));
		this.entity = entity;
		name = POS_COMMAND;
		data = new EnumMap<COORD, String>(COORD.class);
		// initialize array
		data.put(COORD.X, "0");
		data.put(COORD.Y, "0");
		data.put(COORD.Z, "0");
		// now enter the data, using the setter to ensure canonical format
		setX(X);
		setY(Y);
		setZ(Z);
	}
	
	public Pos(Pos p) {
		// no defensive copies, because all the values are immutable
		this(p.getEntity(), p.getXString(), p.getYString(), p.getZString(), p.getTimeIncrementString());
	}
	
	@Override
	public String dataToString() {
		String s = "";
		if (data != null) {
			for (Entry<COORD, String> iterable_element : data.entrySet()) {
				s += " " + iterable_element.getValue();
			}
			/*
			for (Set<Entry<COORD, String>> entrySet =  data.entrySet()) {
				s += " " + iterable_element.toString();
			}
			*/
		}
		return s;
	}
	private String getValueString(COORD pos) {
		return (String) data.get(pos);
	}
	
	private String getYString() {
		return getValueString(COORD.Y);
	}

	private String getZString() {
		return getValueString(COORD.Z);
	}

	private String getTimeIncrementString() {
		return String.valueOf(timeIncrement);
	}

	private String getXString() {
		return getValueString(COORD.X);
	}

	public Pos(String entity, String X, String Y, String Z) {
		this(entity, X, Y, Z, "0");
	}

	protected double getValue(COORD pos) {
		double d = Double.parseDouble((String) data.get(pos));
		return d;
	}
	
	protected void setValue(COORD pos, double d) {
		String s;
		// zeroes don't include decimal -- don't know if that's required or just a funny bit about the way DEMORECORD produces output
		if (d == 0) {
			s = "0";
		} else {
			s = String.format("%1$.6f", d);
		}
		data.put(pos, s);
	}
	
	protected void setValue(COORD pos, String s) {
		// ensure canonical internal format
		double d = Double.parseDouble(s);
		setValue(pos, d);
	}
	
	public double getX() {
		return getValue(COORD.X);
	}
	
	public void setX(double d) {
		setValue(COORD.X, d);
	}

	public void setX(String s) {
		setValue(COORD.X, s);
	}

	public double getY() {
		return getValue(COORD.Y);
	}
	
	public void setY(double d) {
		setValue(COORD.Y, d);
	}

	public void setY(String s) {
		setValue(COORD.Y, s);
	}

	public double getZ() {
		return getValue(COORD.Z);
	}
	
	public void setZ(double d) {
		setValue(COORD.Z, d);
	}

	public void setZ(String s) {
		setValue(COORD.Z, s);
	}
	
	public void move(COORD pos, double distance) {
		double d = getValue(pos);
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
	
	public void moveTo(COORD pos, double newPosition) {
		setValue(pos, newPosition);
	}

	
	/*
	 * X is East-West, Y is North-South
	 * def North(feet):
    return (0,  -feet,  0)

	def NorthEast(feet,  angle):
    	return (math.sin(math.radians(angle)) * -feet,  math.cos(math.radians(angle)) * -feet, 0)

	def East(feet):
    	return (-feet,  0,  0)

	def South(feet):
    	return (0,  feet,  0)

	def SouthEast(feet,  angle):
    	return (math.sin(math.radians(angle)) * -feet,  math.cos(math.radians(angle)) * feet, 0)

	def West(feet):
    	return (feet,  0,  0)
    
	def Up(feet):
    	return (0,  0,  feet)
    
	def Down(feet):
    	return (0,  0,  -feet)


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
	
	private void moveAtAngle(double angle, double feet) {
		move(Math.sin(Math.toRadians(angle)) * -feet,  Math.cos(Math.toRadians(angle)) * -feet, 0);
	}
}
