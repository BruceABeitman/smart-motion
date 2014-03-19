package com.cse.smartmotion;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class New_Gesture extends Activity {

	private static final int GET_META_DATA = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new__gesture_activity);
		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new__gesture, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	public void onResume(){
		super.onResume();
		Bundle extras = getIntent().getExtras(); 
		
		if (null != extras){
        String package_selected = (String) extras.getString("package_name");

        final PackageManager pm = getApplicationContext().getPackageManager();
        ApplicationInfo app_selected=null;
        try {
        	app_selected = pm.getApplicationInfo( package_selected, GET_META_DATA);
           
        } catch (final NameNotFoundException e) {
            e.printStackTrace();
        }
        
        TextView tv = (TextView)findViewById(R.id.app_selected);
        tv.setText("App to Launch: "+ app_selected.loadLabel(pm));
		}
		
		
		
	}
	
	public void select_app(View view){
		Intent intent=new Intent(this, AllAppsActivity.class);
		startActivity(intent);
	}
	


}
