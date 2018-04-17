package org.coolimc.JavaTS3Bot.Handlers;

import org.coolimc.JavaTS3Bot.Events.TS3BotClientSpeakerToggleEvent;

public interface TS3BotOnClientSpeakerToggleHandler
{
	public void fireEvent(TS3BotClientSpeakerToggleEvent e);
}