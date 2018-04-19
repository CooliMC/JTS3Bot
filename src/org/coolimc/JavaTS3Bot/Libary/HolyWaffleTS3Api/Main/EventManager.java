package org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main;

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
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.TS3Event;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.TS3Listener;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Event.TextMessageEvent;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Exception.TS3UnknownEventException;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Wrapper.Wrapper;
import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Commands.Response.DefaultArrayResponse;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventManager
{
	// CopyOnWriteArrayList for thread safety
	private final Collection<ListenerTask> tasks = new CopyOnWriteArrayList<>();
	private final TS3Query ts3;

	EventManager(TS3Query query) {
		ts3 = query;
	}

	public void addListeners(TS3Listener... listeners) {
		for (TS3Listener listener : listeners) {
			if (listener == null) throw new IllegalArgumentException("A listener was null");
			ListenerTask task = new ListenerTask(listener);
			tasks.add(task);
		}
	}

	public void removeListeners(TS3Listener... listeners) {
		// Bad performance (O(n*m)), but this method is rarely if ever used
		Iterator<ListenerTask> taskIterator = tasks.iterator();
		while (taskIterator.hasNext()) {
			ListenerTask task = taskIterator.next();
			TS3Listener taskListener = task.getListener();

			for (TS3Listener listener : listeners) {
				if (taskListener.equals(listener)) {
					taskIterator.remove();
					break;
				}
			}
		}
	}

	public void fireEvent(String notifyName, String notifyBody) {
		final DefaultArrayResponse response = DefaultArrayResponse.parse(notifyBody);

		for (Wrapper dataWrapper : response.getResponses()) {
			Map<String, String> eventData = dataWrapper.getMap();
			TS3Event event = createEvent(notifyName, eventData);

			fireEvent(event);
		}
	}

	public void fireEvent(TS3Event event) {
		if (event == null) throw new IllegalArgumentException("TS3Event was null");
		for (ListenerTask task : tasks) {
			task.enqueueEvent(event);
		}
	}

	private static TS3Event createEvent(String notifyName, Map<String, String> eventData) {
		switch (notifyName) {
			case "notifytextmessage":
				return new TextMessageEvent(eventData);
			case "notifycliententerview":
				return new ClientJoinEvent(eventData);
			case "notifyclientleftview":
				return new ClientLeaveEvent(eventData);
			case "notifyserveredited":
				return new ServerEditedEvent(eventData);
			case "notifychanneledited":
				return new ChannelEditedEvent(eventData);
			case "notifychanneldescriptionchanged":
				return new ChannelDescriptionEditedEvent(eventData);
			case "notifyclientmoved":
				return new ClientMovedEvent(eventData);
			case "notifychannelcreated":
				return new ChannelCreateEvent(eventData);
			case "notifychanneldeleted":
				return new ChannelDeletedEvent(eventData);
			case "notifychannelmoved":
				return new ChannelMovedEvent(eventData);
			case "notifychannelpasswordchanged":
				return new ChannelPasswordChangedEvent(eventData);
			case "notifytokenused":
				return new PrivilegeKeyUsedEvent(eventData);
			default:
				throw new TS3UnknownEventException(notifyName + " " + eventData);
		}
	}

	/*
	 * Do not synchronize on instances of this class from outside the class itself!
	 */
	private class ListenerTask implements Runnable {

		private static final int START_QUEUE_SIZE = 16;

		private final TS3Listener listener;
		private final Queue<TS3Event> eventQueue;

		ListenerTask(TS3Listener ts3Listener) {
			listener = ts3Listener;
			eventQueue = new ArrayDeque<>(START_QUEUE_SIZE);
		}

		TS3Listener getListener() {
			return listener;
		}

		synchronized void enqueueEvent(TS3Event event) {
			if (eventQueue.isEmpty()) {
				// Add the event to the queue and start a task to process this event and any events
				// that might be enqueued before the last event is removed from the queue
				eventQueue.add(event);
				ts3.submitUserTask("Event listener task", this);
			} else {
				// Just add the event to the queue, the running task will pick it up
				eventQueue.add(event);
			}
		}

		@Override
		public void run() {
			TS3Event currentEvent;
			synchronized (this) {
				currentEvent = eventQueue.peek();
				if (currentEvent == null) throw new IllegalStateException("Task started without events");
			}

			do {
				try {
					currentEvent.fire(listener);
				} catch (Throwable throwable) {
					System.err.println("Event listener threw an exception" + throwable.getMessage());
				}

				synchronized (this) {
					eventQueue.remove();
					currentEvent = eventQueue.peek();
				}
			} while (currentEvent != null);
		}
	}
}
