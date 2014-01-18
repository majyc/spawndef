package com.resurgence.spawndef;

import static org.junit.Assert.assertEquals;

import java.util.Scanner;


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
				"\tPos -25.000000 0 8.400000\n" + 
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
				"\t\tPos 125.000000 0 91.600000\n" + 
				"\tEnd\n" + 
				"End\n" + 
				"";
		Group g = new Group(expected);
		assertEquals(expected, g.toString());
	}

}
