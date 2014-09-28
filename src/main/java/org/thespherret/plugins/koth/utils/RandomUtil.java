package org.thespherret.plugins.koth.utils;

import java.util.Random;

public final class RandomUtil {

	private static Random random = new Random();

	public static int randomNumber(int max)
	{
		return random.nextInt(max);
	}

	public static boolean checkRandomNumber(int max)
	{
		return randomNumber(max) == 0;
	}

	public static Random getRandom()
	{
		return random;
	}
}