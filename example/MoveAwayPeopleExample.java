import org.coolimc.JavaTS3Bot.TS3Bot;
import org.coolimc.JavaTS3Bot.Events.TS3BotClientAwayToggleEvent;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnClientAwayToggleHandler;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.TS3Api;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.TextMessageTargetMode;

/*
 * A simple example for a bot that texts clients
 * who toggle their away state. Different message for
 * toggle away on and off.
 */
public class MoveAwayPeopleExample
{
	public static void main(String[] args)
	{
		TS3Bot tsBot = new TS3Bot("77.77.77.77", "serveradmin", "serveradminpassword", 1, 10011, "[Bot]Ts3Guard");
		TS3Api api = tsBot.getTS3Api();
		
		/*
		 * Listen to all clients if the toggle the away state.
		 * State is checked, if the new state is away send
		 * a message to the client.
		 */
		tsBot.addTS3BotOnClientAwayToggleHandler(new TS3BotOnClientAwayToggleHandler()
		{
			public void fireEvent(TS3BotClientAwayToggleEvent e)
			{
				if(e.getInvokerAway())
				{
					api.sendTextMessage(TextMessageTargetMode.CLIENT, e.getInvokerId(), "Please come back or you will be moved into the AFK-Channel.");
				} else {
					api.sendTextMessage(TextMessageTargetMode.CLIENT, e.getInvokerId(), "Welcome back my friend.");
				}
			}
		});
	}
}
