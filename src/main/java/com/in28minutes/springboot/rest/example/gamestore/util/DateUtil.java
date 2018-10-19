package com.in28minutes.springboot.rest.example.gamestore.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {
	/* This util contain function about date only */
	public static Calendar getCalendar(Date date) {
	    Calendar cal = Calendar.getInstance(Locale.US);
	    cal.setTime(date);
	    return cal;
	}
	public static int getAge(Date birthDate) {
		Date today = new Date();
		Calendar a = DateUtil.getCalendar(birthDate);
		Calendar b = DateUtil.getCalendar(today);
		int age = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
		if (a.get(Calendar.DAY_OF_YEAR) > b.get(Calendar.DAY_OF_YEAR)) {
	        age--;
	    }
		return age;
	}
}
