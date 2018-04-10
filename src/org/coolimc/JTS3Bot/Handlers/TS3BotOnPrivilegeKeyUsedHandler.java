package org.coolimc.JTS3Bot.Handlers;

import com.github.theholywaffle.teamspeak3.api.event.PrivilegeKeyUsedEvent;

public interface TS3BotOnPrivilegeKeyUsedHandler
{
	public void fireEvent(PrivilegeKeyUsedEvent e);
}
