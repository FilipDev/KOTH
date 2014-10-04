package org.thespherret.plugins.koth.commands;

import org.thespherret.plugins.koth.utils.Chat;

public class NextArenaCommand extends Command {

	@Override
	public void execute()
	{
		Chat.sendRemainingTime(p, cm.getMain());
	}
}
