package org.thespherret.plugins.koth.date.time;


import java.util.Calendar;

public abstract class Time {

	public static InaccurateTime currentInnacurateTime()
	{
		return new InaccurateTime(Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE));
	}

	public static Time compareInaccurate(InaccurateTime inaccurateTime1, InaccurateTime inaccurateTime2)
	{
		InaccurateTime comparedTime = new InaccurateTime(inaccurateTime1.getHour() - inaccurateTime2.getHour(), inaccurateTime1.getMinute() - inaccurateTime2.getMinute());
		if (comparedTime.toNumberValue() < 0)
			return null;
		return comparedTime;
	}

	public static Time compareAccurate(AccurateTime accurateTime1, AccurateTime accurateTime2)
	{
		return new AccurateTime(Math.abs(Math.abs(accurateTime1.getHour()) - Math.abs(accurateTime2.getHour())), Math.abs(Math.abs(accurateTime1.getMinute()) - Math.abs(accurateTime2.getMinute())), Math.abs(Math.abs(accurateTime1.getSecond()) - Math.abs(accurateTime2.getSecond())));
	}
}
