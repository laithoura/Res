package control_classes;

import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatter{
	public static String dateFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}
	
	public static String timeFormat(Date time) {
		System.out.println(time.toString());
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(time);
	}
	
	public static Date stringToDate(String dateText) {
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			 date = format.parse(dateText);
		} catch (ParseException e) {		
			e.printStackTrace();
		}
		return date;
	}
	
	public static Time stringToSqlTime(String timeText) {
		
		SimpleDateFormat sfd = new SimpleDateFormat("HH:mm");
		long ms = 0;
		try {
			ms = sfd.parse(timeText).getTime();
		} catch (ParseException e) {			
			e.printStackTrace();
		}		
		return new Time(ms); 
		
	}
	
	public static String numberToText(double value) {
		DecimalFormat df = new DecimalFormat("###,##0.00");
		return df.format(value);
	}
	
	public static String numberToInvoiceNo(int invoiceNO) {
		return String.format("%08d", invoiceNO);
	}
	
}
