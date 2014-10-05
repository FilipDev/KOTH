package org.thespherret.plugins.koth.commands;

import org.thespherret.plugins.koth.messages.Message;
import org.thespherret.plugins.koth.utils.Chat;

public class JoinCommand extends Command {

	@Override
	public void execute()
	{
		cm.getMain().getQM().getQueue().add(p);
		Chat.sendMessage(p, Message.JOINED_QUEUE);
	}
}
