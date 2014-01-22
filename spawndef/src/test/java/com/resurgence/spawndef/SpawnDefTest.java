package com.resurgence.spawndef;

import static org.junit.Assert.assertEquals;

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
		String expected = 
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
		SpawnDef sd = new SpawnDef(expected);
		assertEquals("toString prints expected result", expected, sd.toString());
	}
}
