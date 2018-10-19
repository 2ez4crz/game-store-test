package com.in28minutes.springboot.rest.example.gamestore.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.in28minutes.springboot.rest.example.gamestore.contract.PublisherResponse;
import com.in28minutes.springboot.rest.example.gamestore.entity.Publisher;

@Component
public class PublisherMapper {
	public List<PublisherResponse> ListPublisherToListPublisherResponse(List<Publisher> publisher) {
		List<PublisherResponse> result = new ArrayList<PublisherResponse>();
		for(int i =0; i<publisher.size();i++){
			PublisherResponse newPublisher = new PublisherResponse(publisher.get(i));
			result.add(newPublisher);
		}
		return result;
	}
}
