/**
 * Actor - an individual "Actor" (mob) within the spawn
 */
package resurgence.spawndef;

import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * @author Joshua
 * Example actor:
 * 
  	Actor {
  		ActorName B_Council
		Number 1
		Location 30
		MinimumHeroesRequired 1
		MaximumHeroesRequired 8
		Gang 2
		Type eVillain
		Villain Council_War_Walker
		VillainGroup Council
		VillainType Boss
		AI_Group 1
		AI_InActive <<Wander>>
		AI_Alerted <<Combat>>
	}
 *
 */
public class Actor implements IDefinition {
	public static final String OPEN_DEF = "{";
	public static final String CLOSE_DEF = "}";
	public static final String OPEN_LIST = "<<";
	public static final String CLOSE_LIST = ">>";

	public static final String E_VILLAIN = "eVillain";
	public static final String E_NPC = "eNPC";
	public static final String ACTOR_LIT = "Actor";
	
	public enum Element { ActorName,
		  Number,
		  Location,
		  MinimumHeroesRequired,
		  MaximumHeroesRequired,
		  Gang,
		  Type,
		  Villain,
		  Model,
		  VillainGroup,
		  VillainType,
		  ShoutChance,
		  ShoutRange,
		  AI_Group,
		  AI_InActive,
		  Dialog_InActive,
		  AI_Alerted,
		  Dialog_Alerted,
		  AI_Completion,
		  Dialog_Completion,
		  Dialog_ThankHero				
	}
	
	// preserve the order of insertions so that the definition comes out in a predictable order
	protected LinkedHashMap<Element, String> elements = new LinkedHashMap<Element, String>();
	
	public Actor() {
		
	}
	public Actor(String actorName, long number, int location,
			int minimumHeroesRequired, int maximumHeroesRequired, String gang,
			String type, String villain, String villainGroup,
			String villainType, int aI_Group, String aI_InActive,
			String aI_Alerted) {
		super();
		setActorName(actorName);
		setNumber(number);
		setLocation(location);
		setMinimumHeroesRequired(minimumHeroesRequired);
		setMaximumHeroesRequired(maximumHeroesRequired);
		setGang(gang);
		setType(type);
		if (isEVillain()) {
			setVillain(villain); 
		} else {
			// must be eNPC, so the "villain" is really a costume name
			setModel(villain);
		}
		setVillainGroup(villainGroup);
		setVillainType(villainType);
		setAI_Group(aI_Group);
		setAI_InActive(aI_InActive);
		setAI_Alerted(aI_Alerted);
	}
	public Actor(String actorString) throws InvalidFormatException {		
		parse(actorString);
	}
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder(); 
		s.append(ACTOR_LIT).append("\n").append(OPEN_DEF);
		s.append(dataToString());
		s.append("\n").append(CLOSE_DEF).append("\n");
		return s.toString();
	}

	protected String dataToString() {
		StringBuilder sb = new StringBuilder();
		for (Element key : elements.keySet()) {
			sb.append("\n\t").append(key).append(" ").append(elements.get(key));
		}
		return sb.toString();
	}
	public boolean isEVillain() {
		return getType().equalsIgnoreCase(E_VILLAIN) ? true : false;
	}
	public String getActorName() {
		return elements.get(Element.ActorName);
	}
	public void setActorName(String actorName) {
		elements.put(Element.ActorName, actorName);
	}
	public long getNumber() {
		return Long.parseLong(elements.get(Element.Number));
	}
	public void setNumber(long number) {
		elements.put(Element.Number, Long.toString(number));
	}
	public int getLocation() {
		return Integer.parseInt(elements.get(Element.Location));
	}
	public void setLocation(int location) {
		elements.put(Element.Location, Integer.toString(location));
	}
	public int getMinimumHeroesRequired() {
		return Integer.parseInt(elements.get(Element.MinimumHeroesRequired));
	}
	public void setMinimumHeroesRequired(int minimumHeroesRequired) {
		elements.put(Element.MinimumHeroesRequired, Integer.toString(minimumHeroesRequired));
	}
	public int getMaximumHeroesRequired() {
		return Integer.parseInt(elements.get(Element.MaximumHeroesRequired));
	}
	public void setMaximumHeroesRequired(int maximumHeroesRequired) {
		elements.put(Element.MaximumHeroesRequired, Integer.toString(maximumHeroesRequired));
	}
	public String getGang() {
		return elements.get(Element.Gang);
	}
	public void setGang(String gang) {
		elements.put(Element.Gang, gang);
	}
	public String getType() {
		return elements.get(Element.Type);
	}
	public void setType(String type) {
		elements.put(Element.Type, type);
	}
	public String getVillain() {
		return elements.get(Element.Villain);
	}
	public void setVillain(String villain) {
		elements.put(Element.Villain, villain);
	}
	public String getModel() {
		return elements.get(Element.Model);
	}
	public void setModel(String model) {
		elements.put(Element.Model, model);
	}
	public String getVillainGroup() {
		return elements.get(Element.VillainGroup);
	}
	public void setVillainGroup(String villainGroup) {
		elements.put(Element.VillainGroup, villainGroup);
	}
	public String getVillainType() {
		return elements.get(Element.VillainType);
	}
	public void setVillainType(String villainType) {
		elements.put(Element.VillainType, villainType);
	}
	public String getShoutChance() {
		return elements.get(Element.ShoutChance);
	}
	public void setShoutChance(String shoutChance) {
		elements.put(Element.ShoutChance, shoutChance);
	}
	public String getShoutRange() {
		return elements.get(Element.ShoutRange);
	}
	public void setShoutRange(String shoutRange) {
		elements.put(Element.ShoutRange, shoutRange);
	}
	public int getAI_Group() {
		return Integer.parseInt(elements.get(Element.AI_Group));
	}
	public void setAI_Group(int aI_Group) {
		elements.put(Element.AI_Group, Integer.toString(aI_Group));
	}
	public String getAI_InActive() {
		return elements.get(Element.AI_InActive);
	}
	public void setAI_InActive(String aI_InActive) {
		elements.put(Element.AI_InActive, aI_InActive);
	}
	public String getAI_Alerted() {
		return elements.get(Element.AI_Alerted);
	}
	public void setAI_Alerted(String aI_Alerted) {
		elements.put(Element.AI_Alerted, aI_Alerted);
	}
	
	public void parse(String actorString) throws InvalidFormatException {
		actorString = actorString.trim();
		if(!actorString.endsWith(CLOSE_DEF)) {
				throw new InvalidFormatException("Missing close braces in Actor definition: [" + actorString + "]");
		}
		// new Java 7 Automatic Resource Management lets you declare a scope and ensure that resources opened for that scope
		// always get close() called as if there was a finally block
		try (Scanner scanner = new Scanner(actorString)) {
			if (!scanner.hasNext()) throw new InvalidFormatException("Empty element in Actor definition: [" + actorString + "]");
			String next = scanner.next();
			if (!next.equals(ACTOR_LIT)) throw new InvalidFormatException("Expected ACTOR in Actor definition: [" + actorString + "]" );
			next = scanner.next();
			if (!next.equals(OPEN_DEF)) throw new InvalidFormatException("Expected " + OPEN_DEF + " in Actor definition: [" + actorString + "]");
			while (scanner.hasNext()) {
				next = scanner.next();
				if (next.equals(CLOSE_DEF)) break;
				parseElement(next, scanner);
			}
		}
	}
	
	protected void parseElement(String elementType, Scanner scanner) throws InvalidFormatException  {
		if (!scanner.hasNext()) throw new InvalidFormatException("No data found for element [" + elementType + "]");
		String next = scanner.next();
		elements.put(Element.valueOf(elementType), next);
	}
	@Override
	public String getName() {
		return null;
	}
	@Override
	public void setName(String name) {
		// not implemented, required for interface
		
	}
	public String getElementByName(Element actorElementName) throws InvalidFormatException {
		switch(actorElementName) {
		case ActorName:
			return getActorName();
		case Number:
			return String.valueOf(getNumber());
		case Location:
			return String.valueOf(getLocation());
		case MinimumHeroesRequired:
			return String.valueOf(getMinimumHeroesRequired());
		case MaximumHeroesRequired:
			return String.valueOf(getMaximumHeroesRequired());
		case Gang:
			return getGang();			
		case Type:
			return getType();
		case Villain:
			return getVillain();
		case Model:
			return getModel();
		case VillainGroup:
			return getVillainGroup();
		case VillainType:
			return getVillainType();
		case ShoutChance:
			return getShoutChance();
		case ShoutRange:
			return getShoutRange();
		case AI_Group:
			return String.valueOf(getAI_Group());
		case AI_InActive:
			return getAI_InActive();
		case Dialog_InActive:
			return getDialog_InActive();
		case AI_Alerted:
			return getAI_Alerted();
		case Dialog_Alerted:
			return getDialog_Alerted();
		case AI_Completion:
			return getAI_Completion();
		case Dialog_Completion:
			return getDialog_Completion();
		case Dialog_ThankHero:						
			return getDialog_ThankHero();
		default:
			throw new InvalidFormatException("Unrecognized Element [" + actorElementName + "]");
		}
	}
	
	public void setElementByName(Element name, String value) throws InvalidFormatException {
		switch(name) {
		case ActorName:
			setActorName(value);
			break;
		case Number:
			setNumber(Long.parseLong(value));
			break;
		case Location:
			setLocation(Integer.parseInt(value));
			break;
		case MinimumHeroesRequired:
			setMinimumHeroesRequired(Integer.parseInt(value));
			break;
		case MaximumHeroesRequired:
			setMaximumHeroesRequired(Integer.parseInt(value));
			break;
		case Gang:
			setGang(value);			
			break;
		case Type:
			setType(value);
			break;
		case Villain:
			setVillain(value);
			break;
		case Model:
			setModel(value);
			break;
		case VillainGroup:
			setVillainGroup(value);
			break;
		case VillainType:
			setVillainType(value);
			break;
		case ShoutChance:
			setShoutChance(value);
			break;
		case ShoutRange:
			setShoutRange(value);
			break;
		case AI_Group:
			setAI_Group(Integer.parseInt(value));
			break;
		case AI_InActive:
			setAI_InActive(value);
			break;
		case Dialog_InActive:
			setDialog_InActive(value);
			break;
		case AI_Alerted:
			setAI_Alerted(value);
			break;
		case Dialog_Alerted:
			setDialog_Alerted(value);
			break;
		case AI_Completion:
			setAI_Completion(value);
			break;
		case Dialog_Completion:
			setDialog_Completion(value);
			break;
		case Dialog_ThankHero:						
			setDialog_ThankHero(value);
			break;
		default:
			throw new InvalidFormatException("Unrecognized Element [" + name + "]");
		}		
		
	}
	public String getAI_Completion() {
		return elements.get(Element.AI_Completion);
	}
	public void setAI_Completion(String value) {
		elements.put(Element.AI_Completion, value);				
	}
	public String getDialog_Completion() {
		return elements.get(Element.Dialog_Completion);
	}
	public void setDialog_Completion(String value) {
		elements.put(Element.Dialog_Completion, value);				
	}
	public String getDialog_ThankHero() {
		return elements.get(Element.Dialog_ThankHero);
	}
	public void setDialog_ThankHero(String value) {
		elements.put(Element.Dialog_ThankHero, value);				
	}
	public String getDialog_Alerted() {
		return elements.get(Element.Dialog_Alerted);
	}
	public void setDialog_Alerted(String value) {
		elements.put(Element.Dialog_Alerted, value);		
	}
	public String getDialog_InActive() {
		return elements.get(Element.Dialog_InActive);
	}
	public void setDialog_InActive(String value) {
		elements.put(Element.Dialog_InActive, value);
	}
}