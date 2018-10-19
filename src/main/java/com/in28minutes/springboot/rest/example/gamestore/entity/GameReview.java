package com.in28minutes.springboot.rest.example.gamestore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.in28minutes.springboot.rest.example.gamestore.contract.GameReviewRequest;

@Entity
@Table(name="game_review")
public class GameReview {
	@Id
	@SequenceGenerator(name="game_review", sequenceName="game_review_id_seq", allocationSize=1) 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="game_review")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="game_id", nullable=false)
	private Game game;

	@Column(name="posted_date", nullable=false)
	private Date date;
	
	@Column(name="score", nullable=false)
	private int score;
	
	@Column(name="review")
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String review;
	
	public GameReview() {
		
	}
	public GameReview(GameReviewRequest input) {
		this.score = input.getScore();
		this.review = input.getReview();
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@JsonIgnore
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	@JsonIgnore
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Date getDate() {
		return date;
	}

	public void setDate() {
		this.date = new Date();
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
	
	
}
