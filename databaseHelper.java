package com.pramod;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class databaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "applicationdataofautmaticprofilechanger(Don't delete it)";

	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table automaticprofile (_id integer primary key autoincrement, "
			+ "name_of_profile text not null, type_of_profile text not null," +
					" Startinghour text not null,Startingminute text not null,Endinghour text not null,Endingminute text not null,Sunday boolean not null,Monday Boolean not null,Tuesday Boolean not null,Wednesday Boolean not null,Thursday Boolean not null,Friday Boolean not null,Saturday Boolean not null,spinboxno text not null);";

	public databaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Method is called during creation of the database
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	// Method is called during an upgrade of the database, e.g. if you increase
	// the database version
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		Log.w(databaseHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS automaticprofile");
		onCreate(database);
	}

}
