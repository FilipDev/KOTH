package org.thespherret.plugins.koth;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class Countdown extends BukkitRunnable {

	private int secondsLeft;

	private Arena arena;

	private int taskID;

	public static List<Integer> tellTimes = Arrays.asList(15, 10, 5, 4, 3, 2, 1);

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
