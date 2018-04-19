package org.coolimc.JavaTS3Bot.Events;

import org.coolimc.JavaTS3Bot.TS3Client;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.ClientMovedEvent;

public class TS3BotClientMovedEvent extends ClientMovedEvent
{
	private final int sourceChannelId;
	private final int clientDBId;
	private final String clientUid;
	
	public TS3BotClientMovedEvent(ClientMovedEvent basicEvent, TS3Client basicClient)
	{
		super(basicEvent.getMap());

		this.sourceChannelId = basicClient.getClientChannelID();
		this.clientDBId = basicClient.getClientDBID();
		this.clientUid = basicClient.getClientUID();
	}
	
	public int getSourceChannelId()
	{
		return this.sourceChannelId;
	}
	
	public int getClientDatabaseId()
	{
		return this.clientDBId;
	}
	
	public String getClientUniqueId()
	{
		return this.clientUid;
	}
}
