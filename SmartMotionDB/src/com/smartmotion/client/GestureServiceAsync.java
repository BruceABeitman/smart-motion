package com.smartmotion.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GestureServiceAsync {
	void addNote(String[] account, AsyncCallback<Void> callback);
	void getNotes(AsyncCallback<String[][]> callback);
	void removeNote(String note, AsyncCallback<Void> callback);
}
