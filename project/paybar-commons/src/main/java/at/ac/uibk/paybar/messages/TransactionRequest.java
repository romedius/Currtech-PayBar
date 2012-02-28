package at.ac.uibk.paybar.messages;

/**
 * This is the kind of request used for communicating with FastCheck. Objects of
 * this type will be posted to FastCheck webservices.
 * 
 * @author wolfi
 * 
 */
public class TransactionRequest {

	public TransactionRequest() {
		super();
	}
	
	public TransactionRequest(long amount, String posOrBankId) {
		super();
		this.amount = amount;
		this.posOrBankId = posOrBankId;
	}

	private long amount;
	private String posOrBankId;

	public long getAmount() {
		return amount;
	}

	public String getPosOrBankId() {
		return posOrBankId;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public void setPosOrBankId(String posOrBankId) {
		this.posOrBankId = posOrBankId;
	}

}
