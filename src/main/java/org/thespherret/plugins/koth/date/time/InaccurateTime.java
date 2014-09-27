package org.thespherret.plugins.koth.date.time;

import java.util.Calendar;

public class InaccurateTime extends Time {

	private int hour, minute;

	public InaccurateTime(int hour, int minute)
	{
		this.hour = hour;
		this.minute = minute;
	}

	public InaccurateTime(String time)
	{
		String[] args = time.split(":");
		this.hour = Integer.parseInt(args[0]);
		this.minute = Integer.parseInt(args[1]);
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		InaccurateTime inaccurateTime = (InaccurateTime) o;

		return hour == inaccurateTime.hour && minute == inaccurateTime.minute;

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

	public InaccurateTime compare(InaccurateTime inaccurateTime)
	{
		return (InaccurateTime) InaccurateTime.compareInaccurate(this, inaccurateTime);
	}

	public static InaccurateTime currentTime()
	{
		return new InaccurateTime(Calendar.HOUR_OF_DAY, Calendar.MINUTE);
	}

	public static InaccurateTime compare(InaccurateTime inaccurateTime1, InaccurateTime inaccurateTime2)
	{
		return new InaccurateTime(Math.abs(Math.abs(inaccurateTime1.getHour()) - Math.abs(inaccurateTime2.getHour())), Math.abs(Math.abs(inaccurateTime1.getMinute()) - Math.abs(inaccurateTime2.getMinute())));
	}
}