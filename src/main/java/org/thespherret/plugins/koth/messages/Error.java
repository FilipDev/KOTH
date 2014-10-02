package org.thespherret.plugins.koth.messages;

import org.bukkit.ChatColor;

public enum Error {

	INVALID_ARGUMENT("You have used an invalid argument."), NO_ARENAS("You have not created any arenas.");

	private String error;

	Error(String error)
	{
		this.error = error;
	}

	public String toString()
	{
		return ChatColor.RED + "Error: " + ChatColor.DARK_RED + this.error;
	}

	public String getFormatted(String... strings)
	{
		String s1 = toString();
		for (String s2 : strings)
			s1 = s1.replaceFirst("%v", ChatColor.RED + s2 + ChatColor.DARK_RED);
		return s1;
	}
}
