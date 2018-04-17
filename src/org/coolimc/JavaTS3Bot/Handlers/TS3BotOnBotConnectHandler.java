package org.coolimc.JavaTS3Bot.Handlers;

import com.github.theholywaffle.teamspeak3.TS3Query;

public interface TS3BotOnBotConnectHandler
{
	public void fireEvent(TS3Query e);
}