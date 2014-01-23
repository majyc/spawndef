package resurgence.spawndef;

import java.util.Scanner;

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
public class PosCommand extends Command {

	public static final String POS_COMMAND = "POS";

	private Pos data = new Pos();


	/**
	 * No-arg Contructor
	 * sets all values to 0 and name to POS_COMMAND
	 */
	public PosCommand() {
		super();
		name = POS_COMMAND;
		data = new Pos();
	}

	/**
	 * @param entity
	 * @param X
	 * @param Y
	 * @param Z
	 * @param timeIncrement
	 */
	public PosCommand(String entity, String X, String Y, String Z, String timeIncrement) {
		this();
		setEntity(entity);
		setTimeIncrement(timeIncrement);
		// now enter the data, using the setter to ensure canonical format
		data.setX(X);
		data.setY(Y);
		data.setZ(Z);
	}
	
	
	/**
	 * String constructor - expects a string like
	 * "POS 143.326782 168.000000 -646.427490"
	 * from the demorecord format with the values for
	 * timeincrement entity POS x_coord y_coord, z_coord
	 * @param s
	 */
	public PosCommand(String s) {
		this();
		parse(s);		 
	}

	/**
	 * 
	 */
	protected void parse(String string) {
		try (Scanner scanner = new Scanner(string)) {
			timeIncrement = scanner.nextInt();
			entity = scanner.next();
			name = scanner.next();
			setX(scanner.nextDouble());
			setY(scanner.nextDouble());
			setZ(scanner.nextDouble());
		}
	}
	
	public PosCommand(PosCommand p) {
		this(p.toString());
	}
	
	@Override
	public String dataToString() {
		return data.dataToString();
	}
	
	public String getValueString(COORD pos) {
		return data.getValue(pos);
	}
	
	public String getXString() {
		return getValueString(COORD.X);
	}

	public String getYString() {
		return getValueString(COORD.Y);
	}

	public String getZString() {
		return getValueString(COORD.Z);
	}

	public String getTimeIncrementString() {
		return String.valueOf(timeIncrement);
	}

	public PosCommand(String entity, String X, String Y, String Z) {
		this(entity, X, Y, Z, "0");
	}

	protected double getValue(COORD pos) {
		double d = Double.parseDouble(data.getValue(pos));
		return d;
	}
	
	protected void setValue(COORD pos, double d) {
		data.setValue(pos, d);
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
		data.move(pos, distance);
	}
	
	public void move(double xDistance, double yDistance, double zDistance) {
		data.move(xDistance, yDistance, zDistance);
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
	
	
	/**
	 * moveDirection - moves the Pos in a compass direction by a number of feet; use @moveAtAngle to move freely in different directions
	 * @param dir  compass direction, including UP and DOWN
	 * @param feet number of feet to move in a direction; negative feet will move in the opposite direction (negative move WEST is 
	 * same as positive move EAST)
	 */
	public void moveDirection(COMPASS dir, double feet) {
		data.moveDirection(dir, feet);
	}
	
	/**
	 * moveAtAngle moves a number of feet at an arbitrary angle 
	 * @param angle measured in degrees starting from 0 straight North and going clockwise
	 * @param feet  distance to move
	 */
	public void moveAtAngle(double angle, double feet) {
		data.moveAtAngle(angle, feet);
	}
	
	/**
	 * moveAtAngle moves a number of feet along an arbitrary angle with regard to the Z-axis 
	 * @param angle measured in degrees starting from 0 in the current Z plane and 90 straight up
	 * @param feet  distance to move
	 * @param fixedAxis which axis to regard as fixed (e.g. Y means that moving up at an angle will change only the Z and X coords)
	 * @throws InvalidFixedAxisException 
	 */
	public void moveAtZAngle(double angle, double feet, COORD fixedAxis) throws InvalidFixedAxisException {
		data.moveAtZAngle(angle, feet, fixedAxis);
	}	
	
}
