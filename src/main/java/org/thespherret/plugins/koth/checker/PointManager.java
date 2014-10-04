package org.thespherret.plugins.koth.checker;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.thespherret.plugins.koth.arena.Arena;
import org.thespherret.plugins.koth.Main;
import org.thespherret.plugins.koth.messages.Message;
import org.thespherret.plugins.koth.updater.UpdateEvent;
import org.thespherret.plugins.koth.updater.UpdateType;
import org.thespherret.plugins.koth.utils.Chat;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

public class PointManager implements Listener {

	private Main main;

	private Queue<Player> enterQueue = new PriorityQueue<>();

	private Arena arena;

	private HashMap<String, Integer> inCubeTime = new HashMap<>();

	public PointManager(Main main)
	{
		this.main = main;

		Bukkit.getPluginManager().registerEvents(this, main);

		this.arena = main.getAM().getCurrentArena();
	}

	@EventHandler
	public void onUpdate(UpdateEvent e)
	{
		if (e.getUpdateType() == UpdateType.SECOND)
		{
			boolean isInHill = false;
			while (!isInHill)
			{
				if (enterQueue.size() > 0)
				{
					if (!arena.getCuboid().contains(enterQueue.peek().getLocation()))
					{
						Chat.sendMessage(enterQueue.remove(), Message.LEFT_HILL);
						resetPoints(enterQueue.peek());
					}
					else
						isInHill = true;
				}
				else
					isInHill = true;
			}

			addPoints(enterQueue.peek());
		}
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e)
	{
		if (!enterQueue.contains(e.getPlayer()))
		{
			if (arena.getCuboid().contains(e.getTo()))
			{
				enterQueue.add(e.getPlayer());
				resetPoints(e.getPlayer());
			}
		}
	}

	private void addPoints(Player player)
	{
		inCubeTime.put(player.getName(), inCubeTime.get(player.getName()) + 1);
	}

	private void resetPoints(Player player)
	{
		inCubeTime.put(player.getName(), 0);
	}
}
