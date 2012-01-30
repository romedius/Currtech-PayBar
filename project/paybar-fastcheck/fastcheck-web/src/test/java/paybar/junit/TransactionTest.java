package paybar.junit;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

/**
 * Test class for {@link MyServlet}
 * <p>
 * This is an integration test.
 * <p>
 * In order to pass these tests within an IDE, you have to first run Jetty. This
 * is configured for you via maven, if you run the command:
 * 
 * <pre>
 * mvn jetty:run
 * </pre>
 * 
 * Alternatively, you can just run the maven command:
 * 
 * <pre>
 * mvn integration-test
 * </pre>
 * 
 * and maven will do everything for you (start jetty, run test, stop jetty).
 * <p>
 * Make sure the literal URL below matches that defined in pom.xml
 */

// from: http://zenoconsulting.wikidot.com/blog:1
public class TransactionTest extends TestCase {

	/** URL */
	private static final String URL = "http://localhost:8080/fastcheck/rest/transaction/test/123/1234/200/";

	private static final String ERR_MSG = "Unexpected exception in test. Is Jetty Running at "
			+ URL + " ? ->";

	@SuppressWarnings("unused")
	private PostMethodWebRequest req;
	private WebConversation wc;

	@Before
	public void setUp() {
		req = new PostMethodWebRequest(URL);
		wc = new WebConversation();
	}

	@After
	protected void tearDown() throws Exception {
	}

	
	@Test
	public void testDoGet() {
		System.out.println("testit");
		try {
			WebResponse wr = wc.getResponse(URL);
			System.out.println(wr.getText());
			Assert.assertTrue(wr.getText().contains("Done"));
		} catch (Exception e) {
			Assert.fail(ERR_MSG + e);
		}
	}

	@Test
	public void testDoPost() {
		try {
			// req.setParameter("xml", StringUtil.readFileAsString(XML));
			// WebResponse resp = wc.getResponse(req);
			// Assert.assertNotNull("response was null", resp);
			// Assert.assertEquals(200, resp.getResponseCode());
		} catch (Exception e) {
			Assert.fail(ERR_MSG + e);
		}
	}

}