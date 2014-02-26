package resurgence.spawndef;

import static org.junit.Assert.*;
import resurgence.spawndef.Actor;
import resurgence.spawndef.Actor.Element;
import resurgence.spawndef.InvalidFormatException;
import resurgence.spawndef.MissingBracesException;
import resurgence.spawndef.MissingElementDataException;
import resurgence.spawndef.UnrecognizedElementException;

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

	@org.junit.Test
	public void updateNamedElement() throws UnrecognizedElementException, InvalidFormatException, MissingBracesException, MissingElementDataException {
		String initial = "Actor\n"
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
		String expected = "Actor\n"
				+ "{\n" + 
				"\tActorName D_Council\n" + 
				"\tNumber 3\n" + 
				"\tLocation 40\n" + 
				"\tMinimumHeroesRequired 3\n" + 
				"\tMaximumHeroesRequired 7\n" + 
				"\tGang 3\n" + 
				"\tType eNPC1\n" + 
				"\tModel Costume_11\n" + 
				"\tVillainGroup BanishedPantheon\n" + 
				"\tVillainType Minion\n" + 
				"\tAI_Group 2\n" + 
				"\tAI_InActive <<Menace>>\n" + 
				"\tAI_Alerted <<Wander>>\n" + 
				"\tAI_Completion <<Thank>>\n" + 
				"\tDialog_InActive Boy, I sure could go for a Scooby Snack\n" + 
				"\tDialog_Alerted Jinkies!\n" + 
				"\tDialog_Completion Thank you, brave hero!\n" + 
				"}\n"; 
		Actor actor = new Actor(initial);
		String gang = actor.getElementByName(Element.Gang);
		assertEquals("Gang matches innitial", "2", gang);
		actor.setElementByName(Element.ActorName, "D_Council");
		actor.setElementByName(Element.Number, "3");
		actor.setElementByName(Element.Location, "40");
		actor.setElementByName(Element.MinimumHeroesRequired, "3");
		actor.setElementByName(Element.MaximumHeroesRequired, "7");
		actor.setElementByName(Element.Gang, "3");
		actor.setElementByName(Element.Type, "eNPC1");
		actor.setElementByName(Element.Model, "Costume_11");
		actor.setElementByName(Element.VillainGroup, "BanishedPantheon");
		actor.setElementByName(Element.VillainType, "Minion");
		actor.setElementByName(Element.AI_Group, "2");
		actor.setElementByName(Element.AI_InActive, "<<Menace>>");
		actor.setElementByName(Element.AI_Alerted, "<<Wander>>");
		actor.setElementByName(Element.AI_Completion, "<<Thank>>");
		actor.setElementByName(Element.Dialog_InActive, "Boy, I sure could go for a Scooby Snack");
		actor.setElementByName(Element.Dialog_Alerted, "Jinkies!");
		actor.setElementByName(Element.Dialog_Completion, "Thank you, brave hero!");
		
		assertEquals("Parsed Actor string prints expected result", expected, actor.toString());
	}
	
	
}
