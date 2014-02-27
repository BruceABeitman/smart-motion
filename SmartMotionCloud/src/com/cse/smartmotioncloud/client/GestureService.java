package com.cse.smartmotioncloud.client;

import java.util.List;

import com.cse.smartmotioncloud.server.Gesture;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("gesture")
public interface GestureService extends RemoteService {
	
	public List<Gesture> getAccounts();
	public Gesture getAccount(String account);
	
	public void addAccount(String account, String password, String gestures);
	public void removeAccount(String account);
	public boolean accountExists(String account);
	
	public String getPassword(String account);
	public String getGestures(String account);
	
	public void setGestures(String account, String gestures);
	public void addGesture(String account, String gesture);
	public boolean hasGesture(String account, String gesture);
	public void removeGesture(String account, String gesture);
}
