package resurgence.spawndef;

import static org.junit.Assert.assertEquals;
import resurgence.spawndef.Property;

public class PropertyTest {

	/*
	 *  Property   "CanSpawn1"   "SpawnDefs\City_03_01\ExampleDef.spawndef"   0
	 * 
	 */
	
	@org.junit.Test
	public void createProperty() {
		String expected = "Property   \"CanSpawn1\"   \"SpawnDefs\\City_03_01\\ExampleDef.spawndef\"   0";
		Property p = new Property(expected);
		assertEquals(expected, p.toString());
	}

}
