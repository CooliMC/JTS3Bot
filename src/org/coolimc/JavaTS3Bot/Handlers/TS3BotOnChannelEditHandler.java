package org.coolimc.JavaTS3Bot.Handlers;

import com.github.theholywaffle.teamspeak3.api.event.ChannelEditedEvent;

public interface TS3BotOnChannelEditHandler
{
	public void fireEvent(ChannelEditedEvent e);
}
