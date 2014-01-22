package com.resurgence.spawndef;

import static org.junit.Assert.*;

/*
 * Example:
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

public class ActorTest {

	@org.junit.Test
	public void actorToString() {
		String expected = "Actor\n"
				+ "{\n" + 
				"\tActorName B_Council\n" + 
				"\tNumber 1\n" + 
				"\tLocation 30\n" + 
				"\tMinimumHeroesRequired 1\n" + 
				"\tMaximumHeroesRequired 8\n" + 
				"\tGang 2\n" + 
				"\tType eVillain\n" + 
				"\tVillain Council_War_Walker\n" + 
				"\tVillainGroup Council\n" + 
				"\tVillainType Boss\n" + 
				"\tAI_Group 1\n" + 
				"\tAI_InActive <<Wander>>\n" + 
				"\tAI_Alerted <<Combat>>\n" + 
				"}\n"; 
		Actor actor = new Actor("B_Council", 1, 30, 1, 8, "2", "eVillain", "Council_War_Walker", "Council", "Boss", 1, "<<Wander>>", "<<Combat>>");
		assertEquals("toString prints expected result", expected, actor.toString());
	}

	@org.junit.Test
	public void eNPCToString() {
		String expected = "Actor\n"
				+ "{\n" + 
				"\tActorName B_Council\n" + 
				"\tNumber 1\n" + 
				"\tLocation 30\n" + 
				"\tMinimumHeroesRequired 1\n" + 
				"\tMaximumHeroesRequired 8\n" + 
				"\tGang 2\n" + 
				"\tType eNPC\n" + 
				"\tModel Costume_1\n" + 
				"\tVillainGroup Council\n" + 
				"\tVillainType Boss\n" + 
				"\tAI_Group 1\n" + 
				"\tAI_InActive <<Wander>>\n" + 
				"\tAI_Alerted <<Combat>>\n" + 
				"}\n"; 
		Actor actor = new Actor("B_Council", 1, 30, 1, 8, "2", "eNPC", "Costume_1", "Council", "Boss", 1, "<<Wander>>", "<<Combat>>");
		assertEquals("toString prints expected result", expected, actor.toString());
	}

	@org.junit.Test
	public void eNPCFromString() throws UnrecognizedElementException, InvalidFormatException, MissingBracesException, MissingElementDataException {
		String expected = "Actor\n"
				+ "{\n" + 
				"\tActorName B_Council\n" + 
				"\tNumber 1\n" + 
				"\tLocation 30\n" + 
				"\tMinimumHeroesRequired 1\n" + 
				"\tMaximumHeroesRequired 8\n" + 
				"\tGang 2\n" + 
				"\tType eNPC\n" + 
				"\tModel Costume_1\n" + 
				"\tVillainGroup Council\n" + 
				"\tVillainType Boss\n" + 
				"\tAI_Group 1\n" + 
				"\tAI_InActive <<Wander>>\n" + 
				"\tAI_Alerted <<Combat>>\n" + 
				"}\n"; 
		Actor actor = new Actor(expected);
		assertEquals("Parsed Actor string prints expected result", expected, actor.toString());
	}

}
