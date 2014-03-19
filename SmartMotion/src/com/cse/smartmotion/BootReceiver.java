package com.cse.smartmotion;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Simple receiver that will handle the boot completed intent and send the intent to 
 * launch the BootDemoService.
 *
 */
public class BootReceiver extends BroadcastReceiver {
	 @Override
	 public void onReceive(final Context context, final Intent bootintent) {
		  Intent mServiceIntent = new Intent();
		  mServiceIntent.setAction("com.cse.smartmotion.MotionService");
		  context.startService(mServiceIntent);
	 }
}
