package org.thespherret.plugins.koth.countdown;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.thespherret.plugins.koth.Main;
import org.thespherret.plugins.koth.arena.Arena;
import org.thespherret.plugins.koth.date.time.AccurateTime;
import org.thespherret.plugins.koth.date.time.Time;

import java.util.ArrayList;
import java.util.List;

public class Countdown extends BukkitRunnable {

	private int secondsLeft;

	private Arena arena;

	private int taskID;

	public static List<Time> tellTimes = new ArrayList<>();

	static
	{
		tellTimes.add(new AccurateTime(0, 15, 0));
	}

	public Countdown(Arena arena)
	{
		this.arena = arena;
		this.taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), this, 0L, 20L);
	}

	public synchronized int getSecondsLeft()
	{
		return secondsLeft;
	}

	public synchronized void setSecondsLeft(int secondsLeft)
	{
		this.secondsLeft = secondsLeft;
	}

	public int getTaskID()
	{
		return this.taskID;
	}

	@Override
	public void run()
	{
		setSecondsLeft(getSecondsLeft() - 1);
	}
}
