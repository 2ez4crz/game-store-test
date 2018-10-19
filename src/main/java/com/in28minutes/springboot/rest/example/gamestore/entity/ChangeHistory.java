package com.in28minutes.springboot.rest.example.gamestore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="change_history")
public class ChangeHistory {
	@Id
	@SequenceGenerator(name="user_activity_id", sequenceName="user_activity_id_seq", allocationSize=1) 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_activity_id")
	private Long id;
	
	@Column(name="modified_table", nullable=false)
	private String table;
	
	@Column(name="modified_column", nullable=false)
	private String column;
	
	@Column(name="modified_at", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@ManyToOne
    @JoinColumn(name="modified_by", nullable=false)
	private User user;
    
    @Column(name="before_change", nullable=true)
    private String before;
    
    @Column(name="after_change", nullable=false)
    private String after;
    
    @Column(name="description", nullable=true)
    private String description;

	public ChangeHistory() {

	}

	public ChangeHistory(String table, String column, User user, String before, String after,
			String description) {
		this.table = table;
		this.column = column;
		this.date = new Date();
		this.user = user;
		this.before = before;
		this.after = after;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBefore() {
		return before;
	}

	public void setBefore(String before) {
		this.before = before;
	}

	public String getAfter() {
		return after;
	}

	public void setAfter(String after) {
		this.after = after;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
    
}
