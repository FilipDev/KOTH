package org.thespherret.plugins.koth.commands;

import org.thespherret.plugins.koth.messages.Error;
import org.thespherret.plugins.koth.messages.Message;
import org.thespherret.plugins.koth.messages.Warning;
import org.thespherret.plugins.koth.utils.Chat;

public class SetSpawnCommand extends Command {

	@Override
	public void execute()
	{
		System.out.println("executed " + this.subCommand);
		if (args.length == 1)
		{
			if (cm.getMain().getAM().getArena(args[0]) == null)
			{
				Chat.sendWarning(p, Warning.CREATED_ARENA);
			}
			cm.getMain().getAM().setArenaSpawn(args[0], p.getLocation());
			Chat.sendMessage(p, Message.CHANGED_SPAWN_POINT, args[0]);
		}
		else
		{
			Chat.sendError(p, Error.TOO_MANY_ARGS);
		}
	}
}
