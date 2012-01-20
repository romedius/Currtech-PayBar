package paybar.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class DetailAccountData {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String locationhash;
	private float credit;
	private boolean active;
	private String security;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLocationhash() {
		return locationhash;
	}
	public void setLocationhash(String locationhash) {
		this.locationhash = locationhash;
	}
	public float getCredit() {
		return credit;
	}
	public void setCredit(float credit) {
		this.credit = credit;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getSecurity() {
		return security;
	}
	public void setSecurity(String security) {
		this.security = security;
	}

}
