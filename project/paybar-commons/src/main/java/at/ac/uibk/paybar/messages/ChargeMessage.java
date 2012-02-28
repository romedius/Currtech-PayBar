package at.ac.uibk.paybar.messages;

import java.io.Serializable;

public class ChargeMessage implements Serializable {
	

	private static final long serialVersionUID = 1L;
 
	private long userId;
	private long diffamount;
	private long newAmount;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getDiffamount() {
		return diffamount;
	}
	public void setDiffamount(long diffamount) {
		this.diffamount = diffamount;
	}
	public long getNewAmount() {
		return newAmount;
	}
	public void setNewAmount(long newAmount) {
		this.newAmount = newAmount;
	}

}
