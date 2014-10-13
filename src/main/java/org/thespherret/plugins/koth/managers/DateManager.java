package org.thespherret.plugins.koth.managers;

import org.bukkit.Bukkit;
import org.thespherret.plugins.koth.Main;
import org.thespherret.plugins.koth.date.Date;
import org.thespherret.plugins.koth.date.Day;
import org.thespherret.plugins.koth.date.time.InaccurateTime;
import org.thespherret.plugins.koth.date.time.Time;
import org.thespherret.plugins.koth.messages.Error;
import org.thespherret.plugins.koth.utils.Chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DateManager {

	private Main main;

	private Map<Date, String> arenaMap = new HashMap<>();

	public DateManager(Main main)
	{
		this.main = main;

		addDate(new Date(Day.FRIDAY, new InaccurateTime(20, 58)), "test");
		addDate(new Date(Day.FRIDAY, new InaccurateTime(21, 00)), "test");
		addDate(new Date(Day.FRIDAY, new InaccurateTime(21, 46)), "test");
		addDate(new Date(Day.FRIDAY, new InaccurateTime(21, 44)), "test");
		addDate(new Date(Day.FRIDAY, new InaccurateTime(21, 42)), "test");
		addDate(new Date(Day.FRIDAY, new InaccurateTime(21, 8)), "test");
		addDate(new Date(Day.FRIDAY, new InaccurateTime(21, 10)), "test");
		addDate(new Date(Day.FRIDAY, new InaccurateTime(21, 12)), "test");
		addDate(new Date(Day.FRIDAY, new InaccurateTime(20, 54)), "test");
		addDate(new Date(Day.FRIDAY, new InaccurateTime(20, 56)), "test");

		if (main.getAM().getArenaMap().size() == 0)
			Chat.sendError(Bukkit.getConsoleSender(), Error.NO_ARENAS, "");
	}

	public void addDate(Date date, String arena)
	{
		arenaMap.put(date, arena);
	}

	public boolean arenaExists(Date date)
	{
		for (Date date1 : arenaMap.keySet())
			if (date1.equals(date))
				return true;
		return false;
	}

	public String getArena(Date date)
	{
		return arenaMap.get(date);
	}

	public Date getNextArena()
	{
		Date returnDate = null;
		InaccurateTime newMax = Time.currentInnacurateTime();
		ArrayList<Integer> ints = new ArrayList<>();

		for (Date date : arenaMap.keySet())
		{
			ints.add((int) date.getInaccurateTime().toNumberValue());
		}
		int currentTime = getLargest(ints.toArray(new Integer[arenaMap.keySet().size()]));

		for (Date date : arenaMap.keySet())
		{
			if (date.getDay() == Day.valueOf(java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_WEEK)))
			{
				//System.out.println("curr iter " + date.getInaccurateTime().toNumberValue());
				//System.out.println("max " + newMax.toNumberValue());
				//System.out.println("current " + currentTime);
				if (date.getInaccurateTime().toNumberValue() >= Time.currentInnacurateTime().toNumberValue())
				{
					if (date.getInaccurateTime().toNumberValue() <= currentTime)
					{
						if (date.getInaccurateTime().toNumberValue() <= newMax.toNumberValue())
						{
							System.out.println("curr iter " + date.getInaccurateTime().toNumberValue());
							System.out.println("max " + newMax.toNumberValue());
							System.out.println("current " + currentTime);
							newMax = date.getInaccurateTime();
							returnDate = date;
							System.out.println(returnDate);
						}
					}
				}
			}
			System.out.println(returnDate);
		}
		System.out.println(returnDate);

		if (returnDate == null)
		{
			Date date = new Date(null, null);
			date.setTomorrow();
			returnDate = date;
		}
		return returnDate;
	}

	private int getLargest(Integer... ints)
	{
		int maxInt = 0;
		for (int x : ints)
		{
			if (x > maxInt)
			{
				maxInt = x;
			}
		}
		return maxInt;
	}
}
