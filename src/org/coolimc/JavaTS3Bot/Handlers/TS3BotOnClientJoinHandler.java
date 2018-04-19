package org.coolimc.JavaTS3Bot.Handlers;

import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.ClientJoinEvent;

public interface TS3BotOnClientJoinHandler
{
	public void fireEvent(ClientJoinEvent e);
}
