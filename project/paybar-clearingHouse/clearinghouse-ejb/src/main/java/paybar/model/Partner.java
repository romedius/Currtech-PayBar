package paybar.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@NamedQueries({
		@NamedQuery(name = "getPartnerByName", query = "Select p FROM Partner p WHERE p.userName = ?1"),
		@NamedQuery(name = "getPartnerByPosName", query = "Select p FROM Partner p, IN (p.pointsOfSale) ps WHERE ps.name = ?1") })
public class Partner implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	long id;

	@NotNull
	@NotEmpty
	String locationHash;

	@NotNull
	@NotEmpty
	String adress;

	@NotNull
	@NotEmpty
	String billingInformation;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 25)
	private String userName;

	@NotNull
	@NotEmpty
	@Size(min = 6, max = 25)
	private String password;

	long credit;

	/**
	 * This is the list of PointsOfSale of a Partner.
	 * */
	@OneToMany
	private List<PointOfSale> pointsOfSale;

	public List<PointOfSale> getPointsOfSale() {
		return pointsOfSale;
	}

	public void setPointsOfSale(List<PointOfSale> pointsOfSale) {
		this.pointsOfSale = pointsOfSale;
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

	public long getCredit() {
		return credit;
	}

	public void setCredit(long credit) {
		this.credit = credit;
	}

}
