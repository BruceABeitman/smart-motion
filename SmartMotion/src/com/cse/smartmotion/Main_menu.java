package com.cse.smartmotion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class Main_menu extends Activity {
String tag="SmartMotion";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu_activity);
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
		Intent intent=new Intent(this, New_Gesture.class);
		startActivity(intent);
	}
	
	public void Settings(View view){
		Intent intent=new Intent(this, Settings.class);
		startActivity(intent);
	}
	
}
