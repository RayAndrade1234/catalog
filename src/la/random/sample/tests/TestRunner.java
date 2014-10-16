package la.random.sample.tests;

import junit.framework.TestSuite;
import android.test.InstrumentationTestRunner;
import android.test.InstrumentationTestSuite;

public class TestRunner extends InstrumentationTestRunner {

	@Override
	public ClassLoader getLoader() {
		// TODO Auto-generated method stub
		return TestRunner.class.getClassLoader();
	}

	@Override
	public TestSuite getAllTests() {
		InstrumentationTestSuite suite = new InstrumentationTestSuite(this);
		//suite.addTestSuite(SimpleFooTestCase.class);
		suite.addTestSuite(DbHelperTestCase.class);
		//suite.addTestSuite(ProductServicesTestCase.class);
		//suite.addTestSuite(JSONhelperTestCase.class);
		
		return suite;
		
	}

}
