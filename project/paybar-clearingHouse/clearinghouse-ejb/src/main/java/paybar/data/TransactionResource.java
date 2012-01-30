package paybar.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

	public void createTransaction(Transaction transactionNew) {
		em.persist(transactionNew);
		em.flush();
	}
	
	public List<Transaction> getTransactionsByUsername(String name) {
		try {
			Query query = em.createNamedQuery("getTransactionsByUserName");
			query.setParameter(1, name);
			@SuppressWarnings("unchecked")
			List<Transaction> result = (List<Transaction>) query.getResultList();
			return result;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
	
	public List<Transaction> getTransactionsByCompanyName(String name) {
		try {
			Query query = em.createNamedQuery("getTransactionsByCompanyName");
			query.setParameter(1, name);
			@SuppressWarnings("unchecked")
			List<Transaction> result = (List<Transaction>) query.getResultList();
			return result;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
	
	
	//public Transaction getTransactio
}
