package paybar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class PointOfSale {

	@Id
	@GeneratedValue
    double id;
    
	@NotNull
	@NotEmpty
    String locationHash;

	@NotNull
	@NotEmpty
    String name;

    public double getId() {
        return id;
    }

    public void setId(double id) {
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
