package la.random.sample.tests;

import junit.framework.Assert;
import junit.framework.TestCase;

public class SimpleFooTestCase extends TestCase {

	public SimpleFooTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testMyFirst(){
		Assert.assertEquals("hello", SimpleFoo.getString());
		//Assert.fail(message);
	}

}
