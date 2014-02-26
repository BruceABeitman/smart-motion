package com.cse.smartmotion;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Main_menu extends Activity {

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

	
	public void New_Gesture(View view){
		Intent intent=new Intent(this, New_Gesture.class);
		startActivity(intent);
	}
	
	public void Settings(View view){
		Intent intent=new Intent(this, Settings.class);
		startActivity(intent);
	}
	
}
