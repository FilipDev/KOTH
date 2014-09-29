package org.thespherret.plugins.koth.commands;

import org.thespherret.plugins.koth.utils.Permissions;

public class CreateCommand extends Command {

	@Override
	public void execute()
	{
		if (Permissions.isAdmin(p))
		{
			cm.getMain().getAM().createArena(args[0]);
		}
	}
}
