package paybar.rest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

import org.infinispan.AdvancedCache;
import org.infinispan.Cache;
import org.infinispan.manager.CacheContainer;
import org.infinispan.manager.CacheManager;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import paybar.junit.CouponAfterRegisterTest;

import at.ac.uibk.paybar.messages.Configuration;
import at.ac.uibk.paybar.messages.MetadataMessage;
import at.ac.uibk.paybar.messages.TransactionMessage;
import at.ac.uibk.paybar.messages.TransactionRequest;
import at.ac.uibk.paybar.model.FastAccount;
import at.ac.uibk.paybar.model.FastCoupon;
import at.ac.uibk.paybar.model.TransferAccount;

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

	private static final int BATCH_SIZE = 10000;
	public static final String VALID_POS_ID = "1060";
	public static final String VALID_TAN_CODE = "21";
	public static final long CREDIT = 100000;
	public static final String FASTCHECK_JNDI_NAME = "java:jboss/infinispan/container/fastcheck";

	@Inject
	private Logger log;

	// @Resource(lookup = "java:jboss/infinispan/fastcheck")
	// CacheContainer cacheContainer;

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
			String posId = transactionRequest.getPosOrBankId();
			long amount = transactionRequest.getAmount();
			if (posId != null && VALID_POS_ID.equals(VALID_POS_ID)) { // TODO:
																		// Weiter
				if (tanCode != null && VALID_TAN_CODE.equals(VALID_TAN_CODE)) {
					// see if account has enough credit
					long oldCredit = CREDIT;
					long newCredit = CREDIT - amount;
					success = newCredit > 0.1d;

					if (success) {

						// TODO: fetch username from cache
						TransactionMessage transactionMessage = new TransactionMessage(
								TransactionMessage.TYPE_TRANSACTION, posId,
								amount, System.currentTimeMillis(), "dummy",
								tanCode);
						// transmit to JMS

						// obtain context

						InitialContext ic = null;
						Connection jmsConnection = null;
						java.sql.Connection jdbcConnection = null;

						try {
							try {
								// Step 1. Lookup the initial context
								ic = new InitialContext();

								// JMS operations

								// Step 2. Look up the XA Connection Factory
								ConnectionFactory cf = (ConnectionFactory) ic
										.lookup("java:/JmsXA");

								// Step 3. Look up the Queue
								Queue queue = (Queue) ic.lookup("queue/test");

								// Step 4. Create a connection, a session and a
								// message producer for the queue
								jmsConnection = cf.createConnection();
								Session session = jmsConnection.createSession(
										false, Session.AUTO_ACKNOWLEDGE);
								MessageProducer messageProducer = session
										.createProducer(queue);

								// Step 5. Create a Text Message
								ObjectMessage message = session
										.createObjectMessage(transactionMessage);
								messageProducer.send(message);

							} finally {
								// Step 12. Be sure to close all resources!
								if (ic != null) {
									ic.close();
								}
								if (jmsConnection != null) {
									jmsConnection.close();
								}
								if (jdbcConnection != null) {
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
	@Path("/charge/{username}/{creditCardNumber}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String charge(@PathParam("username") String username,
			@PathParam("creditCardNumber") String creditCardNumber,
			TransactionRequest transactionRequest) {
		String result = null;
		boolean success = true;

		// method needs @Form parameter with @POST
		if (transactionRequest != null) {
			String posId = transactionRequest.getPosOrBankId();
			long amount = transactionRequest.getAmount();

			if (success) {
				TransactionMessage transactionMessage = new TransactionMessage(
						TransactionMessage.TYPE_CHARGE, posId, amount,
						System.currentTimeMillis(), username, creditCardNumber);
				// transmit to JMS

				// obtain context

				InitialContext ic = null;
				Connection jmsConnection = null;
				java.sql.Connection jdbcConnection = null;

				try {
					try {
						// Step 1. Lookup the initial context
						ic = new InitialContext();

						// JMS operations

						// Step 2. Look up the XA Connection Factory
						ConnectionFactory cf = (ConnectionFactory) ic
								.lookup("java:/JmsXA");

						// Step 3. Look up the Queue
						Queue queue = (Queue) ic.lookup("queue/test");

						// Step 4. Create a connection, a session and a message
						// producer for the queue
						jmsConnection = cf.createConnection();
						Session session = jmsConnection.createSession(false,
								Session.AUTO_ACKNOWLEDGE);
						MessageProducer messageProducer = session
								.createProducer(queue);

						// Step 5. Create a Text Message
						ObjectMessage message = session
								.createObjectMessage(transactionMessage);
						// TextMessage message = session.createTextMessage("");

						// Step 6. Send The Text Message
						messageProducer.send(message);
						// TODO: log this
					} finally {
						// Step 12. Be sure to close all resources!
						if (ic != null) {
							ic.close();
						}
						if (jmsConnection != null) {
							jmsConnection.close();
						}
						if (jdbcConnection != null) {
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
	public String initWebService() {
		boolean result = initCaches();
		return result ? "SUCCESS" : "FAILURE";
	}

	/**
	 * Fetch the data for the cache... Do a full initialization. Maybe a
	 * "kernel" could do this in a more centralized way...
	 */
	private boolean initCaches() {
		boolean result = false;

		Cache<String, Long> couponCache = null;
		Cache<Long, FastAccount> accountCache = null;
		boolean openTransaction = false;

		try {
			InitialContext ic = new InitialContext();
			CacheContainer cacheContainer = (CacheContainer) ic
					.lookup(FASTCHECK_JNDI_NAME);
			couponCache = cacheContainer.getCache("coupons");
			accountCache = cacheContainer.getCache("accounts");

			// empty the cache
			couponCache.clear();

			AdvancedCache<Long, FastAccount> advancedCache = accountCache
					.getAdvancedCache();
			TransactionManager accountTransactionManager = advancedCache
					.getTransactionManager();
			accountTransactionManager.begin();
			openTransaction = true;

			// empty the cache
			accountCache.clear();
			// now we will fill the whole cache

			/*
			 * Fetch data from the clearing house
			 */

			// fetch all data from the clearing house...
			int numberAccountsUnread = 0;
			ClientRequest clientRequest = new ClientRequest(
					"http://localhost:8080/clearinghouse/rest/fastcheckInterface/metaData");
			clientRequest.accept("application/json");
			/*
			 * clientRequest .body("application/json", new
			 * TransactionRequest(amount,
			 * Configuration.BankPosName.toString()));
			 */

			ClientResponse<MetadataMessage> fastcheckResponse = clientRequest
					.get();

			// check error code of response
			if (Response.Status.OK
					.equals(fastcheckResponse.getResponseStatus())) {

				MetadataMessage mm = fastcheckResponse
						.getEntity(MetadataMessage.class);

				numberAccountsUnread = mm.getNoOfAccounts();

				int currentAccountIndex = 0;

				int numberAccountsRequested = 0;

				if (numberAccountsUnread > BATCH_SIZE) {
					numberAccountsRequested = BATCH_SIZE;
				} else {
					numberAccountsRequested = numberAccountsUnread;
				}

				while (numberAccountsUnread > 0) {
					clientRequest = new ClientRequest(
							"http://localhost:8080/clearinghouse/rest/fastcheckInterface/accountBatch/"
									+ currentAccountIndex + "/"
									+ numberAccountsRequested);
					clientRequest.accept("application/json");

					ClientResponse<List<TransferAccount>> response = clientRequest
							.get();

					List<TransferAccount> batch = response.getEntity();

					for (TransferAccount transferAccount : batch) {
						ArrayList<FastCoupon> fastCoupons = transferAccount
								.getCoupons();
						FastAccount fastAccount = new FastAccount();

						/*
						 * Fast index for every coupon that is added to the
						 * system... every coupon has a unique code that links
						 * to its user
						 */
						for (FastCoupon fastCoupon : fastCoupons) {
							couponCache.put(fastCoupon.getCouponCode(),
									new Long(fastAccount.getId()));
						}

						fastAccount.addFastCoupons(fastCoupons);
						accountCache.put(new Long(fastAccount.getId()),
								fastAccount);
					}

					if (batch.size() < numberAccountsRequested) {
						// there has been some change in the available accounts
						// number... stop fetching accounts now.
						numberAccountsUnread = 0;
					} else {
						numberAccountsUnread -= numberAccountsRequested;
					}
				}

				/*
				 * Commit the newly initialized caches.
				 */

				accountTransactionManager.commit();
				// couponCache.getAdvancedCache().getTransactionManager().commit();
				openTransaction = false;

				result = true;
			} else {
				accountTransactionManager.commit();
				openTransaction = false;
			}

		} catch (NamingException e) {
			// TODO: logging
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// close all open transactions
			if (accountCache != null && openTransaction) {
				try {
					// rollback only if the transaction has not been closed
					// already
					accountCache.getAdvancedCache().getTransactionManager()
							.rollback();

					// catch all inner exceptions and log them...
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SystemException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return result;
	}

}
