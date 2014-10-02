package org.thespherret.plugins.koth.managers;

import org.bukkit.Bukkit;
import org.thespherret.plugins.koth.Main;
import org.thespherret.plugins.koth.calendar.Calendar;
import org.thespherret.plugins.koth.date.Date;
import org.thespherret.plugins.koth.date.Day;
import org.thespherret.plugins.koth.date.time.InaccurateTime;
import org.thespherret.plugins.koth.messages.Error;
import org.thespherret.plugins.koth.utils.Chat;

import java.util.HashMap;
import java.util.Map;

public class DateManager {

	private Main main;

	private Map<Date, String> arenaMap = new HashMap<>();

	public DateManager(Main main)
	{
		this.main = main;

		addDate(new Date(Day.MONDAY, new InaccurateTime(8, 10)), "test");
		addDate(new Date(Day.TUESDAY, new InaccurateTime(10, 0)), "test");
		addDate(new Date(Day.THURSDAY, new InaccurateTime(1, 40)), "test");

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
		boolean isTomorrow = false;
		long newMax = Long.MAX_VALUE;

		for (Date date : arenaMap.keySet())
		{
			if (date.getDay() == Day.valueOf(Calendar.calendar.getGregorianChange().getDay()))
			{
				if (date.getInaccurateTime().toNumberValue() - newMax < 0)
				{
					System.out.println(date);
					newMax = date.getInaccurateTime().toNumberValue() - newMax;
					returnDate = date;
				}
			}
			isTomorrow = returnDate == null;
		}
		if (isTomorrow)
		{
			Date date = new Date(null, null);
			date.setTomorrow();
			returnDate = date;
		}
		return returnDate;
	}
}
