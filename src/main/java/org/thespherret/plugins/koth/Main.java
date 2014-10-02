package org.thespherret.plugins.koth;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.thespherret.plugins.koth.calendar.Calendar;
import org.thespherret.plugins.koth.drops.LootManager;
import org.thespherret.plugins.koth.managers.*;
import org.thespherret.plugins.koth.messages.Message;
import org.thespherret.plugins.koth.queue.RejoinReminder;
import org.thespherret.plugins.koth.utils.Chat;
import org.thespherret.plugins.koth.utils.NewYAML;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main extends JavaPlugin {

	public NewYAML arenas1, playerData1, kits1, messages1, loot1;
	public YamlConfiguration arenas, playerData, kits, messages, loot;

	CommandManager cm;
	ArenaManager am;
	PlayerManager pm;
	DateManager dm;
	QueueManager qm;
	LootManager lm;

	RejoinReminder rejoinReminder;

	Calendar calendar;

	private static String pluginName;

	public static Main getMain()
	{
		return ((Main) Bukkit.getPluginManager().getPlugin(Main.pluginName));
	}

	public static String PREFIX;

	public void onEnable()
	{
		Main.pluginName = getDescription().getName();
		Main.PREFIX = ChatColor.WHITE + "[" + ChatColor.DARK_GRAY + Main.pluginName + ChatColor.WHITE + "] ";

		this.arenas = (this.arenas1 = new NewYAML(new File(getDataFolder(), "arenas.dat"))).newYaml();
		this.kits = (this.kits1 = new NewYAML(new File(getDataFolder(), "kits.dat"))).newYaml();
		this.playerData = (this.playerData1 = new NewYAML(new File(getDataFolder(), "players.dat"))).newYaml();
		this.loot = (this.loot1 = new NewYAML(new File(getDataFolder(), "loot.dat"))).newYaml();

		this.cm = new CommandManager(this);
		this.am = new ArenaManager(this);
		this.pm = new PlayerManager(this);
		this.dm = new DateManager(this);
		this.qm = new QueueManager(this);
		this.lm = new LootManager(this);

		this.am.initArenas();

		this.calendar = new Calendar(this);

		generateMessages();
		this.saveDefaultConfig();

		Chat.sendMessage(Bukkit.getConsoleSender(), Message.INITIALIZING);

		for (String command : getDescription().getCommands().keySet())
			getCommand(command).setExecutor(cm);
	}

	public void onDisable()
	{
		if (this.am.getCurrentArena() != null)
			if (this.am.getCurrentArena().hasStarted())
				this.am.getCurrentArena().endArena();
		this.saveConfig();

		lm.saveRewards();
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
				{
					System.out.println(line);
					System.out.println(line.split(": ").length);
					messages.set(line.split(": ")[0], line.split(": ")[1]);
				}
				messages.save(messages1.getFile());
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else
			messages = (messages1 = new NewYAML(messagesFile)).newYaml1();
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

	public DateManager getDM()
	{
		return this.dm;
	}

	public LootManager getLM()
	{
		return lm;
	}

	public QueueManager getQM()
	{
		return qm;
	}

	public Calendar getCalendar()
	{
		return this.calendar;
	}
}
