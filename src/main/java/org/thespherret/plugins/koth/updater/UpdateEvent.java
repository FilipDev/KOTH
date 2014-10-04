package org.thespherret.plugins.koth.updater;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class UpdateEvent extends Event {

	private UpdateType updateType;

	private static final HandlerList handlers = new HandlerList();

	public UpdateEvent(UpdateType updateType)
	{
		this.updateType = updateType;
	}

	public UpdateType getUpdateType()
	{
		return updateType;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
