package org.thespherret.plugins.koth.date;

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
}
