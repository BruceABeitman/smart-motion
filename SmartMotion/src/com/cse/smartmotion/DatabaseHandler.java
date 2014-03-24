package com.cse.smartmotion;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	   // All Static variables
	   // Database Version
	
	private static final int DATABASE_VERSION = 1;
	
	// Database Name
	
	private static final String DATABASE_NAME = "gesturesmanager";
	//Contacts table name
	
	private static final String TABLE_GESTURES="gestures";
	
	// Contacts Table Columns names
	
	private static final String KEY_ID="id";
	private static final String KEY_NAME="name";
	private static final String KEY_GEST="gest";
	
	public DatabaseHandler(Context context){
		super(context, DATABASE_NAME,null,DATABASE_VERSION);
		
	}
	
	public void onCreate(SQLiteDatabase db) {
       String CREATE_GESTURES_TABLE = "CREATE TABLE " + TABLE_GESTURES + "("
          + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
          + KEY_GEST + " TEXT" + ")";
         db.execSQL(CREATE_GESTURES_TABLE);
    }

// Upgrading database
@Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  // Drop older table if existed
         db.execSQL("DROP TABLE IF EXISTS " + TABLE_GESTURES);

  // Create tables again
         onCreate(db);
    }

    void addGesture(Motiongesture gesture) {
         SQLiteDatabase db = this.getWritableDatabase();

         ContentValues values = new ContentValues();
         values.put(KEY_NAME, gesture.getName()); // Contact Name
         values.put(KEY_GEST, gesture.getGest()); // Contact Phone

         // Inserting Row
         db.insert(TABLE_GESTURES, null, values);
         db.close(); // Closing database connection
    }

    Motiongesture getGesture(int id) {
         SQLiteDatabase db = this.getReadableDatabase();

         Cursor cursor = db.query(TABLE_GESTURES, new String[] { KEY_ID,
          KEY_NAME, KEY_GEST }, KEY_ID + "=?",
          new String[] { String.valueOf(id) }, null, null, null, null);
          if (cursor != null)
          cursor.moveToFirst();

         Motiongesture gesture = new Motiongesture(Integer.parseInt(cursor.getString(0)),
          cursor.getString(1), cursor.getString(2));
          // return contact
         return gesture;
    }

    public List<Motiongesture> getAllGestures() {
          List<Motiongesture> gestureList = new ArrayList<Motiongesture>();
          // Select All Query
          String selectQuery = "SELECT  * FROM " + TABLE_GESTURES;

          SQLiteDatabase db = this.getWritableDatabase();
          Cursor cursor = db.rawQuery(selectQuery, null);

          // looping through all rows and adding to list
          if (cursor.moveToFirst()) {
          do {
              Motiongesture gesture = new Motiongesture();
              gesture.setID(Integer.parseInt(cursor.getString(0)));
              gesture.setName(cursor.getString(1));
              gesture.setGest(cursor.getString(2));
              // Adding contact to list
              gestureList.add(gesture);
        } while (cursor.moveToNext());
       }

       // return contact list
         return gestureList;
     }

       public int updateGesture(Motiongesture gesture) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_NAME, gesture.getName());
            values.put(KEY_GEST, gesture.getGest());

             // updating row
            return db.update(TABLE_GESTURES, values, KEY_ID + " = ?",
            new String[] { String.valueOf(gesture.getID()) });
       }

       public void deleteGesture(Motiongesture gesture) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_GESTURES, KEY_ID + " = ?",
            new String[] { String.valueOf(gesture.getID()) });
            db.close();
       }


// Getting contacts Count
       public int getGesturesCount() {
             String countQuery = "SELECT  * FROM " + TABLE_GESTURES;
             SQLiteDatabase db = this.getReadableDatabase();
             Cursor cursor = db.rawQuery(countQuery, null);
             cursor.close();

  // return count
             return cursor.getCount();
       }
}
