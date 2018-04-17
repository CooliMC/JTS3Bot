package org.coolimc.JavaTS3Bot.Handlers;

import org.coolimc.JavaTS3Bot.Events.TS3BotClientNicknameChangedEvent;

public interface TS3BotOnClientNicknameChangedHandler
{
	public void fireEvent(TS3BotClientNicknameChangedEvent e);
}
