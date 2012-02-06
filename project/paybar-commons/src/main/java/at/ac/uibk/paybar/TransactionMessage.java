package at.ac.uibk.paybar;

import java.io.Serializable;

public class TransactionMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long amount;
	private String posId;
	private String tanCode;
	private long timestamp;

	// additional verificaton information
	private long preTransactionCredit;
	private long pastTransactionCredit;

	public TransactionMessage() {
		
	}
	
	public TransactionMessage(String tanCode, String posId, long amount,
			long oldCredit, long newCredit, long timestamp) {
		this.tanCode = tanCode;
		this.posId = posId;
		this.amount = amount;
		this.timestamp = timestamp;
		this.preTransactionCredit = oldCredit;
		this.pastTransactionCredit = newCredit;
	}

	public long getAmount() {
		return amount;
	}

	public long getPastTransactionCredit() {
		return pastTransactionCredit;
	}

	public String getPosId() {
		return posId;
	}

	public long getPreTransactionCredit() {
		return preTransactionCredit;
	}

	public String getTanCode() {
		return tanCode;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public void setPastTransactionCredit(long pastTransactionCredit) {
		this.pastTransactionCredit = pastTransactionCredit;
	}

	public void setPosId(String posId) {
		this.posId = posId;
	}

	public void setPreTransactionCredit(long preTransactionCredit) {
		this.preTransactionCredit = preTransactionCredit;
	}

	public void setTanCode(String tanCode) {
		this.tanCode = tanCode;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
