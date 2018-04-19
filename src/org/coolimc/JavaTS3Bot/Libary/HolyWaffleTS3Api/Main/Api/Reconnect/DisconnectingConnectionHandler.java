package org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Api.Reconnect;

import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.TS3Query;

/*
 * #%L
 * TeamSpeak 3 Java API
 * %%
 * Copyright (C) 2015 Bert De Geyter
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

public class DisconnectingConnectionHandler implements ConnectionHandler
{
	private final ConnectionHandler userConnectionHandler;

	public DisconnectingConnectionHandler(ConnectionHandler userConnectionHandler) {
		this.userConnectionHandler = userConnectionHandler;
	}

	@Override
	public void onConnect(TS3Query ts3Query) {
		if (userConnectionHandler != null) {
			userConnectionHandler.onConnect(ts3Query);
		}
	}

	@Override
	public void onDisconnect(TS3Query ts3Query) {
		System.err.println("[Connection] Disconnected from TS3 server");

		if (userConnectionHandler != null) {
			userConnectionHandler.onDisconnect(ts3Query);
		}
	}
}
