package org.coolimc.JavaTS3Bot.Handlers;

import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.TextMessageEvent;

public interface TS3BotOnTextMessageHandler
{
	public void fireEvent(TextMessageEvent e);
}
