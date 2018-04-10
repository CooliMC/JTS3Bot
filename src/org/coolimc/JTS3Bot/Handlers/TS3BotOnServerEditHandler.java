package org.coolimc.JTS3Bot.Handlers;

import com.github.theholywaffle.teamspeak3.api.event.ServerEditedEvent;

public interface TS3BotOnServerEditHandler
{
	public void fireEvent(ServerEditedEvent e);
}
