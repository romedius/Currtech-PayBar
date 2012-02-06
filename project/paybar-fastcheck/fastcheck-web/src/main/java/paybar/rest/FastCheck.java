package paybar.rest;



import java.sql.SQLException;

import javax.enterprise.context.RequestScoped;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import at.ac.uibk.paybar.TransactionMessage;


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
@Path("/transactions")
@RequestScoped
public class FastCheck {

	public static final String VALID_POS_ID = "1060";
	public static final String VALID_TAN_CODE = "21";
	public static final long CREDIT = 100000;

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
	@POST
	@Path("/tan/{tanCode}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String transaction(@PathParam("tanCode") String tanCode,
			TransactionRequest transactionRequest) {
		String result = null;
		boolean success = true;

		// method needs @Form parameter with @POST
		if (transactionRequest != null) {
			String posId = transactionRequest.getPosId();
			long amount = transactionRequest.getAmount();
			if (posId != null && VALID_POS_ID.equals(VALID_POS_ID)) { // TODO: Weiter
				if (tanCode != null && VALID_TAN_CODE.equals(VALID_TAN_CODE)) {
					// see if account has enough credit
					long oldCredit = CREDIT;
					long newCredit = CREDIT - amount;
					success = newCredit > 0.1d;

					if (success) {
						TransactionMessage transactionMessage = new TransactionMessage(
								tanCode, posId, amount, oldCredit, newCredit,
								System.currentTimeMillis());
						// transmit to JMS
						
						// obtain context
						
						InitialContext ic = null;
					      Connection jmsConnection = null;
					      java.sql.Connection jdbcConnection = null;

					      try {
							try
							  {
							     // Step 1. Lookup the initial context
							     ic = new InitialContext();

							     // JMS operations

							     // Step 2. Look up the XA Connection Factory
							     ConnectionFactory cf = (ConnectionFactory)ic.lookup("java:/JmsXA");

							     // Step 3. Look up the Queue
							     Queue queue = (Queue)ic.lookup("queue/test");

							     // Step 4. Create a connection, a session and a message producer for the queue
							     jmsConnection = cf.createConnection();
							     Session session = jmsConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
							     MessageProducer messageProducer = session.createProducer(queue);

							     // Step 5. Create a Text Message
							     ObjectMessage message = session.createObjectMessage(transactionMessage);
							     // TextMessage message = session.createTextMessage("");

							     // Step 6. Send The Text Message
							     messageProducer.send(message);
							     // TODO: log this
							     // System.out.println("Sent message: " + message.getText() + "(" + message.getJMSMessageID() + ")");

							     /*
							     PreparedStatement pr = jdbcConnection.prepareStatement("INSERT INTO " + SendMessageBean.TABLE +
							                                                            " (id, text) VALUES ('" +
							                                                            message.getJMSMessageID() +
							                                                            "', '" +
							                                                            text +
							                                                            "');");

							     // Step 10. execute the prepared statement
							     pr.execute();

							     // Step 11. close the prepared statement
							     pr.close();
							     */
							  }
							  finally
							  {
							     // Step 12. Be sure to close all resources!
							     if (ic != null)
							     {
							        ic.close();
							     }
							     if (jmsConnection != null)
							     {
							        jmsConnection.close();
							     }
							     if (jdbcConnection != null)
							     {
							        jdbcConnection.close();
							     }
							  }
						} catch (NamingException e) {
							throw new WebApplicationException(e);
						} catch (JMSException e) {
							throw new WebApplicationException(e);
						} catch (SQLException e) {
							throw new WebApplicationException(e);
						}
						
					}
				}
			}
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
