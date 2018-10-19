package com.in28minutes.springboot.rest.example.gamestore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.in28minutes.springboot.rest.example.gamestore.enumeration.Status;

@Entity
@Table(name = "user_activity")
public class UserActivity {
	@Id
	@SequenceGenerator(name="user_activity_id", sequenceName="user_activity_id_seq", allocationSize=1) 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_activity_id")
	private Long id;
	
	@Column(name="date", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
    @Column(name="username", nullable=false)
	private String username;
	
	@Column(name="activity", nullable=false)
	private String activity;
	
	@Column(name="status", nullable=false)
	@Enumerated(EnumType.STRING)
	private Status status;

	public UserActivity() {

	}

	public UserActivity(String username, String activity, Status status) {
		this.date = new Date();
		this.username = username;
		this.activity = activity;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUsername() {
		return username;
	}

	public void setUser(String username) {
		this.username = username;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
}
