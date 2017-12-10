package com.ssm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	//将2017-06-22 10:15:59 转换为	
	//	2017年06月22日
	//	上午
	//	10:15分

	public static String [] DateStr2Array(String dateString){
		
		String [] strings = null;
		
		try {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			Date date = sdf1.parse(dateString);
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 a HH:mm:ss E");
			
			String dateStr = simpleDateFormat.format(date);
			
			strings = dateStr.split(" ");
			
			if(strings[1].equals("AM")){
				strings[1] = "上午";
			}else{
				strings[1] = "下午";
			}
			
			strings[2] = strings[2].substring(0, 5)+"分";

			switch (strings[3]) {
				case "Mon":
					strings[3] = "星期一";
					break;
				case "Tue":
					strings[3] = "星期二";
					break;
				case "Wed":
					strings[3] = "星期三";
					break;
				case "Thu":
					strings[3] = "星期四";
					break;
				case "Fri":
					strings[3] = "星期五";
					break;
				case "Sat":
					strings[3] = "星期六";
					break;
				case "Sun":
					strings[3] = "星期日";
					break;

				default:
					break;
			}
			
//			for (String string : strings) {
//				System.out.println(string);
//			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return strings;
	}
	
}
