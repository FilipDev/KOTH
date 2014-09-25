package org.thespherret.plugins.koth.calendar;

import org.bukkit.event.EventHandler;
import org.thespherret.plugins.koth.Main;
import org.thespherret.plugins.koth.date.Date;
import org.thespherret.plugins.koth.date.DateManager;
import org.thespherret.plugins.koth.date.Day;
import org.thespherret.plugins.koth.date.Time;
import org.thespherret.plugins.koth.updater.UpdateEvent;
import org.thespherret.plugins.koth.updater.UpdateType;

import java.util.HashMap;

public class Calendar {

	private Main main;

	public Calendar(Main main)
	{
		this.main = main;
	}

	@EventHandler
	public void onUpdate(UpdateEvent event)
	{
		if (event.getUpdateType() == UpdateType.MINUTE)
		{
			Date currentDate = new Date(Day.valueOf(java.util.Calendar.DAY_OF_WEEK), new Time(DateManager.));
			if ()
		}
	}

}
