package org.coolimc.JavaTS3Bot.Handlers;

import org.coolimc.JavaTS3Bot.Events.TS3BotClientMovedEvent;

public interface TS3BotOnClientMovedHandler
{
	public void fireEvent(TS3BotClientMovedEvent e);
}
