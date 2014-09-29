package org.thespherret.plugins.koth.drops;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.thespherret.plugins.koth.Main;
import org.thespherret.plugins.koth.messages.Message;
import org.thespherret.plugins.koth.utils.RandomUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LootManager {

	private Main main;
	private HashMap<String, Integer> rewardAmounts = new HashMap<>();
	private List<Loot> rewardPool = new ArrayList<>();

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

	private void registerReward(Loot loot)
	{
		this.rewardPool.add(loot);
	}

	private void registerArenaAmount(String key, int amount)
	{
		this.rewardAmounts.put(key, amount);
	}

	private void registerArenaAmounts()
	{
		for (String key : main.arenas.getConfigurationSection("arena").getKeys(false))
			registerArenaAmount(key, main.arenas.getInt("arena." + key + ".reward-amount", 0));
	}

	private void registerRewards()
	{
		for (String key : main.loot.getConfigurationSection("items").getKeys(false))
			registerReward(new Loot(key));
	}

	public int getRewards(String arena)
	{
		return main.arenas.getInt("arena." + arena + ".reward-amount", 0);
	}

	public Inventory getInventory()
	{
		Inventory inventory = Bukkit.createInventory(null, 18, Message.REWARD_INVENTORY_NAME.toString());

		ArrayList<ItemStack> itemStackList = new ArrayList<>();

		for (Loot loot : rewardPool)
		{
			itemStackList.add(loot.getLoot());
		}

		inventory.setContents(itemStackList.toArray(new ItemStack[itemStackList.size()]));

		return inventory;
	}

	public Inventory getEditableInventory()
	{
		Inventory inventory = Bukkit.createInventory(null, 18, Message.REWARD_EDIT_INVENTORY_NAME.toString());

		ArrayList<ItemStack> itemStackList = new ArrayList<>();

		for (Loot loot : rewardPool)
		{
			itemStackList.add(loot.getLoot());
		}

		inventory.setContents(itemStackList.toArray(new ItemStack[itemStackList.size()]));

		return inventory;
	}

	public Inventory getClaimInventory(int amount)
	{
		Inventory inventory = Bukkit.createInventory(null, 18, Message.REWARD_CLAIM_INVENTORY_NAME.toString());

		ArrayList<ItemStack> itemStackList = new ArrayList<>();

		for (int x = 0; x <= amount; x++)
			itemStackList.add(rewardPool.get(RandomUtil.randomNumber(rewardPool.size() - 1)).getLoot());

		inventory.setContents(itemStackList.toArray(new ItemStack[itemStackList.size()]));

		return inventory;
	}

}
