package paybar.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class PointOfSale implements Serializable {

	public PointOfSale(String locationHash, String name) {
		super();
		this.locationHash = locationHash;
		this.name = name;
	}

	public PointOfSale() {
		super();
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	long id;

	@NotNull
	@NotEmpty
	String locationHash;

	@NotNull
	@NotEmpty
	String name;

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
