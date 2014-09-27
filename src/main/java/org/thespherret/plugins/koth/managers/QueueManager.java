package org.thespherret.plugins.koth.managers;

import org.bukkit.Bukkit;
import org.thespherret.plugins.koth.Main;
import org.thespherret.plugins.koth.queue.LeaveListener;

public class QueueManager {

	private Main main;
	private LeaveListener leaveListener;

	public QueueManager(Main main)
	{
		this.main = main;
		this.leaveListener = new LeaveListener(main);

		Bukkit.getPluginManager().registerEvents(this.leaveListener, main);
	}

	public Main getMain()
	{
		return main;
	}

	public LeaveListener getLeaveListener()
	{
		return this.leaveListener;
	}
}
