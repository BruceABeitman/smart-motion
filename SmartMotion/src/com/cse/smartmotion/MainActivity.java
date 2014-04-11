package com.cse.smartmotion;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {
	
	private DataBaseHelper mDbHelper;
	private static Context context;
	
	private AdView mAdView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		MainActivity.context = getApplicationContext();
		
		mDbHelper = new DataBaseHelper(this);
        mDbHelper.open();
        
        Button signIn = (Button) findViewById(R.id.signIn_button);
        Button signUp = (Button) findViewById(R.id.signUp_button);
        Button startService = (Button) findViewById(R.id.startService);
        
        signIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                verifyAccount();
            	
            }
        });
        
        signUp.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                createAccount();
            }
        });
        
        startService.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
            	startService();
            }
        });
        
		 AdRequest adRequest = new AdRequest.Builder().addTestDevice("3A9B4BB089824ED7149E29CF657FABB9").build();

         mAdView = (AdView) findViewById(R.id.adView);
         mAdView.setAdListener(new ToastAdListener(this));
         mAdView.loadAd(adRequest);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void verifyAccount() {
//		Intent intent=new Intent(this, Main_menu.class);
//		startActivity(intent);
		
		EditText editUsername = (EditText)findViewById(R.id.usr_name_edit);
		EditText editPassword = (EditText)findViewById(R.id.pass_name_edit);

		String username = editUsername.getText().toString();
		String password = editPassword.getText().toString().trim();
		
		if (mDbHelper.checkAccountExists(username)) {
			// account exists, check password
			String correctPass = mDbHelper.fetchPassword(username);
			Log.i("MainActivity", "user entered pass: " + password);
			Log.i("MainActivity", "correct pass: " + correctPass);
			if (correctPass.equals(password)) {
				// correct password, login
				startActivity(new Intent(this, Main_menu.class));
			}
			else {
				// account exists, but wrong password, display message
				// Try again?
				new AlertDialog.Builder(this)
						.setTitle("Error")
						.setMessage("Wrong password")
						.setNeutralButton("Try Again",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
									}
								}).show();
			}
		}
		else {
			// account doesn't exist, display message
			// Try again?
			new AlertDialog.Builder(this)
					.setTitle("Error")
					.setMessage("Account doesn't exist")
					.setNeutralButton("Try Again",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).show();
		}
	
	
	}
	

	private void createAccount() {
		startActivity(new Intent(this, CreateAccount.class));
	}
	
	private void startService() {
		Intent mServiceIntent = new Intent();
	    mServiceIntent.setAction("com.cse.smartmotion.MotionService");
	    context.startService(mServiceIntent);
	}
	

}
