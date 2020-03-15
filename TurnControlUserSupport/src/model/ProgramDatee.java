package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
* NICOLAS ESTEBAN COLMENARES RUIZ
* UNIVERSIDAD ICESI
* ALGORITMOS Y PROGRAMACION 2
* LABORATORIO 1 TurnControl 
* 09/03/2020
*/

public class ProgramDatee {
	
	//attributes
	
	private int day;
	private int month;
	private int year;
	private int hour;
	private int min;
	private int sec;
	private SimpleDateFormat date;
	private String actualDate;
	private long timeMilis;
	
	// methods
	
	public ProgramDatee() {
		
		date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		actualDate = date.format(new Date());
		timeMilis = System.currentTimeMillis();
	}
	
	public void ModifyDate(int d, int m, int y, int h, int min, int s) {
		day = d;
		month = m;
		year = y;
		hour = h;
		this.min = min;
		sec = s;
		ShowDate();
	}
	
	@SuppressWarnings("deprecation")
	public String ShowDate() {
		Date d = new Date();
		long timeMili = System.currentTimeMillis();
		if(year != 0) {
			String[] divide = actualDate.split(" ");
			String[] aDate = divide[0].split("-");
			String[] time = divide[1].split(":");
			int difDay = day-Integer.parseInt(aDate[0]);
			int difMonth = month - Integer.parseInt(aDate[1]);
			int difYear = year - Integer.parseInt(aDate[2]);
			int difHour = hour - Integer.parseInt(time[0]);
			int difMin = min - Integer.parseInt(time[1]);
			int difSec = sec - Integer.parseInt(time[2]);
			d.setSeconds(d.getSeconds() + difSec);
			d.setMinutes(d.getMinutes() + difMin);
			d.setHours(d.getHours()+difHour);
			d.setDate(d.getDate()+difDay);
			d.setMonth(d.getMonth()+difMonth);
			d.setYear(d.getYear()+difYear);
			timeMilis = timeMili - timeMilis;
			actualDate = date.format(d);
		}else {
			timeMilis = timeMili - timeMilis;
			actualDate = date.format(new Date());
		}
		return actualDate;
	}
	
	public float getTimeInMinutes() {
		float timeInMin = 0;
		float sec	= (float) (timeMilis / 1000);
		timeInMin = sec / 60;
		return timeInMin;
	}
}
