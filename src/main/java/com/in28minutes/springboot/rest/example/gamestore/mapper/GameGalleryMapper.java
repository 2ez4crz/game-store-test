package com.in28minutes.springboot.rest.example.gamestore.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.in28minutes.springboot.rest.example.gamestore.contract.ImagePathResponse;
import com.in28minutes.springboot.rest.example.gamestore.entity.GameGallery;

@Component
public class GameGalleryMapper {
	public static List<ImagePathResponse> ListGameGalleryTOListImagePathResponse(List<GameGallery> gg){
		List<ImagePathResponse> result = new ArrayList<ImagePathResponse>();
		for(int i =0; i<gg.size();i++) {
			GameGallery gameGallery = gg.get(i);
			ImagePathResponse imagePath = new ImagePathResponse(gameGallery.getImagePath());
			result.add(imagePath);
		}
		return result;
	}
}
