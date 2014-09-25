package org.thespherret.plugins.koth.messages;

import org.bukkit.ChatColor;
import org.thespherret.plugins.koth.Main;

public enum Message {

	INITIALIZING,
	DEATH,
	STARTING,
	;

	private String message;

	private Message()
	{
		this.message = ChatColor.translateAlternateColorCodes('&', Main.PREFIX + Main.getMain().messages.getString(name()));
	}

	@Override
	public String toString()
	{
		return message;
	}

	public String getFormatted(Object... strings)
	{
		String s1 = toString();
		for (Object s2 : strings)
			s1 = s1.replaceFirst("%v", s2.toString());
		return s1;
	}

}
