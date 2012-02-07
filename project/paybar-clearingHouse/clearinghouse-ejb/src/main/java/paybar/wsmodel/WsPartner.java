package paybar.wsmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import paybar.model.Partner;
import paybar.model.PointOfSale;

public class WsPartner implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private String locationHash;

	private String adress;

	private String billingInformation;

	private String userName;

	private String password;

	private long credit;

	private List<Long> pointsOfSaleId;

	public WsPartner() {
	}

	public WsPartner(Partner p) {
		this.adress = p.getAdress();
		this.billingInformation = p.getBillingInformation();
		this.credit = p.getCredit();
		this.id = p.getId();
		this.locationHash = p.getLocationHash();
		this.password = null;
		this.pointsOfSaleId = new ArrayList<Long>();
		for (PointOfSale pos : p.getPointsOfSale()) {
			this.pointsOfSaleId.add(pos.getId());
		}
		this.userName = p.getUserName();
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

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getBillingInformation() {
		return billingInformation;
	}

	public void setBillingInformation(String billingInformation) {
		this.billingInformation = billingInformation;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getCredit() {
		return credit;
	}

	public void setCredit(long credit) {
		this.credit = credit;
	}

	public List<Long> getPointsOfSaleId() {
		return pointsOfSaleId;
	}

	public void setPointsOfSaleId(List<Long> pointsOfSaleId) {
		this.pointsOfSaleId = pointsOfSaleId;
	}

}
