package org.thespherret.plugins.koth.commands;

import org.thespherret.plugins.koth.Main;
import org.thespherret.plugins.koth.messages.Message;

public class JoinCommand extends Command {

	@Override
	public void execute()
	{
		cm.getMain().getAM().getCurrentArena().getPlayers().add(p);
		Main.sendMessage(p, Message.JOINED_QUEUE);
	}
}
