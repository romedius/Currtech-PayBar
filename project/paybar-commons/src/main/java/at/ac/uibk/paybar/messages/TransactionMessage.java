package at.ac.uibk.paybar.messages;

import java.io.Serializable;

public class TransactionMessage implements Serializable {

	/**
	 * Version number used by JVM for serialization purposes.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Designates loading money on to a user's account (putting money on to his
	 * account from the credit card).
	 */
	public static final int TYPE_CHARGE = 1;

	/**
	 * Designates removing money from a user's account (sending the money to a
	 * partner company, e.g. when the customer buys something at a pos).
	 */
	public static final int TYPE_TRANSACTION = -1;

	/**
	 * The amount to be transferred.
	 */
	private long amount;

	/**
	 * Account status as seen by FastCheck after the transaction has been
	 * placed. Has definitely some problems with messages worked not in
	 * sequence.
	 */
	private long pastTransactionCredit;

	private String posOrBankId;

	// additional verificaton information

	/**
	 * The tancode is the coupon's security code used as a security token for
	 * the transaction of removing money from the account.
	 */
	private String couponCode;

	/**
	 * The timestamp holds the point of time the transaction when it has been
	 * processed by FastCheck.
	 */
	private long timestamp;

	/**
	 * Type may be either {@link #TYPE_TRANSACTION} or {@value #TYPE_CHARGE}.
	 * Designates the transaction's purpose of either loading money onto the
	 * account or removing money from the account.
	 */
	private int type = 0;

	/**
	 * The username by which the user is known to the system.
	 */
	private String userName;

	public TransactionMessage() {

	}

	public TransactionMessage(int type, String posOrBankId, long amount,
			long timestamp, String userName, String tanCode) {
		this.type = type;
		this.couponCode = tanCode;
		this.posOrBankId = posOrBankId;
		this.amount = amount;
		this.timestamp = timestamp;
		this.userName = userName;
	}

	public long getAmount() {
		return amount;
	}

	public String getPosOrBankId() {
		return posOrBankId;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public int getType() {
		return type;
	}

	public String getUserName() {
		return userName;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public void setPosOrBankId(String posOrBankId) {
		this.posOrBankId = posOrBankId;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
