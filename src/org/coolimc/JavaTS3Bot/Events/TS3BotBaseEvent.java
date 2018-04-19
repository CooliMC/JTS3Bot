package org.coolimc.JavaTS3Bot.Events;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.BaseEvent;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.TS3Listener;

abstract class TS3BotBaseEvent extends BaseEvent
{
	public static final String EVENT_VALUE_KEY_INVOKER_ID = new String("invokerid");
	public static final String EVENT_VALUE_KEY_INVOKER_DBID = new String("invokerdbid");
	public static final String EVENT_VALUE_KEY_INVOKER_UID = new String("invokeruid");
	public static final String EVENT_VALUE_KEY_INVOKER_NAME = new String("invokername");
	public static final String EVENT_VALUE_KEY_REASON_ID = new String("reasonid");
	public static final String EVENT_VALUE_KEY_REASON_MSG = new String("reasonmsg");
	
	public static final Map<String, String> buildEventMap(List<String[]> baseStrings)
	{
		String[][] tempArray = new String[baseStrings.size()][2];
		
		for(int index = 0; index < baseStrings.size(); index++)
			tempArray[index] = baseStrings.get(index);
		
		return TS3BotBaseEvent.buildEventMap(tempArray);
	}
	
	public static final Map<String, String> buildEventMap(String[][] baseStrings)
	{
		Map<String, String> tempMap = new HashMap<>(baseStrings.length);
		
		for(String[] tempStrArr : baseStrings)
		{			
			if((tempStrArr != null) && (tempStrArr.length > 0))
			{
				if(tempStrArr.length > 1)
					tempMap.put(tempStrArr[0], tempStrArr[1]);
				else
					tempMap.put(tempStrArr[0], "");
			} else {
				tempMap.put("", "");
			}
		}
		
		return tempMap;
	}
	
	TS3BotBaseEvent(Map<String, String> map)
	{
		super(map);
	}
	
	public int getInvokerDatabaseId()
	{
		return getInt("invokerdbid");
	}

	public final void fire(TS3Listener arg0)
	{
		//Unimplemented cause no use
	}
}
