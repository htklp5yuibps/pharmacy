package no.comp.finalproject.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateConverter {
	public static Date convert(String date)
			throws ParseException {
		
		if (date == null) return null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(sdf.parse(date));
    return calendar.getTime();
	}
}
