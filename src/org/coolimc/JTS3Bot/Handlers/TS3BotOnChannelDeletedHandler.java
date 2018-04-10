package org.coolimc.JTS3Bot.Handlers;

import com.github.theholywaffle.teamspeak3.api.event.ChannelDeletedEvent;

public interface TS3BotOnChannelDeletedHandler
{
	public void fireEvent(ChannelDeletedEvent e);
}
