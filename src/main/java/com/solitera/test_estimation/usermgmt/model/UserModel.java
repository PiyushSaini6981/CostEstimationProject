package com.solitera.test_estimation.usermgmt.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String name;
	@Column(unique = true)
	private String email;
	@Column
	private String password;
	@Column
	private String companyName;
	@Column
	private String country;
	@Column
	private String phoneNumber;
	@Column
	private String token;
	@Column
	private String otp;
	@Column
	private Boolean emailVarified;
	@Column
	private Timestamp timestamp;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	public UserModel(String name, String email, String password, String companyName, String country, String phoneNumber,
			String token) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.companyName = companyName;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.token = token;
	}
	
	public void setTimestamp(long currentTimeMillis) {
	    Timestamp timestamp = new Timestamp(currentTimeMillis);
	    this.timestamp = timestamp;
	}
	public long getTimestamp() {
	    if (this.timestamp != null) {
	        return this.timestamp.getTime();
	    } else {
	        return 0;
	    }
	}
}
