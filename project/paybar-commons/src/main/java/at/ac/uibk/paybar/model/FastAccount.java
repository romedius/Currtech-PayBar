package at.ac.uibk.paybar.model;

import java.io.Serializable;

/**
 * This Class is even Used for Storage of Accounts.
 * */
public class FastAccount implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private long credit;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getCredit() {
		return credit;
	}

	public void setCredit(long credit) {
		this.credit = credit;
	}

}
