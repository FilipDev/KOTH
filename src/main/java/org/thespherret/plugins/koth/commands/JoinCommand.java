package org.thespherret.plugins.koth.commands;

import org.thespherret.plugins.koth.Main;

public class JoinCommand extends Command {

	@Override
	public void execute()
	{
		Main.getMain().getAM().getCurrentArena();
	}
}
