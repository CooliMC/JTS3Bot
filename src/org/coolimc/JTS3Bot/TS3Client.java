package org.coolimc.JTS3Bot;

import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

public class TS3Client
{
	public static final int DEFAULT_LAST_CHANNEL_ID = -1;
	public static final TS3Client SERVER_OBJ = new TS3Client(0, -1, "server", -1);
	
	private final String client_uId;
	private final int client_dbId;
	private final int client_id;
	
	private int client_cId;
	private int client_lcId;
	
	private boolean client_needCheck;
	
	TS3Client(Client client)
	{
		this(client.getId(), client.getDatabaseId(), client.getUniqueIdentifier(), client.getChannelId());
	}
	
	TS3Client(int id, int dbId, String uId, int cId)
	{
		this.client_id = id;
		this.client_dbId = dbId;
		this.client_uId = uId;
		
		this.client_cId = cId;
		this.client_lcId = TS3Client.DEFAULT_LAST_CHANNEL_ID;
		
		this.client_needCheck = false;
	}
	
	public final int getClientID()
	{
		return this.client_id;
	}
	
	public final int getClientDBID()
	{
		return this.client_dbId;
	}
	
	public final String getClientUID()
	{
		return this.client_uId;
	}
	
	public final int getClientChannelID()
	{
		return this.client_cId;
	}
	
	final void setClientChannelID(int cId)
	{
		this.setLastClientChannelID(this.client_cId);
		this.client_cId = cId;
	}
	
	public final int getLastClientChannelID()
	{
		return this.client_lcId;
	}
	
	private final void setLastClientChannelID(int lcId)
	{
		this.client_lcId = lcId;
	}
	
	public final boolean getClientNeedCheck()
	{
		return this.client_needCheck;
	}
	
	final void setClientNeedCheck(boolean nValue)
	{
		this.client_needCheck = nValue;
	}
	
	public boolean equals(TS3Client toCompare)
	{
		if(toCompare.getClientID() != this.getClientID())
		{
			if(toCompare.getClientDBID() == this.getClientDBID())
			{
				this.setClientNeedCheck(true);
				toCompare.setClientNeedCheck(true);
			}
		} else {
			if(toCompare.getClientDBID() == this.getClientDBID())
			{
				return true;
			} else {
				this.setClientNeedCheck(true);
				toCompare.setClientNeedCheck(true);
			}
		}
		
		return false;
	}
	
	public int hashCode()
	{
		return ((this.getClientDBID() * ((int) Math.pow(10, ("" + this.getClientID()).length()))) + this.client_id);
	}
}
