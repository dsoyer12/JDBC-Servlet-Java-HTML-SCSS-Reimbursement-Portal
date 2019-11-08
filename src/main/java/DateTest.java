import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DateTest {
	public static void main(String[] args) throws SQLException, IOException, InterruptedException, ParseException  {
		
		 LocalDate date = java.time.LocalDate.now();
		 String newdate = date.toString();
		 Date d = (Date) new SimpleDateFormat("dd/MMM/yyyy").parse(newdate);
		 System.out.println("d");
		
	}
	

	}


