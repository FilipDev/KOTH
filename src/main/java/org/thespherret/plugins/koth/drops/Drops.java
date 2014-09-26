package org.thespherret.plugins.koth.drops;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.thespherret.plugins.koth.utils.Chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Drops {

	private static Random random = new Random();

	public static ItemStack getDrop(Plugin main, String key) {

		int number = randomNumber(100);

		int selected = Integer.parseInt(key.split(" - ")[0]);

		if (number < selected)
		{
			ConfigurationSection data = main.getConfig().getConfigurationSection("options.items." + key);

			ItemStack itemStack = new ItemStack(Material.matchMaterial(key.split(" - ")[1]));
			itemStack.setAmount(data.getInt("amount", 1));
			itemStack.setDurability((short) data.getInt("data", 0));
			ItemMeta itemMeta = Bukkit.getItemFactory().getItemMeta(itemStack.getType());
			for (ReadyEnchantment readyEnchantment : getEnchantment(data))
				itemMeta.addEnchant(readyEnchantment.enchantment, readyEnchantment.level, false);

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

			return itemStack;
		}
		return null;
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

	public static int randomNumber(int max)
	{
		return random.nextInt(max);
	}

	public static boolean checkRandomNumber(int max)
	{
		return randomNumber(max) == 0;
	}

	public static class ReadyEnchantment {

		public Enchantment getEnchantment()
		{
			return enchantment;
		}

		public void setEnchantment(Enchantment enchantment)
		{
			this.enchantment = enchantment;
		}

		public int getLevel()
		{
			return level;
		}

		public void setLevel(int level)
		{
			this.level = level;
		}

		private Enchantment enchantment;
		private int level;

		public ReadyEnchantment(Enchantment enchantment, int level)
		{
			this.enchantment = enchantment;
			this.level = level;
		}

	}

}
