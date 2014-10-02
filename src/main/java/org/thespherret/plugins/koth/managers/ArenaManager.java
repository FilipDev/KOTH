package org.thespherret.plugins.koth.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.thespherret.plugins.koth.arena.Arena;
import org.thespherret.plugins.koth.Main;

import java.io.IOException;
import java.util.HashMap;

public class ArenaManager {

	final Main main;
	private final int matchDelay;

	private Arena currentArena;

	public final HashMap<String, Arena> arenas = new HashMap<>();

	public Arena getCurrentArena()
	{
		return this.currentArena;
	}

	public void setCurrentArena(Arena arena)
	{
		this.currentArena = arena;
	}

	public ArenaManager(Main main)
	{
		this.main = main;
		this.matchDelay = main.getConfig().getInt("match-start-delay", 30);
	}

	public Arena getArena(String arenaName)
	{
		return arenas.get(arenaName);
	}

	public void addArena(Arena arena)
	{
		arenas.put(arena.getArenaName(), arena);
	}

	public void initArenas()
	{
		try{
			for (String arenaString : main.arenas.getConfigurationSection("arenas").getKeys(false))
			{
				Bukkit.getConsoleSender().sendMessage("Initializing arena " + arenaString + ".");
				this.addArena(new Arena(this, arenaString));
			}
		}catch (NullPointerException ignored){}
	}

	public World getWorld(String arenaName)
	{
		return Bukkit.getWorld(main.arenas.getString("arenas." + arenaName + ".1.world"));
	}

	public Location getSpawnPoint(String arenaName)
	{
		return new Location(getWorld(arenaName), main.arenas.getDouble("arenas." + arenaName + ".x"), main.arenas.getDouble("arenas." + arenaName + ".y"), main.arenas.getDouble("arenas." + arenaName + ".z"), main.arenas.getInt("arenas." + arenaName + ".yaw"), main.arenas.getInt("arenas." + arenaName + ".pitch"));
	}

	public void setArenaSpawn(String arenaName, Location location)
	{
		main.arenas.set("arenas." + arenaName + ".world", location.getWorld().getName());
		main.arenas.set("arenas." + arenaName + ".x", location.getX());
		main.arenas.set("arenas." + arenaName + ".y", location.getY());
		main.arenas.set("arenas." + arenaName + ".z", location.getZ());
		main.arenas.set("arenas." + arenaName + ".yaw", location.getYaw());
		main.arenas.set("arenas." + arenaName + ".pitch", location.getPitch());
		try {
			if (!main.arenas1.getFile().exists())
				if (main.arenas1.getFile().createNewFile())
					main.arenas.save(main.arenas1.getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		initArenas();
	}

	public void setArenaCapturePoint(String arenaName, int arenaCapturePoint, Location location)
	{
		ConfigurationSection section = getArena(arenaName).getConfigurationSection();
		section.set("capturepoint." + arenaCapturePoint + ".x", location.getBlockX());
		section.set("capturepoint." + arenaCapturePoint + ".y", location.getBlockY());
		section.set("capturepoint." + arenaCapturePoint + ".z", location.getBlockZ());
	}

	public void createArena(String arenaName)
	{
		main.arenas.set("arenas." + arenaName, "");
	}

	public Main getMain()
	{
		return this.main;
	}

	public void lobbyTeleport(Player p)
	{
		this.teleportNoChecks(new Location(Bukkit.getWorld(main.getConfig().getString("lobby.world")), main.getConfig().getDouble("lobby.x"), main.getConfig().getDouble("lobby.y"), main.getConfig().getDouble("lobby.z"), main.getConfig().getInt("lobby.yaw"), main.getConfig().getInt("lobby.pitch")), p);
	}

	public Integer getMatchStartDelay()
	{
		return this.matchDelay;
	}

	public void teleportNoChecks(Location loc, Player p)
	{
		p.teleport(loc, PlayerTeleportEvent.TeleportCause.ENDER_PEARL);
	}

	public HashMap<String, Arena> getArenaMap()
	{
		return this.arenas;
	}

}