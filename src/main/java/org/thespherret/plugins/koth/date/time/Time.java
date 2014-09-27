package org.thespherret.plugins.koth.date.time;

import java.util.Calendar;

public abstract class Time {

	public static InaccurateTime currentInnacurateTime()
	{
		return new InaccurateTime(Calendar.HOUR_OF_DAY, Calendar.MINUTE);
	}

	public static Time compareInaccurate(InaccurateTime inaccurateTime1, InaccurateTime inaccurateTime2)
	{
		return new InaccurateTime(Math.abs(Math.abs(inaccurateTime1.getHour()) - Math.abs(inaccurateTime2.getHour())), Math.abs(Math.abs(inaccurateTime1.getMinute()) - Math.abs(inaccurateTime2.getMinute())));
	}

	public static AccurateTime currentAccurateTime()
	{
		return new AccurateTime(Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND);
	}

	public static Time compareAccurate(AccurateTime accurateTime1, AccurateTime accurateTime2)
	{
		return new AccurateTime(Math.abs(Math.abs(accurateTime1.getHour()) - Math.abs(accurateTime2.getHour())), Math.abs(Math.abs(accurateTime1.getMinute()) - Math.abs(accurateTime2.getMinute())), Math.abs(Math.abs(accurateTime1.getSecond()) - Math.abs(accurateTime2.getSecond())));
	}

}
