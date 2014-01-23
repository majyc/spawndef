/**
 * SpawnDef - the definition of a single spawn, including all flags and actors
 */
package resurgence.spawndef;

import java.util.ArrayList;
import java.util.Scanner;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;

/**
 * @author Joshua
 *
 */
public class SpawnDef implements IDefinition {

	private static final String PARAM_DELIMITER = " ";
	private static final String NEW_LINE = "\n";
	private static final String LINE_START = "\t";
	private static final String VILLAIN_MIN_LEVEL = "VillainMinLevel";
	private static final String DIALOG = "Dialog";
	private static final String FLAGS = "Flags";
	private static final String ENCOUNTER_ALLIANCE = "EncounterAlliance";
	private static final String MAX_TEAM_SIZE = "MaxTeamSize";
	private static final String MIN_TEAM_SIZE = "MinTeamSize";
	private static final String VILLAIN_MAX_LEVEL = "VillainMaxLevel";

	private int villainMinLevel;
	private int villainMaxLevel;
	private int minTeamSize;
	private int maxTeamSize;
	private String encounterAlliance;
	private String dialog;
	private ArrayList<String> flags = new ArrayList<String>();
	private ArrayList<Actor> actors = new ArrayList<Actor>();
	
	public static final ImmutableSet<String> PROPERTIES_NAMES = ImmutableSet.of(
			VILLAIN_MIN_LEVEL,
			VILLAIN_MAX_LEVEL,
			MIN_TEAM_SIZE,
			MAX_TEAM_SIZE,
			ENCOUNTER_ALLIANCE,
			DIALOG
			);
	
	
	// used to enforce only flags names that we understand, in case there are typos
	// or old or invalid formats
	public static final ImmutableSet<String> FLAG_NAMES = ImmutableSet.of(
			  "AllowAddingActors",
			  "IgnoreRadius",
			  "AutoStart",
			  "MissionRespawn",
			  "CloneGroup",
			  "NeighborhoodDefined"
			  );
	private static final String CLOSE_DEF = "}";
	private static final String SPAWNDEF_LIT = "SpawnDef";
	private static final String OPEN_DEF = "{";	

	public SpawnDef() {
		setName(SPAWNDEF_LIT);
	}
	
	public SpawnDef(String constructorString) throws InvalidFormatException {
		this();
		parse(constructorString);
	}
	
	private void parse(String constructorString) throws InvalidFormatException {
		constructorString = constructorString.trim();
		if(!constructorString.endsWith(CLOSE_DEF)) {
				throw new InvalidFormatException("Missing close braces in SpawnDef definition: [" + constructorString + "]");
		}
		// new Java 7 Automatic Resource Management lets you declare a scope and ensure that resources opened for that scope
		// always get close() called as if there was a finally block
		try (Scanner scanner = new Scanner(constructorString)) {
			if (!scanner.hasNext()) throw new InvalidFormatException(String.format("Empty element in SpawnDef definition: [%s]", constructorString));
			String next = scanner.next();
			if (!next.equals(SPAWNDEF_LIT)) throw new InvalidFormatException(String.format("Expected %s in SpawnDef definition: [%s]", SPAWNDEF_LIT, constructorString));
			next = scanner.next();
			if (!next.equals(OPEN_DEF)) throw new InvalidFormatException(String.format("Expected %s in SpawnDef definition: [%s]", OPEN_DEF, constructorString));
			while (scanner.hasNext()) {
				next = scanner.next();
				if (next.equals(CLOSE_DEF)) break;
				parseElement(next, scanner);
			}
		}		
	}

	private void parseElement(String type, Scanner scanner) throws InvalidFormatException  {
		switch(type) {
		case VILLAIN_MIN_LEVEL:
			villainMinLevel = scanner.nextInt();
			break;
		case VILLAIN_MAX_LEVEL:
			villainMaxLevel = scanner.nextInt();
			break;
		case MIN_TEAM_SIZE:
			minTeamSize = scanner.nextInt();
			break;
		case MAX_TEAM_SIZE:
			maxTeamSize = scanner.nextInt();
			break;
		case ENCOUNTER_ALLIANCE:
			encounterAlliance = scanner.next();
			break;
		case FLAGS:
			parseFlags(scanner);
			break;
		case Actor.ACTOR_LIT:
			parseActor(scanner);
		default:
			break;
		}
	}

	private void parseActor(Scanner scanner) throws InvalidFormatException {
		// Strip off the entirety of the Actor def and create an Actor
		StringBuilder actorString = new StringBuilder();
		// change so that the only delimiter is CLOSE_DEF... should let us scoop up the rest as a single token
		scanner.useDelimiter(Actor.CLOSE_DEF);
		String nextToken = scanner.next();
		scanner.reset();  // restore the default (whitespace) delimiter
		actorString.append(Actor.ACTOR_LIT).append(nextToken).append(Actor.CLOSE_DEF);
		Actor actor = new Actor(actorString.toString());
		actors.add(actor);
	}

	private void parseFlags(Scanner scanner) throws InvalidFormatException {
		String line = scanner.nextLine();
		String[] flagArray = line.split("\\s+");
		for (String flag : flagArray) {
			if (!FLAG_NAMES.contains(flag)) throw new InvalidFormatException("Unrecognized Flag [" + flag + "]");
			flags.add(flag);
		}	
	}

	public int getVillainMinLevel() {
		return villainMinLevel;
	}

	public void setVillainMinLevel(int villainMinLevel) {
		this.villainMinLevel = villainMinLevel;
	}

	public int getVillainMaxLevel() {
		return villainMaxLevel;
	}

	public void setVillainMaxLevel(int villainMaxLevel) {
		this.villainMaxLevel = villainMaxLevel;
	}

	public int getMinTeamSize() {
		return minTeamSize;
	}

	public void setMinTeamSize(int minTeamSize) {
		this.minTeamSize = minTeamSize;
	}

	public int getMaxTeamSize() {
		return maxTeamSize;
	}

	public void setMaxTeamSize(int maxTeamSize) {
		this.maxTeamSize = maxTeamSize;
	}

	public String getEncounterAlliance() {
		return encounterAlliance;
	}

	public void setEncounterAlliance(String encounterAlliance) {
		this.encounterAlliance = encounterAlliance;
	}

	public String getDialog() {
		return dialog;
	}

	public void setDialog(String dialog) {
		this.dialog = dialog;
	}
	
	public ArrayList<String> getFlags() {
		return flags;
	}

	public void setFlags(ArrayList<String> flags) {
		this.flags = flags;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder(); 
		s.append(SPAWNDEF_LIT).append(NEW_LINE).append(OPEN_DEF).append(NEW_LINE);
		s.append(dataToString());
		s.append(NEW_LINE).append(CLOSE_DEF).append(NEW_LINE);
		return s.toString();
	}

	/* (non-Javadoc)
	 * @see resurgence.spawndef.Definition#dataToString()
	 */
	protected String dataToString() {
		StringBuilder sb = new StringBuilder();
		sb.append(LINE_START).append(VILLAIN_MIN_LEVEL).append(PARAM_DELIMITER).append(villainMinLevel).append(NEW_LINE);
		sb.append(LINE_START).append(VILLAIN_MAX_LEVEL).append(PARAM_DELIMITER).append(villainMaxLevel).append(NEW_LINE);
		sb.append(LINE_START).append(MIN_TEAM_SIZE).append(PARAM_DELIMITER).append(minTeamSize).append(NEW_LINE);
		sb.append(LINE_START).append(MAX_TEAM_SIZE).append(PARAM_DELIMITER).append(maxTeamSize).append(NEW_LINE);
		if (encounterAlliance != null) {
			sb.append(LINE_START).append(ENCOUNTER_ALLIANCE).append(PARAM_DELIMITER).append(encounterAlliance).append(NEW_LINE);			
		}
		if (dialog != null) {
			sb.append(LINE_START).append(DIALOG).append(PARAM_DELIMITER).append(dialog).append(NEW_LINE);			
		}
		if (!flags.isEmpty())  {
			sb.append(LINE_START).append(FLAGS);
			for(String flag : flags) {
				sb.append(PARAM_DELIMITER).append(flag);
			}
			sb.append(NEW_LINE);
		}
		if (!actors.isEmpty()) {
			sb.append(NEW_LINE);
			String[] temp;
			String indented;
			for (Actor actor : actors) {
				temp = actor.toString().split(NEW_LINE);
				indented = Joiner.on( NEW_LINE + LINE_START ).join( temp );
				sb.append(LINE_START).append(indented);
			}
		}
		return sb.toString();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

}
