package org.thespherret.plugins.koth.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.thespherret.plugins.koth.Main;
import org.thespherret.plugins.koth.messages.Message;
import org.thespherret.plugins.koth.utils.Permissions;

public class LootCommand extends Command implements Listener {

	public LootCommand()
	{
		Bukkit.getPluginManager().registerEvents(this, Main.getMain());
	}

	@Override
	public void execute()
	{
		if (args.length == 0)
			p.openInventory(cm.getMain().getLM().getInventory());
		if (args.length == 1)
		{
			if (Permissions.isAdmin(p))
			{
				if (args[0].equalsIgnoreCase("edit"))
				{
					edit();
				}
			}
			if (args[0].equalsIgnoreCase("claim"))
			{
				claim();
			}
		}
	}

	private void claim()
	{
		p.openInventory(cm.getMain().getLM().getClaimInventory(cm.getMain().getPM().getSlots(p)));
	}

	private void edit()
	{
		p.openInventory(cm.getMain().getLM().getEditableInventory());
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e)
	{
		if (e.getInventory().getName().equals(Message.REWARD_CLAIM_INVENTORY_NAME.toString()))
		{
			for (ItemStack itemStack : e.getInventory().getContents())
				if (itemStack != null)
					e.getPlayer().getWorld().dropItemNaturally(e.getPlayer().getLocation(), itemStack);
		}

		if (e.getInventory().getName().equals(Message.REWARD_EDIT_INVENTORY_NAME.toString()))
		{
			cm.getMain().getLM().storeEdits(e.getInventory());
		}
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e)
	{
		if (e.getInventory().getName().equals(Message.REWARD_VIEW_POSSIBLE_INVENTORY_NAME.toString()))
			e.setCancelled(true);

		if (e.getInventory().getName().equals(Message.REWARD_CLAIM_INVENTORY_NAME.toString()))
		{
			Player player = (Player) e.getViewers().get(0);

			player.getWorld().dropItem(player.getLocation(), e.getCurrentItem());

			e.getCurrentItem().setType(Material.AIR);
		}
	}

	private boolean isInventoryEmpty(Inventory inventory)
	{
		boolean itemFound = false;

		for (ItemStack itemStack : inventory.getContents())
			if (itemStack != null)
				itemFound = true;
		return !itemFound;
	}
}
