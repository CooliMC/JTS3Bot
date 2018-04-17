package org.coolimc.JavaTS3Bot.Handlers;

import com.github.theholywaffle.teamspeak3.api.event.ChannelCreateEvent;

public interface TS3BotOnChannelCreateHandler
{
	public void fireEvent(ChannelCreateEvent e);
}
