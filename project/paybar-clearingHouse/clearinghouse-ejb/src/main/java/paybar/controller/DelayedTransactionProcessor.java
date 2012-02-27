package paybar.controller;

import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import paybar.data.CouponResource;
import paybar.data.TransactionResource;


@MessageDriven(name = "DelayedTransactionProcessor", messageListenerInterface = MessageListener.class, activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/test"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class DelayedTransactionProcessor implements MessageListener {
	
	@Inject
	private TransactionResource tr;
	
	@Inject
	private CouponResource cr;

	// maybe this is sub-optimal...
	private static final Logger log = Logger
			.getLogger(DelayedTransactionProcessor.class.getName());


	public void onMessage(Message message) {
		Connection connection = null;
		try {
			ObjectMessage textMessage = (ObjectMessage) message;
			at.ac.uibk.paybar.TransactionMessage transactionMessage = (at.ac.uibk.paybar.TransactionMessage) textMessage
					.getObject();

			String text = "TransactionProcessor: posId: "
					+ transactionMessage.getPosId() + ", tanCode: "
					+ transactionMessage.getTanCode() + ", amount: "
					+ transactionMessage.getAmount() + ", timestamp: "
					+ transactionMessage.getTimestamp();

			log.info(text);
			if (cr.isValidCoupon(transactionMessage.getTanCode())){
				tr.createDebitTransaction(transactionMessage.getAmount(), transactionMessage.getTanCode(), text, transactionMessage.getPosId(), new Date(transactionMessage.getTimestamp()),transactionMessage.getPreTransactionCredit(), transactionMessage.getPastTransactionCredit());
			}  
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
