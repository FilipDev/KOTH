package org.thespherret.plugins.koth.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.thespherret.plugins.koth.Main;

import java.util.HashMap;

public class QueueCommand extends Command {

	@Override
	public void execute()
	{
		HashMap<Integer, String> stringGroups = new HashMap<>();
		int playersOnLine = 1, playersPerLine = 3, line = 0, displayNumber = 1;
		for (Player player : Main.getMain().getAM().getCurrentArena().getPlayers())
		{
			String currString = stringGroups.get(line);
			if (currString == null)
				stringGroups.put(line, displayNumber + ". " + player.getDisplayName());
			else
				stringGroups.put(line, currString + ChatColor.RESET + ", " + displayNumber + ". " + player.getDisplayName());
			if (playersOnLine == playersPerLine)
			{
				line++;
				playersOnLine = 0;
			}
			playersOnLine++;
			displayNumber++;
		}
		for (int x = 0; x <= stringGroups.size() - 1; x++)
		{
			p.sendMessage(stringGroups.get(x));
			if (!(x == stringGroups.size() - 1))
				p.sendMessage("");
		}
	}
}
