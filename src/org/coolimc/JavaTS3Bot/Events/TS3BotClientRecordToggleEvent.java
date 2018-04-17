package org.coolimc.JavaTS3Bot.Events;

public class TS3BotClientRecordToggleEvent extends TS3BotBaseEvent
{
	private final static String EVENT_VALUE_REASON_ID = new String("0");
	private final static String EVENT_VALUE_REASON_MSG = new String("client record toggle");
	private final static String EVENT_VALUE_KEY_INVOKER_RECORDING = new String("invokerrecording");
	
	public TS3BotClientRecordToggleEvent(int invokerID, int invokerDBID, String invokerUID, String invokerName, boolean invokerRecording)
	{
		this(invokerID, invokerDBID, invokerUID, invokerName, (invokerRecording ? 1 : 0));
	}
	
	public TS3BotClientRecordToggleEvent(int invokerID, int invokerDBID, String invokerUID, String invokerName, int invokerRecording)
	{
		super(buildEventMap(new String[][]
		{
			{TS3BotClientRecordToggleEvent.EVENT_VALUE_KEY_INVOKER_ID, new String("" + invokerID)},
			{TS3BotClientRecordToggleEvent.EVENT_VALUE_KEY_INVOKER_DBID, new String("" + invokerDBID)},
			{TS3BotClientRecordToggleEvent.EVENT_VALUE_KEY_INVOKER_UID, invokerUID},
			{TS3BotClientRecordToggleEvent.EVENT_VALUE_KEY_INVOKER_NAME, invokerName},
			{TS3BotClientRecordToggleEvent.EVENT_VALUE_KEY_INVOKER_RECORDING, new String("" + invokerRecording)},
			{TS3BotClientRecordToggleEvent.EVENT_VALUE_KEY_REASON_ID, TS3BotClientRecordToggleEvent.EVENT_VALUE_REASON_ID},
			{TS3BotClientRecordToggleEvent.EVENT_VALUE_KEY_REASON_MSG, TS3BotClientRecordToggleEvent.EVENT_VALUE_REASON_MSG}
		}));
	}
	
	public boolean getInvokerRecording()
	{
		return getBoolean(TS3BotClientRecordToggleEvent.EVENT_VALUE_KEY_INVOKER_RECORDING);
	}
}
