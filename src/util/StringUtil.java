package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import net.sourceforge.pinyin4j.PinyinHelper;

public class StringUtil {
	public static String datePattern="yyyy-MM-dd";
	public static SimpleDateFormat dateFormate=new SimpleDateFormat(datePattern);
	public static String dateTimePattern="yyyy-MM-dd kk:mm:ss";
	public static SimpleDateFormat dateTimeFormate=new SimpleDateFormat(datePattern);
	
	
	//如果字符串为null或""返回true
 public static boolean isEmpty(String name){
	  if(name==null||"".equals(name))
		  return true;
	  return false;
 }
 
 public static boolean isNotEmpty(String name){
	 return isEmpty(name)!=true;
 }
 public static Date StringToDate(String date) throws Exception{
	 if(isNotEmpty(date)){
		date=date.replaceAll("/", "-").replaceAll(".", "-").replaceAll("\\","-");
		synchronized (dateFormate) {
		 return	dateFormate.parse(date);
		}
	 }
	 return null;
 }
 
 public static String DateToString(Date date){
	 if(date!=null){
		 synchronized (dateFormate) {
			return dateFormate.format(date);
		}
	 }
	 return null;
 }
 
 
 public static Date StringToDateTime(String date) throws Exception{
	 if(isNotEmpty(date)){
		date=date.replaceAll("/", "-").replaceAll(".", "-").replaceAll("\\","-");
		synchronized (dateTimeFormate) {
		 return	dateTimeFormate.parse(date);
		}
	 }
	 return null;
 }
 
 public static String DateTimeToString(Date date){
	 if(date!=null){
		 synchronized (dateTimeFormate) {
			return dateTimeFormate.format(date);
		}
	 }
	 return null;
 }
 //当前月的第一天
 public static Date getMonthBeginDay(Date date){
	 
	 Calendar c = Calendar.getInstance(); 
	 c.setTime(date);
	 c.add(Calendar.MONTH, 0);
     c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
      synchronized(dateFormate){
    	  try {
		 return	dateFormate.parse(dateFormate.format(c.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
      }
    		
 }
//当前月的最后一天
public static Date getMonthEndDay(Date date){
	 Calendar ca = Calendar.getInstance();
	 ca.setTime(date);
     ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH)); 
 
      synchronized(dateFormate){
    	  try {
		 return	dateFormate.parse(dateFormate.format(ca.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
      }
    		
 }
 
 public static String getPY(String name){
	  if(isNotEmpty(name)){
		  String convert = "";
	        for (int j = 0; j < name.length(); j++) {
	            char word = name.charAt(j);
	            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
	            if (pinyinArray != null) {
	                convert += pinyinArray[0].charAt(0);
	            } else {
	                convert += word;
	            }
	        }
	        return convert.toUpperCase();
	  }
	  return null;
 }

 

 
}
