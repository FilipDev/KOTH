package org.thespherret.plugins.koth.commands;

import org.thespherret.plugins.koth.messages.Message;
import org.thespherret.plugins.koth.utils.Chat;

public class LeaveCommand extends Command {

	@Override
	public void execute()
	{
		cm.getMain().getQM().getQueue().remove(p);
		Chat.sendMessage(p, Message.LEFT_QUEUE);
	}
}
