package com.cse.smartmotioncloud.client;

import java.util.List;

import com.cse.smartmotioncloud.server.Gesture;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GestureService</code>.
 */
public interface GestureServiceAsync {
	
	void getAccounts(AsyncCallback<List<Gesture>> callback);
	void getAccount(String account, AsyncCallback<Gesture> callback);
	
	void addAccount(String account, String password, String gestures, AsyncCallback<Void> callback);
	void removeAccount(String account, AsyncCallback<Void> callback);
	void accountExists(String account, AsyncCallback<Boolean> callback);
	
	void getPassword(String account, AsyncCallback<String> callback);
	void getGestures(String account, AsyncCallback<String> callback);
	
	void setGestures(String account, String gestures, AsyncCallback<Void> callback);
	void addGesture(String account, String gesture, AsyncCallback<Void> callback);
	void hasGesture(String account, String gesture, AsyncCallback<Boolean> callback);
	void removeGesture(String account, String gesture, AsyncCallback<Void> callback);
}
