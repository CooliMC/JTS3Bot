package org.coolimc.JTS3Bot.Handlers;

import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;

public interface TS3BotOnTextMessageHandler
{
	public void fireEvent(TextMessageEvent e);
}
