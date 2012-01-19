package paybar.model;

public class Partner {

    String id;
    
    String locationHash;
    
    String adress;
    
    String billingInformation;
    
    float credit;

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

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }
    
}
