package org.thespherret.plugins.koth.drops;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
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
	}

	public Main getMain()
	{
		return this.main;
	}

	public void registerReward(Loot loot)
	{
		this.rewardPool.add(loot);
	}

	public void registerRewards()
	{
		for (String key : main.loot.getConfigurationSection("loot").getKeys(false))
			registerReward(new Loot(key));
	}

	public List<Loot> getRewards(String arena)
	{
		ArrayList<Loot> loot = new ArrayList<>();

		for (int x = 0; x <= rewardAmounts.get(arena); x++)
			loot.add(rewardPool.get(RandomUtil.randomNumber(rewardPool.size() - 1)));

		return loot;
	}

	public Inventory getInventory()
	{
		return Bukkit.createInventory(null, 18, Message.REWARD_INVENTORY_NAME.toString());
	}

}
