package org.thespherret.plugins.koth.date.time;

import java.util.concurrent.TimeUnit;

public class AccurateTime extends Time {

	private int hour, minute, second;

	public AccurateTime(int hour, int minute, int second)
	{
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	public AccurateTime(String time)
	{
		String[] args = time.split(":");
		this.hour = Integer.parseInt(args[0]);
		this.minute = Integer.parseInt(args[1]);
		this.second = Integer.parseInt(args[2]);
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AccurateTime that = (AccurateTime) o;

		return hour == that.hour && minute == that.minute && second == that.second;

	}

	@Override
	public int hashCode()
	{
		int result = hour;
		result = 31 * result + minute;
		result = 31 * result + second;
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

	public int getSecond()
	{
		return second;
	}

	@Override
	public String toString()
	{
		return this.getHour() + "h" + ":" + this.getMinute() + "m" + ":" + this.getSecond() + "s";
	}

	public AccurateTime compare(AccurateTime time)
	{
		return (AccurateTime) Time.compareAccurate(this, time);
	}

	public static AccurateTime fromSeconds(int sec)
	{
		long hours = TimeUnit.SECONDS.toHours(sec);
		sec -= TimeUnit.HOURS.toMillis(hours);
		long minutes = TimeUnit.SECONDS.toMinutes(sec);
		sec -= TimeUnit.MINUTES.toMillis(minutes);
		long seconds = TimeUnit.SECONDS.toSeconds(sec);

		return new AccurateTime((int) hours, (int) minutes, (int) seconds);
	}
}
