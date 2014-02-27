package com.cse.smartmotioncloud.server;

import java.util.List;
import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import com.cse.smartmotioncloud.client.GestureService;
import com.cse.smartmotioncloud.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GestureServiceImpl extends RemoteServiceServlet implements
	GestureService {

	private static final long serialVersionUID = 1L;
	 
//	private static final Logger LOG = Logger.getLogger(NoteServiceImpl.class.getName());
 
	private static final PersistenceManagerFactory PMF = JDOHelper.getPersistenceManagerFactory("transactions-optional");
 
	
	@Override
	public List<Gesture> getAccounts() {
		PersistenceManager pm = getPersistenceManager();
		try {
			String query = "select from " + Gesture.class.getName();
			List<Gesture> Gestures = (List<Gesture>) pm.newQuery(query).execute();
			return Gestures;
		} finally {
			pm.close();
		}
	}
	
	@Override
	public void addAccount(String account, String password, String gestures) {
		PersistenceManager pm = getPersistenceManager();
		try {
			pm.makePersistent(new Gesture(account, password, gestures));
		} finally {
			pm.close();
		}
		
	}
	
	@Override
	public Gesture getAccount(String account) {
		PersistenceManager pm = getPersistenceManager();
		try {
			long deleteCount = 0;	
			String query = "select from " + Gesture.class.getName();
			List<Gesture> Gestures = (List<Gesture>) pm.newQuery(query).execute();
			for (Gesture Gesture : Gestures) {
				if (Gesture.equals(account)) {
					return Gesture;
				}
			}
			if (deleteCount != 1) {
//				LOG.log(Level.WARNING, "removeNote deleted " + deleteCount + " Notes");
			}
		} finally {
			pm.close();
		}
		return null;
		
	}

	@Override
	public void removeAccount(String account) {
		PersistenceManager pm = getPersistenceManager();
		try {
			long deleteCount = 0;	
			String query = "select from " + Gesture.class.getName();
			List<Gesture> Gestures = (List<Gesture>) pm.newQuery(query).execute();
			for (Gesture Gesture : Gestures) {
				if (Gesture.equals(account)) {
					deleteCount++;
					pm.deletePersistent(Gesture);
				}
			}
			if (deleteCount != 1) {
//				LOG.log(Level.WARNING, "removeNote deleted " + deleteCount + " Notes");
			}
		} finally {
			pm.close();
		}
		
	}

	@Override
	public boolean accountExists(String account) {
		PersistenceManager pm = getPersistenceManager();
		
		try {	
			String query = "select from " + Gesture.class.getName();
			List<Gesture> Gestures = (List<Gesture>) pm.newQuery(query).execute();
			for (Gesture Gesture : Gestures) {
				if (Gesture.equals(account)) {
					return true;
				}
			}
		} finally {
			pm.close();
		}
		return false;
	}

	@Override
	public String getPassword(String account) {
		PersistenceManager pm = getPersistenceManager();
		
		try {	
			String query = "select from " + Gesture.class.getName();
			List<Gesture> Gestures = (List<Gesture>) pm.newQuery(query).execute();
			for (Gesture Gesture : Gestures) {
				if (Gesture.equals(account)) {
					return Gesture.getPassword();
				}
			}
		} finally {
			pm.close();
		}
		return null;
	}

	@Override
	public String getGestures(String account) {
PersistenceManager pm = getPersistenceManager();
		
		try {	
			String query = "select from " + Gesture.class.getName();
			List<Gesture> Gestures = (List<Gesture>) pm.newQuery(query).execute();
			for (Gesture Gesture : Gestures) {
				if (Gesture.equals(account)) {
					return Gesture.getGestures();
				}
			}
		} finally {
			pm.close();
		}
		return null;
	}

	@Override
	public void setGestures(String account, String gestures) {
		PersistenceManager pm = getPersistenceManager();
		
		try {	
			String query = "select from " + Gesture.class.getName();
			List<Gesture> Gestures = (List<Gesture>) pm.newQuery(query).execute();
			for (Gesture Gesture : Gestures) {
				if (Gesture.equals(account)) {
					Gesture.setGestures(gestures);
					return;
				}
			}
		} finally {
			pm.close();
		}
		return;
	}

	@Override
	public void addGesture(String account, String gesture) {
		PersistenceManager pm = getPersistenceManager();
		
		try {	
			String query = "select from " + Gesture.class.getName();
			List<Gesture> Gestures = (List<Gesture>) pm.newQuery(query).execute();
			for (Gesture Gesture : Gestures) {
				if (Gesture.equals(account)) {
					Gesture.addGesture(gesture);
					return;
				}
			}
		} finally {
			pm.close();
		}
		return;
		
	}

	@Override
	public boolean hasGesture(String account, String gesture) {
		PersistenceManager pm = getPersistenceManager();
		
		try {	
			String query = "select from " + Gesture.class.getName();
			List<Gesture> Gestures = (List<Gesture>) pm.newQuery(query).execute();
			for (Gesture Gesture : Gestures) {
				if (Gesture.equals(account)) {
					return (Gesture.hasGesture(gesture));
				}
			}
		} finally {
			pm.close();
		}
		return false;
	}

	@Override
	public void removeGesture(String account, String gesture) {
		PersistenceManager pm = getPersistenceManager();
		
		try {	
			String query = "select from " + Gesture.class.getName();
			List<Gesture> Gestures = (List<Gesture>) pm.newQuery(query).execute();
			for (Gesture Gesture : Gestures) {
				if (Gesture.equals(account)) {
					Gesture.removeGesture(gesture);
					return;
				}
			}
		} finally {
			pm.close();
		}
		return;
		
	}
	
	
	private PersistenceManager getPersistenceManager() {
		return PMF.getPersistenceManager();
	}
}
