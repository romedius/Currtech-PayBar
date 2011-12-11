package at.ac.uibk.paybar;

public class Account {
	private String id;
	private String locationHash;
	private float credit;
	private boolean active;
	private String securityKey;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLocationHash() {
		return locationHash;
	}
	public void setLocationHash(String locationHash) {
		this.locationHash = locationHash;
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
	public String getSecurityKey() {
		return securityKey;
	}
	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
	}
	
}
