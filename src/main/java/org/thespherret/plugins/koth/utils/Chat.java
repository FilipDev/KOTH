package org.thespherret.plugins.koth.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.thespherret.plugins.koth.messages.Error;
import org.thespherret.plugins.koth.messages.Message;

import java.util.concurrent.TimeUnit;

public class Chat {

	public static String getFormatted(String s, Object... strings)
	{
		String s1 = s;
		for (Object s2 : strings)
			s1 = s1.replaceFirst("%v", String.valueOf(s2));
		return colorize(s1);
	}

	public static String calculateTime(long millis)
	{
		long hours = TimeUnit.MILLISECONDS.toHours(millis);
		millis -= TimeUnit.HOURS.toMillis(hours);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
		millis -= TimeUnit.MINUTES.toMillis(minutes);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

		return (hours == 0 ? "" : hours + " hours ") + (minutes == 0 ? "" : minutes + " minutes ") + seconds + " seconds";
	}

	public static void sendMessage(CommandSender sender, Message message)
	{
		sender.sendMessage(message.toString());
	}

	public static void sendFormattedMessage(CommandSender sender, Message message, String... strings)
	{
		sender.sendMessage(message.getFormatted(strings));
	}

	public static void sendError(CommandSender sender, Error error, String... strings)
	{
		sender.sendMessage(error.getFormatted(strings));
	}

	public static String colorize(String s)
	{
		return ChatColor.translateAlternateColorCodes('&', s);
	}

}
