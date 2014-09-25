package org.thespherret.plugins.koth.date;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

public class DateManager {

	private static Queue<Date> arenaCalendar = new PriorityQueue<>();

	{
		arenaCalendar.add(new D)
	}

	public static String getArena(Date date)
	{
		return arenaCalendar.get(date);
	}

	public static void addDate(Date date, String arena)
	{
		arenaCalendar.put(date, arena);
	}

}
