package org.thespherret.plugins.koth.commands;

import org.thespherret.plugins.koth.messages.Error;
import org.thespherret.plugins.koth.messages.Message;
import org.thespherret.plugins.koth.utils.Chat;
import org.thespherret.plugins.koth.utils.Permissions;

public class SetCaptureCommand extends Command {

	@Override
	public void execute()
	{
		if (Permissions.isAdmin(p))
		{
			if (args.length > 1)
			{
				Integer capturePoint;
				try {
					capturePoint = Integer.parseInt(args[1]);
				} catch (NumberFormatException e)
				{
					Chat.sendError(p, Error.INVALID_ARGUMENT, args[1]);
					return;
				}

				cm.getMain().getAM().setArenaCapturePoint(args[0], capturePoint, p.getLocation());
				Chat.sendMessage(p, Message.SET_CAPTURE_POINT, String.valueOf(capturePoint), args[0]);
			}
		}
	}
}
