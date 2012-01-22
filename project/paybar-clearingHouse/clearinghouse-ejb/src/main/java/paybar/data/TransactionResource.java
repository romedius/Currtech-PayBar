package paybar.data;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import paybar.model.Transaction;

@Stateless
public class TransactionResource {

	@PersistenceContext(name = "primary")
	private EntityManager em;

	public void createTransaction(double amount, String locationHash,
			long tranactionTime) {
		Transaction transactionNew = new Transaction();
		transactionNew.setAmount(amount);
		transactionNew.setLocationHash(locationHash);
		transactionNew.setTransactionTime(tranactionTime);

		em.persist(transactionNew);
		em.flush();
		// log.info("Successful persisted new transaction!");

	}
}
