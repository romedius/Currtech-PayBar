package paybar.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Partner {

	@Id
    long id;
    
	@NotNull
    String locationHash;
    
	@NotNull
    String adress;
    
	@NotNull
    String billingInformation;
    
    double credit;

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

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }
    
}
