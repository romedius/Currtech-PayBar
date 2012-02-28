package at.ac.uibk.paybar.model;

public class StoredCoupon extends FastCoupon {
	
	private static final long serialVersionUID = 1L;

	private FastAccount fastAccount;

	public FastAccount getFastAccount() {
		return fastAccount;
	}

	public void setFastAccount(FastAccount fastAccount) {
		this.fastAccount = fastAccount;
	}

}
