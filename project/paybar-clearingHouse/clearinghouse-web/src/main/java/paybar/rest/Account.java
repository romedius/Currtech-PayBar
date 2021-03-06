package paybar.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import at.ac.uibk.paybar.messages.Configuration;
import at.ac.uibk.paybar.messages.TransactionRequest;
import at.ac.uibk.paybar.model.FastCoupon;
import at.ac.uibk.paybar.model.TransferAccount;

import paybar.data.DetailAccountResource;
import paybar.data.ModelToWebserviceTransformer;
import paybar.data.TransactionResource;
import paybar.model.Coupon;
import paybar.model.DetailAccount;
import paybar.model.Transaction;
import paybar.wsmodel.WsTransaction;

/**
 * This Webservice provides the functionalities needed for the User Interface to
 * query results asynchronously
 * 
 * */

@Path("/account")
@RequestScoped
public class Account {

	@Inject
	Logger log;

	@Inject
	private DetailAccountResource dar;

	@Inject
	private TransactionResource trr;

	@Inject
	private ModelToWebserviceTransformer mtwt;

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnMessage createAccount(DetailAccount da) {
		log.info("create ACOUNT");
		try {
			dar.createNewDetailAccount(da);

			ClientRequest clientRequest = new ClientRequest(
					"http://localhost:8080/fastcheck/rest/transactions/account/new");
			clientRequest.accept("application/json");
			da = dar.getUserByName(da.getUserName(), false);
			clientRequest.body("application/json", da.getTransferAccount());

			ClientResponse<String> fastcheckResponse = clientRequest
					.post(String.class);
			ReturnMessageEnum ret = (fastcheckResponse.getStatus() == 200 ? ReturnMessageEnum.SUCCESS
					: ReturnMessageEnum.FAILURE);
			ReturnMessage rm = new ReturnMessage(ret, ret.toString());
			return rm;
		} catch (Exception e) {
			ReturnMessage rm = new ReturnMessage(ReturnMessageEnum.FAILURE,
					ReturnMessageEnum.FAILURE.toString());
			return rm;
		}
	}

	@GET
	@Path("/get/{name}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public DetailAccount getAccount(@PathParam("name") String userName) {
		log.info("Username:" + userName);
		try {
			return dar.getUserByName(userName, true);
		} catch (NoResultException e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		} catch (Exception e) {
			throw new WebApplicationException(e,
					Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@GET
	@Path("/getTransactions/{name}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<WsTransaction> getTransactionsByUsername(
			@PathParam("name") String userName) {
		try {
			List<WsTransaction> transactions = new ArrayList<WsTransaction>();
			for (Transaction t : trr.getTransactionsByUsername(userName)) {
				transactions.add(mtwt.transactionToWsTransaction(t));
			}
			return transactions;
		} catch (NoResultException e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		} catch (Exception e) {
			throw new WebApplicationException(e,
					Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@GET
	@Path("/getTransactioncnt/{name}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int getTransactionCountByUsername(@PathParam("name") String userName) {
		try {
			return trr.getTransactionsByUsername(userName).size();
		} catch (NoResultException e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		} catch (Exception e) {
			throw new WebApplicationException(e,
					Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

}
