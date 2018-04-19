package org.coolimc.JavaTS3Bot.Handlers;

import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.ChannelMovedEvent;

public interface TS3BotOnChannelMovedHandler
{
	public void fireEvent(ChannelMovedEvent e);
}
