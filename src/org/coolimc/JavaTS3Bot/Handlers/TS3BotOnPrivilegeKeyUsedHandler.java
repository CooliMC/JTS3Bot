package org.coolimc.JavaTS3Bot.Handlers;

import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.PrivilegeKeyUsedEvent;

public interface TS3BotOnPrivilegeKeyUsedHandler
{
	public void fireEvent(PrivilegeKeyUsedEvent e);
}
