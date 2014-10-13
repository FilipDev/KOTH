package org.thespherret.plugins.koth.managers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.thespherret.plugins.koth.Main;
import org.thespherret.plugins.koth.commands.*;
import org.thespherret.plugins.koth.utils.Chat;

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
		commandMap.put("loot", new LootCommand());
		commandMap.put("setspawn", new SetSpawnCommand());
		commandMap.put("setcapture", new SetCaptureCommand());
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
	{
		Chat.sendRemainingTime(commandSender, getMain());


		if (commandSender instanceof Player)
		{
			if (strings.length != 0)
			{
				commandMap.get(strings[0]).execute(this, (Player) commandSender, strings);
				return true;
			}
		}
		return false;
	}

	public Main getMain()
	{
		return this.main;
	}
}