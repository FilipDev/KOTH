package org.thespherret.plugins.koth.messages;

import org.bukkit.ChatColor;

public enum Warning {

	CREATED_ARENA("Arena does not exist, created one."), LEFT_QUEUE("Left server while in queue. Rejoin with /koth join.");

	private String warning;

	Warning(String warning)
	{
		this.warning = warning;
	}

	public String toString()
	{
		return ChatColor.YELLOW + "Warning: " + ChatColor.GOLD + this.warning;
	}

	public String getFormatted(String... strings)
	{
		String s1 = toString();
		for (String s2 : strings)
			s1 = s1.replaceFirst("%v", ChatColor.YELLOW + s2 + ChatColor.GOLD);
		return s1;
	}
}
