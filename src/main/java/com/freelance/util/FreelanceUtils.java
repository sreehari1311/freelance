package com.freelance.util;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public abstract class FreelanceUtils {

	public static String DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm";
	private static DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
	public static Object copyProperties(Object source, Object destination) {
		if (source == null) {
			return destination;
		}
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(destination, source);
			 
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destination;
	}
	
	public static Date getDateTime(String strDate){
		Date date = null;
		try {
			date = dateFormat.parse(strDate);
		} catch (ParseException e) {
		 e.printStackTrace();
		}
		return date;
	}
	 
}
