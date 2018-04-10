package org.coolimc.JTS3Bot.Handlers;

import org.coolimc.JTS3Bot.Events.TS3BotClientLeaveEvent;

public interface TS3BotOnClientLeaveHandler
{
	public void fireEvent(TS3BotClientLeaveEvent e);
}
