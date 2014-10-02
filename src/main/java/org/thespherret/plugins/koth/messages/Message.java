package org.thespherret.plugins.koth.messages;

import org.bukkit.ChatColor;
import org.thespherret.plugins.koth.Main;
import org.thespherret.plugins.koth.utils.Chat;

public enum Message {

	INITIALIZING,
	DEATH,
	STARTING,
	LEFT_QUEUE, 
	RETURN_TO_QUEUE, 
	GAME_STARTING_IN, 
	JOINED_QUEUE, 
	NEXT_ARENA_IN, 
	FIRST_PLAYER_IN_HILL, 
	LEFT_HILL, 
	REWARD_VIEW_POSSIBLE_INVENTORY_NAME,
	CHANGED_SPAWN_POINT, 
	REWARD_EDIT_INVENTORY_NAME, 
	REWARD_CLAIM_INVENTORY_NAME, GAME_STARTING_TOMORROW;

	private String message;

	private Message()
	{
		this.message = ChatColor.translateAlternateColorCodes('&', Main.PREFIX + Main.getMain().messages.getString(name()));
	}

	@Override
	public String toString()
	{
		return Chat.colorize(message);
	}

	public String getFormatted(Object... strings)
	{
		String s1 = toString();
		for (Object s2 : strings)
			s1 = s1.replaceFirst("%v", s2.toString());
		return s1;
	}

}
