package org.thespherret.plugins.koth.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.thespherret.plugins.koth.Main;

import java.io.IOException;

public class PlayerManager {

	final Main main;

	public PlayerManager(Main main)
	{
		this.main = main;
	}

	public void addSlots(Player player, int slots)
	{
		if (main.playerData.isConfigurationSection("claimable-rewards." + player.getUniqueId().toString() + ".slots"))
			main.playerData.set("claimable-rewards." + player.getUniqueId().toString() + ".slots", main.playerData.getInt("claimable-rewards." + player.getUniqueId().toString() + ".slots") + slots);
	}

	public int getSlots(Player player)
	{
		return main.playerData.getInt("claimable-rewards." + player.getUniqueId().toString() + ".slots");
	}

	public void loadInventory(Player p)
	{
		ItemStack[] invMain = new ItemStack[0], invArmor = new ItemStack[0];
		try {
			invMain = (ItemStack[]) main.playerData.get(p.getUniqueId().toString() + ".inventoryMain");
			invArmor = (ItemStack[]) main.playerData.get(p.getUniqueId().toString() + ".inventoryArmor");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!(invMain == null || invArmor == null)){
			try {
				p.getInventory().setContents(invMain);
				p.getInventory().setArmorContents(invArmor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void saveInventory(Player p)
	{
		try {
			main.playerData.set(p.getUniqueId().toString() + ".inventoryMain", p.getInventory().getContents());
			main.playerData.set(p.getUniqueId().toString() + ".inventoryArmor", p.getInventory().getArmorContents());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveData(Player p)
	{
		main.playerData.set(p.getUniqueId().toString() + ".world", p.getLocation().getWorld().getName());
		main.playerData.set(p.getUniqueId().toString() + ".x", p.getLocation().getX());
		main.playerData.set(p.getUniqueId().toString() + ".y", p.getLocation().getY());
		main.playerData.set(p.getUniqueId().toString() + ".z", p.getLocation().getZ());
		main.playerData.set(p.getUniqueId().toString() + ".pitch", p.getLocation().getPitch());
		main.playerData.set(p.getUniqueId().toString() + ".yaw", p.getLocation().getYaw());
	}

	public void revertPlayer(Player p)
	{
		ConfigurationSection data;
		try
		{
			data = main.playerData.getConfigurationSection(p.getUniqueId().toString());
		}
		catch (Exception e) {
			e.printStackTrace();
			return;
		}
		Location location = new Location(Bukkit.getWorld(data.getString("world", "world")), data.getDouble("x"), data.getDouble("y"), data.getDouble("z"), data.getInt("pitch"), data.getInt("yaw"));
		try
		{
			main.playerData.save(main.playerData1.getFile());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		main.getAM().teleportNoChecks(location, p);
	}
}
