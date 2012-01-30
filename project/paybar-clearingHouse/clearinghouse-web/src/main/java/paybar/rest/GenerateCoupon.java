package paybar.rest;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;



import javax.ws.rs.*;

import paybar.data.CouponResource;



/**
 * GenerateCoupon
 * This class is responsible for defining a Web Service.
 * The Web Service should generate Coupons and write them
 * in the persistence Data Source
 * @author Michael
 */
@Path("/generateCoupon")
@RequestScoped
public class GenerateCoupon {

	@Inject
	CouponResource cr;

	@GET
	public String generateNewCoupons() {
		java.util.Date d = new java.util.Date();
		cr.createNewCoupon(true, "meinerstesCoupon", "lochash", d, d ,d );
		return "generated";
	}

}
