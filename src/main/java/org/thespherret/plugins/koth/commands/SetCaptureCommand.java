package org.thespherret.plugins.koth.commands;

import org.thespherret.plugins.koth.cuboid.Cuboid;
import org.thespherret.plugins.koth.messages.Error;
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

				Cuboid cuboid = cm.getMain().getAM().getArena(args[0]).getCuboid();

				if (capturePoint == 1)
					cuboid.setPoint1(p.getLocation());
				else if (capturePoint == 2)
					cuboid.setPoint2(p.getLocation());

			}
		}
	}
}
