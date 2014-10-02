package org.thespherret.plugins.koth.countdown;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.thespherret.plugins.koth.arena.Arena;
import org.thespherret.plugins.koth.date.time.AccurateTime;
import org.thespherret.plugins.koth.date.time.Time;
import org.thespherret.plugins.koth.messages.Message;
import org.thespherret.plugins.koth.updater.UpdateEvent;
import org.thespherret.plugins.koth.updater.UpdateType;
import org.thespherret.plugins.koth.utils.Chat;

import java.util.HashSet;

public class Countdown implements Listener {

	private int secondsLeft;

	private Arena arena;

	public static HashSet<Time> tellTimes = new HashSet<>();

	static
	{
		tellTimes.add(new AccurateTime(0, 15, 0));
		tellTimes.add(new AccurateTime(0, 15, 0));
	}

	public Countdown(Arena arena)
	{
		this.arena = arena;

		Bukkit.getPluginManager().registerEvents(this, arena.getAM().getMain());
	}

	public synchronized int getSecondsLeft()
	{
		return secondsLeft;
	}

	public synchronized void setSecondsLeft(int secondsLeft)
	{
		this.secondsLeft = secondsLeft;
	}

	@EventHandler
	public void onUpdate(UpdateEvent e)
	{
		if (e.getUpdateType() == UpdateType.SECOND)
		{
			setSecondsLeft(getSecondsLeft() - 1);

			Time time = AccurateTime.fromSeconds(getSecondsLeft());

			if (tellTimes.contains(time))
			{
				for (Player player : arena.getPlayers())
				{
					Chat.sendFormattedMessage(player, Message.GAME_STARTING_IN, time.toString());
				}
			}
		}
	}

}
