package com.resurgence.spawndef;

/**
 * CompondDefinition - a CompoundDefinition consists of a set of Definitions data, generically looking like.  All Compound Definitions
 * are multiline.
 * 
 * BEGIN name
 *    COMPOUND_DEFINITION*
 *    DEFINITION*
 *    PROPERTY*
 * END
 * 
 * where DEFINITION looks like
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
 * a more complex example might be a nested set of compound definitions
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
public abstract class CompoundDefinition extends Definition {
	private String beginDelimiter = "Def";
	private String endDelimiter = "End";
	private String name = "";

	public String toString() {
		String s = beginDelimiter + " " + name + dataToString() + "\n" + endDelimiter + "\n";
		return s;
	}

	public String getBeginDelimiter() {
		return beginDelimiter;
	}

	public void setBeginDelimiter(String beginDelimiter) {
		this.beginDelimiter = beginDelimiter;
	}

	public String getEndDelimiter() {
		return endDelimiter;
	}

	public void setEndDelimiter(String endDelimiter) {
		this.endDelimiter = endDelimiter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	abstract protected String dataToString();
}
