package com.cse.smartmotion;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

// Here's a comment for you
// I changed something here for you
//what happend here???
public class MainActivity extends Activity {
	
	private DataBaseHelper mDbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mDbHelper = new DataBaseHelper(this);
        mDbHelper.open();
        
        Button signIn = (Button) findViewById(R.id.signIn_button);
        Button signUp = (Button) findViewById(R.id.signUp_button);
        
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void Main_Menu(View view){
		Intent intent=new Intent(this, Main_menu.class);
		startActivity(intent);
	}
	
	private void verifyAccount() {
		
		EditText editUsername = (EditText)findViewById(R.id.usr_name_edit);
		EditText editPassword = (EditText)findViewById(R.id.pass_name_edit);

		String username = editUsername.getText().toString();
		String password = editPassword.getText().toString().trim();
		
		if (mDbHelper.checkAccountExists(username)) {
			// account exists, check password
			String correctPass = mDbHelper.fetchPassword(username);
			if (correctPass.equals(password)) {
				// correct password, login
				startActivity(new Intent(this, New_Gesture.class));
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
	
	

}
