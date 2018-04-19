package org.coolimc.JavaTS3Bot.Handlers;

import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.ServerEditedEvent;

public interface TS3BotOnServerEditHandler
{
	public void fireEvent(ServerEditedEvent e);
}
