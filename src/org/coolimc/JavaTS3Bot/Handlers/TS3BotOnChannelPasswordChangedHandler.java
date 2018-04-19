package org.coolimc.JavaTS3Bot.Handlers;

import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.ChannelPasswordChangedEvent;

public interface TS3BotOnChannelPasswordChangedHandler
{
	public void fireEvent(ChannelPasswordChangedEvent e);
}
