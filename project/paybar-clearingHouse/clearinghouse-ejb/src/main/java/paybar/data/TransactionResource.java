package paybar.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import paybar.model.Coupon;
import paybar.model.DetailAccount;
import paybar.model.PointOfSale;
import paybar.model.Transaction;

@Stateless
public class TransactionResource {

	@PersistenceContext(name = "primary")
	private EntityManager em;

	public void createTransaction(double amount, long tranactionTime) {
		Transaction transactionNew = new Transaction();
		transactionNew.setAmount(amount);
		transactionNew.setTransactionTime(tranactionTime);
		

		em.persist(transactionNew);
		em.flush();
		// log.info("Successful persisted new transaction!");

	}

	public void createTransaction(Transaction transactionNew) {
		em.persist(transactionNew);
		em.flush();
	}

	public void createTransactionWithCoupon(long ammount, String couponCode,
			String Message, String posId, long transactionTime, Long preTransactionCredit, Long pastTransactionCredit) throws PersistenceException {
		Query query = em
				.createQuery("Select a from Coupon a where a.couponCode like :param");
		query.setParameter("param", couponCode);
		Coupon c = null;
		try {
			c = (Coupon) query.getSingleResult();
		} catch (NoResultException e) {
			throw new PersistenceException("Coupon does not exists");
		}
		/* Make transaction */
		query = em
				.createQuery("SELECT da FROM DetailAccount da WHERE :param2 in (da.coupons) ");
		query.setParameter("param2", c);
		DetailAccount da = null;
		try {
			da = (DetailAccount) query.getSingleResult();
		} catch (NoResultException e) {
			throw new PersistenceException("Coupon already used");
		}
		query = em
				.createQuery("Select pos from PointOfSale p where p.name like :param3 ");
		query.setParameter("param3", posId);
		PointOfSale pos = null;
		try {
			pos = (PointOfSale) query.getSingleResult();

		} catch (NoResultException e) {
			throw new PersistenceException("Pos does not exists");
		}
		if (da.getCredit() < ammount) {
			if (preTransactionCredit!=null && da.getCredit()!=preTransactionCredit) {
				throw new PersistenceException("Infinispan cache information of user's credit before transaction is not equal to that in the Database");
			}
			long newAmmount = da.getCredit() - ammount;
			if (preTransactionCredit!=null && newAmmount!=preTransactionCredit) {
				throw new PersistenceException("Infinispan cache information of user's credit after transaction is not equal to that in the database");
			}

			Transaction tr = new Transaction();
			/* Create transaction */
			tr.setAmount(ammount);
			tr.setCoupon(c);
			tr.setDetailAccount(da);
			tr.setPos(pos);
			tr.setTransactionTime(transactionTime);
			/* alter user */
			da.setCredit(newAmmount);
			da.getCoupons().remove(c);
			/* Persist */
			em.persist(tr);
			em.merge(da);
			em.flush();
		}
		else
		{
			throw new PersistenceException("User is out of credit");
		}
	}

	public List<Transaction> getTransactionsByUsername(String name) {
		try {
			Query query = em.createNamedQuery("getTransactionsByUserName");
			query.setParameter(1, name);
			@SuppressWarnings("unchecked")
			List<Transaction> result = (List<Transaction>) query
					.getResultList();
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
			List<Transaction> result = (List<Transaction>) query
					.getResultList();
			return result;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}

}
