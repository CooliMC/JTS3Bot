package org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Commands;

import org.coolimc.JavaTS3Bot.Libary.HolyWaffleTS3Api.Main.Commands.Parameter.KeyValueParam;

/*
 * #%L
 * TeamSpeak 3 Java API
 * %%
 * Copyright (C) 2017 Bert De Geyter, Roger Baumgartner
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

public final class BanCommands {

	private BanCommands() {
		throw new Error("No instances");
	}

	public static Command banAdd(String ip, String name, String uid, long timeInSeconds, String reason) {
		if (ip == null && name == null && uid == null) {
			throw new IllegalArgumentException("Either IP, name or UId must be non-null");
		}

		CommandBuilder builder = new CommandBuilder("banadd", 5);
		builder.addIf(ip != null, new KeyValueParam("ip", ip));
		builder.addIf(name != null, new KeyValueParam("name", name));
		builder.addIf(uid != null, new KeyValueParam("uid", uid));
		builder.addIf(timeInSeconds > 0, new KeyValueParam("time", timeInSeconds));
		builder.addIf(reason != null, new KeyValueParam("banreason", reason));
		return builder.build();
	}

	public static Command banClient(int clientId, long timeInSeconds, String reason) {
		CommandBuilder builder = new CommandBuilder("banclient", 3);
		builder.add(new KeyValueParam("clid", clientId));
		builder.addIf(timeInSeconds > 0, new KeyValueParam("time", timeInSeconds));
		builder.addIf(reason != null, new KeyValueParam("banreason", reason));
		return builder.build();
	}

	public static Command banDel(int banId) {
		return new CommandBuilder("bandel", 1).add(new KeyValueParam("banid", banId)).build();
	}

	public static Command banDelAll() {
		return new CommandBuilder("bandelall").build();
	}

	public static Command banList() {
		return new CommandBuilder("banlist").build();
	}
}
