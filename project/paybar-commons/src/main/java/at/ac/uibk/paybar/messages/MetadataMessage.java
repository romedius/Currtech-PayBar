package at.ac.uibk.paybar.messages;

import java.io.Serializable;

public class MetadataMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long noOfAccounts;

	public Long getNoOfAccounts() {
		return noOfAccounts;
	}

	public void setNoOfAccounts(Long long1) {
		this.noOfAccounts = long1;
	}
	

}
