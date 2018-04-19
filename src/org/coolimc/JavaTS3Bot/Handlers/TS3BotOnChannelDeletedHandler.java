package org.coolimc.JavaTS3Bot.Handlers;

import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.ChannelDeletedEvent;

public interface TS3BotOnChannelDeletedHandler
{
	public void fireEvent(ChannelDeletedEvent e);
}
