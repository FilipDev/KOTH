package org.thespherret.plugins.koth.date;

public class Date {

	private Day day;
	private Time time;

	public Date(Day day, Time time)
	{
		this.day = day;
		this.time = time;
	}

	public Day getDay()
	{
		return day;
	}

	public Time getTime()
	{
		return time;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Date date = (Date) o;

		return day == date.day && time.equals(date.time);

	}

	@Override
	public int hashCode()
	{
		int result = day.hashCode();
		result = 31 * result + time.hashCode();
		return result;
	}

}