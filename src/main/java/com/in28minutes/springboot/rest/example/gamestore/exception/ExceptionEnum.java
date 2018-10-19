package com.in28minutes.springboot.rest.example.gamestore.exception;

public enum ExceptionEnum {
	//general error
	MUST_NOT_NULL("ERR-001", "Parameter must not be null"),
	CLASS_NOT_FOUND("ERR-002","Not found"),
	INVALID_INPUT("ERR-003","Invalid Input"),
	//user account error
	UNAUTHORIZED("ACC-000","Authentication required"),
	USER_ALREADY_BECOME_PUBLISHER("ACC-001","User already registered as publisher"),
	PAYMENT_REQUIRED("ACC-002","Need to purchase to do this action"),
	PASSWORD_MISMATCH("ACC-003","Pasword did not match"),
	ACCESS_DENIED("ACC-004", "User has no right to access this"),
	AGE_RESTRICTED("ACC-005", "User doesn't meet minimum age requirement to access this"),
	USERNAME_ALREADY_EXIST("ACC-006", "Username already exist"),
	EMAIL_ALREADY_EXIST("ACC-007", "Email already exist"),
	//transaction error
	INVALID_TRANSACTION_AMOUNT("TRN-001","Invalid transaction amount"),
	INSUFFICIENT_BALANCE("TRN-002","Insufficient balance for transaction request"),
	GAME_ALREADY_PURCHASED("TRN-003", "Game already purchased"),
	VOUCHER_ALREADY_USED("TRN-004","Voucher code already used"),
	INVALID_VOUCHER_CODE("TRN-005","Invalid voucher code"),
	GAME_IS_NOT_PUBLISHED("TRN-006", "Game is not published"),
	YOU_ARE_THE_PUBLISHER("TRN-007", "User are the publisher of this game"),
	VOUCHER_ALREADY_EXIST("TRN-008", "Voucher already exist"),
	BANK_ACCOUNT_NEEDED("TRN-009", "Bank account needed to do this action"),
	BANK_ACCOUNT_DOES_NOT_MATCH("TRN-010", "Bank account does not match"),
	BANK_ACCOUNT_DOES_NOT_ACTIVE("TRN-011", "Bank account does no active"),
	DOUBLE_TRANSACTION_DETECTED("TRN-12", "Double transaction detected"),
	//file error
	INVALID_FILE_TYPE("FIL-001","Invalid file type"),
	DIRECTORY_NOT_FOUND("FIL-002", "Directory not found"),
	UPLOAD_FAILED("FIL-003", "Failed to upload file"),
	FILE_EXCEED_LIMIT("FIL-004", "File size exceed limit");
	private String code;
	private String message;
	
	private ExceptionEnum(String code, String message){
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
}
