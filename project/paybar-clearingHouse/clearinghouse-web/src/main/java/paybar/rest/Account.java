package paybar.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import paybar.data.DetailAccountResource;
import paybar.data.TransactionResource;
import paybar.model.DetailAccount;
import paybar.model.Transaction;

@Path("/account")
@RequestScoped
public class Account {

	@Inject
	private DetailAccountResource dar;

	@Inject
	private TransactionResource trr;
	
	@PUT
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnMessage createAccount(DetailAccount da) {
		dar.createNewDetailAccount(da);
		ReturnMessage rm = new ReturnMessage(ReturnMessageEnum.SUCCESS,
				ReturnMessageEnum.SUCCESS.toString());
		return rm;
	}

	@POST
	@Path("/get")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public DetailAccount getAccount(String userName) {
		try {
			return dar.getUserByName(userName);
		} catch (NoResultException e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		} catch (Exception e) {
			throw new WebApplicationException(e,Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GET
	@Path("/getTransactions")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Transaction> getTransactionsByUsername(String name) {
		try {
			return trr.getTransactionsByUsername(name);
		} catch (NoResultException e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		} catch (Exception e) {
			throw new WebApplicationException(e,Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	

}
