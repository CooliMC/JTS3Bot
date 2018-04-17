package org.coolimc.JavaTS3Bot.Handlers;

import org.coolimc.JavaTS3Bot.Events.TS3BotClientTimeoutEvent;

public interface TS3BotOnClientTimeoutHandler
{
	public void fireEvent(TS3BotClientTimeoutEvent e);
}
