package org.thespherret.plugins.koth;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.thespherret.plugins.koth.date.Time;
import org.thespherret.plugins.koth.messages.Message;

import java.util.HashSet;

public class Events implements Listener {

	Main main;

	public Events(Main main)
	{
		this.main = main;
	}

	private final HashSet<String> leftQueue = new HashSet<>();

	@EventHandler
	public void onPlayerLeave(final PlayerQuitEvent e)
	{
		HashSet<Player> players = main.getAM().getCurrentArena().getPlayers();
		if (players.contains(e.getPlayer()))
		{
			players.remove(e.getPlayer());
			leftQueue.add(e.getPlayer().getName());
			Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
				@Override
				public void run()
				{
					leftQueue.remove(e.getPlayer().getName());
				}
			}, 400L);
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e)
	{
		if (leftQueue.contains(e.getPlayer().getName()))
			Main.sendMessage(e.getPlayer(), Message.RETURN_TO_QUEUE);

		Main.sendFormattedMessage(e.getPlayer(), Message.GAME_STARTING_IN, Time.currentTime().toString());
	}
}
