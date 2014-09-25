package org.thespherret.plugins.koth;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.thespherret.plugins.koth.calendar.Calendar;
import org.thespherret.plugins.koth.managers.ArenaManager;
import org.thespherret.plugins.koth.managers.CommandManager;
import org.thespherret.plugins.koth.managers.PlayerManager;
import org.thespherret.plugins.koth.messages.Message;
import org.thespherret.plugins.koth.utils.NewYAML;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class Main extends JavaPlugin {

	public NewYAML arenas1, playerData1, kits1, messages1;
	public YamlConfiguration arenas, playerData, kits, messages;

	CommandManager cm;
	ArenaManager am;
	PlayerManager pm;

	Calendar calendar;

	private static String pluginName;

	public static Main getMain()
	{
		return ((Main) Bukkit.getPluginManager().getPlugin(Main.pluginName));
	}

	public final Events events = new Events(this);

	public static String PREFIX;

	public void onEnable()
	{
		Main.pluginName = getDescription().getName();
		Main.PREFIX = ChatColor.WHITE + "[" + ChatColor.DARK_GRAY + Main.pluginName + ChatColor.WHITE + "] ";

		this.cm = new CommandManager(this);
		this.am = new ArenaManager(this);
		this.pm = new PlayerManager(this);

		this.calendar = new Calendar(this);

		generateMessages();
		this.saveDefaultConfig();

		sendMessage(Bukkit.getConsoleSender(), Message.INITIALIZING);

		for (String command : getDescription().getCommands().keySet())
			getCommand(command).setExecutor(cm);

		this.arenas = (this.arenas1 = new NewYAML(new File(getDataFolder(), "arenas.dat"))).newYaml();
		this.kits = (this.kits1 = new NewYAML(new File(getDataFolder(), "kits.dat"))).newYaml();
		this.playerData = (this.playerData1 = new NewYAML(new File(getDataFolder(), "players.dat"))).newYaml();
		this.am.initArenas();
		Bukkit.getPluginManager().registerEvents(events, this);
	}

	public void onDisable()
	{
		if (this.am.getCurrentArena().hasStarted())
			this.am.getCurrentArena().endArena();
		this.saveConfig();
	}

	private void generateMessages()
	{
		File messagesFile = new File(getDataFolder(), "messages.yml");

		if (!messagesFile.exists())
		{
			messages = (messages1 = new NewYAML(messagesFile)).newYaml();
			BufferedReader input = new BufferedReader(new InputStreamReader(getClassLoader().getResourceAsStream("messages.yml")));
			String line;
			try {
				while ((line = input.readLine()) != null)
					messages.set(line.split(": ")[0], line.split(": ")[1]);
				messages.save(messages1.getFile());
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else
			messages = (messages1 = new NewYAML(messagesFile)).newYaml1();
	}

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

	public static String colorize(String s)
	{
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	public ArenaManager getAM()
	{
		return this.am;
	}

	public CommandManager getCM()
	{
		return this.cm;
	}

	public PlayerManager getPM()
	{
		return this.pm;
	}


}
