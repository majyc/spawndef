/**
 * Actor - an individual "Actor" (mob) within the spawn
 */
package com.resurgence.spawndef;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.common.collect.ImmutableSet;

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
public class Actor {
	public static final String OPEN_DEF = "{";
	public static final String CLOSE_DEF = "}";
	public static final String OPEN_LIST = "<<";
	public static final String CLOSE_LIST = ">>";

	public static final String E_VILLAIN = "eVillain";
	public static final String E_NPC = "eNPC";
	public static final String ACTOR_LIT = "Actor";
		
	public static final ImmutableSet<String> ELEMENT_NAMES = ImmutableSet.of(
			  "ActorName",
			  "Number",
			  "Location",
			  "MinimumHeroesRequired",
			  "MaximumHeroesRequired",
			  "Gang",
			  "Type",
			  "Villain",
			  "Model",
			  "VillainGroup",
			  "VillainType",
			  "ShoutChance",
			  "ShoutRange",
			  "AI_Group",
			  "AI_InActive",
			  "AI_Alerted"
			  );
	
	protected Map<String, String> elements = new HashMap<String, String>();
	
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
	public Actor(String actorString) throws UnrecognizedElementException, EmptyElementException, MissingBracesException, MissingElementDataException {		
		parse(actorString);
	}
	@Override
	public String toString() {
		return  ACTOR_LIT + " " + OPEN_DEF
				+ "\n\tActorName " + getActorName() + "\n\tNumber " + getNumber()
				+ "\n\tLocation " + getLocation() + "\n\tMinimumHeroesRequired "
				+ getMinimumHeroesRequired() + "\n\tMaximumHeroesRequired "
				+ getMaximumHeroesRequired() + "\n\tGang " + getGang() + "\n\tType " + getType()
				+ (isEVillain() ? "\n\tVillain " + getVillain() : "\n\tModel " + getModel())
				+ "\n\tVillainGroup " + getVillainGroup()
				+ "\n\tVillainType " + getVillainType() 
				+ (getShoutChance() != null ? "\n\tShoutChance " + getShoutChance() : "")
				+ (getShoutRange() != null ? "\n\tShoutRange " + getShoutRange() : "")
				+ "\n\tAI_Group " + getAI_Group()
				+ "\n\tAI_InActive " + getAI_InActive() + "\n\tAI_Alerted " + getAI_Alerted()
				+ "\n}\n";
	}
	public boolean isEVillain() {
		return getType().equalsIgnoreCase(E_VILLAIN) ? true : false;
	}
	public String getActorName() {
		return elements.get("ActorName");
	}
	public void setActorName(String actorName) {
		elements.put("ActorName", actorName);
	}
	public long getNumber() {
		return Long.parseLong(elements.get("Number"));
	}
	public void setNumber(long number) {
		elements.put("Number", Long.toString(number));
	}
	public int getLocation() {
		return Integer.parseInt(elements.get("Location"));
	}
	public void setLocation(int location) {
		elements.put("Location", Integer.toString(location));
	}
	public int getMinimumHeroesRequired() {
		return Integer.parseInt(elements.get("MinimumHeroesRequired"));
	}
	public void setMinimumHeroesRequired(int minimumHeroesRequired) {
		elements.put("MinimumHeroesRequired", Integer.toString(minimumHeroesRequired));
	}
	public int getMaximumHeroesRequired() {
		return Integer.parseInt(elements.get("MaximumHeroesRequired"));
	}
	public void setMaximumHeroesRequired(int maximumHeroesRequired) {
		elements.put("MaximumHeroesRequired", Integer.toString(maximumHeroesRequired));
	}
	public String getGang() {
		return elements.get("Gang");
	}
	public void setGang(String gang) {
		elements.put("Gang", gang);
	}
	public String getType() {
		return elements.get("Type");
	}
	public void setType(String type) {
		elements.put("Type", type);
	}
	public String getVillain() {
		return elements.get("Villain");
	}
	public void setVillain(String villain) {
		elements.put("Villain", villain);
	}
	public String getModel() {
		return elements.get("Model");
	}
	public void setModel(String model) {
		elements.put("Model", model);
	}
	public String getVillainGroup() {
		return elements.get("VillainGroup");
	}
	public void setVillainGroup(String villainGroup) {
		elements.put("VillainGroup", villainGroup);
	}
	public String getVillainType() {
		return elements.get("VillainType");
	}
	public void setVillainType(String villainType) {
		elements.put("VillainType", villainType);
	}
	public String getShoutChance() {
		return elements.get("ShoutChance");
	}
	public void setShoutChance(String shoutChance) {
		elements.put("ShoutChance", shoutChance);
	}
	public String getShoutRange() {
		return elements.get("ShoutRange");
	}
	public void setShoutRange(String shoutRange) {
		elements.put("ShoutRange", shoutRange);
	}
	public int getAI_Group() {
		return Integer.parseInt(elements.get("AI_Group"));
	}
	public void setAI_Group(int aI_Group) {
		elements.put("AI_Group", Integer.toString(aI_Group));
	}
	public String getAI_InActive() {
		return elements.get("AI_InActive");
	}
	public void setAI_InActive(String aI_InActive) {
		elements.put("AI_InActive", aI_InActive);
	}
	public String getAI_Alerted() {
		return elements.get("AI_Alerted");
	}
	public void setAI_Alerted(String aI_Alerted) {
		elements.put("AI_Alerted", aI_Alerted);
	}
	
	public void parse(String actorString) throws UnrecognizedElementException, EmptyElementException, MissingBracesException, MissingElementDataException {
		actorString = actorString.trim();
		if(!actorString.endsWith(CLOSE_DEF)) throw new MissingBracesException("Missing close braces in definition");
		Scanner scanner = new Scanner(actorString);
		try {
			if (!scanner.hasNext()) throw new EmptyElementException();
			String next = scanner.next();
			if (!next.equals(ACTOR_LIT)) throw new UnrecognizedElementException();
			next = scanner.next();
			if (!next.equals(OPEN_DEF)) throw new MissingBracesException();
			while (scanner.hasNext()) {
				next = scanner.next();
				if (next.equals(CLOSE_DEF)) break;
				parseElement(next, scanner);
			}
		} finally {
			scanner.close();  // here's where we wish we had D's scope(exit) statement
		}
	}
	
	protected void parseElement(String elementType, Scanner scanner) throws MissingElementDataException, UnrecognizedElementException {
		if (!ELEMENT_NAMES.contains(elementType)) throw new UnrecognizedElementException("Unrecognized Element [" + elementType + "]");
		if (!scanner.hasNext()) throw new MissingElementDataException("No data found for element [" + elementType + "]");
		String next = scanner.next();
		elements.put(elementType, next);
	}
}