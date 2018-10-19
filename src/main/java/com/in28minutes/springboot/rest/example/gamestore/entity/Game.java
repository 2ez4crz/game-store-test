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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.in28minutes.springboot.rest.example.gamestore.contract.GameRequest;

@Entity
@Table(name = "tb_game")
//@JsonView(View.Simple.class)
public class Game {
	@Id
	@SequenceGenerator(name="game_id", sequenceName="game_id_seq", allocationSize=1) 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="game_id")
	private Long id;
	
	@Column(name="game_name", nullable = false)
	private String gameName;
	
	@Column(name="game_description", nullable = false)
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String gameDescription;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="publisher_id", nullable = false)
	private Publisher publisher;
	
	@Column(name="published_date", nullable = false)
	private Date publishedDate;
	
	@Column(name="price", nullable = false)
	private float price;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="rating_code", nullable = false)
	private Rating ratingCode;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "game_genre",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genre = new HashSet<>();
	
	@Column(name="purchased")
	private Long purchased;
	
	@Column(name="is_published")
	private boolean isPublished;
	
	public Game() {
		
	}
	public Game(Game game) {
		this.id = game.getId();
		this.gameName = game.getGameName();
		this.gameDescription = game.getGameDescription();
		this.publisher = game.getPublisher();
		this.price = game.getPrice();
		this.ratingCode = game.getRatingCode();
		this.genre = game.getGenre();
		this.purchased = game.getPurchased();
		this.isPublished = game.isPublished();
	}
	public Game(GameRequest input) {
		this.gameName = input.getGameName();
		this.gameDescription = input.getGameDescription();
		this.price = input.getPrice();
		
	}
	public Game(GameRequest input, Publisher publisher, Rating rating, Set<Genre> genre) {
		this.gameName = input.getGameName();
		this.gameDescription = input.getGameDescription();
		this.publisher = publisher;
		this.publishedDate =new Date();
		this.price = input.getPrice();
		this.ratingCode = rating;
		this.genre =genre;
		this.purchased = (long) 0;
		this.isPublished = true;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGameDescription() {
		return gameDescription;
	}

	public void setGameDescription(String gameDescription) {
		this.gameDescription = gameDescription;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Rating getRatingCode() {
		return ratingCode;
	}

	public void setRatingCode(Rating ratingCode) {
		this.ratingCode = ratingCode;
	}

	public Long getPurchased() {
		return purchased;
	}

	public void setPurchased(Long purchased) {
		this.purchased = purchased;
	}
	
	public Set<Genre> getGenre() {
		return genre;
	}

	public void setGenre(Set<Genre> genre) {
		this.genre = genre;
	}

	public void setDefault() {
		this.publishedDate =new Date();
		this.purchased = (long) 0;
		this.isPublished = true;
	}
	public boolean isPublished() {
		return isPublished;
	}
	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}
	
}
