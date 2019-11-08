package com.revature.beans;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class ReimbursementTest {

	@Test
	void test() throws ClassNotFoundException, Exception, IOException {
		Reimbursement dj = new Reimbursement(1006,"TRIP TO JAPAN","11-NOV-2019",5000,"Djourn Soyer");
		dj.reimburseCommit();
	}

}
