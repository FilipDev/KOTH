package org.thespherret.plugins.koth.managers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.thespherret.plugins.koth.Main;
import org.thespherret.plugins.koth.commands.*;

import java.util.Arrays;
import java.util.HashMap;

public class CommandManager implements CommandExecutor {

	final Main main;
	final HashMap<String, org.thespherret.plugins.koth.commands.Command> commandMap = new HashMap<>();

	public CommandManager(Main main)
	{
		this.main = main;
		commandMap.put("create", new CreateCommand());
		commandMap.put("join", new JoinCommand());
		commandMap.put("leave", new LeaveCommand());
		commandMap.put("whenisnext", new NextArenaCommand());
		commandMap.put("queue", new QueueCommand());
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
	{
		if (commandSender instanceof Player)
		{
			commandMap.get(strings[0]).execute(this, (Player) commandSender, Arrays.copyOfRange(strings, 1, strings.length - 1));
			return true;
		}
		else
		{
			return false;
		}
	}

	public Main getMain()
	{
		return this.main;
	}
}