package org.thespherret.plugins.koth.date;

import java.util.Calendar;

public class Time {

	private int hour, minute;

	public Time(int hour, int minute)
	{
		this.hour = hour;
		this.minute = minute;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Time time = (Time) o;

		return hour == time.hour && minute == time.minute;

	}

	@Override
	public int hashCode()
	{
		int result = hour;
		result = 31 * result + minute;
		return result;
	}

	public int getHour()
	{
		return hour;
	}

	public int getMinute()
	{
		return minute;
	}

	@Override
	public String toString()
	{
		return this.getHour() + "h" + ":" + this.getMinute() + "m";
	}

	public Time compare(Time time)
	{
		return Time.compare(this, time);
	}

	public static Time currentTime()
	{
		return new Time(Calendar.HOUR_OF_DAY, Calendar.MINUTE);
	}

	public static Time compare(Time time1, Time time2)
	{
		return new Time(Math.abs(Math.abs(time1.getHour()) - Math.abs(time2.getHour())), Math.abs(Math.abs(time1.getMinute()) - Math.abs(time2.getMinute())));
	}
}
