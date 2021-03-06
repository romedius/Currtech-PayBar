package paybar.data;

import java.util.Date;
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

	public void createTransaction(double amount, Date tranactionTime) {
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

	/**
	 * This Method is used to create a transactions where the user account get's
	 * charged. The Ammount must be positive.
	 * */
	public void createChargeTransactionById(long amount, String Message,
			String posId, long id, Date transactionTime)
			throws PaybarResourceException {
		Query query = em
		// SELECT da FROM DetailAccount da, IN (da.coupons) c WHERE :param2 = c
		// SELECT da FROM DetailAccount da, Coupon c WHERE c = :param2 AND c IN
		// (da.coupons)
				.createQuery("SELECT da FROM DetailAccount da WHERE :param2 = da.id");
		query.setParameter("param2", id);
		DetailAccount da = null;
		try {
			da = (DetailAccount) query.getSingleResult();
		} catch (NoResultException e) {
			throw new PersistenceException("No such user");
		}
		createChargeTransaction(amount, Message, posId, da, transactionTime);
	}

	/**
	 * This Method is used to create a transactions where the user account get's
	 * charged. The Ammount must be positive.
	 * */
	public void createChargeTransactionByUsername(long amount, String Message,
			String posId, String username, Date transactionTime)
			throws PaybarResourceException {
		Query query = em
		// SELECT da FROM DetailAccount da, IN (da.coupons) c WHERE :param2 = c
		// SELECT da FROM DetailAccount da, Coupon c WHERE c = :param2 AND c IN
		// (da.coupons)
				.createQuery("SELECT da FROM DetailAccount da WHERE :param2 = da.userName");
		query.setParameter("param2", username);
		DetailAccount da = null;
		try {
			da = (DetailAccount) query.getSingleResult();
		} catch (NoResultException e) {
			throw new PersistenceException("No such user");
		}
		createChargeTransaction(amount, Message, posId, da, transactionTime);
	}

	/**
	 * This Method is used to create a transactions where the user account get's
	 * charged. The Ammount must be positive.
	 * */
	public void createChargeTransaction(long amount, String Message,
			String posId, DetailAccount da, Date transactionTime)
			throws PaybarResourceException {

		Query query = em
				.createQuery("Select p from PointOfSale p where p.name like :param3 ");
		query.setParameter("param3", posId);
		PointOfSale pos = null;
		try {
			pos = (PointOfSale) query.getSingleResult();

		} catch (NoResultException e) {
			throw new PaybarResourceException("Pos does not exists");
		}
		if (amount > 0) {
			long newAmmount = da.getCredit() + amount;
			Transaction tr = new Transaction();
			/* Create transaction */
			tr.setAmount(amount);
			tr.setCoupon(null);
			tr.setDetailAccount(da);
			tr.setPos(pos);
			tr.setTransactionTime(transactionTime);
			/* alter user */
			da.setCredit(newAmmount);
			/* Persist */
			em.persist(tr);
			em.merge(da);
			em.flush();
		} else {
			throw new PaybarResourceException("Charged Amount must be positive");
			// Which means the transaction value must be negative, because Money
			// flows from the Bank to the user.
		}
	}

	/**
	 * This Method is used to create a transactions where the user account get's
	 * debited. The Amount must be positive.
	 * */
	public void createDebitTransaction(long amount, String couponCode,
			String Message, String posId, Date transactionTime)
			throws PaybarResourceException {
		Query query = em
				.createQuery("Select a from Coupon a where a.couponCode like :param");
		query.setParameter("param", couponCode);
		Coupon c = null;
		try {
			c = (Coupon) query.getSingleResult();
		} catch (NoResultException e) {
			throw new PaybarResourceException("Coupon does not exists");
		}
		/* Make transaction */
		query = em
		// SELECT da FROM DetailAccount da, IN (da.coupons) c WHERE :param2 = c
		// SELECT da FROM DetailAccount da, Coupon c WHERE c = :param2 AND c IN
		// (da.coupons)
				.createQuery("SELECT da FROM DetailAccount da, IN (da.coupons) c  WHERE :param2 = c");
		query.setParameter("param2", c);
		DetailAccount da = null;
		try {
			da = (DetailAccount) query.getSingleResult();
		} catch (NoResultException e) {
			throw new PersistenceException("Coupon already used :" + couponCode);
		}
		query = em
				.createQuery("Select p from PointOfSale p where p.name like :param3 ");
		query.setParameter("param3", posId);
		PointOfSale pos = null;
		try {
			pos = (PointOfSale) query.getSingleResult();

		} catch (NoResultException e) {
			throw new PaybarResourceException("Pos does not exists");
		}

		if (amount > 0) {
			if (da.getCredit() > amount) {
				/*
				 * removed due to assumed uselessness if (preTransactionCredit
				 * != null && da.getCredit() >= preTransactionCredit) { // Maybe
				 * there was an update of the amounts due to a charge //
				 * process. In this case the credit of the user is bigger //
				 * than the one stored in the Infinispan cache. throw new
				 * PaybarResourceException(
				 * "Infinispan cache information of user's credit before transaction is not equal to that in the Database"
				 * ); }
				 */
				long newAmmount = da.getCredit() - amount;

				/*
				 * removed due to assumed uselessness if (pastTransactionCredit
				 * != null && newAmmount >= pastTransactionCredit) { throw new
				 * PaybarResourceException(
				 * "Infinispan cache information of user's credit after transaction is not equal to that in the database"
				 * ); }
				 */

				Transaction tr = new Transaction();
				/* Create transaction */
				tr.setAmount(0 - amount);
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
			} else {
				throw new PaybarResourceException("User is out of credit");
			}
		} else {
			throw new PaybarResourceException("Amount must be positive");
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
