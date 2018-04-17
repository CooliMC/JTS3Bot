package org.coolimc.JavaTS3Bot.Handlers;

import com.github.theholywaffle.teamspeak3.api.event.ChannelPasswordChangedEvent;

public interface TS3BotOnChannelPasswordChangedHandler
{
	public void fireEvent(ChannelPasswordChangedEvent e);
}
