package utils;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class DateHandler {
	
	public static java.util.Date strTojava(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dateString);
    }
	
	public static String sqlTimeToStr(Timestamp inputTimestamp) {
		SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy h:mm a");
        String outputDateString = outputDateFormat.format(inputTimestamp);
        return outputDateString;
	}
	
	public static java.util.Date sqlTimeToJava(Timestamp timestamp) {
		if(timestamp == null )return null;
        return new Date(timestamp.getTime());
	}
	
	public static Timestamp javaToSqlTime(java.util.Date utilDate) {
		if(utilDate == null )return null;

        return new Timestamp(utilDate.getTime());
    }
	
	public static String javaToStr(java.util.Date utilDate) {
		if(utilDate == null )return "";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy h:mm a");
        return formatter.format(utilDate);
    }
	
	public static java.util.Date sqlDateToJava(Date sqlDate) {
        if (sqlDate == null) {
            return null;
        }
        long milliseconds = sqlDate.getTime();
        return new java.util.Date(milliseconds);
    }
	
	public static Date javaToSqlDate(java.util.Date utilDate) {
        if (utilDate == null) {
            return null;
        }
        long milliseconds = utilDate.getTime();
        return new Date(milliseconds);
    }

}

