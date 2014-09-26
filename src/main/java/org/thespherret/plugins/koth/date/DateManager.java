package org.thespherret.plugins.koth.date;

import org.thespherret.plugins.koth.Main;

import java.util.PriorityQueue;
import java.util.Queue;

public class DateManager {

	private Main main;

	private Queue<Date> dateQueue = new PriorityQueue<>();
	private Queue<String> arenaQueue = new PriorityQueue<>();

	static
	{
		//TODO: ADD DEFAULT DATES
	}

	public DateManager(Main main)
	{
		this.main = main;
	}

	public void addDate(Date date, String arena)
	{
		dateQueue.add(date);
		arenaQueue.add(arena);
	}

	public Date getNextDate()
	{
		return dateQueue.peek();
	}

	public String getNextArena()
	{
		dateQueue.remove();
		return arenaQueue.remove();
	}


}
