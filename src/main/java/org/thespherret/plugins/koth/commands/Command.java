package org.thespherret.plugins.koth.commands;

import org.bukkit.entity.Player;
import org.thespherret.plugins.koth.managers.CommandManager;

import java.util.Arrays;

public abstract class Command {

	CommandManager cm;
	String[] args;
	Player p;
	String subCommand;

	public void execute(CommandManager cm, Player p, String[] args)
	{
		this.cm = cm;
		this.p = p;
		this.subCommand = args[0];
		this.args = Arrays.copyOfRange(args, 1, args.length);
		execute();
	}

	public abstract void execute();

}