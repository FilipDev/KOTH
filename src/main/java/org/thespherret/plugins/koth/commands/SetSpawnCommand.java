package org.thespherret.plugins.koth.commands;

import org.thespherret.plugins.koth.arena.Arena;
import org.thespherret.plugins.koth.messages.Message;
import org.thespherret.plugins.koth.utils.Chat;

public class SetSpawnCommand extends Command {

	@Override
	public void execute()
	{
		if (args.length > 0)
		{
			Arena targetArena;
			if ((targetArena = cm.getMain().getAM().getArena(args[0])) != null)
			{
				targetArena.setSpawn(p.getLocation());
				Chat.sendFormattedMessage(p, Message.CHANGED_SPAWN_POINT, targetArena.getArenaName());
			}
		}
	}
}
