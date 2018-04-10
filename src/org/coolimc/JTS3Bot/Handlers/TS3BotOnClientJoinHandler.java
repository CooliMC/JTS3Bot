package org.coolimc.JTS3Bot.Handlers;

import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;

public interface TS3BotOnClientJoinHandler
{
	public void fireEvent(ClientJoinEvent e);
}
