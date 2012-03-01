package paybar.junit;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import org.junit.BeforeClass;
import org.junit.Test;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class CouponAfterRegisterTest {

	/** URL */
	private static final String Register_URL = "http://localhost:8080/clearinghouse/rest/account/create";
	private static final String Get_URL = "http://localhost:8080/clearinghouse/rest/account/get/";
	private static String userName = "CouponTest";
	private static JSONObject jsonOb;
	
	/** URL */
	private static final String Transaction_URL = "http://localhost:8080/fastcheck/rest/transactions";

	@BeforeClass
	public static void setUp() {
		String str = "{\"firstName\":\"Michl\"," + "\"sureName\":\"Dejoei\","
				+ "\"userName\":\"" + userName + "\","
				+ "\"password\":\"michidejori\","
				+ "\"phoneNumber\":\"23423234\","
				+ "\"locationHash\":\"TIROL\","
				+ "\"moveToLocationHash\":null," + "\"credit\":100,"
				+ "\"coupons\":[]," + "\"securityKey\":\"sadfwaerawr\"}";
		InputStream bais = new ByteArrayInputStream(str.getBytes());
		WebRequest postreq = new PostMethodWebRequest(Register_URL, bais,
				"application/json");
		WebConversation wc = new WebConversation();
		try {
			@SuppressWarnings("unused")
			WebResponse webresponse = wc.getResponse(postreq);
			// wait for 500 ms so that server could initiated the new accout
			Thread.sleep(500);
			WebRequest getreq = new GetMethodWebRequest(Get_URL + userName);
			getreq.setHeaderField("Content-type", "application/json");
			getreq.setHeaderField("Accept", "application/json");
			WebConversation wc2 = new WebConversation();
			WebResponse webresponse2 = wc2.getResponse(getreq);
			jsonOb = (JSONObject) new JSONParser().parse(webresponse2
					.getText());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testNameSetCorrectly() {
		assertEquals("firstname", jsonOb.get("firstName"), "Michl");
		assertEquals("surename", jsonOb.get("sureName"), "Dejoei");
	}
	
	/**
	 * 
	 * 
	@Test
	public void Coupons() {
		JSONArray jArray = (JSONArray) jsonOb.get("coupons");
		
		for (int i = 0; i < jArray.size(); i++) {
			JSONObject jo = (JSONObject) jArray.get(0);
			String couponCode = jo.get("couponCode").toString();
			System.out.println(couponCode);
			
			String urls = this.Transaction_URL + "/tan/" + couponCode;
			String str = "{\"amount\":\"10\"," 
					+ "\"posOrBankId\":\"1060\"}";
			InputStream bais = new ByteArrayInputStream(str.getBytes());
			WebRequest postreq = new PostMethodWebRequest(Register_URL, bais,
					"application/json");
			WebConversation wc = new WebConversation();
			
		}
	}
	*/


}
