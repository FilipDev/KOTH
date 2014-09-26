package org.thespherret.plugins.koth.managers;

import org.bukkit.Bukkit;
import org.thespherret.plugins.koth.Main;
import org.thespherret.plugins.koth.queue.LeaveQueueListener;

public class QueueManager {

	private Main main;
	private LeaveQueueListener leaveListener;

	public QueueManager(Main main)
	{
		this.main = main;
		this.leaveListener = new LeaveQueueListener(main);

		Bukkit.getPluginManager().registerEvents(this.leaveListener, main);
	}

	public Main getMain()
	{
		return main;
	}

	public LeaveQueueListener getLeaveListener()
	{
		return this.leaveListener;
	}
}
