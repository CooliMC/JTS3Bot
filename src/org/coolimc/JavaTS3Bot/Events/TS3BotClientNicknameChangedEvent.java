package org.coolimc.JavaTS3Bot.Events;

public class TS3BotClientNicknameChangedEvent extends TS3BotBaseEvent
{
	private final static String EVENT_VALUE_REASON_ID = new String("0");
	private final static String EVENT_VALUE_REASON_MSG = new String("client nickname change");
	private final static String EVENT_VALUE_KEY_INVOKER_LASTNAME = new String("invokerlastname");
	
	public TS3BotClientNicknameChangedEvent(int invokerID, int invokerDBID, String invokerUID, String invokerName, String invokerLastName)
	{
		super(buildEventMap(new String[][]
		{
			{TS3BotClientNicknameChangedEvent.EVENT_VALUE_KEY_INVOKER_ID, new String("" + invokerID)},
			{TS3BotClientNicknameChangedEvent.EVENT_VALUE_KEY_INVOKER_DBID, new String("" + invokerDBID)},
			{TS3BotClientNicknameChangedEvent.EVENT_VALUE_KEY_INVOKER_UID, invokerUID},
			{TS3BotClientNicknameChangedEvent.EVENT_VALUE_KEY_INVOKER_NAME, invokerName},
			{TS3BotClientNicknameChangedEvent.EVENT_VALUE_KEY_INVOKER_LASTNAME, invokerLastName},
			{TS3BotClientNicknameChangedEvent.EVENT_VALUE_KEY_REASON_ID, TS3BotClientNicknameChangedEvent.EVENT_VALUE_REASON_ID},
			{TS3BotClientNicknameChangedEvent.EVENT_VALUE_KEY_REASON_MSG, TS3BotClientNicknameChangedEvent.EVENT_VALUE_REASON_MSG}
		}));
	}
	
	public String getInvokerLastName()
	{
		return get(TS3BotClientNicknameChangedEvent.EVENT_VALUE_KEY_INVOKER_LASTNAME);
	}
}
