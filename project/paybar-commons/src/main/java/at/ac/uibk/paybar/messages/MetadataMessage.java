package at.ac.uibk.paybar.messages;

import java.io.Serializable;

public class MetadataMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer noOfAccounts;

	public Integer getNoOfAccounts() {
		return noOfAccounts;
	}

	public void setNoOfAccounts(Integer noOfAccounts) {
		this.noOfAccounts = noOfAccounts;
	}
	

}
