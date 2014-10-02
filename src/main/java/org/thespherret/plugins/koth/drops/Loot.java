package org.thespherret.plugins.koth.drops;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.thespherret.plugins.koth.utils.Chat;

import java.util.ArrayList;
import java.util.List;

public class Loot {

	private LootManager lootManager;

	private ItemStack itemStack;

	public Loot(int n, LootManager lootManager)
	{
		this.lootManager = lootManager;

		ConfigurationSection data = lootManager.getMain().loot.getConfigurationSection("items." + n);

		if (data != null)
		{
			ItemStack itemStack = new ItemStack(Material.matchMaterial(data.getString("id")));
			itemStack.setAmount(data.getInt("amount", 1));
			itemStack.setDurability((short) data.getInt("data", 0));
			ItemMeta itemMeta = Bukkit.getItemFactory().getItemMeta(itemStack.getType());
			for (ReadyEnchantment readyEnchantment : getEnchantment(data))
				itemMeta.addEnchant(readyEnchantment.getEnchantment(), readyEnchantment.getLevel(), false);

			if (data.get("name") != null)
				itemMeta.setDisplayName(Chat.colorize(data.getString("name")));
			if (data.get("lore") != null)
			{
				ArrayList<String> colouredLore = new ArrayList<>();
				for (String loreString : data.getStringList("lore"))
					colouredLore.add(Chat.colorize(loreString));
				itemMeta.setLore(colouredLore);
			}
			itemStack.setItemMeta(itemMeta);

			this.itemStack = itemStack;
		}
		else
		{
			this.itemStack = new ItemStack(Material.AIR);
		}
	}

	public ItemStack getLoot()
	{
		return this.itemStack;
	}

	private static List<ReadyEnchantment> getEnchantment(ConfigurationSection section)
	{
		List<ReadyEnchantment> enchantments = new ArrayList<>();
		for (String enchString : section.getStringList(".enchantments"))
		{
			String name = enchString.split(":")[0];
			int level = Integer.parseInt(enchString.split(":")[1]);
			enchantments.add(new ReadyEnchantment(Enchantment.getByName(name.toUpperCase()), level));
		}
		return enchantments;
	}

}
