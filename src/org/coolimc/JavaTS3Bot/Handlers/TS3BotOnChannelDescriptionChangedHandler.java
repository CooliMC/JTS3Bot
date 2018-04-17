package org.coolimc.JavaTS3Bot.Handlers;

import com.github.theholywaffle.teamspeak3.api.event.ChannelDescriptionEditedEvent;

public interface TS3BotOnChannelDescriptionChangedHandler
{
	public void fireEvent(ChannelDescriptionEditedEvent e);
}
