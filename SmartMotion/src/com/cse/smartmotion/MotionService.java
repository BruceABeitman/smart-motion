package com.cse.smartmotion;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Simple demo service that schedules a timer task to write something to 
 * the log at regular intervals.
 * @author BMB
 *
 */
public class MotionService extends Service {
 /**
  * Delay until first execution of the Log task.
  */
 private final long mDelay = 0;
 /**
  * Period of the Log task.
  */
 private final long mPeriod = 500;
 /**
  * Log tag for this service.
  */ 
 private final String LOGTAG = "BootDemoService";
 /**
  * Timer to schedule the service.
  */
 private Timer mTimer;
 
 /**
  * Implementation of the timer task.
  */
 private class LogTask extends TimerTask {
  public void run() {
   Log.i(LOGTAG, "scheduled");
  }
 }
 private LogTask mLogTask; 
 
 @Override
 public IBinder onBind(final Intent intent) {
  return null;
 }
 
 @Override
 public void onCreate() {
  super.onCreate();
  Log.i(LOGTAG, "created");
  mTimer = new Timer();
  mLogTask = new LogTask();
 }
 
 @Override
 public void onStart(final Intent intent, final int startId) {
  super.onStart(intent, startId);
  Log.i(LOGTAG, "started");
  mTimer.schedule(mLogTask, mDelay, mPeriod);
 }
}
