package paybar.rest;

import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import paybar.data.PartnerResource;
import paybar.model.DetailAccount;
import paybar.model.PointOfSale;

/**
 * FastCheck is responsible for authorizing a transaction. This includes account
 * and TAN-code checkup. If both are valid the FastCheck answers the client with
 * success and queues a transaction + updates the distributed caches account
 * status immediately. The TAN-code should be invalidated as soon as possible
 * too.
 * 
 * @author wolfi
 * 
 */
@Path("/setup")
@RequestScoped
public class SetupDatabase {

	public static final String VALID_POS_ID = "1060";
	public static final String VALID_TAN_CODE = "21";
	public static final double CREDIT = 100000d;

	@Inject
	private PartnerResource pr;

	/**
	 * At least the put works. Should probably exchanged by post with a
	 * structure like this:
	 * http://stackoverflow.com/questions/2637017/how-do-i-do
	 * -a-multipart-form-file-upload-with-jax-rs
	 * 
	 * @param posId
	 * @param tanCode
	 * @param amount
	 * @return
	 * @throws NamingException
	 */
	@PUT
	@Path("/database")
	@Produces(MediaType.APPLICATION_JSON)
	public String transaction() {
		String result = null;
		boolean success = true;

		// setup our database with new data
		// generate a list of points of sale for the partner
		// 10 should be sufficient
		ArrayList<PointOfSale> pointsOfSale = new ArrayList<PointOfSale>(10);
		for (int i = 0; i < 10; i++) {
			PointOfSale pos = new PointOfSale("TIROL", "FILIALE-" + (i + 1));
			pointsOfSale.add(pos);
		}
		// first create a company
		pr.createNewpartner("TIROL", "6020", "bankingdata-KPREIS", "kpreis",
				"blabla", pointsOfSale, 0d);

		// create 10 customers with simple usernames and passwords
		// fill up with some credit and issue them some coupons
		ArrayList<DetailAccount> detailAccounts = new ArrayList<DetailAccount>(
				10);
		for (int i = 0; i < 10; i++) {
			DetailAccount da = new DetailAccount();
			
			
			
			da.setCredit(i * 1000);
			da.setSecurityKey("user-" + i); // TODO: dummy key for early
													// development for later
													// stages
			
			da.setAdress("Birkenweg " + i);
			da.setFirstName("Hans der " + i + ".");
			da.setPassword("hallo123");
			da.setPhoneNumber("0123456789");
			da.setSureName(" von Mesopotamien");
			da.setUserName("user-" + i);
			da.setActive(true);
			da.setLocationHash("TIROL");
			da.regenerateCoupons();

			detailAccounts.add(da); // TODO: important persist this
		}
		
		

		if (success) {
			result = new String("SUCCESS");
		} else {
			// TODO: use ExceptionMapper here or create more detailed response
			// status code
			throw new WebApplicationException(404);
		}

		return result;
	}

	@GET
	public String getAllTransactions() {
		return "Much has happened since you've started to participate in history.";
	}

}
