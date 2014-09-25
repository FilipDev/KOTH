package org.thespherret.plugins.koth.updater;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class UpdateEvent extends Event {

	private UpdateType updateType;

	private static final HandlerList handlerList = new HandlerList();

	public UpdateEvent(UpdateType updateType)
	{
		this.updateType = updateType;
	}

	public UpdateType getUpdateType()
	{
		return updateType;
	}

	@Override
	public HandlerList getHandlers()
	{
		return handlerList;
	}
}
