package org.thespherret.plugins.koth.commands;

import org.thespherret.plugins.koth.Main;
import org.thespherret.plugins.koth.messages.Message;

public class LeaveCommand extends Command {

	@Override
	public void execute()
	{
		cm.getMain().getAM().getCurrentArena().getPlayers().remove(p);
		Main.sendMessage(p, Message.LEFT_QUEUE);
	}
}
