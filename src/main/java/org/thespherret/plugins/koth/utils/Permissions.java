package org.thespherret.plugins.koth.utils;

import org.bukkit.entity.Player;

public final class Permissions {

	public static boolean isAdmin(Player player)
	{
		return player.hasPermission("koth.admin") || player.isOp();
	}

}
