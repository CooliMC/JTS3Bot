package org.coolimc.JavaTS3Bot.Events;

import org.coolimc.JavaTS3Bot.TS3Client;
import com.github.theholywaffle.teamspeak3.api.event.ClientLeaveEvent;

public class TS3BotClientLeaveEvent extends ClientLeaveEvent
{
	private final int clientDBId;
	private final String clientUid;
	private final int invokerDBId;
	private final int invokerChannelId;
	
	public TS3BotClientLeaveEvent(ClientLeaveEvent basicEvent, TS3Client basicClient, TS3Client invokerClient)
	{
		super(basicEvent.getMap());
		
		this.clientDBId = basicClient.getClientDBID();
		this.clientUid = basicClient.getClientUID();
		
		this.invokerDBId = invokerClient.getClientDBID();
		this.invokerChannelId = invokerClient.getClientChannelID();
	}
	
	TS3BotClientLeaveEvent(TS3BotClientLeaveEvent e)
	{
		super(e.getMap());
		
		this.clientDBId = e.getClientDatabaseId();
		this.clientUid = e.getClientUniqueId();
		
		this.invokerDBId = e.getInvokerDatabaseId();
		this.invokerChannelId = e.getInvokerChannelId();
	}

	public int getClientDatabaseId()
	{
		return this.clientDBId;
	}
	
	public String getClientUniqueId()
	{
		return this.clientUid;
	}
	
	public int getInvokerDatabaseId()
	{
		return this.invokerDBId;
	}
	
	public int getInvokerChannelId()
	{
		return this.invokerChannelId;
	}
	
	public int getInvokerId()
	{
		if(super.getInvokerId() < 0)
			return this.getClientId();
		else
			return super.getInvokerId();	
	}
	
	public String getInvokerUniqueId()
	{
		if(super.getInvokerId() < 0)
			return this.getClientUniqueId();
		else if(super.getInvokerId() == 0)
			return TS3Client.SERVER_OBJ.getClientUID();
		else
			return super.getInvokerUniqueId();
	}
}
