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
	
	// methods
	
	public ProgramDatee() {
		
		date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		actualDate = date.format(new Date());
	}
	
	public void ModifyDate(int d, int m, int y, int h, int min, int s) {
		day = d;
		month = m;
		year = y;
		hour = h;
		this.min = min;
		sec = s;
	}
	
	public String ShowDate() {
		String toReturn = "";
		if(year != 0) {
			String[] divide = actualDate.split(" ");
			String[] date = divide[0].split("-");
			String[] time = divide[1].split(":");
			int difDay = day-Integer.parseInt(date[0]);
			int difMonth = month - Integer.parseInt(date[1]);
			int difYear = year - Integer.parseInt(date[2]);
			int difHour = hour - Integer.parseInt(time[0]);
			int difMin = min - Integer.parseInt(time[1]);
			int difSec = sec - Integer.parseInt(time[2]);
			toReturn = date.format(new Date(difDay, difMonth, difYear, difHour, difMin, difSec));
		}
		else {
			toReturn = date.format(new Date());
		}
		return toReturn;
	}
}
