package com.pramod;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class dbAdapter {
	public static final String KEY_ROWID = "_id";
	public static final String KEY_Name_of_profile = "name_of_profile";
	public static final String KEY_type_of_profile = "type_of_profile";
	public static final String KEY_starting_hour = "Startinghour";
	public static final String KEY_starting_minute = "Startingminute";
	public static final String KEY_ending_hour = "Endinghour";
	public static final String KEY_ending_minute = "Endingminute";
	public static final String KEY_Sunday = "Sunday";
	public static final String KEY_Monday = "Monday";
	public static final String KEY_Tuesday = "Tuesday";
	public static final String KEY_Wednesday = "Wednesday";
	public static final String KEY_Thursday = "Thursday";
	public static final String KEY_Friday = "Friday";
	public static final String KEY_Saturday = "Saturday";
	public static final String KEY_Spinboxno = "spinboxno";
	
	private static final String DATABASE_TABLE = "automaticprofile";
	private Context context;
	private SQLiteDatabase database;
	private databaseHelper dbHelper;

	public dbAdapter(Context context) {
		this.context = context;
	}

	public dbAdapter open() throws SQLException {
		dbHelper = new databaseHelper(context);
		database = dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		dbHelper.close();
	}

	/**
	 * Create a new todo If the todo is successfully created return the new
	 * rowId for that note, otherwise return a -1 to indicate failure.
	 *///name_of_profile,type_of_profile,Startinghour,Startingminute,Endinghour,Endingminute,Sunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday
	public long createAutomaticProfile(String name_of_profile, String type_of_profile, String Startinghour,String Startingminute,String Endinghour,String Endingminute,String Sunday,String Monday,String Tuesday,String Wednesday,String Thursday,String Friday,String Saturday,String Spinboxno) {
		ContentValues initialValues = createContentValues(name_of_profile,type_of_profile,Startinghour,Startingminute,Endinghour,Endingminute,Sunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Spinboxno);

		return database.insert(DATABASE_TABLE, null, initialValues);
	}

	/**
	 * Update the todo
	 */
	public boolean updateAutomaticProfile(long rowId, String name_of_profile, String type_of_profile, String Startinghour,String Startingminute,String Endinghour,String Endingminute,String Sunday,String Monday,String Tuesday,String Wednesday,String Thursday,String Friday,String Saturday,String Spinboxno) {
		ContentValues updateValues = createContentValues(name_of_profile,type_of_profile,Startinghour,Startingminute,Endinghour,Endingminute,Sunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Spinboxno);

		return database.update(DATABASE_TABLE, updateValues, KEY_ROWID + "="
				+ rowId, null) > 0;
	}

	/**
	 * Deletes todo
	 */
	public boolean deleteAutomaticProfile(long rowId) {
		return database.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}

	/**
	 * Return a Cursor over the list of all todo in the database
	 * 
	 * @return Cursor over all notes
	 */
	public Cursor fetchAllAutomaticProfile() {
		return database.query(DATABASE_TABLE, new String[] { KEY_ROWID,
				KEY_Name_of_profile, KEY_type_of_profile, KEY_starting_hour,KEY_starting_minute,KEY_ending_hour,KEY_ending_minute,KEY_Sunday,KEY_Monday,KEY_Tuesday,KEY_Wednesday,KEY_Thursday,KEY_Friday,KEY_Saturday,KEY_Spinboxno }, null, null, null,
				null, null);
	}

	/**
	 * Return a Cursor positioned at the defined todo
	 */
	public Cursor fetchAutomaticProfile(long rowId) throws SQLException {
		Cursor mCursor = database.query(true, DATABASE_TABLE, new String[] {
				KEY_ROWID, KEY_Name_of_profile, KEY_type_of_profile, KEY_starting_hour,KEY_starting_minute,KEY_ending_hour,KEY_ending_minute,KEY_Sunday,KEY_Monday,KEY_Tuesday,KEY_Wednesday,KEY_Thursday,KEY_Friday,KEY_Saturday,KEY_Spinboxno },
				KEY_ROWID + "=" + rowId, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	private ContentValues createContentValues(String name_of_profile,String type_of_profile,String Startinghour,String Startingminute,String Endinghour,String Endingminute,String Sunday,String Monday,String Tuesday,String Wednesday,String Thursday,String Friday,String Saturday,String spinboxno) {
		ContentValues values = new ContentValues();
		values.put(KEY_Name_of_profile, name_of_profile);
		values.put(KEY_type_of_profile, type_of_profile);
		values.put(KEY_starting_hour, Startinghour);
		values.put(KEY_starting_minute, Startingminute);
		values.put(KEY_ending_hour, Endinghour);
		values.put(KEY_ending_minute, Endingminute);
		values.put(KEY_Sunday, Sunday);
		values.put(KEY_Monday, Monday);
		values.put(KEY_Tuesday, Tuesday);
		values.put(KEY_Wednesday, Wednesday);
		values.put(KEY_Thursday, Thursday);
		values.put(KEY_Friday, Friday);
		values.put(KEY_Saturday, Saturday);
		values.put(KEY_Spinboxno, spinboxno);
		return values;
	}

}
