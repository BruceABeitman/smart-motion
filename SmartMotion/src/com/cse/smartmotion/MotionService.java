package com.cse.smartmotion;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.os.IBinder;
import android.util.Log;

/**
 * Simple demo service that schedules a timer task to write something to 
 * the log at regular intervals.
 * @author BMB
 *
 */
public class MotionService extends Service {
	
	private SensorManager mSensorManager;
	private Sensor mSensor;
	private TriggerEventListener mTriggerEventListener;
	
	private SensorManager sensorManager;
	
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
  
  mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
  mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION);
  
  mTriggerEventListener = new TriggerEventListener() {
	    @Override
	    public void onTrigger(TriggerEvent event) {
//	    	RawData gyroData = new RawData();
//	    	ArrayList sensorData=new ArrayList();
//	    	StringBuilder record=new StringBuilder();
////    		started=true;
//	    	SensorManager sensorManager;
//	    	sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
//    		Sensor accel=sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
////    		this.startTime = System.currentTimeMillis();
////    		this.lastTimePatternChecked = this.startTime;
//    		sensorManager.registerListener(this, accel,SensorManager.SENSOR_DELAY_FASTEST);
////    		requestTriggerSensor(TriggerEventListener, Sensor)
	    	Log.i(LOGTAG, "Significant Motion Detected!!");
	    }
	};
	
  mSensorManager.requestTriggerSensor(mTriggerEventListener, mSensor);
  
  // Used to verify service running
  //  Log.i(LOGTAG, "started");
  //  mTimer.schedule(mLogTask, mDelay, mPeriod);
  
 }
}
