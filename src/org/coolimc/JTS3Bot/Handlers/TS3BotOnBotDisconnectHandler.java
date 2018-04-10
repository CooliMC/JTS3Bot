package org.coolimc.JTS3Bot.Handlers;

import com.github.theholywaffle.teamspeak3.TS3Query;

public interface TS3BotOnBotDisconnectHandler
{
	public void fireEvent(TS3Query e);
}
