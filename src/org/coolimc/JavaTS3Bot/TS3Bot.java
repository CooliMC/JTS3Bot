package org.coolimc.JavaTS3Bot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import org.coolimc.JavaTS3Bot.Events.TS3BotClientAwayToggleEvent;
import org.coolimc.JavaTS3Bot.Events.TS3BotClientBannedEvent;
import org.coolimc.JavaTS3Bot.Events.TS3BotClientDisconnectEvent;
import org.coolimc.JavaTS3Bot.Events.TS3BotClientKickedEvent;
import org.coolimc.JavaTS3Bot.Events.TS3BotClientLeaveEvent;
import org.coolimc.JavaTS3Bot.Events.TS3BotClientMicrophoneToggleEvent;
import org.coolimc.JavaTS3Bot.Events.TS3BotClientMovedEvent;
import org.coolimc.JavaTS3Bot.Events.TS3BotClientNicknameChangedEvent;
import org.coolimc.JavaTS3Bot.Events.TS3BotClientRecordToggleEvent;
import org.coolimc.JavaTS3Bot.Events.TS3BotClientSpeakerToggleEvent;
import org.coolimc.JavaTS3Bot.Events.TS3BotClientTimeoutEvent;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnBotConnectHandler;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnBotDisconnectHandler;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnChannelCreateHandler;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnChannelDeletedHandler;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnChannelDescriptionChangedHandler;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnChannelEditHandler;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnChannelMovedHandler;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnChannelPasswordChangedHandler;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnClientAwayToggleHandler;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnClientBannedClientHandler;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnClientBannedComplaintHandler;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnClientBannedHandler;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnClientDisconnectHandler;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnClientJoinHandler;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnClientKickedClientHandler;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnClientKickedHandler;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnClientKickedIdleHandler;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnClientLeaveHandler;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnClientMicrophoneToggleHandler;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnClientMovedHandler;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnClientNicknameChangedHandler;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnClientRecordToggleHandler;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnClientSpeakerToggleHandler;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnClientTimeoutHandler;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnPrivilegeKeyUsedHandler;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnServerEditHandler;
import org.coolimc.JavaTS3Bot.Handlers.TS3BotOnTextMessageHandler;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.TS3Api;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.TS3ApiAsync;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.TS3Config;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.TS3Query;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.ChannelCreateEvent;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.ChannelDeletedEvent;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.ChannelDescriptionEditedEvent;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.ChannelEditedEvent;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.ChannelMovedEvent;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.ChannelPasswordChangedEvent;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.ClientJoinEvent;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.ClientLeaveEvent;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.ClientMovedEvent;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.PrivilegeKeyUsedEvent;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.ServerEditedEvent;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.TS3EventAdapter;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.TextMessageEvent;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Reconnect.ConnectionHandler;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Reconnect.ReconnectStrategy;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Wrapper.Client;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Wrapper.ClientInfo;

public final class TS3Bot
{
	//Definitions
	private static final long DEFAULT_QUERY_DELAY = 2500;
	private static final int DEFAULT_QUERY_PORT = 10011;
	private static final int DEFAULT_VIRTUAL_SERVER_ID = 1;
	private static final String DEFAULT_NICKNAME = new String("TS3_BOT");
	
	//Final Variables
	private final String bot_hostname;
	private final String bot_username;
	private final String bot_password;
	private final String bot_nickName;
	
	private final Integer bot_queryPort;
	private final Integer bot_virtualServerId;
	
	private final Set<TS3Client> bot_clientList;
	private final AtomicReference<ExtendedEventHandler> bot_exendedEventHandler;
	
	//Normal Variables
	private AtomicLong bot_extendedEventHandlerDelay;
	private TS3ApiAsync bot_ts3ApiAsync;
	private TS3Api bot_ts3Api;	
	
	//Handler
	private final List<TS3BotOnBotConnectHandler> ts3BotOnBotConnectHandler;
	private final List<TS3BotOnBotDisconnectHandler> ts3BotOnBotDisconnectHandler;
	private final List<TS3BotOnChannelCreateHandler> ts3BotOnChannelCreateHandler;
	private final List<TS3BotOnChannelDeletedHandler> ts3BotOnChannelDeletedHandler;
	private final List<TS3BotOnChannelDescriptionChangedHandler> ts3BotOnChannelDescriptionChangedHandler;
	private final List<TS3BotOnChannelEditHandler> ts3BotOnChannelEditHandler;
	private final List<TS3BotOnChannelMovedHandler> ts3BotOnChannelMovedHandler;
	private final List<TS3BotOnChannelPasswordChangedHandler> ts3BotOnChannelPasswordChangedHandler;
	private final List<TS3BotOnClientAwayToggleHandler> ts3BotOnClientAwayToggleHandler;
	private final List<TS3BotOnClientBannedHandler> ts3BotOnClientBannedHandler;
	private final List<TS3BotOnClientBannedClientHandler> ts3BotOnClientBannedClientHandler;
	private final List<TS3BotOnClientBannedComplaintHandler> ts3BotOnClientBannedComplaintHandler;
	private final List<TS3BotOnClientDisconnectHandler> ts3BotOnClientDisconnectHandler;
	private final List<TS3BotOnClientJoinHandler> ts3BotOnClientJoinHandler;
	private final List<TS3BotOnClientKickedHandler> ts3BotOnClientKickedHandler;
	private final List<TS3BotOnClientKickedClientHandler> ts3BotOnClientKickedClientHandler;
	private final List<TS3BotOnClientKickedIdleHandler> ts3BotOnClientKickedIdleHandler;
	private final List<TS3BotOnClientLeaveHandler> ts3BotOnClientLeaveHandler;
	private final List<TS3BotOnClientMicrophoneToggleHandler> ts3BotOnClientMicrophoneToggleHandler;
	private final List<TS3BotOnClientMovedHandler> ts3BotOnClientMovedHandler;
	private final List<TS3BotOnClientNicknameChangedHandler> ts3BotOnClientNicknameChangedHandler;
	private final List<TS3BotOnClientRecordToggleHandler> ts3BotOnClientRecordToggleHandler;
	private final List<TS3BotOnClientSpeakerToggleHandler> ts3BotOnClientSpeakerToggleHandler;
	private final List<TS3BotOnClientTimeoutHandler> ts3BotOnClientTimeoutHandler;
	private final List<TS3BotOnPrivilegeKeyUsedHandler> ts3BotOnPrivilegeKeyUsedHandler;
	private final List<TS3BotOnServerEditHandler> ts3BotOnServerEditHandler;
	private final List<TS3BotOnTextMessageHandler> ts3BotOnTextMessageHandler;
	
	//Public Methodes
	public TS3Bot(String hostname, String username, String password)
	{
		this(hostname, username, password, TS3Bot.DEFAULT_VIRTUAL_SERVER_ID, TS3Bot.DEFAULT_QUERY_PORT, TS3Bot.DEFAULT_NICKNAME);
	}
	
	public TS3Bot(String hostname, String username, String password, int virtuelServerId)
	{
		this(hostname, username, password, virtuelServerId, TS3Bot.DEFAULT_QUERY_PORT, TS3Bot.DEFAULT_NICKNAME);
	}
	
	public TS3Bot(String hostname, String username, String password, int virtuelServerId , int queryPort)
	{
		this(hostname, username, password, virtuelServerId, queryPort, TS3Bot.DEFAULT_NICKNAME);
	}
	
	
	public TS3Bot(String hostname, String username, String password, int virtuelServerId , String nickname)
	{
		this(hostname, username, password, virtuelServerId, TS3Bot.DEFAULT_QUERY_PORT, nickname);
	}
	
	public TS3Bot(String hostname, String username, String password, int virtualServerId , int queryPort, String nickname)
	{
		this.bot_hostname = hostname;
		this.bot_username = username;
		this.bot_password = password;
		this.bot_nickName = nickname;
		
		this.bot_queryPort = queryPort;
		this.bot_virtualServerId = virtualServerId;
		
		this.bot_clientList = Collections.synchronizedSet(new HashSet<TS3Client>());
		this.bot_exendedEventHandler = new AtomicReference<ExtendedEventHandler>();
		
		this.ts3BotOnBotConnectHandler = new ArrayList<TS3BotOnBotConnectHandler>();
		this.ts3BotOnBotDisconnectHandler = new ArrayList<TS3BotOnBotDisconnectHandler>();
		this.ts3BotOnChannelCreateHandler = new ArrayList<TS3BotOnChannelCreateHandler>();
		this.ts3BotOnChannelDeletedHandler = new ArrayList<TS3BotOnChannelDeletedHandler>();
		this.ts3BotOnChannelDescriptionChangedHandler = new ArrayList<TS3BotOnChannelDescriptionChangedHandler>();
		this.ts3BotOnChannelEditHandler = new ArrayList<TS3BotOnChannelEditHandler>();
		this.ts3BotOnChannelMovedHandler = new ArrayList<TS3BotOnChannelMovedHandler>();
		this.ts3BotOnChannelPasswordChangedHandler = new ArrayList<TS3BotOnChannelPasswordChangedHandler>();
		this.ts3BotOnClientAwayToggleHandler = new ArrayList<TS3BotOnClientAwayToggleHandler>();
		this.ts3BotOnClientBannedHandler = new ArrayList<TS3BotOnClientBannedHandler>();
		this.ts3BotOnClientBannedClientHandler = new ArrayList<TS3BotOnClientBannedClientHandler>();
		this.ts3BotOnClientBannedComplaintHandler = new ArrayList<TS3BotOnClientBannedComplaintHandler>();
		this.ts3BotOnClientDisconnectHandler = new ArrayList<TS3BotOnClientDisconnectHandler>();
		this.ts3BotOnClientJoinHandler = new ArrayList<TS3BotOnClientJoinHandler>();
		this.ts3BotOnClientKickedHandler = new ArrayList<TS3BotOnClientKickedHandler>();
		this.ts3BotOnClientKickedClientHandler = new ArrayList<TS3BotOnClientKickedClientHandler>();
		this.ts3BotOnClientKickedIdleHandler = new ArrayList<TS3BotOnClientKickedIdleHandler>();
		this.ts3BotOnClientLeaveHandler = new ArrayList<TS3BotOnClientLeaveHandler>();
		this.ts3BotOnClientMicrophoneToggleHandler = new ArrayList<TS3BotOnClientMicrophoneToggleHandler>();
		this.ts3BotOnClientMovedHandler = new ArrayList<TS3BotOnClientMovedHandler>();
		this.ts3BotOnClientNicknameChangedHandler = new ArrayList<TS3BotOnClientNicknameChangedHandler>();
		this.ts3BotOnClientRecordToggleHandler = new ArrayList<TS3BotOnClientRecordToggleHandler>();
		this.ts3BotOnClientSpeakerToggleHandler = new ArrayList<TS3BotOnClientSpeakerToggleHandler>();
		this.ts3BotOnClientTimeoutHandler = new ArrayList<TS3BotOnClientTimeoutHandler>();
		this.ts3BotOnPrivilegeKeyUsedHandler = new ArrayList<TS3BotOnPrivilegeKeyUsedHandler>();
		this.ts3BotOnServerEditHandler = new ArrayList<TS3BotOnServerEditHandler>();
		this.ts3BotOnTextMessageHandler = new ArrayList<TS3BotOnTextMessageHandler>();
		
		this.bot_extendedEventHandlerDelay = new AtomicLong(TS3Bot.DEFAULT_QUERY_DELAY);
		
		//Connection Handlers
		this.setUpConnectionHandlers();
		
		//Connect and Control
		final TS3Query ts3Query = this.setUpQuery();
		
		this.bot_ts3Api = this.setUpApi(ts3Query);
		this.bot_ts3ApiAsync = ts3Query.getAsyncApi();
		
		//TeamSpeak Handlers
		this.setUpHandlers();
		this.setUpInternalHandlers();
	}
	
	public final TS3Api getTS3Api()
	{
		return this.bot_ts3Api;
	}
	
	public final TS3ApiAsync getTS3ApiAsync()
	{
		return this.bot_ts3ApiAsync;
	}
	
	public final List<TS3Client> getCachedClients()
	{
		List<TS3Client> toRet = new ArrayList<TS3Client>();
		
		toRet.addAll(this.bot_clientList);
		
		return toRet;
	}
	
	public final TS3Client getCachedClientById(int clientId)
	{
		return this.getClientByClientID(clientId);
	}
	
	public final void setExtendedEventHandlerCheckDelay(long millis)
	{
		this.bot_extendedEventHandlerDelay.set(millis);
		ExtendedEventHandler tempExEvHa = this.bot_exendedEventHandler.get();
		
		if(tempExEvHa != null && tempExEvHa.isRunning())
			tempExEvHa.setSleepTime(millis);
	}
	
	//PRIVATE
	private final TS3Config setUpTS3Config()
	{
		final TS3Config config = new TS3Config();
		
		config.setHost(this.bot_hostname);
		config.setQueryPort(this.bot_queryPort);
		
		config.setReconnectStrategy(ReconnectStrategy.exponentialBackoff());
		config.setConnectionHandler(new ConnectionHandler()
		{
			public void onDisconnect(TS3Query query)
			{
				fireOnBotDisconnect(query);
			}
			
			public void onConnect(TS3Query query)
			{
				fireOnBotConnect(query);
			}
		});
		
		return config;
	}
	
	private final TS3Query setUpQuery()
	{
		final TS3Config config = this.setUpTS3Config();
		final TS3Query query = new TS3Query(config);
		
		query.connect();

		return query;
	}
	
	private final TS3Api setUpApi(TS3Query query)
	{
		final TS3Api api = query.getApi();
		
		if(api == null)
			throw new IllegalArgumentException("Can't connect to server, wrong hostname or queryport.");
		
		try { api.login(this.bot_username, this.bot_password); } catch (Exception e) {
			throw new IllegalArgumentException("Can't log into the severquery account, wrong username or password.");
		}
			
		try { api.selectVirtualServerById(this.bot_virtualServerId); } catch (Exception e) {
			throw new IllegalArgumentException("No existing virtuel server with the id " + this.bot_virtualServerId);
		}

		try { api.setNickname(this.bot_nickName); } catch (Exception e1) {
			try { api.setNickname("TS3Bot_" + this.toString()); } catch (Exception e2) {
				throw new IllegalArgumentException("Can't set the nickname to " + this.bot_nickName);
			}
		}
		
		return api;
	}
	
	private final void setUpHandlers()
	{
		this.getTS3Api().registerAllEvents();
		
		this.getTS3Api().addTS3Listeners(new TS3EventAdapter()
		{
			public void onChannelCreate(ChannelCreateEvent e)
			{
				fireOnChannelCreate(e);
			}
			
			public void onChannelDeleted(ChannelDeletedEvent e)
			{
				fireOnChannelDeleted(e);
			}
			
			public void onChannelDescriptionChanged(ChannelDescriptionEditedEvent e)
			{
				fireOnChannelDescriptionChanged(e);
			}
			
			public void onChannelEdit(ChannelEditedEvent e)
			{
				fireOnChannelEdit(e);
			}
			
			public void onChannelMoved(ChannelMovedEvent e)
			{
				fireOnChannelMoved(e);
			}
			
			public void onChannelPasswordChanged(ChannelPasswordChangedEvent e)
			{
				fireOnChannelPasswordChanged(e);
			}
			
			public void onClientJoin(ClientJoinEvent e)
			{
				fireOnClientJoin(e);
			}
			
			public void onClientLeave(ClientLeaveEvent e)
			{
				fireOnClientLeave(e);
			}
			
			public void onClientMoved(ClientMovedEvent e)
			{
				fireOnClientMoved(e);
			}
			
			public void onPrivilegeKeyUsed(PrivilegeKeyUsedEvent e)
			{
				fireOnPrivilegeKeyUsed(e);
			}
			
			public void onServerEdit(ServerEditedEvent e)
			{
				fireOnServerEdit(e);
			}
			
			public void onTextMessage(TextMessageEvent e)
			{
				fireOnTextMessage(e);
			}
		});
	}
	
	private final void setUpInternalHandlers()
	{
		this.ts3BotOnClientJoinHandler.clear();
		this.ts3BotOnClientLeaveHandler.clear();
		this.ts3BotOnClientMovedHandler.clear();
		
		this.addTS3BotOnClientJoinHandler(new TS3BotOnClientJoinHandler()
		{
			public void fireEvent(ClientJoinEvent e)
			{
				addClientToClientListAndClean(new TS3Client(getTS3Api().getClientInfo(e.getClientId())));
			}
		});
		
		this.addTS3BotOnClientLeaveHandler(new TS3BotOnClientLeaveHandler()
		{
			public void fireEvent(TS3BotClientLeaveEvent e)
			{
				//Extend events for more control and before remove to get old client informations
				switchClientLeaveEvent(e);
				
				//ClientCach remove that leaving user from Chach
				removeClientFromClientListAndCleanByClientID(e.getClientId());
			}
		});
		
		this.addTS3BotOnClientMovedHandler(new TS3BotOnClientMovedHandler()
		{
			public void fireEvent(TS3BotClientMovedEvent e)
			{
				updateClientChannelID(e.getClientId(), e.getTargetChannelId());
			}
		});
		
		this.refillClientList();
	}
	
	private final void setUpConnectionHandlers()
	{
		this.ts3BotOnBotConnectHandler.clear();
		
		this.addTS3BotOnBotConnectHandler(new TS3BotOnBotConnectHandler()
		{
			public void fireEvent(TS3Query e)
			{
				if(getTS3Api() == null || getTS3Api() == e.getApi()) return;
				
				//Refresh local data and Handlers
				bot_ts3Api = setUpApi(e);
				bot_ts3ApiAsync = e.getAsyncApi();
				
				//Only Normal Handlers SetUp cause on reconnect InternalHandlers Stay
				setUpHandlers();
			}
		});
	}
	
	private final void switchClientLeaveEvent(TS3BotClientLeaveEvent e)
	{	
		if(e.getReasonId() == 3) {
			this.fireOnClientTimeout(new TS3BotClientTimeoutEvent(e));
		} else if(e.getReasonId() == 5) {
			TS3BotClientKickedEvent tempE = new TS3BotClientKickedEvent(e);
			this.fireOnClientKicked(tempE);
			
			if(e.getInvokerId() != 0) {
				this.fireOnClientKickedClient(tempE);
			} else {
				this.fireOnClientKickedIdle(tempE);
			}
		} else if(e.getReasonId() == 6) {
			TS3BotClientBannedEvent tempE = new TS3BotClientBannedEvent(e);
			this.fireOnClientBanned(tempE);
			
			if(e.getInvokerId() != 0) {
				this.fireOnClientBannedClient(tempE);
			} else {
				this.fireOnClientBannedComplaint(tempE);
			}
		} else if(e.getReasonId() == 8) {
			this.fireOnClientDisconnect(new TS3BotClientDisconnectEvent(e));
		}
	}
	
	private final void checkExtendedEventsHandler()
	{
		if(this.bot_exendedEventHandler.get() == null || !this.bot_exendedEventHandler.get().isRunning())
		{
			if(this.doExtendedEventHandlersExist())
			{
				ExtendedEventHandler tempExEvHa = new ExtendedEventHandler(this.bot_extendedEventHandlerDelay.get());
				this.bot_exendedEventHandler.set(tempExEvHa);
				
				tempExEvHa.start();
			}
		} else if(!this.doExtendedEventHandlersExist()) {
			this.bot_exendedEventHandler.getAndSet(null).stopThread();
		}
	}
	
	private final boolean doExtendedEventHandlersExist()
	{
		return (
			!this.ts3BotOnClientAwayToggleHandler.isEmpty() ||
			!this.ts3BotOnClientMicrophoneToggleHandler.isEmpty() ||
			!this.ts3BotOnClientNicknameChangedHandler.isEmpty() ||
			!this.ts3BotOnClientRecordToggleHandler.isEmpty() ||
			!this.ts3BotOnClientSpeakerToggleHandler.isEmpty()
		);
				
	}
	
	//TODO: Client List
	private final void refillClientList()
	{
		this.clearClientList();
		
		List<Client> tempClients = this.getTS3Api().getClients();
		for(Client tempClient : tempClients)
		{
			if(tempClient != null)
			{
				this.addClientToClientList(new TS3Client(tempClient));
			}
		}
	}
	
	private final void addClientToClientListAndClean(TS3Client client)
	{
		this.addClientToClientList(client);
		this.checkCleanAndFixClientList();
	}
	
	private final void addClientToClientList(TS3Client client)
	{
		this.bot_clientList.add(client);
	}
	
	private final void removeClientFromClientListAndCleanByClientID(int clientID)
	{
		this.removeClientFromClientListByClientID(clientID);
		this.checkCleanAndFixClientList();
	}
	
	private final void removeClientFromClientListByClientID(int clientID)
	{
		for(TS3Client tempClient : this.bot_clientList)
		{
			if(tempClient.getClientID() == clientID)
			{
				this.bot_clientList.remove(tempClient);
				break;
			}
		}
	}
	
	@SuppressWarnings("unused")
	private final void removeClientFromClientListAndCleanByDBID(int clientDBID)
	{
		this.removeClientFromClientListByDBID(clientDBID);
		this.checkCleanAndFixClientList();
	}
	
	private final void removeClientFromClientListByDBID(int clientDBID)
	{
		for(TS3Client tempClient : this.bot_clientList)
		{
			if(tempClient.getClientDBID() == clientDBID)
			{
				this.bot_clientList.remove(tempClient);
				break;
			}
		}
	}
	
	@SuppressWarnings("unused")
	private final void removeClientFromClientListAndCleanByUID(String clientUID)
	{
		this.removeClientFromClientListByUID(clientUID);
		this.checkCleanAndFixClientList();
	}
	
	private final void removeClientFromClientListByUID(String clientUID)
	{
		for(TS3Client tempClient : this.bot_clientList)
		{
			if(tempClient.getClientUID().equals(clientUID))
			{
				this.bot_clientList.remove(tempClient);
				break;
			}
		}
	}
	
	private final void checkCleanAndFixClientList()
	{
		for(TS3Client tempClient : this.bot_clientList)
		{
			if(tempClient.getClientNeedCheck())
			{
				ClientInfo tempClientInfo = this.getTS3Api().getClientInfo(tempClient.getClientID());
				tempClient.setClientNeedCheck(false);
				
				if((tempClientInfo == null) || (tempClientInfo.getDatabaseId() != tempClient.getClientDBID()))
				{
					this.removeClientFromClientListByClientID(tempClient.getClientID());
				}
			}
		}
	}
	
	private final TS3Client getClientByClientID(int clientID)
	{
		if(clientID < 0) return null;
		if(clientID == 0) return TS3Client.SERVER_OBJ;
		
		for(TS3Client tempClient : this.bot_clientList)
		{
			if(tempClient.getClientID() == clientID)
			{
				return tempClient;
			}
		}
		
		return null;
	}
	
	private final void updateClientChannelID(int clientID, int nChannelID)
	{
		TS3Client tempClient = this.getClientByClientID(clientID);
		
		if(tempClient != null)
		{
			tempClient.setClientChannelID(nChannelID);
		}
	
	}
	
	private final void clearClientList()
	{
		this.bot_clientList.clear();
	}
	
	//TODO: Fire Handler
	private final void fireOnBotConnect(TS3Query e)
	{
		if(this.ts3BotOnBotConnectHandler.isEmpty()) return;
		
		new Thread()
		{
			public void run()
			{
				for(TS3BotOnBotConnectHandler tempObj : ts3BotOnBotConnectHandler)
					tempObj.fireEvent(e);
			}
		}.start();
	}
	
	private final void fireOnBotDisconnect(TS3Query e)
	{
		if(this.ts3BotOnBotDisconnectHandler.isEmpty()) return;
		
		new Thread()
		{
			public void run()
			{
				for(TS3BotOnBotDisconnectHandler tempObj : ts3BotOnBotDisconnectHandler)
					tempObj.fireEvent(e);
			}
		}.start();
	}
	
	private final void fireOnChannelCreate(ChannelCreateEvent e)
	{
		if(this.ts3BotOnChannelCreateHandler.isEmpty()) return;
		
		new Thread() 
		{
			public void run()
			{
				for(TS3BotOnChannelCreateHandler tempObj : ts3BotOnChannelCreateHandler)
					tempObj.fireEvent(e);
			}
		}.start();
	}
		
	private final void fireOnChannelDeleted(ChannelDeletedEvent e)
	{
		if(this.ts3BotOnChannelDeletedHandler.isEmpty()) return;
		
		new Thread() 
		{
			public void run()
			{
				for(TS3BotOnChannelDeletedHandler tempObj : ts3BotOnChannelDeletedHandler)
					tempObj.fireEvent(e);
			}
		}.start();
	}

	private final void fireOnChannelDescriptionChanged(ChannelDescriptionEditedEvent e)
	{
		if(this.ts3BotOnChannelDescriptionChangedHandler.isEmpty()) return;
		
		new Thread() 
		{
			public void run()
			{
				for(TS3BotOnChannelDescriptionChangedHandler tempObj : ts3BotOnChannelDescriptionChangedHandler)
					tempObj.fireEvent(e);
			}
		}.start();
	}
		
	private final void fireOnChannelEdit(ChannelEditedEvent e)
	{
		if(this.ts3BotOnChannelEditHandler.isEmpty()) return;
		
		new Thread() 
		{
			public void run()
			{
				for(TS3BotOnChannelEditHandler tempObj : ts3BotOnChannelEditHandler)
					tempObj.fireEvent(e);
			}
		}.start();
	}

	private final void fireOnChannelMoved(ChannelMovedEvent e)
	{
		if(this.ts3BotOnChannelMovedHandler.isEmpty()) return;
		
		new Thread() 
		{
			public void run()
			{
				for(TS3BotOnChannelMovedHandler tempObj : ts3BotOnChannelMovedHandler)
					tempObj.fireEvent(e);
			}
		}.start();
	}

	private final void fireOnChannelPasswordChanged(ChannelPasswordChangedEvent e)
	{
		if(this.ts3BotOnChannelPasswordChangedHandler.isEmpty()) return;
		
		new Thread() 
		{
			public void run()
			{
				for(TS3BotOnChannelPasswordChangedHandler tempObj : ts3BotOnChannelPasswordChangedHandler)
					tempObj.fireEvent(e);
			}
		}.start();
	}
	
	private final void fireOnClientAwayToggle(TS3BotClientAwayToggleEvent e)
	{
		if(this.ts3BotOnClientAwayToggleHandler.isEmpty()) return;
		
		new Thread()
		{
			public void run()
			{
				for(TS3BotOnClientAwayToggleHandler tempObj : ts3BotOnClientAwayToggleHandler)
					tempObj.fireEvent(e);
			}
		}.start();
	}
	
	private final void fireOnClientBanned(TS3BotClientBannedEvent e)
	{
		if(this.ts3BotOnClientBannedHandler.isEmpty()) return;
		
		new Thread()
		{
			public void run()
			{
				for(TS3BotOnClientBannedHandler tempObj : ts3BotOnClientBannedHandler)
					tempObj.fireEvent(e);
			}
		}.start();
	}
	
	private final void fireOnClientBannedClient(TS3BotClientBannedEvent e)
	{
		if(this.ts3BotOnClientBannedClientHandler.isEmpty()) return;
		
		new Thread()
		{
			public void run()
			{
				for(TS3BotOnClientBannedClientHandler tempObj : ts3BotOnClientBannedClientHandler)
					tempObj.fireEvent(e);
			}
		}.start();
	}
	
	private final void fireOnClientBannedComplaint(TS3BotClientBannedEvent e)
	{
		if(this.ts3BotOnClientBannedComplaintHandler.isEmpty()) return;
		
		new Thread()
		{
			public void run()
			{
				for(TS3BotOnClientBannedComplaintHandler tempObj : ts3BotOnClientBannedComplaintHandler)
					tempObj.fireEvent(e);
			}
		}.start();
	}
	
	private final void fireOnClientDisconnect(TS3BotClientDisconnectEvent e)
	{
		if(this.ts3BotOnClientDisconnectHandler.isEmpty()) return;
		
		new Thread()
		{
			public void run()
			{
				for(TS3BotOnClientDisconnectHandler tempObj : ts3BotOnClientDisconnectHandler)
					tempObj.fireEvent(e);
			}
		}.start();
	}
		
	private final void fireOnClientJoin(ClientJoinEvent e)
	{
		if(this.ts3BotOnClientJoinHandler.isEmpty()) return;
		
		new Thread() 
		{
			public void run()
			{	
				for(TS3BotOnClientJoinHandler tempObj : ts3BotOnClientJoinHandler)
					tempObj.fireEvent(e);
			}
		}.start();
	}
	
	private final void fireOnClientKicked(TS3BotClientKickedEvent e)
	{
		if(this.ts3BotOnClientKickedHandler.isEmpty()) return;
		
		new Thread() 
		{
			public void run()
			{	
				for(TS3BotOnClientKickedHandler tempObj : ts3BotOnClientKickedHandler)
					tempObj.fireEvent(e);
			}
		}.start();
	}
	
	private final void fireOnClientKickedClient(TS3BotClientKickedEvent e)
	{
		if(this.ts3BotOnClientKickedClientHandler.isEmpty()) return;
		
		new Thread() 
		{
			public void run()
			{	
				for(TS3BotOnClientKickedClientHandler tempObj : ts3BotOnClientKickedClientHandler)
					tempObj.fireEvent(e);
			}
		}.start();
	}
	
	private final void fireOnClientKickedIdle(TS3BotClientKickedEvent e)
	{
		if(this.ts3BotOnClientKickedIdleHandler.isEmpty()) return;
		
		new Thread() 
		{
			public void run()
			{	
				for(TS3BotOnClientKickedIdleHandler tempObj : ts3BotOnClientKickedIdleHandler)
					tempObj.fireEvent(e);
			}
		}.start();
	}
		
	private final void fireOnClientLeave(ClientLeaveEvent e)
	{
		if(this.ts3BotOnClientLeaveHandler.isEmpty()) return;
		
		new Thread() 
		{
			public void run()
			{	
				TS3Client tempClient = getClientByClientID(e.getClientId());
				TS3Client tempInvoker = getClientByClientID(e.getInvokerId());
				
				TS3BotClientLeaveEvent tempE = new TS3BotClientLeaveEvent(e, tempClient, ((tempInvoker == null) ? tempClient : tempInvoker));
				
				for(TS3BotOnClientLeaveHandler tempObj : ts3BotOnClientLeaveHandler)
					tempObj.fireEvent(tempE);
			}
		}.start();
	}
	
	private final void fireOnClientMicrophoneToggle(TS3BotClientMicrophoneToggleEvent e)
	{
		if(this.ts3BotOnClientMicrophoneToggleHandler.isEmpty()) return;
		
		new Thread() 
		{
			public void run()
			{
				for(TS3BotOnClientMicrophoneToggleHandler tempObj : ts3BotOnClientMicrophoneToggleHandler)
					tempObj.fireEvent(e);
			}
		}.start();
	}
	
	private final void fireOnClientMoved(ClientMovedEvent e)
	{
		if(this.ts3BotOnClientMovedHandler.isEmpty()) return;
		
		new Thread() 
		{
			public void run()
			{
				TS3BotClientMovedEvent tempE = new TS3BotClientMovedEvent(e, getClientByClientID(e.getClientId()));
				
				for(TS3BotOnClientMovedHandler tempObj : ts3BotOnClientMovedHandler)
					tempObj.fireEvent(tempE);
			}
		}.start();
	}
	
	private final void fireOnClientNicknameChanged(TS3BotClientNicknameChangedEvent e)
	{
		if(this.ts3BotOnClientNicknameChangedHandler.isEmpty()) return;
		
		new Thread() 
		{
			public void run()
			{
				for(TS3BotOnClientNicknameChangedHandler tempObj : ts3BotOnClientNicknameChangedHandler)
					tempObj.fireEvent(e);
			}
		}.start();
	}
	
	private final void fireOnClientRecordToggle(TS3BotClientRecordToggleEvent e)
	{
		if(this.ts3BotOnClientRecordToggleHandler.isEmpty()) return;
		
		new Thread() 
		{
			public void run()
			{
				for(TS3BotOnClientRecordToggleHandler tempObj : ts3BotOnClientRecordToggleHandler)
					tempObj.fireEvent(e);
			}
		}.start();
	}
	
	private final void fireOnClientSpeakerToggle(TS3BotClientSpeakerToggleEvent e)
	{
		if(this.ts3BotOnClientSpeakerToggleHandler.isEmpty()) return;
		
		new Thread() 
		{
			public void run()
			{
				for(TS3BotOnClientSpeakerToggleHandler tempObj : ts3BotOnClientSpeakerToggleHandler)
					tempObj.fireEvent(e);
			}
		}.start();
	}

	private final void fireOnClientTimeout(TS3BotClientTimeoutEvent e)
	{
		if(this.ts3BotOnClientTimeoutHandler.isEmpty()) return;
		
		new Thread() 
		{
			public void run()
			{
				for(TS3BotOnClientTimeoutHandler tempObj : ts3BotOnClientTimeoutHandler)
					tempObj.fireEvent(e);
			}
		}.start();
	}
	
	private final void fireOnPrivilegeKeyUsed(PrivilegeKeyUsedEvent e)
	{
		if(this.ts3BotOnPrivilegeKeyUsedHandler.isEmpty()) return;
		
		new Thread() 
		{
			public void run()
			{
				for(TS3BotOnPrivilegeKeyUsedHandler tempObj : ts3BotOnPrivilegeKeyUsedHandler)
					tempObj.fireEvent(e);
			}
		}.start();
	}

	private final void fireOnServerEdit(ServerEditedEvent e)
	{
		if(this.ts3BotOnServerEditHandler.isEmpty()) return;
		
		new Thread() 
		{
			public void run()
			{
				for(TS3BotOnServerEditHandler tempObj : ts3BotOnServerEditHandler)
					tempObj.fireEvent(e);
			}
		}.start();
	}

	private final void fireOnTextMessage(TextMessageEvent e)
	{
		if(this.ts3BotOnTextMessageHandler.isEmpty()) return;
		
		new Thread() 
		{
			public void run()
			{
				for(TS3BotOnTextMessageHandler tempObj : ts3BotOnTextMessageHandler)
					tempObj.fireEvent(e);
			}
		}.start();
	}
		
	//TODO: Handlers Add
	public final void addTS3BotOnBotConnectHandler(TS3BotOnBotConnectHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnBotConnectHandler.add(e);
		}
	}
	
	public final void addTS3BotOnBotDisconnectHandler(TS3BotOnBotDisconnectHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnBotDisconnectHandler.add(e);
		}
	}
	
	public final void addTS3BotOnChannelCreateHandler(TS3BotOnChannelCreateHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnChannelCreateHandler.add(e);
		}
	}
	
	public final void addTS3BotOnChannelDeletedHandler(TS3BotOnChannelDeletedHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnChannelDeletedHandler.add(e);
		}
	}

	public final void addTS3BotOnChannelDescriptionChangedHandler(TS3BotOnChannelDescriptionChangedHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnChannelDescriptionChangedHandler.add(e);
		}
	}
	
	public final void addTS3BotOnChannelEditHandler(TS3BotOnChannelEditHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnChannelEditHandler.add(e);
		}
	}

	public final void addTS3BotOnChannelMovedHandler(TS3BotOnChannelMovedHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnChannelMovedHandler.add(e);
		}
	}

	public final void addTS3BotOnChannelPasswordChangedHandler(TS3BotOnChannelPasswordChangedHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnChannelPasswordChangedHandler.add(e);
		}
	}
	
	public final void addTS3BotOnClientAwayToggleHandler(TS3BotOnClientAwayToggleHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientAwayToggleHandler.add(e);
			this.checkExtendedEventsHandler();
		}
	}
	
	public final void addTS3BotOnClientBannedHandler(TS3BotOnClientBannedHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientBannedHandler.add(e);
		}
	}
	
	public final void addTS3BotOnClientBannedClientHandler(TS3BotOnClientBannedClientHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientBannedClientHandler.add(e);
		}
	}
	
	public final void addTS3BotOnClientBannedComplaintHandler(TS3BotOnClientBannedComplaintHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientBannedComplaintHandler.add(e);
		}
	}
	
	public final void addTS3BotOnClientDisconnectHandler(TS3BotOnClientDisconnectHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientDisconnectHandler.add(e);
		}
	}
	
	public final void addTS3BotOnClientJoinHandler(TS3BotOnClientJoinHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientJoinHandler.add(e);
		}
	}
	
	public final void addTS3BotOnClientKickedHandler(TS3BotOnClientKickedHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientKickedHandler.add(e);
		}
	}
	
	public final void addTS3BotOnClientKickedClientHandler(TS3BotOnClientKickedClientHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientKickedClientHandler.add(e);
		}
	}
	
	public final void addTS3BotOnClientKickedIdleHandler(TS3BotOnClientKickedIdleHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientKickedIdleHandler.add(e);
		}
	}
	
	public final void addTS3BotOnClientLeaveHandler(TS3BotOnClientLeaveHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientLeaveHandler.add(e);
		}
	}
	
	public final void addTS3BotOnClientMicrophoneToggleHandler(TS3BotOnClientMicrophoneToggleHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientMicrophoneToggleHandler.add(e);
			this.checkExtendedEventsHandler();
		}
	}
	
	public final void addTS3BotOnClientMovedHandler(TS3BotOnClientMovedHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientMovedHandler.add(e);
		}
	}
	
	public final void addTS3BotOnClientNicknameChangedHandler(TS3BotOnClientNicknameChangedHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientNicknameChangedHandler.add(e);
			this.checkExtendedEventsHandler();
		}
	}
	
	public final void addTS3BotOnClientRecordToggleHandler(TS3BotOnClientRecordToggleHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientRecordToggleHandler.add(e);
			this.checkExtendedEventsHandler();
		}
	}
	
	public final void addTS3BotOnClientSpeakerToggleHandler(TS3BotOnClientSpeakerToggleHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientSpeakerToggleHandler.add(e);
			this.checkExtendedEventsHandler();
		}
	}
	
	public final void addTS3BotOnClientTimeoutHandler(TS3BotOnClientTimeoutHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientTimeoutHandler.add(e);
		}
	}

	public final void addTS3BotOnPrivilegeKeyUsedHandler(TS3BotOnPrivilegeKeyUsedHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnPrivilegeKeyUsedHandler.add(e);
		}
	}

	public final void addTS3BotOnServerEditHandler(TS3BotOnServerEditHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnServerEditHandler.add(e);
		}
	}

	public final void addTS3BotOnTextMessageHandler(TS3BotOnTextMessageHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnTextMessageHandler.add(e);
		}
	}
	
	//TODO: Handlers remove
	public final void removeTS3BotOnBotConnectHandler(TS3BotOnBotConnectHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnBotConnectHandler.remove(e);
		}
	}
	
	public final void removeTS3BotOnBotDisconnectHandler(TS3BotOnBotDisconnectHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnBotDisconnectHandler.remove(e);
		}
	}
	
	public final void removeTS3BotOnChannelCreateHandler(TS3BotOnChannelCreateHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnChannelCreateHandler.remove(e);
		}
	}
	
	public final void removeTS3BotOnChannelDeletedHandler(TS3BotOnChannelDeletedHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnChannelDeletedHandler.remove(e);
		}
	}

	public final void removeTS3BotOnChannelDescriptionChangedHandler(TS3BotOnChannelDescriptionChangedHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnChannelDescriptionChangedHandler.remove(e);
		}
	}
	
	public final void removeTS3BotOnChannelEditHandler(TS3BotOnChannelEditHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnChannelEditHandler.remove(e);
		}
	}

	public final void removeTS3BotOnChannelMovedHandler(TS3BotOnChannelMovedHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnChannelMovedHandler.remove(e);
		}
	}

	public final void removeTS3BotOnChannelPasswordChangedHandler(TS3BotOnChannelPasswordChangedHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnChannelPasswordChangedHandler.remove(e);
		}
	}
	
	public final void removeTS3BotOnClientAwayToggleHandler(TS3BotOnClientAwayToggleHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientAwayToggleHandler.remove(e);
			this.checkExtendedEventsHandler();
		}
	}
	
	public final void removeTS3BotOnClientBannedHandler(TS3BotOnClientBannedHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientBannedHandler.remove(e);
		}
	}
	
	public final void removeTS3BotOnClientBannedClientHandler(TS3BotOnClientBannedClientHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientBannedClientHandler.remove(e);
		}
	}
	
	public final void removeTS3BotOnClientBannedComplaintHandler(TS3BotOnClientBannedComplaintHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientBannedComplaintHandler.remove(e);
		}
	}
	
	public final void removeTS3BotOnClientDisconnectHandler(TS3BotOnClientDisconnectHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientDisconnectHandler.remove(e);
		}
	}
	
	public final void removeTS3BotOnClientJoinHandler(TS3BotOnClientJoinHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientJoinHandler.remove(e);
		}
	}
	
	public final void removeTS3BotOnClientKickedHandler(TS3BotOnClientKickedHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientKickedHandler.remove(e);
		}
	}
	
	public final void removeTS3BotOnClientKickedClientHandler(TS3BotOnClientKickedClientHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientKickedClientHandler.remove(e);
		}
	}
	
	public final void removeTS3BotOnClientKickedIdleHandler(TS3BotOnClientKickedIdleHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientKickedIdleHandler.remove(e);
		}
	}
	
	public final void removeTS3BotOnClientLeaveHandler(TS3BotOnClientLeaveHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientLeaveHandler.remove(e);
		}
	}
	
	public final void removeTS3BotOnClientMicrophoneToggleHandler(TS3BotOnClientMicrophoneToggleHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientMicrophoneToggleHandler.remove(e);
			this.checkExtendedEventsHandler();
		}
	}
	
	public final void removeTS3BotOnClientMovedHandler(TS3BotOnClientMovedHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientMovedHandler.remove(e);
		}
	}
	
	public final void removeTS3BotOnClientNicknameChangedHandler(TS3BotOnClientNicknameChangedHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientNicknameChangedHandler.remove(e);
			this.checkExtendedEventsHandler();
		}
	}
	
	public final void removeTS3BotOnClientRecordToggleHandler(TS3BotOnClientRecordToggleHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientRecordToggleHandler.remove(e);
			this.checkExtendedEventsHandler();
		}
	}
	
	public final void removeTS3BotOnClientSpeakerToggleHandler(TS3BotOnClientSpeakerToggleHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientSpeakerToggleHandler.remove(e);
			this.checkExtendedEventsHandler();
		}
	}
	
	public final void removeTS3BotOnClientTimeoutHandler(TS3BotOnClientTimeoutHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnClientTimeoutHandler.remove(e);
		}
	}

	public final void removeTS3BotOnPrivilegeKeyUsedHandler(TS3BotOnPrivilegeKeyUsedHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnPrivilegeKeyUsedHandler.remove(e);
		}
	}

	public final void removeTS3BotOnServerEditHandler(TS3BotOnServerEditHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnServerEditHandler.remove(e);
		}
	}

	public final void removeTS3BotOnTextMessageHandler(TS3BotOnTextMessageHandler e)
	{
		if(e != null)
		{
			this.ts3BotOnTextMessageHandler.remove(e);
		}
	}
	
	//TODO: Handlers clear
	public final void removeAllTS3BotOnBotConnectHandler()
	{
		this.setUpConnectionHandlers();
	}
	
	public final void removeAllTS3BotOnBotDisconnectHandler()
	{
		this.ts3BotOnBotDisconnectHandler.clear();
	}
	
	public final void removeAllTS3BotOnChannelCreateHandler()
	{
		this.ts3BotOnChannelCreateHandler.clear();
	}
	
	public final void removeAllTS3BotOnChannelDeletedHandler()
	{
		this.ts3BotOnChannelDeletedHandler.clear();
	}

	public final void removeAllTS3BotOnChannelDescriptionChangedHandler()
	{
		this.ts3BotOnChannelDescriptionChangedHandler.clear();
	}
	
	public final void removeAllTS3BotOnChannelEditHandler()
	{
		this.ts3BotOnChannelEditHandler.clear();
	}

	public final void removeAllTS3BotOnChannelMovedHandler()
	{
		this.ts3BotOnChannelMovedHandler.clear();
	}

	public final void removeAllTS3BotOnChannelPasswordChangedHandler()
	{
		this.ts3BotOnChannelPasswordChangedHandler.clear();
	}
	
	public final void removeAllTS3BotOnClientAwayToggleHandler()
	{
		this.ts3BotOnClientAwayToggleHandler.clear();
		this.checkExtendedEventsHandler();
	}
	
	public final void removeAllTS3BotOnClientBannedHandler()
	{
		this.ts3BotOnClientBannedHandler.clear();
	}
	
	public final void removeAllTS3BotOnClientBannedClientHandler()
	{
		this.ts3BotOnClientBannedClientHandler.clear();
	}
	
	public final void removeAllTS3BotOnClientBannedComplaintHandler()
	{
		this.ts3BotOnClientBannedComplaintHandler.clear();
	}
	
	public final void removeAllTS3BotOnClientDisconnectHandler()
	{
		this.ts3BotOnClientDisconnectHandler.clear();
	}
	
	public final void removeAllTS3BotOnClientJoinHandler()
	{
		//TODO: ALLES 3 HANDLERS WILL BE REMOVED NOT ONLY THIS 
		this.setUpInternalHandlers();
	}
	
	public final void removeAllTS3BotOnClientKickedHandler()
	{
		this.ts3BotOnClientKickedHandler.clear();
	}
	
	public final void removeAllTS3BotOnClientKickedClientHandler()
	{
		this.ts3BotOnClientKickedClientHandler.clear();
	}
	
	public final void removeAllTS3BotOnClientKickedIdleHandler()
	{
		this.ts3BotOnClientKickedIdleHandler.clear();
	}
	
	public final void removeAllTS3BotOnClientLeaveHandler()
	{
		//TODO: ALLES 3 HANDLERS WILL BE REMOVED NOT ONLY THIS 
		this.setUpInternalHandlers();
	}
	
	public final void removeAllTS3BotOnClientMicrophoneToggleHandler()
	{
		this.ts3BotOnClientMicrophoneToggleHandler.clear();
		this.checkExtendedEventsHandler();
	}
	
	public final void removeAllTS3BotOnClientMovedHandler()
	{
		//TODO: ALLES 3 HANDLERS WILL BE REMOVED NOT ONLY THIS 
		this.setUpInternalHandlers();
	}
	
	public final void removeAllTS3BotOnClientNicknameChangedHandler()
	{
		this.ts3BotOnClientNicknameChangedHandler.clear();
		this.checkExtendedEventsHandler();
	}
	
	public final void removeAllTS3BotOnClientRecordToggleHandler()
	{
		this.ts3BotOnClientRecordToggleHandler.clear();
		this.checkExtendedEventsHandler();
	}
	
	public final void removeAllTS3BotOnClientSpeakerToggleHandler()
	{
		this.ts3BotOnClientSpeakerToggleHandler.clear();
		this.checkExtendedEventsHandler();
	}
	
	public final void removeAllTS3BotOnClientTimeoutHandler()
	{
		this.ts3BotOnClientTimeoutHandler.clear();
	}

	public final void removeAllTS3BotOnPrivilegeKeyUsedHandler()
	{
		this.ts3BotOnPrivilegeKeyUsedHandler.clear();
	}

	public final void removeAllTS3BotOnServerEditHandler()
	{
		this.ts3BotOnServerEditHandler.clear();
	}

	public final void removeAllTS3BotOnTextMessageHandler()
	{
		this.ts3BotOnTextMessageHandler.clear();
	}
	
	//TODO: ExtendedEventHandler
	private final class ExtendedEventHandler extends Thread
	{
		private static final long MINIMUM_SLEEP_TIME = 1000;
		private static final long MAXIMUM_SLEEP_TIME = 30000;
	
		private final AtomicBoolean running;
		private final AtomicLong sleepTime;
		
		private ExtendedEventHandler()
		{
			this.running = new AtomicBoolean(false);
			this.sleepTime = new AtomicLong(ExtendedEventHandler.MINIMUM_SLEEP_TIME);
		}
		
		private ExtendedEventHandler(long sleepTime)
		{
			this.running = new AtomicBoolean(false);
			this.sleepTime = new AtomicLong(sleepTime);
		}
		
		public final void run()
		{
			this.running.set(true);
			long lastWhile;
			
			while(this.running.get())
			{
				//Clock
				lastWhile = System.currentTimeMillis();
				
				//Code
				if(getTS3Api() != null)
				{
					TS3Client cachedClient;
					
					for(Client tempClient : getTS3Api().getClients())
					{
						//GetCachedClient
						cachedClient = getCachedClientById(tempClient.getId());
						
						//CheckAway
						if(!ts3BotOnClientAwayToggleHandler.isEmpty() && (tempClient.isAway() != cachedClient.isClientAway()))
						{
							fireOnClientAwayToggle(new TS3BotClientAwayToggleEvent
							(
								tempClient.getId(),
								tempClient.getDatabaseId(),
								tempClient.getUniqueIdentifier(),
								tempClient.getNickname(),
								tempClient.getAwayMessage(),
								tempClient.isAway()
							));
							
							cachedClient.setClientAway(tempClient.isAway());
						}
						
						//CheckNickname
						if(!ts3BotOnClientNicknameChangedHandler.isEmpty() && (!tempClient.getNickname().equals(cachedClient.getClientNickname())))
						{
							fireOnClientNicknameChanged(new TS3BotClientNicknameChangedEvent
							(
								tempClient.getId(),
								tempClient.getDatabaseId(),
								tempClient.getUniqueIdentifier(),
								tempClient.getNickname(),
								cachedClient.getClientNickname()
							));
							
							cachedClient.setClientNickname(tempClient.getNickname());
						}
						
						//CheckMicrophone
						if(!ts3BotOnClientMicrophoneToggleHandler.isEmpty() && (tempClient.isInputMuted() != cachedClient.isClientMicrophoneMuted()))
						{
							fireOnClientMicrophoneToggle(new TS3BotClientMicrophoneToggleEvent
							(
								tempClient.getId(),
								tempClient.getDatabaseId(),
								tempClient.getUniqueIdentifier(),
								tempClient.getNickname(),
								tempClient.isInputMuted()
							));
							
							cachedClient.setClientMicrophoneMuted(tempClient.isInputMuted());
						}
						
						//CheckSpeaker
						if(!ts3BotOnClientSpeakerToggleHandler.isEmpty() && (tempClient.isOutputMuted() != cachedClient.isClientSpeakerMuted()))
						{
							fireOnClientSpeakerToggle(new TS3BotClientSpeakerToggleEvent
							(
								tempClient.getId(),
								tempClient.getDatabaseId(),
								tempClient.getUniqueIdentifier(),
								tempClient.getNickname(),
								tempClient.isOutputMuted()
							));
							
							cachedClient.setClientSpeakerMuted(tempClient.isOutputMuted());
						}
						
						//CheckRecord
						if(!ts3BotOnClientRecordToggleHandler.isEmpty() && (tempClient.isRecording() != cachedClient.isClientRecording()))
						{
							fireOnClientRecordToggle(new TS3BotClientRecordToggleEvent(
								tempClient.getId(),
								tempClient.getDatabaseId(),
								tempClient.getUniqueIdentifier(),
								tempClient.getNickname(),
								tempClient.isRecording()
							));
							
							cachedClient.setClientRecording(tempClient.isRecording());
						}
					}
				}
				
				//Sleep
				if((System.currentTimeMillis() - lastWhile) < this.sleepTime.get())
				{
					try {
						Thread.sleep(this.sleepTime.get() - (System.currentTimeMillis() - lastWhile));
					} catch(Exception e) {
						//DO NOTHING
					}
				}
			}
			
			this.running.set(false);
		}
			
		public final void setSleepTime(long millis)
		{
			if((millis < ExtendedEventHandler.MINIMUM_SLEEP_TIME) || (millis > ExtendedEventHandler.MAXIMUM_SLEEP_TIME)) return;
			
			this.sleepTime.set(millis);
		}
		
		public final boolean isRunning()
		{
			return this.running.get();
		}
		
		public final void stopThread()
		{
			this.running.set(false);
		}
	}
}
