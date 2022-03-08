package com.example.springboot.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "customers_detail")
public class CustomerModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "firstname")
	@NotNull
	@Size(min = 2, max = 70, message = "FirstName should be at least 2 or less than 70 characters long")
	private String firstName;

	@Column(name = "lastname")
	@NotNull
	@Size(min = 2, max = 70, message = "LastName should be at least 2 or less than 70 characters long")
	private String lastName;

	@Column(name = "dateofbirth")
	@NotNull
	private Date dateOfBirth;

	@Column(name = "mobile")
	@NotNull
	@Size(min = 10, max = 17, message = "Number should have at least 10 or less than 17 digits")
	private String mobile;

	@Column(name = "address_one")
	@NotEmpty(message = "Address is must")
	@Size(max = 70, message = "Character value should be less than 70")
	private String addressOne;

	@Size(max = 70, message = "Character value should be less than 70")
	@Column(name = "address_two")
	private String addressTwo;

	@NotNull
	@Column(name = "age")
	private int age;

	@NotNull
	@Column(name = "gender", columnDefinition = "TINYINT NOT NULL COMMENT '1 for Male, 2 for Female' ", length = 1)
	private int gender;

	@Email
	@NotEmpty(message = "Email id is must")
	@Size(max = 70, message = "Please enter a value less than 255 for the email address")
	@Column(name = "email")
	private String email;

	public CustomerModel() {
	}

}