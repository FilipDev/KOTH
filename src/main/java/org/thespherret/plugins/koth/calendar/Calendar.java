package org.thespherret.plugins.koth.calendar;

import org.bukkit.event.EventHandler;
import org.thespherret.plugins.koth.Main;
import org.thespherret.plugins.koth.arena.Arena;
import org.thespherret.plugins.koth.date.Date;
import org.thespherret.plugins.koth.updater.UpdateEvent;
import org.thespherret.plugins.koth.updater.UpdateType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;

public class Calendar {

	private Main main;

	public static GregorianCalendar calendar = new GregorianCalendar(new SimpleTimeZone(-5, "EST"));

	public Calendar(Main main)
	{
		this.main = main;

		java.util.Date date = new java.util.Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");

		String[] dateStrings = dateFormat.format(date).split("/");

		calendar.set(Integer.parseInt(dateStrings[0]), Integer.parseInt(dateStrings[1]), Integer.parseInt(dateStrings[2]));
	}

	@EventHandler
	public void onUpdate(UpdateEvent event)
	{
		if (event.getUpdateType() == UpdateType.MINUTE)
		{
			Date currentDate = Date.currentDate();
			if (main.getDM().arenaExists(currentDate))
			{
				Arena arena = main.getAM().getArena(main.getDM().getArena(currentDate));

				main.getAM().setCurrentArena(arena);

				arena.startArena();
			}
		}
	}

}
