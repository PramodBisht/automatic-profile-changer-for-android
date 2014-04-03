package com.pramod;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBhelper extends SQLiteOpenHelper
{
	private static final String CREATE_TABLE="create table "+Constants.TABLE_NAME+" " +
			"("+Constants.KEY_ID+" integer primary key autoincrement, "
			+Constants.KEY_name_of_profile+" Text not null, "
			+
			Constants.KEY_Startinghour+" integer not null, "+
			Constants.KEY_Startingminute+" integer not null, "+Constants.KEY_Endinghour+
			" integer not null, "+Constants.KEY_Endingminute+" integer not null, "
			+Constants.KEY_Sunday+" Text not null, "
			+Constants.KEY_Monday+" Text not null, "+Constants.KEY_Tuesday+
			" Text not null, "+Constants.KEY_Wednesday+" Text not null, "
			+Constants.KEY_Thursday+" Text not null, "+Constants.KEY_Friday+
			" Text not null, "+Constants.KEY_Saturday+" Text not null, "
			+Constants.KEY_spinboxno+" integer not null);";
	
	public MyDBhelper(Context context, String name, CursorFactory factory,
	int version) {
	super(context, name, factory, version);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
	try {
	db.execSQL(CREATE_TABLE);

	Log.v("MyDBhelper onCreate","Creating all the tables");
	} catch(SQLiteException ex) {
	Log.v("Create table exception", ex.getMessage());
	}
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion,
	int newVersion) {
	Log.w("TaskDBAdapter", "Upgrading from version "+oldVersion
	+" to "+newVersion
	+", which will destroy all old data");
	db.execSQL("drop table if exists "+Constants.TABLE_NAME);
	onCreate(db);
	}

}
