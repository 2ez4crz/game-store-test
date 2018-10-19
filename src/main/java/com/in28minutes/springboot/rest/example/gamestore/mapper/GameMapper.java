package com.in28minutes.springboot.rest.example.gamestore.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.in28minutes.springboot.rest.example.gamestore.contract.GameResponse;
import com.in28minutes.springboot.rest.example.gamestore.entity.Game;
@Component
public class GameMapper {
	public static List<GameResponse> ListGameToListGameResponse(List<Game> games){
		List<GameResponse> listGames = new ArrayList<GameResponse>();
		for(int i =0; i<games.size();i++) {
			GameResponse gameResponse = new GameResponse(games.get(i));
			listGames.add(gameResponse);
		}
		return listGames;
	}
}
