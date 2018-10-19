package com.in28minutes.springboot.rest.example.gamestore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="game_gallery")
public class GameGallery {
	@Id
	@SequenceGenerator(name="game_gallery_id", sequenceName="game_gallery_id_seq", allocationSize=1) 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="game_gallery_id")
	private Long id;
	@ManyToOne
	@JoinColumn(name="game_id", nullable=false)
	private Game game;
	@Column(name="image_path", nullable=false)
	private String imagePath;
	
	public GameGallery() {
		
	}

	public GameGallery(Long id, Game game, String imagePath) {
		this.id = id;
		this.game = game;
		this.imagePath = imagePath;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}
