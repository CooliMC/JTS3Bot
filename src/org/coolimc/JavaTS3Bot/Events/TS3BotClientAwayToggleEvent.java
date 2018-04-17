package org.coolimc.JavaTS3Bot.Events;


public class TS3BotClientAwayToggleEvent extends TS3BotBaseEvent
{
	private final static String EVENT_VALUE_REASON_ID = new String("0");
	private final static String EVENT_VALUE_REASON_MSG = new String("client away toggle");
	private final static String EVENT_VALUE_KEY_INVOKER_AWAY = new String("invokeraway");
	private final static String EVENT_VALUE_KEY_INVOKER_MSGAWAY = new String("invokermsgaway");
	
	public TS3BotClientAwayToggleEvent(int invokerID, int invokerDBID, String invokerUID, String invokerName, String invokerMsgAway, boolean invokerAway)
	{
		this(invokerID, invokerDBID, invokerUID, invokerName, invokerMsgAway, (invokerAway ? 1 : 0));
	}
	
	public TS3BotClientAwayToggleEvent(int invokerID, int invokerDBID, String invokerUID, String invokerName, String invokerMsgAway, int invokerAway)
	{
		super(buildEventMap(new String[][]
		{
			{TS3BotClientAwayToggleEvent.EVENT_VALUE_KEY_INVOKER_ID, new String("" + invokerID)},
			{TS3BotClientAwayToggleEvent.EVENT_VALUE_KEY_INVOKER_DBID, new String("" + invokerDBID)},
			{TS3BotClientAwayToggleEvent.EVENT_VALUE_KEY_INVOKER_UID, invokerUID},
			{TS3BotClientAwayToggleEvent.EVENT_VALUE_KEY_INVOKER_NAME, invokerName},
			{TS3BotClientAwayToggleEvent.EVENT_VALUE_KEY_INVOKER_MSGAWAY, invokerMsgAway},
			{TS3BotClientAwayToggleEvent.EVENT_VALUE_KEY_INVOKER_AWAY, new String("" + invokerAway)},
			{TS3BotClientAwayToggleEvent.EVENT_VALUE_KEY_REASON_ID, TS3BotClientAwayToggleEvent.EVENT_VALUE_REASON_ID},
			{TS3BotClientAwayToggleEvent.EVENT_VALUE_KEY_REASON_MSG, TS3BotClientAwayToggleEvent.EVENT_VALUE_REASON_MSG}
		}));
	}
	
	public String getInvokerMessageAway()
	{
		return get(TS3BotClientAwayToggleEvent.EVENT_VALUE_KEY_INVOKER_MSGAWAY);
	}
	
	public boolean getInvokerAway()
	{
		return getBoolean(TS3BotClientAwayToggleEvent.EVENT_VALUE_KEY_INVOKER_AWAY);
	}
}
