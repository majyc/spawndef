package resurgence.spawndef.controller;

import resurgence.spawndef.InvalidFormatException;
import resurgence.spawndef.SpawnDef;

public class SpawnDefWrapper {
	static String expected = "SpawnDef\n" + 
			"{\n" + 
			"\tVillainMinLevel 14\n" + 
			"\tVillainMaxLevel 19\n" + 
			"\tMinTeamSize 1\n" + 
			"\tMaxTeamSize 8\n" + 
			"\tEncounterAlliance  Hero\n" + 
			"\tDialog  Oh noes! It is $heroName\n" + 
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
	
	private String name;
	private SpawnDef spawnDef;
	public SpawnDefWrapper() throws InvalidFormatException {
		setSpawnDef(new SpawnDef(expected));
		setName("Test SpawnDef");
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SpawnDef getSpawnDef() {
		return spawnDef;
	}
	public void setSpawnDef(SpawnDef spawnDef) {
		this.spawnDef = spawnDef;
	}

}
