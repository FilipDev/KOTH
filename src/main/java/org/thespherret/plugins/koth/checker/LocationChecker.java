package org.thespherret.plugins.koth.checker;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.thespherret.plugins.koth.Arena;
import org.thespherret.plugins.koth.Main;
import org.thespherret.plugins.koth.updater.UpdateEvent;
import org.thespherret.plugins.koth.updater.UpdateType;

import java.util.HashMap;

public class LocationChecker {

	private Main main;

	private Player firstPlayerInCuboid;

	private Arena arena;

	private HashMap<String, Integer> inCubeTime = new HashMap<>();

	public LocationChecker(Main main)
	{
		this.main = main;

		this.arena = main.getAM().getCurrentArena();
	}

	@EventHandler
	public void onUpdate(UpdateEvent e)
	{
		if (e.getUpdateType() == UpdateType.SECOND)
		{
			boolean empty = true;
			if (firstPlayerInCuboid != null)
			{
				for (Player player : arena.getPlayers())
				{
					if (arena.getCuboid().contains(player.getLocation()))
					{
						empty = false;
						if (player == firstPlayerInCuboid)
							inCubeTime.put(player.getName(), inCubeTime.get(player.getName()));
					}
				}
			}

			if (empty)
			{
				this.firstPlayerInCuboid = null;
			}
		}
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e)
	{
		if (firstPlayerInCuboid == null)
		{
			if (arena.getCuboid().contains(e.getTo()))
				firstPlayerInCuboid = e.getPlayer();
		}
	}
}
