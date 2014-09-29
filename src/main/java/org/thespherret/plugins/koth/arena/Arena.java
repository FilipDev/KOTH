package org.thespherret.plugins.koth.arena;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.thespherret.plugins.koth.Main;
import org.thespherret.plugins.koth.checker.PointManager;
import org.thespherret.plugins.koth.countdown.Countdown;
import org.thespherret.plugins.koth.cuboid.Cuboid;
import org.thespherret.plugins.koth.managers.ArenaManager;
import org.thespherret.plugins.koth.messages.Message;
import org.thespherret.plugins.koth.utils.Chat;

import java.util.HashSet;

public class Arena implements Listener {

	ArenaManager am;

	private Cuboid cuboid;
	private Main main;
	private Countdown countdown;
	private Location spawnPoint;
	private String name;
	private boolean started;
	private HashSet<Player> players = new HashSet<>();

	private boolean invulnerable = false;

	private PointManager checker;

	public ConfigurationSection getConfigurationSection()
	{
		return configurationSection;
	}

	private ConfigurationSection configurationSection;

	public Arena(ArenaManager am, String arenaName)
	{
		this.name = arenaName;
		this.spawnPoint = am.getSpawnPoint(arenaName);
		this.am = am;
		this.main = am.getMain();
		this.countdown = new Countdown(this);

		this.configurationSection = main.arenas.getConfigurationSection("arenas." + arenaName);
	}

	public String getArenaName()
	{
		return this.name;
	}

	public void addPlayers()
	{
		for (Player player : players)
		{
			Chat.sendMessage(player, Message.STARTING);
			player.teleport(spawnPoint);
		}
	}

	public HashSet<Player> getPlayers()
	{
		return this.players;
	}

	public Cuboid getCuboid()
	{
		return cuboid;
	}

	public boolean hasStarted()
	{
		return started;
	}

	public void setSpawn(Location newSpawn)
	{
		am.setArenaSpawn(this.getArenaName(), newSpawn);
	}

	public void endArena()
	{
		if (!this.hasStarted())
			return;

		this.started = false;
		for (Player player : players)
		{
			this.main.getPM().revertPlayer(player);
		}
	}

	public void startArena()
	{
		if (this.hasStarted())
			return;

		this.started = true;
		this.invulnerable = true;
		addPlayers();

		this.checker = new PointManager(main);
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e)
	{
		Player player = e.getEntity();
		if (players.contains(player))
		{
			e.setDroppedExp(0);
			e.getDrops().clear();
			e.setDeathMessage(Message.DEATH.getFormatted(player.getName()));
			players.remove(player);
		}

		if (this.getPlayers().size() == 1)
		{

		}
	}

	@EventHandler
	public void onPlayerDamage(EntityDamageByEntityEvent e)
	{
		if (invulnerable)
			if (e.getEntity().getType() == EntityType.PLAYER)
				if (this.getPlayers().contains(e.getEntity()))
					e.setCancelled(true);
	}

}
