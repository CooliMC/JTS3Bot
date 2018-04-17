package org.coolimc.JavaTS3Bot.Handlers;

import com.github.theholywaffle.teamspeak3.api.event.ChannelMovedEvent;

public interface TS3BotOnChannelMovedHandler
{
	public void fireEvent(ChannelMovedEvent e);
}
