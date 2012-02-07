package paybar.wsmodel;

import java.io.Serializable;

import paybar.model.PointOfSale;

public class WsPointOfSale implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	private String locationHash;

	private String name;

	public WsPointOfSale() {
	}
	
	public WsPointOfSale(PointOfSale pos) {
		this.id= pos.getId();
		this.locationHash=pos.getLocationHash();
		this.name=pos.getName();		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLocationHash() {
		return locationHash;
	}

	public void setLocationHash(String locationHash) {
		this.locationHash = locationHash;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
