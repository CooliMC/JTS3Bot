package org.coolimc.JavaTS3Bot.Handlers;

import org.coolimc.JavaTS3Bot.Events.TS3BotClientMicrophoneToggleEvent;

public interface TS3BotOnClientMicrophoneToggleHandler
{
	public void fireEvent(TS3BotClientMicrophoneToggleEvent e);
}