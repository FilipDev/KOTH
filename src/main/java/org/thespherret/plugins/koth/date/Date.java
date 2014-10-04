package org.thespherret.plugins.koth.date;

import org.thespherret.plugins.koth.date.time.InaccurateTime;
import org.thespherret.plugins.koth.date.time.Time;

import java.util.Calendar;

public class Date {

	private Day day;
	private InaccurateTime inaccurateTime;

	private boolean tomorrow = false;

	public Date(Day day, InaccurateTime inaccurateTime)
	{
		this.day = day;
		this.inaccurateTime = inaccurateTime;
	}

	public void setTomorrow()
	{
		this.tomorrow = true;
	}

	public boolean isTomorrow()
	{
		return this.tomorrow;
	}

	public Day getDay()
	{
		return day;
	}

	public InaccurateTime getInaccurateTime()
	{
		return inaccurateTime;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Date date = (Date) o;

		return day == date.day && inaccurateTime.equals(date.inaccurateTime);

	}

	@Override
	public String toString()
	{
		return "Date{" +
				"day=" + day +
				", inaccurateTime=" + inaccurateTime +
				'}';
	}

	@Override
	public int hashCode()
	{
		int result = day.hashCode();
		result = 31 * result + inaccurateTime.hashCode();
		return result;
	}

	public static Date currentDate()
	{
		return new Date(Day.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)), Time.currentInnacurateTime());
	}

}