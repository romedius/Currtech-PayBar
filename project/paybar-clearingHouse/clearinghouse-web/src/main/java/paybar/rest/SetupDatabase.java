package paybar.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import paybar.data.DetailAccountResource;
import paybar.data.PartnerResource;
import paybar.data.PointOfSaleResource;
import paybar.data.TransactionResource;
import paybar.model.DetailAccount;
import paybar.model.PointOfSale;
import at.ac.uibk.paybar.helpers.RN;

/**
 * This can be used to fill the database with test data after startup.
 * 
 * @author alle ;-)
 * 
 */
@Path("/setup")
@RequestScoped
public class SetupDatabase {

	public static final String VALID_POS_ID = "1060";
	public static final String VALID_TAN_CODE = "21";
	public static final double CREDIT = 100000d;

	@Inject
	Logger log;

	@Inject
	private PartnerResource pr;

	@Inject
	private PointOfSaleResource posr;

	@Inject
	private DetailAccountResource dar;

	@Inject
	private TransactionResource trr;

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
			posr.createNewPointOfSale(pos);
			pointsOfSale.add(pos);
		}
		// first create a company
		pr.createNewpartner("TIROL", "6020", "bankingdata-KPREIS", "kpreis",
				"blabla", pointsOfSale, 0l);

		Random r = new Random();
		Date now = new Date();
		for (int i = 0; i < 10; i++) {
			DetailAccount da = new DetailAccount();

			da.setCredit(i * 1000);
			da.setSecurityKey("user-" + i); // TODO: dummy key for early
											// development for later
											// stages

			da.setAdress("Birkenweg " + i);
			da.setFirstName("Hans der " + RN.roman(i + 1));
			da.setPassword("hallo123");
			da.setPhoneNumber("0123456789");
			da.setSureName(" von Mesopotamien");
			da.setUserName("user-" + i);
			da.setActive(true);
			da.setLocationHash("TIROL");
			// da.setOldCoupons(new ArrayList<Coupon>());// Set an empty
			// arraylist
			dar.regenerateCoupons(da);
			dar.createNewDetailAccount(da);

			// Create a bunch of transactions for each user.
			int n = da.getCoupons().size() / 2
					+ r.nextInt(da.getCoupons().size());
			for (int j = 0; j < n; j++) {
				try {
					trr.createTransactionWithCoupon(r.nextLong() % 2500, da
							.getCoupons().get(0).getCouponCode(),
							"Dummy transaction " + j, pointsOfSale.get(j)
									.getName(), now.getTime(), null, null);
				} catch (PersistenceException p) {
					log.info(p.getMessage());
					break;
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			dar.regenerateCoupons(da);
			dar.updateDetailAccount(da);
		}

		// TODO: reload the fastcheck cache after this initial setup or even
		// better use the available methods for notifying it of the new objects

		if (success) {
			result = new String("SUCCESS");
		} else {
			// TODO: use ExceptionMapper here or create more detailed response
			// status code
			throw new WebApplicationException(404);
		}

		return result;
	}

}
