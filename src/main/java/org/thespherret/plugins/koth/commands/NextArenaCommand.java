package org.thespherret.plugins.koth.commands;

import org.thespherret.plugins.koth.date.Time;
import org.thespherret.plugins.koth.messages.Message;
import org.thespherret.plugins.koth.utils.Chat;

public class NextArenaCommand extends Command {

	@Override
	public void execute()
	{
		Chat.sendFormattedMessage(p, Message.NEXT_ARENA_IN, Time.currentTime().compare(cm.getMain().getDM().getNextDate().getTime()).toString());
	}
}
