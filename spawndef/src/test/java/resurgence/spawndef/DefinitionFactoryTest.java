package resurgence.spawndef;

import static org.junit.Assert.assertEquals;
import resurgence.spawndef.DefinitionFactory;
import resurgence.spawndef.Pos;
import resurgence.spawndef.Property;

public class DefinitionFactoryTest {

	@org.junit.Test
	public void createPos() {
		String expected = "Pos 143.326782 188 -676.42749";
		Pos p = (Pos) DefinitionFactory.newInstance("Pos", expected);
		assertEquals("Round trip is successful", expected, p.toString());
		Pos p2 = new Pos("143.326782", "188", "-676.427490");
		assertEquals("Creating via factory is equivalent to manually", p.toString(), p2.toString());
	}

	@org.junit.Test
	public void createProperty() {
		String expected = "Property   \"CanSpawn1\"   \"SpawnDefs\\City_03_01\\ExampleDef.spawndef\"   0";
		Property p = (Property) DefinitionFactory.newInstance("Property", expected);
		assertEquals("Round trip is successful", expected, p.toString());
		Property p2 = new Property(expected);
		assertEquals("Creating via factory is equivalent to manually", p.toString(), p2.toString());
	}

	
}
