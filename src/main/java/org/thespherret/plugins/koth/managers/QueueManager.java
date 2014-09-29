package org.thespherret.plugins.koth.managers;

import org.bukkit.Bukkit;
import org.thespherret.plugins.koth.Main;
import org.thespherret.plugins.koth.queue.RejoinReminder;

public class QueueManager {

	private Main main;
	private RejoinReminder rejoinReminder;

	public QueueManager(Main main)
	{
		this.main = main;
		this.rejoinReminder = new RejoinReminder(main);

		Bukkit.getPluginManager().registerEvents(this.rejoinReminder, main);
	}

	public Main getMain()
	{
		return main;
	}

	public RejoinReminder getRejoinReminder()
	{
		return this.rejoinReminder;
	}
}
