package com.cse.smartmotion;


import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class NewGesture extends Activity implements SensorEventListener, OnClickListener {
	//Static variables:
	private static int PATTERN_CHECK_INTERVAL = 200; //millis
	private static int PATTERN_TIMEOUT_INTERVAL = 4000; //millis
	private static int PATTERN_IDLE_TIMEOUT_INTERVAL = 750; //millis
	
	
	//Private fields:
	private Button btnStart, btnStop, btnUpload;
	//private EditText edit;
	private SensorManager sensorManager;
	private boolean started=false;
	private ArrayList sensorData;
	private TextView acceleration;
	private StringBuilder record;
	private DatabaseHandler db;
	private String package_selected;
	
	//Pattern extraction fields:
	private long startTime;
	private long lastTimePatternChecked;
	private RawData gyroData;
	private Pattern pattern;
	
	private static final int GET_META_DATA = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newgesture);
		sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensorData=new ArrayList();
        db=new DatabaseHandler(this);
        record=new StringBuilder();
        
        btnStart=(Button)findViewById(R.id.btnStart);
        btnStop=(Button)findViewById(R.id.btnStop);
        btnUpload=(Button)findViewById(R.id.btnUpload);
        //edit=(EditText)findViewById(R.id.editText);
        
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnUpload.setOnClickListener(this);
        acceleration=(TextView)findViewById(R.id.acceleration);
        btnStart.setEnabled(true);
        btnStop.setEnabled(false);
        if(sensorData==null||sensorData.size()==0){
        	btnUpload.setEnabled(false);
        	
        }
		
		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new__gesture, menu);
		return true;
	}
	
	  protected void onPause(){
	    	super.onPause();
	    	if(started==true){
	    		sensorManager.unregisterListener(this);
	    	}
	    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			
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
        package_selected = (String) extras.getString("package_name");

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
		finish();
		
	}
	
	public void goback(View view){
		Intent intent=new Intent(this,Main_menu.class);
		startActivity(intent);
		finish();
	}
	
	
	
public void onClick(View v){
    	
    	switch(v.getId()){
    	
    	case R.id.btnStart:
    		
    		this.gyroData = new RawData();
    		btnStart.setEnabled(false);
    		btnStop.setEnabled(true);
    		btnUpload.setEnabled(false);
    		sensorData=new ArrayList();
    		record=new StringBuilder();
    		started=true;
    		Sensor accel=sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    		this.startTime = System.currentTimeMillis();
    		this.lastTimePatternChecked = this.startTime;
    		sensorManager.registerListener(this, accel,SensorManager.SENSOR_DELAY_FASTEST);
    		break;
    		
    	case R.id.btnStop:
    		
    		btnStart.setEnabled(true);
    		btnStop.setEnabled(false);
    		btnUpload.setEnabled(true);
    		
    		started=false;
    		sensorManager.unregisterListener(this);
    		break;
    		
    	case R.id.btnUpload:
    		adddata();
    		
    		break;
    		
    	default:
    		break;
    		
    	}
    	
    }
    
    public void onAccuracyChanged(Sensor sensor, int accuracy){
    	
    }
    
    public void adddata(){
    	String name=package_selected;
    	String result=record.toString();
    	db.addGesture(new Motiongesture(name,result));
    	
    	Toast.makeText(NewGesture.this, "DavaSaved", Toast.LENGTH_LONG).show();
    	
    }
    
    public void onSensorChanged(SensorEvent event){
    	
    	if(started){
    		double x=event.values[0];
    		double y=event.values[1];
    		double z=event.values[2];
    		
    		long timestamp=System.currentTimeMillis();
    		GyroData data = new GyroData(timestamp, x, y, z);
    		sensorData.add(data);
    		record.append(data.toString());
    		this.gyroData.appendData2D(timestamp-this.startTime, (float)x, (float)z);
    		acceleration.setText("X:"+event.values[0]+"\nY:"+event.values[1]+"\nZ"+event.values[2]);
    		
    		if(timestamp-this.lastTimePatternChecked>=NewGesture.PATTERN_CHECK_INTERVAL){
    			this.lastTimePatternChecked = timestamp;
    			this.pattern = new Pattern(this.gyroData);
    			
    			if((RawData.idleTime()>=NewGesture.PATTERN_IDLE_TIMEOUT_INTERVAL) || (timestamp-this.startTime>=NewGesture.PATTERN_TIMEOUT_INTERVAL)){
    				this.gyroData.filterNoiseXY(Pattern.FILTER_PERIOD);
    	    		btnStart.setEnabled(true);
    	    		btnStop.setEnabled(false);
    	    		btnUpload.setEnabled(true);
    				started=false;
    	    		sensorManager.unregisterListener(this);
    			}
    		}
    		
    	}
    }
	


    @Override
    public void onBackPressed() {
    	Intent i = new Intent(this, Main_menu.class);
        startActivity(i);
        finish();
    }
}
