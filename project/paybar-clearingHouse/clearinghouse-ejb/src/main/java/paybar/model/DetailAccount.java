package paybar.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class DetailAccount implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	@NotNull
	@OneToOne(optional = false)
	private Account account;

	/**
	 * Boolean Value to block a user. Blocked useres will not be loaded into the fastcheck-infinispan instances. 
	 * */
	private boolean active;

	private String adress;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 25)
	@Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
	private String firstName;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 25)
	@Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
	private String sureName;


	@NotNull
	@NotEmpty
	@Size(min = 6, max = 25)
	private String password;

	private String PhoneNumber;	

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 25)
	private String userName;


	/**
	 * This Value is used for the Infinispan cache to Attach an account to a certain infinispan Group
	 * */
	@NotNull
	@NotEmpty
	private String locationHash;

	private String moveToLocationHash;

	public Account getAccount() {
		return account;
	}

	public String getAdress() {
		return adress;
	}

	public String getFirstName() {
		return firstName;
	}

	public long getId() {
		return id;
	}

	public String getLocationHash() {
		return locationHash;
	}

	public String getMoveToLocationHash() {
		return moveToLocationHash;
	}

	public String getPassword() {
		return password;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public String getSureName() {
		return sureName;
	}

	public String getUserName() {
		return userName;
	}

	public boolean isActive() {
		return active;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLocationHash(String locationHash) {
		this.locationHash = locationHash;
	}

	public void setMoveToLocationHash(String moveToLocationHash) {
		this.moveToLocationHash = moveToLocationHash;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public void setSureName(String sureName) {
		this.sureName = sureName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


}
