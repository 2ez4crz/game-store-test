package com.in28minutes.springboot.rest.example.gamestore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.in28minutes.springboot.rest.example.gamestore.contract.GenreRequest;

@Entity
@Table(name = "tb_genre")
public class Genre {
	@Id
	@SequenceGenerator(name="genre_id", sequenceName="genre_id_seq", allocationSize=1) 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="genre_id")
	private Long id;
	
	@Column(name="genre_name", nullable = false)
	private String genreName;
	
	@Column(name="description")
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String description;
	
	public Genre() {
		
	}
	public Genre(GenreRequest input) {
			this.genreName = input.getGenreName();
			this.description = input.getDescription();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
