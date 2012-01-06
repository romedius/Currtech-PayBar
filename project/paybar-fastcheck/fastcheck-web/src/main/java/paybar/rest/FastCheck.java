package paybar.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

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

@Path("/transaction")
public class FastCheck {
	
	@POST
	@Path("/{posId}/{tanCode}/{amount}")
	@Consumes("text/plain")
	public String transaction(@PathParam("posId") String posId, @PathParam("tanCode") String tanCode, @PathParam("amount") double amount) {
		String result = null;
		
		return result;
	}

}
