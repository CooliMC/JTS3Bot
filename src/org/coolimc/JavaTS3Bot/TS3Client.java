package org.coolimc.JavaTS3Bot;

import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Wrapper.Client;

public class TS3Client
{
	public static final int DEFAULT_LAST_CHANNEL_ID = -1;
	public static final TS3Client SERVER_OBJ = new TS3Client(0, -1, "server", -1, false, false, false, false, "server");
	
	private final String client_uId;
	private final int client_dbId;
	private final int client_id;
	
	private int client_cId;
	private int client_lcId;
	
	private boolean client_isAway;
	private boolean client_isMicrophoneMuted;
	private boolean client_isSpeakerMuted;
	private boolean client_isRecording;
	private String client_nickname;
	
	private boolean client_needCheck;
	
	TS3Client(Client client)
	{
		this(
			client.getId(),
			client.getDatabaseId(),
			client.getUniqueIdentifier(),
			client.getChannelId(),
			client.isAway(),
			client.isInputMuted(),
			client.isOutputMuted(),
			client.isRecording(),
			client.getNickname()
		);
	}
	
	TS3Client(int id, int dbId, String uId, int cId, boolean ciA, boolean cMm, boolean cSm, boolean cR, String cN)
	{
		this.client_id = id;
		this.client_dbId = dbId;
		this.client_uId = uId;
		
		this.client_cId = cId;
		this.client_lcId = TS3Client.DEFAULT_LAST_CHANNEL_ID;
		
		this.client_isAway = ciA;
		this.client_isMicrophoneMuted = cMm;
		this.client_isSpeakerMuted = cSm;
		this.client_isRecording = cR;
		this.client_nickname = cN;
		
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
	
	final boolean isClientAway()
	{
		return this.client_isAway;
	}
	
	final void setClientAway(boolean ciA)
	{
		this.client_isAway = ciA;
	}
	
	final boolean isClientMicrophoneMuted()
	{
		return this.client_isMicrophoneMuted;
	}
	
	final void setClientMicrophoneMuted(boolean cMm)
	{
		this.client_isMicrophoneMuted = cMm;
	}
	
	final boolean isClientSpeakerMuted()
	{
		return this.client_isSpeakerMuted;
	}
	
	final void setClientSpeakerMuted(boolean cSm)
	{
		this.client_isSpeakerMuted = cSm;
	}
	
	final boolean isClientRecording()
	{
		return this.client_isRecording;
	}
	
	final void setClientRecording(boolean cR)
	{
		this.client_isRecording = cR;
	}
	
	final String getClientNickname()
	{
		return this.client_nickname;
	}
	
	final void setClientNickname(String cN)
	{
		this.client_nickname = cN;
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
