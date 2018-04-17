package org.coolimc.JavaTS3Bot.Events;

public class TS3BotClientSpeakerToggleEvent extends TS3BotBaseEvent
{
	private final static String EVENT_VALUE_REASON_ID = new String("0");
	private final static String EVENT_VALUE_REASON_MSG = new String("client speaker toggle");
	private final static String EVENT_VALUE_KEY_INVOKER_SPEAKERMUTED = new String("invokerspeakermuted");
	
	public TS3BotClientSpeakerToggleEvent(int invokerID, int invokerDBID, String invokerUID, String invokerName, boolean invokerSpeaker)
	{
		this(invokerID, invokerDBID, invokerUID, invokerName, (invokerSpeaker ? 1 : 0));
	}
	
	public TS3BotClientSpeakerToggleEvent(int invokerID, int invokerDBID, String invokerUID, String invokerName, int invokerSpeaker)
	{
		super(buildEventMap(new String[][]
		{
			{TS3BotClientSpeakerToggleEvent.EVENT_VALUE_KEY_INVOKER_ID, new String("" + invokerID)},
			{TS3BotClientSpeakerToggleEvent.EVENT_VALUE_KEY_INVOKER_DBID, new String("" + invokerDBID)},
			{TS3BotClientSpeakerToggleEvent.EVENT_VALUE_KEY_INVOKER_UID, invokerUID},
			{TS3BotClientSpeakerToggleEvent.EVENT_VALUE_KEY_INVOKER_NAME, invokerName},
			{TS3BotClientSpeakerToggleEvent.EVENT_VALUE_KEY_INVOKER_SPEAKERMUTED, new String("" + invokerSpeaker)},
			{TS3BotClientSpeakerToggleEvent.EVENT_VALUE_KEY_REASON_ID, TS3BotClientSpeakerToggleEvent.EVENT_VALUE_REASON_ID},
			{TS3BotClientSpeakerToggleEvent.EVENT_VALUE_KEY_REASON_MSG, TS3BotClientSpeakerToggleEvent.EVENT_VALUE_REASON_MSG}
		}));
	}
	
	public boolean getInvokerSpeaker()
	{
		return getBoolean(TS3BotClientSpeakerToggleEvent.EVENT_VALUE_KEY_INVOKER_SPEAKERMUTED);
	}
}
