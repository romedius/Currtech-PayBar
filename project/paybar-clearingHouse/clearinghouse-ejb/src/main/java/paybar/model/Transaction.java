package paybar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Transaction {
    
	public void setId(Long id) {
		this.id = id;
	}

	public void setLocationHash(String locationHash) {
		this.locationHash = locationHash;
	}

	public void setTransactionTime(long transactionTime) {
		this.transactionTime = transactionTime;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Id
	@GeneratedValue
    Long id;
    
    String locationHash;
    
    long transactionTime;
    
    double amount;

    public Long getId() {
        return id;
    }

    public String getLocationHash() {
        return locationHash;
    }

    public long getTransactionTime() {
        return transactionTime;
    }

    public double getAmount() {
        return amount;
    }
    

}
