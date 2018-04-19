package org.coolimc.JavaTS3Bot.Handlers;

import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.ChannelDescriptionEditedEvent;

public interface TS3BotOnChannelDescriptionChangedHandler
{
	public void fireEvent(ChannelDescriptionEditedEvent e);
}
