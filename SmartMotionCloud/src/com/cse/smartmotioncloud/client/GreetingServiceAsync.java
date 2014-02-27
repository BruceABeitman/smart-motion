package com.cse.smartmotioncloud.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, String userPass, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}
