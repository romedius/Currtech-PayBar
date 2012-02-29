package paybar.rest;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import paybar.data.DetailAccountResource;
import at.ac.uibk.paybar.messages.MetadataMessage;
import at.ac.uibk.paybar.model.TransferAccount;

/**
 * This Interface is used by the fastcheck service to preload the userdata on
 * startup.
 * 
 */
@Path("/fastcheckInterface")
@RequestScoped
public class FastcheckInterface {

	@Inject
	Logger log;

	@Inject
	private DetailAccountResource dar;

	@GET
	@Path("/metaData")
	@Produces(MediaType.APPLICATION_JSON)
	public MetadataMessage getMetaData() {
		MetadataMessage result = new MetadataMessage();
		result.setNoOfAccounts(dar.getNumberOfAccounts());
		return result;
	}

	@GET
	@Path("/accountBatch/{iFrom}/{count}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TransferAccount> getAccountBatch(
			@PathParam("iFrom") int indexFrom, @PathParam("count") int count) {
		return dar.getAccounts(indexFrom, count);
	}
}
