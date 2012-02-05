package paybar.rest;

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
import paybar.model.DetailAccount;

@Path("/account")
@RequestScoped
public class Account {

	@Inject
	private DetailAccountResource dar;

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
	@Path("/test1")
	@Produces(MediaType.APPLICATION_JSON)
	public String test1() {
		return "username";
	}
	
	@PUT
	@Path("/test2")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String test2(String userName) {
		return "Hello " + userName;
	}

}
