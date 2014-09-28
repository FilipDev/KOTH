package org.thespherret.plugins.koth.drops;

import org.bukkit.enchantments.Enchantment;

public class ReadyEnchantment {

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