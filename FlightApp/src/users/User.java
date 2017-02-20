package users;

import java.io.Serializable;

/**
 * 
 * A representation of an user
 * @author Vicky
 *
 */
public abstract class User implements Serializable {
	
	/**
	 * A serial ID used for serialization.
	 */
	private static final long serialVersionUID = -4861823781184998838L;

	/** The user's personal information */
	protected String firstName, lastName, email, address;
	
	/** The user's financial information */
	protected String creditNum, creditProvider, creditExpiry;
	
	/** The user's log in information */
	protected String username, password;
	
	/**
	 * Generates an user with the given last name, first name, 
	 * email, address, credit card number, credit card expire date, username,
	 * and password.
	 * @param firstName first name of the user
	 * @param lastName last name of the user
	 * @param email email of the user
	 * @param address home address of the user
	 * @param creditNum credit card the user is using to pay
	 * @param creditExpiry date of when the credit card expires in YYYY-MM-DD
	 * @param username the log id of the user
	 * @param password id password that lets the user access the account
	 */
	public User (String lastName, String firstName, String email, 
			String address,String creditNum, String creditExpiry, 
			String username, String password){
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.creditNum = creditNum;
		this.creditExpiry = creditExpiry;
		this.username = username;
		this.password = password;

	}
	
	/**
	/**
	 * Generates an user with the given last name, first name, 
	 * email, address, credit card number, credit card expire date.
	 * 
	 * @param firstName first name of the user
	 * @param lastName last name of the user
	 * @param email email of the user
	 * @param address home address of the user
	 * @param creditNum credit card the user is using to pay
	 * @param creditExpiry date of when the credit card expires in YYYY-MM-DD
	 */
	public User (String lastName, String firstName, String email, 
			String address,String creditNum, String creditExpiry){
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.creditNum = creditNum;
		this.creditExpiry = creditExpiry;
		this.username = "guest123";
		this.password = "1111";

	}
	
	/**
	 * Changes one of the personal info of the user
	 * @param editInfo the string that decides which parameter is changed
	 * @param value what the new value of the parameter is
	 */
	public void editPersonal(String editInfo, String value){
		if(editInfo == "firstName"){
			setFirstName(value);
		}
		else if(editInfo == "lastName"){
			setLastName(value);
		}
		else if(editInfo == "email"){
			setEmail(value);
		}
		else if(editInfo == "address"){
			setAddress(value);
		}
		else if(editInfo == "password"){
			setPassword(value);
		}

	}
	
	
	public String toString(){
		return lastName + "," + firstName + "," + email + "," + address;
				
	}
	
	/**
	 * Returns the first name of the user
	 * @return the first name of the user
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the user
	 * @param firstName the new first name of the user
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the last name of the user
	 * @return the last name of the user
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the new last name of the user
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns the email of the user
	 * @return the email of the user
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the new email 
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns the address of this user
	 * @return address of user
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the new address of user
	 * @param address new address of user
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Returns the credit number of the user
	 * @return credit number of user
	 */
	public String getCreditNum() {
		return creditNum;
	}

	/**
	 * Sets the new credit number of user
	 * @param creditNum new credit number
	 */
	public void setCreditNum(String creditNum) {
		this.creditNum = creditNum;
	}


	/**
	 * Returns the credit expiry date of the current credit card
	 * @return credit expiry date
	 */
	public String getCreditExpiry() {
		return creditExpiry;
	}

	/**
	 * Sets the new credit expiry date
	 * @param creditExpiry new credit expiry date
	 */
	public void setCreditExpiry(String creditExpiry) {
		this.creditExpiry = creditExpiry;
	}

	/**
	 * Returns the password of the user
	 * @return password of user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the new password of user
	 * @param password new password for user
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the user name of this User.
	 * @return the user name of this User
	 */
	public String getUsername() {
		return username;
	}
	
		
	}