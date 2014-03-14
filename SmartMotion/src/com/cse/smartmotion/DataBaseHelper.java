package com.cse.smartmotion;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Simple notes database access helper class. Defines the basic CRUD operations
 * for the notepad example, and gives the ability to list all notes as well as
 * retrieve or modify a specific note.
 * 
 * This has been improved from the first version of this tutorial through the
 * addition of better error handling and also using returning a Cursor instead
 * of using a collection of inner classes (which is less scalable and not
 * recommended).
 */
public class DataBaseHelper {

    public static final String KEY_USER = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_ROWID = "_id";

    private static final String TAG = "NotesDbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "data";
    private static final String DATABASE_TABLE = "accounts";
    private static final int DATABASE_VERSION = 2;
    
    /**
     * Database creation sql statement
     */
    private static final String DATABASE_CREATE =
        "create table " + DATABASE_TABLE + " (_id integer primary key autoincrement, "
        + KEY_USER + " text not null, "
        + KEY_PASSWORD + " body text not null);";

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS notes");
            onCreate(db);
        }
    }

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     * 
     * @param ctx the Context within which to work
     */
    public DataBaseHelper(Context ctx) {
        this.mCtx = ctx;
    }

    /**
     * Open the notes database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     * 
     * @return this (self reference, allowing this to be chained in an
     *         initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    public DataBaseHelper open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }


    /**
     * Create a new note using the title and body provided. If the note is
     * successfully created return the new rowId for that note, otherwise return
     * a -1 to indicate failure.
     * 
     * @param title the title of the note
     * @param body the body of the note
     * @return rowId or -1 if failed
     */
    public long createAccount(String user, String password) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_USER, user);
        initialValues.put(KEY_PASSWORD, password);
        
        Log.i("MyActivity", "DATABASE_TABLE " + DATABASE_TABLE);
        Log.i("MyActivity", "user " + user);
        Log.i("MyActivity", "password " + password);
        
//        if (checkAccountExists(user)) {
        	long result = mDb.insert(DATABASE_TABLE, null, initialValues);
        	Log.i("MyActivity", "create result " + result);
        	return result;
//        }
//        else {
//        	return 0;
//        }
    }

    /**
     * Delete the note with the given rowId
     * 
     * @param rowId id of note to delete
     * @return true if deleted, false otherwise
     */
    public boolean deleteNote(long rowId) {

        return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

//    /**
//     * Return a Cursor over the list of all notes in the database
//     * 
//     * @return Cursor over all notes
//     */
//    public Cursor fetchAllNotes() {
//
//        return mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_TITLE,
//                KEY_BODY}, null, null, null, null, null);
//    }
   
    public boolean checkAccountExists(String user) {
    	
    	Log.i("MyActivity", "usernames TESTESTESETESTES");
    	List<String> users = new ArrayList<String>();
    	Cursor mCursor = mDb.query(DATABASE_TABLE, new String[] {KEY_USER}, 
    			null, null, null, null, null);

		if (mCursor.moveToFirst()) {
			do {
//		        Log.i("MyActivity", "usernames: " + mCursor.getString(0));
				users.add(mCursor.getString(0));
			} while (mCursor.moveToNext());
		}
		if (mCursor != null && !mCursor.isClosed()) {
			mCursor.close();
		}
		
		if (users.contains(user)) {
			return true;
		}
		else {
			return false;
		}
	}

    /**
     * Return a Cursor positioned at the note that matches the given rowId
     * 
     * @param rowId id of note to retrieve
     * @return Cursor positioned to matching note, if found
     * @throws SQLException if note could not be found/retrieved
     */
    public String fetchPassword(String user) throws SQLException {

    	String password = null;
    	
        Cursor mCursor =
            mDb.query(true, DATABASE_TABLE, new String[] {
                    KEY_USER, KEY_PASSWORD}, null, null, //KEY_USER + "=" + user, null,
                    null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        
//        Log.i("MyActivity", "password: " + mCursor.getString(1));
        password = mCursor.getString(1);
        return password;
    }
//
//    /**
//     * Update the note using the details provided. The note to be updated is
//     * specified using the rowId, and it is altered to use the title and body
//     * values passed in
//     * 
//     * @param rowId id of note to update
//     * @param title value to set note title to
//     * @param body value to set note body to
//     * @return true if the note was successfully updated, false otherwise
//     */
//    public boolean updateNote(long rowId, String title, String body) {
//        ContentValues args = new ContentValues();
//        args.put(KEY_TITLE, title);
//        args.put(KEY_BODY, body);
//
//        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
//    }
}
