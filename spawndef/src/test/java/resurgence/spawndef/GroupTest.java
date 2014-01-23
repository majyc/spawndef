package resurgence.spawndef;

import static org.junit.Assert.assertEquals;

import java.util.Scanner;

import resurgence.spawndef.Group;


/*
 * Definition - a Definition consists of a set of data, generically looking like
 * 
 * BEGIN name
 *    DEFINITION*
 *    DEF_ELEMENT*
 *    PROPERTY*
 * END
 * 
 * where DEF_ELEMENT looks like
 * name param*
 * 
 * and PROPERTY looks like
 * Property name params+
 * 
 * for example this is a very simple definition: it's a named group with one DEF_ELEMENT, a Pos with some params
 * 
 	Group Omni/EncounterSpawns/Encounter_E_01
		Pos -25 0 8.4
	End
 * 
 * a more complex example might be a nested set of defintions
 	Group grp_spawns
		Group grp_example_def
			Pos 125 0 91.6
		End
	End
 *
 * and an even more complex example, with several nested definitions and properties
 * 
	 Group grp_example_def
		Group Omni/EncounterSpawns/Encounter_E_01
			Pos -25 0 8.4
		End
		Group Omni/EncounterSpawns/Encounter_E_02
			Pos 25 1 -41.6
		End
		Group Omni/EncounterSpawns/Encounter_E_03
			Pos 0 0 33.4
		End
		Property   "CanSpawn1"   "SpawnDefs\City_03_01\ExampleDef.spawndef"   0
	End
 *		
 * @author Joshua
 *
 */

public class GroupTest {
	@org.junit.Test
	public void simpleGroup() {
		String expected = "Group Omni/EncounterSpawns/Encounter_E_01\n" + 
				"\tPos -25 0 8.4\n" + 
				"End\n";
		Group g = new Group(expected);
		assertEquals(expected, g.toString());
	}

	String testParse(Scanner scanner) {
		String s = scanner.next();
		return s;
	}

	@org.junit.Test
	public void passScanner() {
		String expected = "This is a string";
		try (Scanner scanner = new Scanner(expected)) {
			String w1 = scanner.next();
			assertEquals("This", w1);
			String w2 = testParse(scanner);
			assertEquals("is", w2);
			String w3 = scanner.next();
			assertEquals("a", w3);
		}
	}
	
	@org.junit.Test
	public void nestedGroup() {
		String expected = "Group grp_spawns\n" + 
				"\tGroup grp_example_def\n" + 
				"\t\tPos 125 0 91.6\n" + 
				"\tEnd\n" + 
				"End\n" + 
				"";
		Group g = new Group(expected);
		assertEquals(expected, g.toString());
	}

	@org.junit.Test
	public void slightlyWorseNestedGroup() {
		String expected = "Group grp_spawns\n" + 
				"\tGroup grp_example_def\n" + 
				"\t\tPYR 25 0 -10\n" + 
				"\t\tPos 125 0 91.6\n" + 
				"\tEnd\n" + 
				"End\n" + 
				"";
		Group g = new Group(expected);
		assertEquals(expected, g.toString());
	}


	
	@org.junit.Test
	public void complexNestedGroup() {
		String expected = "Group grp_example_def\n" + 
				"\tGroup Omni/EncounterSpawns/Encounter_E_01\n" + 
				"\t\tPos -25 0 8.4\n" + 
				"\tEnd\n" + 
				"\tGroup Omni/EncounterSpawns/Encounter_E_02\n" + 
				"\t\tPos 25 1 -41\n" + 
				"\tEnd\n" + 
				"\tGroup Omni/EncounterSpawns/Encounter_E_03\n" + 
				"\t\tPos 0 0 33.4\n" + 
				"\tEnd\n" + 
				"\tProperty   \"CanSpawn1\"   \"SpawnDefs\\City_03_01\\ExampleDef.spawndef\"   0\n" + 
				"End\n";
		Group g = new Group(expected);
		assertEquals(expected, g.toString());		
	}
	

	@org.junit.Test
	public void nastyComplexNestedGroup() {
		String expected = "Def grp_captured_5th_walker\n" + 
				"\tGroup Omni/EncounterSpawns/Encounter_V_40\n" + 
				"\t\tPos -6.666748 0 -1.333252\n" + 
				"\tEnd\n" + 
				"\tGroup Omni/EncounterSpawns/Encounter_V_41\n" + 
				"\t\tPos -2.666748 0 -1.333252\n" + 
				"\tEnd\n" + 
				"\tGroup Omni/EncounterSpawns/Encounter_E_01\n" + 
				"\t\tPos -6.666748 0 6.666748\n" + 
				"\tEnd\n" + 
				"\tGroup Omni/EncounterSpawns/Encounter_E_02\n" + 
				"\t\tPos 1.333252 0 6.666748\n" + 
				"\tEnd\n" + 
				"\tGroup Omni/EncounterSpawns/Encounter_V_42\n" + 
				"\t\tPos 1.333252 0 -1.333252\n" + 
				"\tEnd\n" + 
				"\tGroup Omni/EncounterSpawns/Encounter_S_30\n" + 
				"\t\tPYR 0 90 -0\n" + 
				"\t\tPos 13.333252 0 -9.333252\n" + 
				"\tEnd\n" + 
				"\tProperty   \"CanSpawn0\"   \"SpawnDefs/Haz_02_01/Council_Captured_5th_Walker_LV14_19_V0.spawndef\"   0\n" + 
				"\tProperty   \"EncounterSpawn\"   \"Apprehend\"   0\n" + 
				"End\n";
		Group g = new Group(expected);
		assertEquals(expected, g.toString());		
	}
	

}
