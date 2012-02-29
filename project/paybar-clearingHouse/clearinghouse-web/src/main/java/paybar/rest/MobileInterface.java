package paybar.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import paybar.data.DetailAccountResource;
import paybar.model.Coupon;
import paybar.model.DetailAccount;
import at.ac.uibk.paybar.model.FastCoupon;

@Path("/fastcheckInterface")
@RequestScoped
public class MobileInterface {

	@Inject
	Logger log;

	@Inject
	private DetailAccountResource dar;

	@POST
	@Path("/getSecurityKey")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSecurityKey(@FormParam("userName")String userName, @FormParam("password")String password) {
		DetailAccount da;
		try {
			da = dar.getUserByName(userName, true);
			if (da.getPassword().equals(password)) {
				return da.getSecurityKey();
			} else {			
				throw new WebApplicationException(401);
			}
		} catch (NoResultException e) {
			throw new WebApplicationException(e,401);
		} catch (Exception e) {
			throw new WebApplicationException(e,500);
		}
	}
	

	@GET
	@Path("/getCoupons/{userName}/{secKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<FastCoupon> getCoupons(@PathParam("userName")String userName, @PathParam("secKey")String securityKey) {
		DetailAccount da;
		try {
			da = dar.getUserByName(userName, true);
			if (da.getSecurityKey().equals(securityKey)) {
				ArrayList<FastCoupon> fcl = new ArrayList<FastCoupon>();
				for (Coupon c : da.getCoupons()) {
					fcl.add(c.getFastCoupon());
				}
				return fcl;
			} else {			
				throw new WebApplicationException(401);
			}
		} catch (NoResultException e) {
			throw new WebApplicationException(e,401);
		} catch (Exception e) {
			throw new WebApplicationException(e,500);
		}
	}

}
