package com.revature.main;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Driver;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class Drivertest {

	@Test
	void test() throws ParseException {
		LocalDate today = LocalDate.now();
		 
		String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));
		 
		System.out.println(formattedDate);
		
	}

}
