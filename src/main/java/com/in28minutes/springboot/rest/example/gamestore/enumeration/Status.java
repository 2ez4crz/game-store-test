package com.in28minutes.springboot.rest.example.gamestore.enumeration;

public enum Status {
	SUCCESS("SUCCESS"),
	PENDING("PENDING"),
	FAILED("FAILED"),
	REJECTED("REJECTED");
	private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }   
}
