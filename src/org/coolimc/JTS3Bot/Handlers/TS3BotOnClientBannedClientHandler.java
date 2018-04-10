package org.coolimc.JTS3Bot.Handlers;

import org.coolimc.JTS3Bot.Events.TS3BotClientBannedEvent;

public interface TS3BotOnClientBannedClientHandler
{
	public void fireEvent(TS3BotClientBannedEvent e);
}