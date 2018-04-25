package control_classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat{
	public static String dateFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(date);
	}
	
	public static String timeFormat(Date time) {
		System.out.println(time.toString());
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(time);
	}
}
