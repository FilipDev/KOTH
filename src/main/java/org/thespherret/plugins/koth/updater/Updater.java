package org.thespherret.plugins.koth.updater;

import org.bukkit.Bukkit;
import org.thespherret.plugins.koth.Main;

public class Updater {

	private Main main;

	public Updater(Main main)
	{
		this.main = main;

		Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
			@Override
			public void run()
			{
				for (UpdateType updateType : UpdateType.values())
					if (updateType.elapsed())
						Bukkit.getPluginManager().callEvent(new UpdateEvent(updateType));
			}
		}, 1L, 1L);
	}

}
