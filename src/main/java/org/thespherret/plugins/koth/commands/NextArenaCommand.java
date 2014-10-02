package org.thespherret.plugins.koth.commands;

import org.thespherret.plugins.koth.date.Date;
import org.thespherret.plugins.koth.date.time.Time;
import org.thespherret.plugins.koth.messages.Message;
import org.thespherret.plugins.koth.utils.Chat;

public class NextArenaCommand extends Command {

	@Override
	public void execute()
	{
		Date date = cm.getMain().getDM().getNextArena();
		String time = Message.GAME_STARTING_TOMORROW.toString();
		if (!date.isTomorrow())
			time = Time.compareInaccurate(cm.getMain().getDM().getNextArena().getInaccurateTime(), Time.currentInnacurateTime()).toString();
		Chat.sendFormattedMessage(p, date.isTomorrow() ? Message.GAME_STARTING_TOMORROW : Message.GAME_STARTING_IN, time);
	}
}
