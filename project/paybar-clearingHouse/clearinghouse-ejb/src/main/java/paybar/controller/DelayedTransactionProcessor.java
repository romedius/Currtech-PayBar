package paybar.controller;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;

import paybar.model.Transaction;


@MessageDriven(name = "DelayedTransactionProcessor", messageListenerInterface = MessageListener.class, activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/test"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class DelayedTransactionProcessor implements MessageListener {

	// maybe this is sub-optimal...
	private static final Logger log = Logger
			.getLogger(DelayedTransactionProcessor.class.getName());
	
	@Inject
	private EntityManager em;

	public void onMessage(Message message) {
		Connection connection = null;
		try {
			// it's a Tachyon Beam Message ;-)
			ObjectMessage textMessage = (ObjectMessage) message;
			at.ac.uibk.paybar.TransactionMessage transactionMessage = (at.ac.uibk.paybar.TransactionMessage) textMessage
					.getObject();

			String text = "TransactionProcessor: posId: "
					+ transactionMessage.getPosId() + ", tanCode: "
					+ transactionMessage.getTanCode() + ", amount: "
					+ transactionMessage.getAmount() + ", timestamp: "
					+ transactionMessage.getTimestamp();

			log.info(text);

			/*
			 * connection = connectionFactory.createConnection();
			 * 
			 * System.out.println("connection created");
			 * 
			 * 
			 * Session session = connection.createSession(false,
			 * Session.AUTO_ACKNOWLEDGE);
			 * 
			 * System.out.println("session created");
			 * 
			 * MessageProducer producer = session.createProducer(replyQueue);
			 * 
			 * System.out.println("sending reply");
			 * 
			 * producer.send(session.createTextMessage("here is a reply"));
			 * 
			 * System.out.println("reply sent");
			 */
			// TODO: persist and log successful persistence
			
			Transaction transactionNew = new Transaction();
			transactionNew.setAmount(transactionMessage.getAmount());
			transactionNew.setLocationHash("blabla!");
			transactionNew.setTransactionTime(transactionMessage.getTimestamp());
			
			em.persist(transactionNew);
			em.flush();
			log.info("Successful persisted new transaction!");
			
			// TODO: move this to the clearing house project

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace(); // To change body of catch statement
											// use File | Settings | File
											// Templates.
				}
			}
		}
	}
}
