package paybar.junit;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class RegisterTest {

	/** URL */
	private static final String URL = "http://localhost:8080/clearinghouse/rest/account/create";

	
	@Test
	public void testRegister() {
		System.out.println("Begin Test Register:");
        String str ="{\"firstName\":\"MichiTest\"," + 
        		"\"sureName\":\"DejoriTest\"," +
    			"\"userName\":\"michidejori\"," +
    			"\"password\":\"michidejori\"," +
    			"\"phoneNumber\":\"23423234\"," + 
    			"\"locationHash\":\"TIROL\"," +
    			"\"moveToLocationHash\":null," + 
    			"\"credit\":100," +
    			"\"coupons\":[]," + 
    			"\"securityKey\":\"sadfwaerawr\"}";
        InputStream bais = new ByteArrayInputStream(str.getBytes());
		WebRequest postreq = new PostMethodWebRequest(URL,bais,"application/json");
		WebConversation wc = new WebConversation();
		try {
			WebResponse webresponse = wc.getResponse(postreq);
			System.out.println("GGG: " + webresponse.getText());
			assertEquals("asda","{\"message\":\"SUCCESS\"}", webresponse.getText());
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testSameRegister() {
		System.out.println("Begin Test Register:");
        String str ="{\"firstName\":\"MichiTest\"," + 
        		"\"sureName\":\"DejoriTest\"," +
    			"\"userName\":\"michidejori\"," +
    			"\"password\":\"michidejori\"," +
    			"\"phoneNumber\":\"23423234\"," + 
    			"\"locationHash\":\"TIROL\"," +
    			"\"moveToLocationHash\":null," + 
    			"\"credit\":100," +
    			"\"coupons\":[]," + 
    			"\"securityKey\":\"sadfwaerawr\"}";
        InputStream bais = new ByteArrayInputStream(str.getBytes());
		WebRequest postreq = new PostMethodWebRequest(URL,bais,"application/json");
		WebConversation wc = new WebConversation();
		try {
			WebResponse webresponse = wc.getResponse(postreq);
			System.out.println("GGG: " + webresponse.getText());
			assertEquals("asda","{\"message\":\"FAILURE\"}", webresponse.getText());
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

}
