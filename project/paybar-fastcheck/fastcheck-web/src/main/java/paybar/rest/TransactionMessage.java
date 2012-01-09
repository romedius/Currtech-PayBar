package paybar.rest;

import java.io.Serializable;

public class TransactionMessage implements Serializable {

	private double amount;
	private String posId;
	private String tanCode;
	private long timestamp;

	// additional verificaton information
	private double preTransactionCredit;
	private double pastTransactionCredit;

	public TransactionMessage() {
		
	}
	
	public TransactionMessage(String tanCode, String posId, double amount,
			double oldCredit, double newCredit, long timestamp) {
		this.tanCode = tanCode;
		this.posId = posId;
		this.amount = amount;
		this.timestamp = timestamp;
		this.preTransactionCredit = oldCredit;
		this.pastTransactionCredit = newCredit;
	}

	public double getAmount() {
		return amount;
	}

	public double getPastTransactionCredit() {
		return pastTransactionCredit;
	}

	public String getPosId() {
		return posId;
	}

	public double getPreTransactionCredit() {
		return preTransactionCredit;
	}

	public String getTanCode() {
		return tanCode;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setPastTransactionCredit(double pastTransactionCredit) {
		this.pastTransactionCredit = pastTransactionCredit;
	}

	public void setPosId(String posId) {
		this.posId = posId;
	}

	public void setPreTransactionCredit(double preTransactionCredit) {
		this.preTransactionCredit = preTransactionCredit;
	}

	public void setTanCode(String tanCode) {
		this.tanCode = tanCode;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
