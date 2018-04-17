package org.coolimc.JavaTS3Bot.Events;

public class TS3BotClientMicrophoneToggleEvent extends TS3BotBaseEvent
{
	private final static String EVENT_VALUE_REASON_ID = new String("0");
	private final static String EVENT_VALUE_REASON_MSG = new String("client microphone toggle");
	private final static String EVENT_VALUE_KEY_INVOKER_MICROPHONEMUTED = new String("invokermicrophonemuted");
	
	public TS3BotClientMicrophoneToggleEvent(int invokerID, int invokerDBID, String invokerUID, String invokerName, boolean invokerMicrophone)
	{
		this(invokerID, invokerDBID, invokerUID, invokerName, (invokerMicrophone ? 1 : 0));
	}
	
	public TS3BotClientMicrophoneToggleEvent(int invokerID, int invokerDBID, String invokerUID, String invokerName, int invokerMicrophone)
	{
		super(buildEventMap(new String[][]
		{
			{TS3BotClientMicrophoneToggleEvent.EVENT_VALUE_KEY_INVOKER_ID, new String("" + invokerID)},
			{TS3BotClientMicrophoneToggleEvent.EVENT_VALUE_KEY_INVOKER_DBID, new String("" + invokerDBID)},
			{TS3BotClientMicrophoneToggleEvent.EVENT_VALUE_KEY_INVOKER_UID, invokerUID},
			{TS3BotClientMicrophoneToggleEvent.EVENT_VALUE_KEY_INVOKER_NAME, invokerName},
			{TS3BotClientMicrophoneToggleEvent.EVENT_VALUE_KEY_INVOKER_MICROPHONEMUTED, new String("" + invokerMicrophone)},
			{TS3BotClientMicrophoneToggleEvent.EVENT_VALUE_KEY_REASON_ID, TS3BotClientMicrophoneToggleEvent.EVENT_VALUE_REASON_ID},
			{TS3BotClientMicrophoneToggleEvent.EVENT_VALUE_KEY_REASON_MSG, TS3BotClientMicrophoneToggleEvent.EVENT_VALUE_REASON_MSG}
		}));
	}
	
	public boolean getInvokerMicrophone()
	{
		return getBoolean(TS3BotClientMicrophoneToggleEvent.EVENT_VALUE_KEY_INVOKER_MICROPHONEMUTED);
	}
}
