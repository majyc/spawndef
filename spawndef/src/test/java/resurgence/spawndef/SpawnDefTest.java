package resurgence.spawndef;

import static org.junit.Assert.assertEquals;
import resurgence.spawndef.InvalidFormatException;
import resurgence.spawndef.SpawnDef;

/*
 * Example:
 * 

SpawnDef
{
	VillainMinLevel 14
	VillainMaxLevel 19
	MinTeamSize 1
	MaxTeamSize 8

	Actor
	{
		ActorName M_Council
		Number 1
		Location 1
		MinimumHeroesRequired 1
		MaximumHeroesRequired 8
		Gang 2
		Type eVillain
		Villain Council_Nebula_Elite_Grenade
		VillainGroup Council
		VillainType Minion
		AI_Group 1
		AI_InActive <<Shotgun_Point>>
		AI_Alerted <<Combat>>
	}
	Actor
	{
		ActorName L_Council
		Number 1
		Location 2
		MinimumHeroesRequired 1
		MaximumHeroesRequired 8
		Gang 2
		Type eVillain
		Villain Council_Nebula_Elite_Lieutenant
		VillainGroup Council
		VillainType Lieutenant
		AI_Group 1
		AI_InActive <<Shotgun_Point>>
		AI_Alerted <<Combat>>
	}
	Actor
	{
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
	Actor
	{
		ActorName V_5th_1
		Number 1
		Location 40
		MinimumHeroesRequired 1
		MaximumHeroesRequired 8
		Gang 2
		Type eVillain
		Villain 5thColumn_Fog_Elite
		VillainGroup 5thColumn
		VillainType Minion
		AI_Group 1
		AI_InActive <<HandsOnHead_Kneel>>
		AI_Alerted <<FleeToNearestDoor(Timer(5)),Combat(Gang(3))>>
	}
	Actor
	{
		ActorName V_5th_2
		Number 1
		Location 41
		MinimumHeroesRequired 1
		MaximumHeroesRequired 8
		Gang 2
		Type eVillain
		Villain 5thColumn_Fog_Elite_Lieutenant
		VillainGroup 5thColumn
		VillainType Lieutenant
		AI_Group 1
		AI_InActive <<HandsOnHead_Kneel>>
		AI_Alerted <<FleeToNearestDoor(Timer(5)),Combat(Gang(3))>>
	}
	Actor
	{
		ActorName V_5th_3
		Number 1
		Location 42
		MinimumHeroesRequired 1
		MaximumHeroesRequired 8
		Gang 2
		Type eVillain
		Villain 5thColumn_Fog_Elite
		VillainGroup 5thColumn
		VillainType Minion
		AI_Group 1
		AI_InActive <<HandsOnHead_Kneel>>
		AI_Alerted <<FleeToNearestDoor(Timer(5)),Combat(Gang(3))>>
	}
}

 *
 */
public class SpawnDefTest {
	String simpleSpawnDef = 
			"SpawnDef\n" + 
			"{\n" + 
			"\tVillainMinLevel 14\n" + 
			"\tVillainMaxLevel 19\n" + 
			"\tMinTeamSize 1\n" + 
			"\tMaxTeamSize 8\n" + 
			"\n" + 
			"\tActor\n" + 
			"\t{\n" + 
			"\t\tActorName M_Council\n" + 
			"\t\tNumber 1\n" + 
			"\t\tLocation 1\n" + 
			"\t\tMinimumHeroesRequired 1\n" + 
			"\t\tMaximumHeroesRequired 8\n" + 
			"\t\tGang 2\n" + 
			"\t\tType eVillain\n" + 
			"\t\tVillain Council_Nebula_Elite_Grenade\n" + 
			"\t\tVillainGroup Council\n" + 
			"\t\tVillainType Minion\n" + 
			"\t\tAI_Group 1\n" + 
			"\t\tAI_InActive <<Shotgun_Point>>\n" + 
			"\t\tAI_Alerted <<Combat>>\n" + 
			"\t}\n" + 
			"}\n"; 

	
	@org.junit.Test
	public void spawnDefSimple() throws InvalidFormatException {
		String expected = simpleSpawnDef;
		SpawnDef sd = new SpawnDef(expected);
		assertEquals("toString prints expected result", expected, sd.toString());
	}

	@org.junit.Test
	public void spawnDefComplex() throws InvalidFormatException {
		String expected = "SpawnDef\n" + 
				"{\n" + 
				"\tVillainMinLevel 14\n" + 
				"\tVillainMaxLevel 19\n" + 
				"\tMinTeamSize 1\n" + 
				"\tMaxTeamSize 8\n" + 
				"\n" + 
				"\tActor\n" + 
				"\t{\n" + 
				"\t\tActorName M_Council\n" + 
				"\t\tNumber 1\n" + 
				"\t\tLocation 1\n" + 
				"\t\tMinimumHeroesRequired 1\n" + 
				"\t\tMaximumHeroesRequired 8\n" + 
				"\t\tGang 2\n" + 
				"\t\tType eVillain\n" + 
				"\t\tVillain Council_Nebula_Elite_Grenade\n" + 
				"\t\tVillainGroup Council\n" + 
				"\t\tVillainType Minion\n" + 
				"\t\tAI_Group 1\n" + 
				"\t\tAI_InActive <<Shotgun_Point>>\n" + 
				"\t\tAI_Alerted <<Combat>>\n" + 
				"\t}\n" + 
				"\tActor\n" + 
				"\t{\n" + 
				"\t\tActorName L_Council\n" + 
				"\t\tNumber 1\n" + 
				"\t\tLocation 2\n" + 
				"\t\tMinimumHeroesRequired 1\n" + 
				"\t\tMaximumHeroesRequired 8\n" + 
				"\t\tGang 2\n" + 
				"\t\tType eVillain\n" + 
				"\t\tVillain Council_Nebula_Elite_Lieutenant\n" + 
				"\t\tVillainGroup Council\n" + 
				"\t\tVillainType Lieutenant\n" + 
				"\t\tAI_Group 1\n" + 
				"\t\tAI_InActive <<Shotgun_Point>>\n" + 
				"\t\tAI_Alerted <<Combat>>\n" + 
				"\t}\n" + 
				"\tActor\n" + 
				"\t{\n" + 
				"\t\tActorName B_Council\n" + 
				"\t\tNumber 1\n" + 
				"\t\tLocation 30\n" + 
				"\t\tMinimumHeroesRequired 1\n" + 
				"\t\tMaximumHeroesRequired 8\n" + 
				"\t\tGang 2\n" + 
				"\t\tType eVillain\n" + 
				"\t\tVillain Council_War_Walker\n" + 
				"\t\tVillainGroup Council\n" + 
				"\t\tVillainType Boss\n" + 
				"\t\tAI_Group 1\n" + 
				"\t\tAI_InActive <<Wander>>\n" + 
				"\t\tAI_Alerted <<Combat>>\n" + 
				"\t}\n" + 
				"\tActor\n" + 
				"\t{\n" + 
				"\t\tActorName V_5th_1\n" + 
				"\t\tNumber 1\n" + 
				"\t\tLocation 40\n" + 
				"\t\tMinimumHeroesRequired 1\n" + 
				"\t\tMaximumHeroesRequired 8\n" + 
				"\t\tGang 2\n" + 
				"\t\tType eVillain\n" + 
				"\t\tVillain 5thColumn_Fog_Elite\n" + 
				"\t\tVillainGroup 5thColumn\n" + 
				"\t\tVillainType Minion\n" + 
				"\t\tAI_Group 1\n" + 
				"\t\tAI_InActive <<HandsOnHead_Kneel>>\n" + 
				"\t\tAI_Alerted <<FleeToNearestDoor(Timer(5)),Combat(Gang(3))>>\n" + 
				"\t}\n" + 
				"\tActor\n" + 
				"\t{\n" + 
				"\t\tActorName V_5th_2\n" + 
				"\t\tNumber 1\n" + 
				"\t\tLocation 41\n" + 
				"\t\tMinimumHeroesRequired 1\n" + 
				"\t\tMaximumHeroesRequired 8\n" + 
				"\t\tGang 2\n" + 
				"\t\tType eVillain\n" + 
				"\t\tVillain 5thColumn_Fog_Elite_Lieutenant\n" + 
				"\t\tVillainGroup 5thColumn\n" + 
				"\t\tVillainType Lieutenant\n" + 
				"\t\tAI_Group 1\n" + 
				"\t\tAI_InActive <<HandsOnHead_Kneel>>\n" + 
				"\t\tAI_Alerted <<FleeToNearestDoor(Timer(5)),Combat(Gang(3))>>\n" + 
				"\t}\n" + 
				"\tActor\n" + 
				"\t{\n" + 
				"\t\tActorName V_5th_3\n" + 
				"\t\tNumber 1\n" + 
				"\t\tLocation 42\n" + 
				"\t\tMinimumHeroesRequired 1\n" + 
				"\t\tMaximumHeroesRequired 8\n" + 
				"\t\tGang 2\n" + 
				"\t\tType eVillain\n" + 
				"\t\tVillain 5thColumn_Fog_Elite\n" + 
				"\t\tVillainGroup 5thColumn\n" + 
				"\t\tVillainType Minion\n" + 
				"\t\tAI_Group 1\n" + 
				"\t\tAI_InActive <<HandsOnHead_Kneel>>\n" + 
				"\t\tAI_Alerted <<FleeToNearestDoor(Timer(5)),Combat(Gang(3))>>\n" + 
				"\t}\n" + 
				"}\n"; 
		SpawnDef sd = new SpawnDef(expected);
		assertEquals("toString prints expected result", expected, sd.toString());
	}

	@org.junit.Test
	public void spawnDefComplexWithDialog() throws InvalidFormatException {
		String expected = "SpawnDef\n" + 
				"{\n" + 
				"\tVillainMinLevel 14\n" + 
				"\tVillainMaxLevel 19\n" + 
				"\tMinTeamSize 1\n" + 
				"\tMaxTeamSize 8\n" + 
				"\tEncounterAlliance Hero\n" + 
				"\tDialog Oh noes! It is $heroName"
				+ "\n" + 
				"\n" + 
				"\tActor\n" + 
				"\t{\n" + 
				"\t\tActorName M_Council\n" + 
				"\t\tNumber 1\n" + 
				"\t\tLocation 1\n" + 
				"\t\tMinimumHeroesRequired 1\n" + 
				"\t\tMaximumHeroesRequired 8\n" + 
				"\t\tGang 2\n" + 
				"\t\tType eVillain\n" + 
				"\t\tVillain Council_Nebula_Elite_Grenade\n" + 
				"\t\tVillainGroup Council\n" + 
				"\t\tVillainType Minion\n" + 
				"\t\tAI_Group 1\n" + 
				"\t\tAI_InActive <<Shotgun_Point>>\n" + 
				"\t\tAI_Alerted <<Combat>>\n" + 
				"\t}\n" + 
				"\tActor\n" + 
				"\t{\n" + 
				"\t\tActorName L_Council\n" + 
				"\t\tNumber 1\n" + 
				"\t\tLocation 2\n" + 
				"\t\tMinimumHeroesRequired 1\n" + 
				"\t\tMaximumHeroesRequired 8\n" + 
				"\t\tGang 2\n" + 
				"\t\tType eVillain\n" + 
				"\t\tVillain Council_Nebula_Elite_Lieutenant\n" + 
				"\t\tVillainGroup Council\n" + 
				"\t\tVillainType Lieutenant\n" + 
				"\t\tAI_Group 1\n" + 
				"\t\tAI_InActive <<Shotgun_Point>>\n" + 
				"\t\tAI_Alerted <<Combat>>\n" + 
				"\t}\n" + 
				"\tActor\n" + 
				"\t{\n" + 
				"\t\tActorName B_Council\n" + 
				"\t\tNumber 1\n" + 
				"\t\tLocation 30\n" + 
				"\t\tMinimumHeroesRequired 1\n" + 
				"\t\tMaximumHeroesRequired 8\n" + 
				"\t\tGang 2\n" + 
				"\t\tType eVillain\n" + 
				"\t\tVillain Council_War_Walker\n" + 
				"\t\tVillainGroup Council\n" + 
				"\t\tVillainType Boss\n" + 
				"\t\tAI_Group 1\n" + 
				"\t\tAI_InActive <<Wander>>\n" + 
				"\t\tAI_Alerted <<Combat>>\n" + 
				"\t}\n" + 
				"\tActor\n" + 
				"\t{\n" + 
				"\t\tActorName V_5th_1\n" + 
				"\t\tNumber 1\n" + 
				"\t\tLocation 40\n" + 
				"\t\tMinimumHeroesRequired 1\n" + 
				"\t\tMaximumHeroesRequired 8\n" + 
				"\t\tGang 2\n" + 
				"\t\tType eVillain\n" + 
				"\t\tVillain 5thColumn_Fog_Elite\n" + 
				"\t\tVillainGroup 5thColumn\n" + 
				"\t\tVillainType Minion\n" + 
				"\t\tAI_Group 1\n" + 
				"\t\tAI_InActive <<HandsOnHead_Kneel>>\n" + 
				"\t\tAI_Alerted <<FleeToNearestDoor(Timer(5)),Combat(Gang(3))>>\n" + 
				"\t}\n" + 
				"\tActor\n" + 
				"\t{\n" + 
				"\t\tActorName V_5th_2\n" + 
				"\t\tNumber 1\n" + 
				"\t\tLocation 41\n" + 
				"\t\tMinimumHeroesRequired 1\n" + 
				"\t\tMaximumHeroesRequired 8\n" + 
				"\t\tGang 2\n" + 
				"\t\tType eVillain\n" + 
				"\t\tVillain 5thColumn_Fog_Elite_Lieutenant\n" + 
				"\t\tVillainGroup 5thColumn\n" + 
				"\t\tVillainType Lieutenant\n" + 
				"\t\tAI_Group 1\n" + 
				"\t\tAI_InActive <<HandsOnHead_Kneel>>\n" + 
				"\t\tAI_Alerted <<FleeToNearestDoor(Timer(5)),Combat(Gang(3))>>\n" + 
				"\t}\n" + 
				"\tActor\n" + 
				"\t{\n" + 
				"\t\tActorName V_5th_3\n" + 
				"\t\tNumber 1\n" + 
				"\t\tLocation 42\n" + 
				"\t\tMinimumHeroesRequired 1\n" + 
				"\t\tMaximumHeroesRequired 8\n" + 
				"\t\tGang 2\n" + 
				"\t\tType eVillain\n" + 
				"\t\tVillain 5thColumn_Fog_Elite\n" + 
				"\t\tVillainGroup 5thColumn\n" + 
				"\t\tVillainType Minion\n" + 
				"\t\tAI_Group 1\n" + 
				"\t\tAI_InActive <<HandsOnHead_Kneel>>\n" + 
				"\t\tAI_Alerted <<FleeToNearestDoor(Timer(5)),Combat(Gang(3))>>\n" + 
				"\t}\n" + 
				"}\n"; 
		SpawnDef sd = new SpawnDef(expected);
		assertEquals("toString prints expected result", expected, sd.toString());
		assertEquals("Hero", sd.getEncounterAlliance());
		assertEquals("Oh noes! It is $heroName", sd.getDialog());
	}
	
	@org.junit.Test
	public void spawnDefTestNamedGetSet() {
		SpawnDef sd = new SpawnDef();
		int valueInt = 0;
		for (String name : SpawnDef.PROPERTIES_NAMES) {
			String value = Integer.toString(valueInt);
			sd.setPropertyByName(name, value);
			assertEquals(value, sd.getPropertyByName(name));
			valueInt++;
		}
	}
	
}
