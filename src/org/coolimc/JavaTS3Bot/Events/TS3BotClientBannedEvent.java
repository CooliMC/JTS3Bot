package org.coolimc.JavaTS3Bot.Events;

public class TS3BotClientBannedEvent extends TS3BotClientLeaveEvent
{
	public TS3BotClientBannedEvent(TS3BotClientLeaveEvent basicEvent)
	{
		super(basicEvent);
	}
}