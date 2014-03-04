package com.smartmotion.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import com.smartmotion.client.GestureService;
import com.smartmotion.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GestureServiceImpl extends RemoteServiceServlet implements
		GestureService {

	private static final long serialVersionUID = 1L;
	 
	private static final Logger LOG = Logger.getLogger(GestureServiceImpl.class.getName());
 
	private static final PersistenceManagerFactory PMF = JDOHelper.getPersistenceManagerFactory("transactions-optional");
 
	public void addNote(String[] account){
		PersistenceManager pm = getPersistenceManager();
//		String[] account = new String[]{note, password, gesture};
		LOG.log(Level.SEVERE, "NoteServiceImpl adding account: " + account[0]);
		try {
			pm.makePersistent(new GAccount(account));
		} finally {
			pm.close();
		}
	}
 
	public void removeNote(String note){
		PersistenceManager pm = getPersistenceManager();
		try {
			long deleteCount = 0;	
			String query = "select from " + GAccount.class.getName();
			List<GAccount> Notes = (List<GAccount>) pm.newQuery(query).execute();
			for (GAccount Note : Notes) {
				if (note.equals(Note.getAccount())) {
					deleteCount++;
					pm.deletePersistent(Note);
				}
			}
			if (deleteCount != 1) {
				LOG.log(Level.WARNING, "removeNote deleted " + deleteCount + " Notes");
			}
		} finally {
			pm.close();
		}
	}
 
	public String[][] getNotes() {
 
		PersistenceManager pm = getPersistenceManager();
		
		List<String> accounts = new ArrayList<String>();
		List<String> passwords = new ArrayList<String>();
		List<String> gestures = new ArrayList<String>();
 
		try {
			String query = "select from " + GAccount.class.getName();
			List<GAccount> Accounts = (List<GAccount>) pm.newQuery(query).execute();
 
			for (GAccount account : Accounts) {
				LOG.log(Level.SEVERE, "account queried 1 " + account.getAccount());
				accounts.add(account.getAccount());
				passwords.add(account.getPassword());
				gestures.add(account.getGesture());
			}
		} finally {
			pm.close();
		}
 
		String[][] ret = new String[3][accounts.size()];
//		String[][] ret = new String[][]{(String[]) accounts.toArray(), (String[]) passwords.toArray(), (String[]) gestures.toArray()};
 
		if (accounts.size() > 0) {
			for (int i=0; i<accounts.size(); i++) {
				ret[0][i] = accounts.get(i);
			}
			
			for (int i=0; i<passwords.size(); i++) {
				ret[1][i] = passwords.get(i);
			}
			
			for (int i=0; i<gestures.size(); i++) {
				ret[2][i] = gestures.get(i);
			}
		}
 
		return ret;
	}
 
	private PersistenceManager getPersistenceManager() {
		return PMF.getPersistenceManager();
	}
}
