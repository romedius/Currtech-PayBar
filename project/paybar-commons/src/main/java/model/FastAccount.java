package model;

import java.io.Serializable;

public class FastAccount implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	
	/**
	 * Credit of an user in cents.
	 * */
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
