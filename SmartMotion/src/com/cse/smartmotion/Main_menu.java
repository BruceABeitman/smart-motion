package com.cse.smartmotion;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupMenu;

public class Main_menu extends ListActivity {
private static final int GET_META_DATA = 0;
String tag="SmartMotion";
private DatabaseHandler db;
private List<ApplicationInfo> applist =  new ArrayList<ApplicationInfo>();
private List<ApplicationInfo> mylist=new ArrayList<ApplicationInfo>();
private PackageManager packageManager;
 private ApplicationAdapter listadaptor =null;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu_activity);
		db=new DatabaseHandler(this);
		 packageManager = getPackageManager();
		 
		 new LoadApplications().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	
	
	@Override
	public void onResume(){
		super.onResume();
		Log.d(tag,"my gesture is in onResume!");	
	}
	
	@Override
	public void onPause(){
		super.onResume();
		Log.d(tag,"my gesture is in onPause!");
	}
	
	@Override
	public void onStop(){
		super.onResume();
		Log.d(tag,"my gesture is in onStop!");
	}
	
	
	public void New_Gesture(View view){
		Intent intent=new Intent(this, NewGesture.class);
		startActivity(intent);
		finish();
	}
	
	public void Settings(View view){
		Intent intent=new Intent(this, Settings.class);
		startActivity(intent);
	}
	
	
	
	@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
 
        final ApplicationInfo app = applist.get(position);
        
        
      //Creating the instance of PopupMenu  
        PopupMenu popup = new PopupMenu(Main_menu.this,v){};  
        
      //Inflating the Popup using xml file  
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu()); 
        //registering popup with OnMenuItemClickListener  
        
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {  
            public boolean onMenuItemClick(MenuItem item) {  
            	
            	
            	List<Motiongesture> packages_with_gesture = db.getAllGestures();   
            	int to_delete_id=-1;
	    		int dbsize=packages_with_gesture.size();
	    		for (int i = 0; i < dbsize; i++) {
	    		
	        	String packages = packages_with_gesture.get(i).getName();
	        	if (packages.equals(app.packageName)) {
	        		 to_delete_id=i;
					break;
				}
	        	
	    		}
            	
            	 Motiongesture todelete=packages_with_gesture.get(to_delete_id);
            	
            	db.deleteGesture(todelete);
            	listadaptor.clear();
            	new LoadApplications().execute();
            
            	
             return true;  
            }  
           }); 
        
        popup.show();
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {
	        ArrayList<ApplicationInfo> applist = new ArrayList<ApplicationInfo>();
	        for (ApplicationInfo info : list) {
	            try {
	                if (null != packageManager.getLaunchIntentForPackage(info.packageName)) {
	                    applist.add(info);
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	 
	        return applist;
	    }
	
	
	
	 private class LoadApplications extends AsyncTask<Void, Void, Void> {
	        private ProgressDialog progress = null;
	 
	        @Override
	        protected Void doInBackground(Void... params) {
	        	
	        	mylist.clear();
	        	applist.clear();
	        	
	    		List<Motiongesture> packages_with_gesture = db.getAllGestures();   
	            
	    		int dbsize=packages_with_gesture.size();
	    		for (int i = 0; i < dbsize; i++) {
	    			
	    		
	        	String packages = packages_with_gesture.get(i).getName();
	        	 
	        	
	        	 final PackageManager pm = getApplicationContext().getPackageManager();
	        	ApplicationInfo app_withgestures=null;
	            try {
	            	app_withgestures = pm.getApplicationInfo( packages, GET_META_DATA);
	               
	            } catch (final NameNotFoundException e) {
	                e.printStackTrace();
	            }
	            
	            
	            mylist.add(app_withgestures);
	    		}
	    		
	            applist = checkForLaunchIntent(mylist);
	            listadaptor = new ApplicationAdapter(Main_menu.this,R.layout.snippet_list_row, applist);
	        	
	 
	            return null;
	        }
	 
	        @Override
	        protected void onCancelled() {
	            super.onCancelled();
	        }
	 
	        @Override
	        protected void onPostExecute(Void result) {
	            setListAdapter(listadaptor);
	            progress.dismiss();
	            super.onPostExecute(result);
	        }
	 
	        @Override
	        protected void onPreExecute() {
	            progress = ProgressDialog.show(Main_menu.this, null,
	                    "Loading application info...");
	            super.onPreExecute();
	        }
	 
	        @Override
	        protected void onProgressUpdate(Void... values) {
	            super.onProgressUpdate(values);
	        }
	    }
	
	
	
	
}
