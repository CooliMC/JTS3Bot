package org.coolimc.JTS3Bot.Handlers;

import org.coolimc.JTS3Bot.Events.TS3BotClientMovedEvent;

public interface TS3BotOnClientMovedHandler
{
	public void fireEvent(TS3BotClientMovedEvent e);
}
