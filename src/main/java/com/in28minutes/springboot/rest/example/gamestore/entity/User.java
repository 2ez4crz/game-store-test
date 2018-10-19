package com.in28minutes.springboot.rest.example.gamestore.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.in28minutes.springboot.rest.example.gamestore.contract.EditUserProfileRequest;
import com.in28minutes.springboot.rest.example.gamestore.contract.SignupRequest;

@Entity
@Table(name = "tb_user")
public class User {
	@Id
	@SequenceGenerator(name="user_id", sequenceName="user_id_seq", allocationSize=1) 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_id")
	private Long id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="username", nullable=false)
	private String username;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@Column(name="birth_date", nullable=false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthDate;
	
	@Column(name="photoProfile")
	private String photoProfile;
	
	@Column(name="registered_date", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date registeredDate;
	
	@Column(name="balance", nullable=false)
	private float balance;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="publisher_id", nullable=true)
	private Publisher publisher;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "game_owned",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    private Set<Game> gameOwned = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
	
	
	
	
	public User() {
		
	}
	public User(SignupRequest input) {
		this.name = input.getName();
		this.username = input.getUsername();
		this.email = input.getEmail();
		this.password = input.getPassword();
		this.birthDate = input.getBirthDate();
	}
	public User(EditUserProfileRequest input) {
		this.name = input.getName();
		this.email = input.getEmail();
		this.birthDate = input.getBirthDate();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	public String getPhotoProfile() {
		return photoProfile;
	}
	public void setPhotoProfile(String photoProfile) {
		this.photoProfile = photoProfile;
	}
	public Set<Game> getGameOwned() {
		return gameOwned;
	}
	public void setGameOwned(Set<Game> gameOwned) {
		this.gameOwned = gameOwned;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public void setDefault() {
		this.registeredDate = new Date();
		this.balance = 0;
		this.photoProfile = ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/user/default.jpg").toUriString();
	}
	public void setEditedProfile(EditUserProfileRequest input) {
		if(input.getName()!=null) {
			this.name = input.getName();
    	}
    	if(input.getBirthDate()!=null) {
    		this.birthDate = input.getBirthDate();
    	}
    	if(input.getEmail()!=null) {
    		this.email = input.getEmail();
    	}
	}
	
}
