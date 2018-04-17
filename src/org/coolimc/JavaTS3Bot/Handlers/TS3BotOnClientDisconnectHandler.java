package org.coolimc.JavaTS3Bot.Handlers;

import org.coolimc.JavaTS3Bot.Events.TS3BotClientDisconnectEvent;

public interface TS3BotOnClientDisconnectHandler
{
	public void fireEvent(TS3BotClientDisconnectEvent e);
}
