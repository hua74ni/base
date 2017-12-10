package com.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {
	
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//Calendar 转化为 Date 日历类转化为时间类
	public static Date Calendar2Date(Calendar calendar){
		
		Date date = null;
		
		if(calendar != null){
			date = calendar.getTime();
		}
		
		return date;
	}
	
	//Date 转化为 Calendar 时间类转化日历类
	public static Calendar Date2Calendar(Date date){
		
		try {
			Calendar calendar = Calendar.getInstance();
			
			calendar.setTime(date);
			
			return calendar;
		} catch (Exception e) {
			return null;
		}
	}
	
	//计算某一天是一年中的第几星期   
	public static int dayIsYearofWeek(int year,int month,int day){
		
		int weekNo = 0;
		
		Calendar calendar = Calendar.getInstance();
		
		
		//12月1日 算第一个星期
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		
		weekNo = calendar.get(Calendar.WEEK_OF_YEAR);
		
		return weekNo;
	}
	
	//计算 一年中的第几星期是几号
	public static void Calendar2DayOfWeek(int year,int weekOfYear,int week){
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.WEEK_OF_YEAR, weekOfYear);
		calendar.set(Calendar.DAY_OF_WEEK, week);
		
		System.out.println(simpleDateFormat.format(calendar.getTime()));
		
	}
	
	//2个时间 相差的天数  2个时间都是 日历类
	public static int getIntervalCals(Calendar startDay,Calendar endDay){
		
		if(startDay.after(endDay)){
			Calendar calendar = startDay;
			startDay = endDay;
			endDay = calendar;
		}
		
		System.out.println("第一个日历时间："+simpleDateFormat.format(startDay.getTime()));
		System.out.println("第二个日历时间："+simpleDateFormat.format(endDay.getTime()));
		
		long sl = startDay.getTimeInMillis();
		long el = endDay.getTimeInMillis();
		
		long ei = el - sl;
		
		return (int) (ei/(1000*60*60*24));
	}
	
	//2个时间 相差的天数  2个时间都是 日历类 改进方式
	public static int getIntervalCals2(Calendar startDay,Calendar endDay){
		
		if(startDay.after(endDay)){
			Calendar calendar = startDay;
			startDay = endDay;
			endDay = calendar;
		}
		
		int days = endDay.get(Calendar.DAY_OF_YEAR) - startDay.get(Calendar.DAY_OF_YEAR);
		int year = endDay.get(Calendar.YEAR);
		
		if(startDay.get(Calendar.YEAR) != year){
			
			do {
				
				days += startDay.getMaximum(Calendar.DAY_OF_YEAR);
				startDay.add(Calendar.YEAR, 1);
				
			} while (startDay.get(Calendar.YEAR) != year);
			
		}
		return days;
		
	}
	
	//2个时间 相差的天数  2个时间都是 时间类
	public static int getIntervalDates(Date startDate, Date endDate){
			
		if(startDate.after(endDate)){
			Date temp = startDate;
			startDate = endDate;
			endDate = temp;
		}
		long sl = startDate.getTime();
		long el = endDate.getTime();
		
		long ei = el - sl;
		
		return (int) (ei/(1000*60*60*24));
	}
	
	public static void main(String[] args) {
		
		//静态类初始化一个日历类
		Calendar now =  Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		int day = now.get(Calendar.DAY_OF_MONTH);
		int week = now.get(Calendar.DAY_OF_WEEK) - 1;
		System.out.println("现在时间是："+year+"年"+month+"月"+day+"日，星期"+week);
		
		long nowMillis = now.getTimeInMillis();
		now.set(1989, 9, 26); //这里与真实的月份之间相差1 actualMonth = fakeMonth + 1;
		long intialMissis = now.getTimeInMillis();
		long days = (nowMillis - intialMissis) / (1000*60*60*24);
		
		System.out.println("今天和1989年10月26日相隔"+days+"天，"+"也就是说我在这个美丽的星球上已经幸福的生活了"+days+"天。");
		
		Date date = Calendar2Date(now);
		System.out.println(date);
		System.out.println(simpleDateFormat.format(date));
		
		Calendar test = Date2Calendar(date);
		System.out.println(test);
		
		System.out.println(dayIsYearofWeek(2006,8,20));
		
		Calendar2DayOfWeek(2006,1,Calendar.MONDAY);
		
		now.set(Calendar.YEAR, 2006);
		now.set(Calendar.MONTH, 8);
		now.set(Calendar.DAY_OF_MONTH, 20);
		
		/*
		 * Calendar add 用法
		 */
		
		//减去4天
		now.add(Calendar.DATE, -4);
		System.out.println(simpleDateFormat.format(now.getTime()));
		//加上4天
		now.add(Calendar.DATE, 4);
		System.out.println(simpleDateFormat.format(now.getTime()));
		
		/*
		 * 	Calendar roll 用法  
		 * 	只在本月内循环 超过本月的WeekOfDay 就从1开始
		 */
		
		now.set(Calendar.YEAR, 2006);
		now.set(Calendar.MONTH, 8);
		now.set(Calendar.DAY_OF_MONTH, 29);
		//加上4天
		now.roll(Calendar.DATE, 4);
		System.out.println(simpleDateFormat.format(now.getTime()));
		//减去4天
		now.roll(Calendar.DATE, -4);
		System.out.println(simpleDateFormat.format(now.getTime()));
		
		Calendar endDay = Calendar.getInstance();
		
		endDay.set(Calendar.YEAR, 2006);
		endDay.set(Calendar.MONTH, 10);
		endDay.set(Calendar.DAY_OF_MONTH, 1);
		
		System.out.println(" Calendar方式1: 这2个日历时间相差了 "+getIntervalCals(now,endDay) + " 天");
		System.out.println(" Calendar方式2: 这2个日历时间相差了 "+getIntervalCals2(now,endDay) + " 天");
		
		String startString = "2006-10-1 20:00:00";
		String endString = "2006-12-12 8:00:00";
		
		Date startDate = null;
		Date endDate = null;
		
		try {
			startDate = simpleDateFormat.parse(startString);
			endDate = simpleDateFormat.parse(endString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println(" Date方式： 这2个日历时间相差了 "+getIntervalDates(startDate,endDate) + " 天");
		
	}
	
}
