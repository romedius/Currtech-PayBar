package paybar.rest;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

@MessageDriven(name = "DelayedTransactionProcessor", messageListenerInterface = MessageListener.class, activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/test"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class DelayedTransactionProcessor implements MessageListener {

	// maybe this is sub-optimal...
	private static final Logger log = Logger
			.getLogger(DelayedTransactionProcessor.class.getName());

	public void onMessage(Message message) {
		Connection connection = null;
		try {
			// it's a Tachyon Beam Message ;-)
			ObjectMessage textMessage = (ObjectMessage) message;
			TransactionMessage transactionMessage = (TransactionMessage) textMessage
					.getObject();

			String text = "TransactionProcessor: posId: "
					+ transactionMessage.getPosId() + ", tanCode: "
					+ transactionMessage.getTanCode() + ", amount: "
					+ transactionMessage.getTanCode() + ", timestamp: "
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
