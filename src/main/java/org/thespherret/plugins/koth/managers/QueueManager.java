package org.thespherret.plugins.koth.managers;

import org.bukkit.entity.Player;
import org.thespherret.plugins.koth.Main;
import org.thespherret.plugins.koth.queue.RejoinReminder;

import java.util.HashSet;

public class QueueManager {

	private Main main;
	private RejoinReminder rejoinReminder;

	private HashSet<Player> queue = new HashSet<>();

	public QueueManager(Main main)
	{
		this.main = main;
		this.rejoinReminder = new RejoinReminder(main);
	}

	public Main getMain()
	{
		return main;
	}

	public RejoinReminder getRejoinReminder()
	{
		return this.rejoinReminder;
	}

	public HashSet<Player> getQueue()
	{
		return this.queue;
	}
}
