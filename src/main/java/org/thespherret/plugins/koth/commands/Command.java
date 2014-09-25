package org.thespherret.plugins.koth.commands;

import org.bukkit.entity.Player;
import org.thespherret.plugins.koth.managers.CommandManager;

import java.util.Arrays;

public abstract class Command {

	CommandManager cm;
	String[] args;
	Player p;

	public void execute(CommandManager cm, Player p, String[] args)
	{
		this.cm = cm;
		this.p = p;
		this.args = Arrays.copyOf(args, args.length - 1);
		execute();
	}

	public abstract void execute();

}