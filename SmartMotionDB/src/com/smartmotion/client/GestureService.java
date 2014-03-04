package com.smartmotion.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("gesture")
public interface GestureService extends RemoteService {
	  public void addNote(String[] account);
	  public void removeNote(String note);
	  public String[][] getNotes();
}
