package fi.uef.caao;


import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>CaaoUserUtilsTest</code> contains tests for the class <code>{@link fi.uef.caao.CaaoUserUtils}</code>.
 *
 * @generatedBy CodePro at 5/10/11 1:03 PM
 * @author zafar.khaydarov
 * @version $Revision: 1.4 $
 */
public class CaaoUserUtilsTest {
	/**
	 * Run the String getServerInfo() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 5/10/11 1:03 PM
	 * @throws Exception */
	@Test
	public void testGetServerInfo_1()
		throws Exception {
		CaaoUserUtils fixture = CaaoUserUtilsFactory.createCaaoUserUtils3();

		String result = fixture.getServerInfo();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NoSuchMethodException: createCaaoUserUtils3
		//       at com.instantiations.eclipse.analysis.expression.model.MethodInvocationExpression.findMethod(MethodInvocationExpression.java:711)
		//       at com.instantiations.eclipse.analysis.expression.model.MethodInvocationExpression.execute(MethodInvocationExpression.java:571)
		//       at com.instantiations.eclipse.analysis.expression.model.MethodInvocationExpression.execute(MethodInvocationExpression.java:550)
		//       at com.instantiations.assist.eclipse.junit.execution.core.ExecutionRequest.execute(ExecutionRequest.java:286)
		//       at com.instantiations.assist.eclipse.junit.execution.communication.LocalExecutionClient$1.run(LocalExecutionClient.java:158)
		//       at java.lang.Thread.run(Unknown Source)
		assertNotNull(result);
	}

	/**
	 * Run the boolean isUserNameIsUniq(String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 5/10/11 1:03 PM
	 * @throws Exception */
	@Test
	public void testIsUserNameIsUniq_1()
		throws Exception {
		CaaoUserUtils fixture = CaaoUserUtilsFactory.createCaaoUserUtils();
		String pUserName = "";

		boolean result = fixture.isUserNameIsUniq(pUserName);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean updateUserPassword(String,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 5/10/11 1:03 PM
	 * @throws Exception */
	@Test
	public void testUpdateUserPassword_1()
		throws Exception {
		CaaoUserUtils fixture = CaaoUserUtilsFactory.createCaaoUserUtils2();
		String pUsername = "";
		String pNewPassword = "";

		boolean result = fixture.updateUserPassword(pUsername, pNewPassword);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	
	 *
	 * @generatedBy CodePro at 5/10/11 1:03 PM
	 * @throws Exception
	 *         if the initialization fails for some reason */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	
	 *
	 * @generatedBy CodePro at 5/10/11 1:03 PM
	 * @throws Exception
	 *         if the clean-up fails for some reason */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 5/10/11 1:03 PM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(CaaoUserUtilsTest.class);
	}
}