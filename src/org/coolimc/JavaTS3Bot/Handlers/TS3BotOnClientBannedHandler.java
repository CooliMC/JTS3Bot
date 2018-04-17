package org.coolimc.JavaTS3Bot.Handlers;

import org.coolimc.JavaTS3Bot.Events.TS3BotClientBannedEvent;

public interface TS3BotOnClientBannedHandler
{
	public void fireEvent(TS3BotClientBannedEvent e);
}
