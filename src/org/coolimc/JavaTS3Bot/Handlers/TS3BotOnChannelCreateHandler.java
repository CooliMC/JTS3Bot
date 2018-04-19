package org.coolimc.JavaTS3Bot.Handlers;

import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.ChannelCreateEvent;

public interface TS3BotOnChannelCreateHandler
{
	public void fireEvent(ChannelCreateEvent e);
}
