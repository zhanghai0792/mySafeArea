package util;

import java.util.Date;

public class getCurrentMonth {
 public static void main(String[] args){
	 System.out.println(StringUtil.DateToString(StringUtil.getMonthEndDay(new Date())));
 }
}
