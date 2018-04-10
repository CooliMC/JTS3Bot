package org.coolimc.JTS3Bot.Handlers;

import org.coolimc.JTS3Bot.Events.TS3BotClientTimeoutEvent;

public interface TS3BotOnClientTimeoutHandler
{
	public void fireEvent(TS3BotClientTimeoutEvent e);
}
