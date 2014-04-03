package com.pramod;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class MyDB {
	private SQLiteDatabase db;
	private final Context context;
	private final MyDBhelper dbhelper;

	public MyDB(Context c) {
		context = c;
		dbhelper = new MyDBhelper(context, Constants.DATABASE_NAME, null,
				Constants.DATABASE_VERSION);
	}

	public void close() {
		//Log.i("pramod", "db is closing");
		try {
			db.close();
		} catch (Exception e) {
			// TODO: handle exception
			//Log.i("pramod message ",
				//	"database could not be closed" + e.getMessage());
		}
	}

	public void open() throws SQLiteException {
		// this method is runnin
		try {
			db = dbhelper.getWritableDatabase();
			//Log.i("message about openning of db", "db is openned successfully");
		} catch (SQLiteException ex) {
			Log.v("Open database exception caught", ex.getMessage());
			db = dbhelper.getReadableDatabase();
		}
	}

	public long insertdiary(int id, String name_of_profile, int Startinghour,
			int Startingminute, int Endinghour, int Endingminute,
			boolean Sunday, boolean Monday, boolean Tuesday, boolean Wednesday,
			boolean Thursday, boolean Friday, boolean Saturday, int spinboxno) {
		// this method is running
		try {
			ContentValues newTaskValue = new ContentValues();
			newTaskValue.put(Constants.KEY_ID, id);
			newTaskValue.put(Constants.KEY_name_of_profile, name_of_profile);
			newTaskValue.put(Constants.KEY_Startinghour, Startinghour);
			newTaskValue.put(Constants.KEY_Startingminute, Startingminute);
			newTaskValue.put(Constants.KEY_Endinghour, Endinghour);
			newTaskValue.put(Constants.KEY_Endingminute, Endingminute);
			newTaskValue.put(Constants.KEY_Sunday, Sunday);
			newTaskValue.put(Constants.KEY_Monday, Monday);
			newTaskValue.put(Constants.KEY_Tuesday, Tuesday);
			newTaskValue.put(Constants.KEY_Wednesday, Wednesday);
			newTaskValue.put(Constants.KEY_Thursday, Thursday);
			newTaskValue.put(Constants.KEY_Friday, Friday);
			newTaskValue.put(Constants.KEY_Saturday, Saturday);
			newTaskValue.put(Constants.KEY_spinboxno, spinboxno);
		//	newTaskValue.put(Constants.delim, delim);
			//Log.i("this is message 3", "inside the insert diary");
			return db.insert(Constants.TABLE_NAME, null, newTaskValue);
		} catch (SQLiteException ex) {

			Log.v("Insert into database exception caught", ex.getMessage());
			return -1;
		}
	}

	public Cursor getdiaries() {
		Cursor c = db.query(Constants.TABLE_NAME, null, null, null, null, null,
				null);
		return c;
	}

	public void deleterow(int rowId) {
		db.delete(Constants.TABLE_NAME, "_id=" + rowId, null);
	}

	public void update_row(int rowId, int row_no) {
		int row2;
		row2 = rowId + 1;
		for (int i = rowId; i <= row_no; i++) {

			//Log.i("inside the MyDB row id is " + rowId, "row_no" + row_no);
			ContentValues args = new ContentValues();
			args.put(Constants.KEY_ID, i);
			// args.put(Constants.KEY_name_of_profile, "modified");

			db.update(Constants.TABLE_NAME, args, "_id=" + row2, null);
			row2++;
		}

	}

	public void modify_row_all_item(int id, String name_of_profile,
			int type_of_profile, int startinghour, int startingmin,
			int endinghour, int endingmin, int mon, int tues, int wed,
			int thurs, int fri, int sat,int sun) {
		ContentValues args = new ContentValues();

		args.put(Constants.KEY_name_of_profile,name_of_profile);
		args.put(Constants.KEY_spinboxno,type_of_profile);
		args.put(Constants.KEY_Startinghour,startinghour);
		args.put(Constants.KEY_Startingminute,startingmin);
		args.put(Constants.KEY_Endinghour,endinghour);
		args.put(Constants.KEY_Endingminute,endingmin);
		args.put(Constants.KEY_Monday,mon);
		args.put(Constants.KEY_Tuesday,tues);
		args.put(Constants.KEY_Wednesday,wed);
		args.put(Constants.KEY_Thursday,thurs);
		args.put(Constants.KEY_Friday,fri);
		args.put(Constants.KEY_Saturday,sat);
		args.put(Constants.KEY_Sunday,sun);
		db.update(Constants.TABLE_NAME, args, "_id=" + id, null);
	}

}
