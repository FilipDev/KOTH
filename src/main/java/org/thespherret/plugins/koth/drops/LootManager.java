package org.thespherret.plugins.koth.drops;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.thespherret.plugins.koth.Main;
import org.thespherret.plugins.koth.messages.Message;
import org.thespherret.plugins.koth.utils.RandomUtil;

import java.io.IOException;
import java.util.*;

public class LootManager {

	private Main main;
	private HashMap<String, Integer> rewardAmounts = new HashMap<>();
	private ItemStack[] rewardPool = new ItemStack[18];

	public LootManager(Main main)
	{
		this.main = main;
		registerArenaAmounts();
		registerRewards();
	}

	public Main getMain()
	{
		return this.main;
	}

	private void registerReward(int n, Loot loot)
	{
		this.rewardPool[n] = (loot.getLoot());
	}

	private void registerArenaAmount(String key, int amount)
	{
		this.rewardAmounts.put(key, amount);
	}

	private void registerArenaAmounts()
	{
		System.out.println(main.arenas);
		if (main.arenas.isConfigurationSection("arena"))
			for (String key : main.arenas.getConfigurationSection("arena").getKeys(false))
				registerArenaAmount(key, main.arenas.getInt("arena." + key + ".reward-amount", 0));
	}

	private void registerRewards()
	{
		if (main.loot.isConfigurationSection("items"))
		{
			Set<String> keySet = main.loot.getConfigurationSection("items").getKeys(false);
			List<String> keys = Arrays.asList(keySet.toArray(new String[keySet.size()]));

			System.out.println(keys);

			for (int x = 0; x <= keys.size() - 1; x++)
			{
				registerReward(x, new Loot(x, this));
			}
		}
	}

	public void storeEdits(Inventory inventory)
	{
		for (int x = 0; x <= 17; x++)
		{
			this.rewardPool[x] = inventory.getContents()[x];
		}
	}

	public int getRewards(String arena)
	{
		return main.arenas.getInt("arena." + arena + ".reward-amount", 0);
	}

	public Inventory getInventory()
	{
		Inventory inventory = Bukkit.createInventory(null, 18, Message.REWARD_VIEW_POSSIBLE_INVENTORY_NAME.toString());
		ArrayList<ItemStack> itemStackList = new ArrayList<>();

		Collections.addAll(itemStackList, rewardPool);

		inventory.setContents(itemStackList.toArray(new ItemStack[itemStackList.size()]));
		return inventory;
	}

	public Inventory getEditableInventory()
	{
		Inventory inventory = Bukkit.createInventory(null, 18, Message.REWARD_EDIT_INVENTORY_NAME.toString());
		ArrayList<ItemStack> itemStackList = new ArrayList<>();

		Collections.addAll(itemStackList, rewardPool);

		inventory.setContents(itemStackList.toArray(new ItemStack[itemStackList.size()]));
		return inventory;
	}

	public Inventory getClaimInventory(int amount)
	{
		Inventory inventory = Bukkit.createInventory(null, 18, Message.REWARD_CLAIM_INVENTORY_NAME.toString());
		ArrayList<ItemStack> itemStackList = new ArrayList<>();

		for (int x = 0; x <= amount; x++)
			itemStackList.add(rewardPool[RandomUtil.randomNumber(rewardPool.length - 1)]);

		inventory.setContents(itemStackList.toArray(new ItemStack[itemStackList.size()]));
		return inventory;
	}

	public void saveReward(int n, ItemStack itemStack)
	{
		if (!getMain().loot.isConfigurationSection("items." + n))
			getMain().loot.createSection("items." + n);

		if (itemStack == null)
			itemStack = new ItemStack(Material.AIR);

		ConfigurationSection data = getMain().loot.getConfigurationSection("items." + n);

		data.set("id", itemStack.getType().name());

		data.set("amount", itemStack.getAmount());
		data.set("data", itemStack.getDurability());
		if (itemStack.hasItemMeta())
		{
			data.set("name", itemStack.getItemMeta().getDisplayName());
			data.set("lore", itemStack.getItemMeta().getLore());
		}
		for (Map.Entry<Enchantment, Integer> enchanmentEntry : itemStack.getEnchantments().entrySet())
			data.set("enchantments", enchanmentEntry.getKey().getName() + ":" + enchanmentEntry.getValue());

		saveLoot();
	}

	private void saveLoot()
	{
		try
		{
			getMain().loot.save(getMain().loot1.getFile());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void saveRewards(Inventory inventory)
	{
		for (int x = 0; x <= 17; x++)
		{
			ItemStack itemStack = inventory.getContents()[x];
				saveReward(x, itemStack);
		}
	}

	public void saveRewards()
	{
		for (int x = 0; x <= 17; x++)
		{
			ItemStack itemStack = this.rewardPool[x];
			saveReward(x, itemStack);
		}
	}

}
