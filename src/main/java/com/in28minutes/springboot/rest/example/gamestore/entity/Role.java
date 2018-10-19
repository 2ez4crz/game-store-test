package com.in28minutes.springboot.rest.example.gamestore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tb_role")
public class Role {
	@Id
	@SequenceGenerator(name="role_id", sequenceName="role_id_seq", allocationSize=1) 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="role_id")
	private int id;
	
	@Column(name="role_name", nullable=false)
	private String roleName;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

//	public RoleName getRoleName() {
//		return roleName;
//	}
//
//	public void setRoleName(RoleName roleName) {
//		this.roleName = roleName;
//	}

	
}
