package org.coolimc.JavaTS3Bot.Handlers;

import org.coolimc.JavaTS3Bot.Events.TS3BotClientLeaveEvent;

public interface TS3BotOnClientLeaveHandler
{
	public void fireEvent(TS3BotClientLeaveEvent e);
}
