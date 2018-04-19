package org.coolimc.JavaTS3Bot.Handlers;

import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.ChannelEditedEvent;

public interface TS3BotOnChannelEditHandler
{
	public void fireEvent(ChannelEditedEvent e);
}
