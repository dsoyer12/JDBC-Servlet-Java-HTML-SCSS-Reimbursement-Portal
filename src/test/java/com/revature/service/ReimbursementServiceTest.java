package com.revature.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.beans.Reimbursement;

class ReimbursementServiceTest {

	@Test
	void test() throws ClassNotFoundException, SQLException, IOException {
		ReimbursementService rem = new ReimbursementService();
		Reimbursement reim = rem.getReimbursmentbyId(121);
		System.out.println(reim.getDescription());
		
	}
     
	@Test
	void test2() throws ClassNotFoundException, SQLException, IOException {
		
		ObjectMapper om = new ObjectMapper();
		om.registerModule(new JavaTimeModule());
		om.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
		ReimbursementService remService = new ReimbursementService();
		System.out.println(remService.getReimbursmentbyId(121));
		String reimbursementJson = om.writeValueAsString(remService.getReimbursmentbyId(121));
		System.out.println(reimbursementJson);
		
	}
}
