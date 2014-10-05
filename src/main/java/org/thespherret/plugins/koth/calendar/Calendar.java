package org.thespherret.plugins.koth.calendar;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.thespherret.plugins.koth.Main;
import org.thespherret.plugins.koth.arena.Arena;
import org.thespherret.plugins.koth.date.Date;
import org.thespherret.plugins.koth.updater.UpdateEvent;
import org.thespherret.plugins.koth.updater.UpdateType;

public class Calendar implements Listener {

	private Main main;

	public Calendar(Main main)
	{
		this.main = main;

		Bukkit.getPluginManager().registerEvents(this, main);
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
