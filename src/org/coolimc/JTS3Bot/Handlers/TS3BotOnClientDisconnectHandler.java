package org.coolimc.JTS3Bot.Handlers;

import org.coolimc.JTS3Bot.Events.TS3BotClientDisconnectEvent;

public interface TS3BotOnClientDisconnectHandler
{
	public void fireEvent(TS3BotClientDisconnectEvent e);
}
