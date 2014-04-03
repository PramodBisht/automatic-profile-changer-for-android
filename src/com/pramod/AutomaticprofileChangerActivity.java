package com.pramod;


import java.util.ArrayList;
import java.util.Locale;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.airpush.android.Airpush;
import com.bugsense.trace.BugSenseHandler;

public class AutomaticprofileChangerActivity extends ListActivity {
	public static int flag = 0;
	static public int flag2;
	int no_of_rows;
	int first_start=0;
	/*
	 * itemno 0 for stop display
	 * item no 1 for start display
	 */
	
	
	public static int flag_for_time_format = 0;

	private final int start_service = 1, stop_service = 2, about = 3,
			Helpscreen = 4,/* donatescreen = 6 */settingscreen = 5, upgradation = 6;
	private PendingIntent mAlarmSender;
	MyDB dba;
	// ListView lv;
	public DiaryAdapter myAdapter;

	// DataAdapter is same as diaryAdapter
	private final int GROUP_DEFAULT = 0;
	private final int GROUP_1 = 1;
	private final int GROUP_2 = 2;
	String data = "mydatastorage";
	// SharedPreferences prefs;

	Editor medit;
	String itemstring = null;
	SharedPreferences prefs, prefs2, prefs3;

	SharedPreferences pre;

	String stmin[] = new String[2];

	String etmin[] = new String[2];

	//private int itemno = 0;// pre.getInt("item_value", 1);
	private int itemno = 0;// pre.getInt("item_value", 1);

	/** Called when the activity is first created. */

	private class MyDiary {
		public String name_of_profile;
		public int startinghour;
		public int startingmin;
		public int endinghour;
		public int endingmin;
		// public String id;

		public int type_of_profile;
		public int mon;
		public int tues;
		public int wed;
		public int thurs;
		public int fri;
		public int sat;
		public int sun;
		

		public MyDiary(String n, int sh, int sm, int eh, int em, int top,
				int mon, int tues, int wed, int thur, int fri, int sat, int sun) {
			// this.id = id;
			name_of_profile = n;
			startinghour = sh;
			startingmin = sm;
			endinghour = eh;
			endingmin = em;
			type_of_profile = top;
			this.mon = mon;
			this.tues = tues;
			this.wed = wed;
			this.thurs = thur;
			this.fri = fri;
			this.sat = sat;
			this.sun = sun;
			

		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.e("EULA", "EULA");
		Eula.show(this);
		dba = new MyDB(this);
		dba.open();

		super.onCreate(savedInstanceState);
		// //Log.i("at the starting ", "of application");
		prefs2 = getSharedPreferences("settingsdata", MODE_WORLD_READABLE);
		int id2 = prefs2.getInt("language_selected", 0);
		pre=getSharedPreferences("mystartsettings", MODE_WORLD_READABLE);
		/* till next //
		 starting of airpush code*/
	if(Integer.parseInt(VERSION.SDK) > 3){

				Log.i("airpush-pramod","starting of airpush code in main");
			//25487
				new Airpush(getApplicationContext(),"15785","1314125326404744960",
				false,true,true);
				//false for real device
				}
			Log.i("airpush-pramod","end of airpush code in main");
			/*
			 * ending of airpush code
			 */

		change_the_language(id2);
		// Toast.makeText(this,String.valueOf(Build.VERSION.CODENAME),
		// Toast.LENGTH_SHORT);

		// //Log.i("no of rows are",String.valueOf(no_of_rows));
       // start_service();
		setContentView(R.layout.main);
		
		/*
		 * bugsense code
		 */
		BugSenseHandler.setup(this,"4b6bd9ad");
		

		
		prefs = getSharedPreferences(data, MODE_WORLD_READABLE);
		prefs3 = getSharedPreferences("sets", MODE_WORLD_WRITEABLE);
	//	start_first=getSharedPreferences("startvar", MODE_WORLD_READABLE);

		medit = prefs.edit();
		//

		mAlarmSender = PendingIntent.getService(
				AutomaticprofileChangerActivity.this, 0, new Intent(
						AutomaticprofileChangerActivity.this,
						BackgroundService.class), 0);

		Button btn = (Button) findViewById(R.id.button1);
		// Drawable d = findViewById(R.id.button1).getBackground();
		// PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.RED,
		// PorterDuff.Mode.SRC_ATOP);
		// d.setColorFilter(filter);

		// ListView lv=(ListView)findViewById(android.R.id.list);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent in = new Intent(AutomaticprofileChangerActivity.this,
						launchwithservice.class);

				startActivity(in);

			}
		});
//		if (itemno == 0) {
//			itemno = prefs.getInt("item_value", 0);
//			if (itemno == 1) {
//				start_service();
//			}
//		}

		// //Log.i("pramod", "i m running before diary adapter");
		myAdapter = new DiaryAdapter(this);
		if (flag == 0) {
			this.setListAdapter(myAdapter);
			// //Log.i("pramod", "i m running");
		}
		getListView().setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {

				default:
					// Object ob=getListAdapter().getItem(arg2);
					// String value=ob.toString();
					// //Log.i("the value of object is ",value);

					dialog_of_main(arg2);

					// Log.i("clicked on button", "true");
					break;

				}
			}
		});
		// //Log.i("version of os is after evaluation",String.valueOf(Build.VERSION.SDK_INT));
		Log.i("pramod ","the error");

	
        first_start();
		
		
	}

	// ---------------------------------------------------------------
	private class DiaryAdapter extends BaseAdapter {

		private LayoutInflater mInflater;
		private ArrayList<MyDiary> diaries;
		int arg0;
		View arg1;
		ViewGroup arg2;

		public DiaryAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
			diaries = new ArrayList<MyDiary>();
			launchwithservice.flag2 = 0;
			getdata();
			// Log.i("diaryAdapter 1", "inside the diary adapter");// running
			// //Log.i("no of entries are",String.valueOf(getCount()));
			getView(arg0, arg1, arg2);
		}

		public void getdata() {
			Cursor c = dba.getdiaries();

			startManagingCursor(c);
			if (c.moveToFirst()) {
				do {// string
					// String id_at_db = c.getString(c
					// .getColumnIndex(Constants.KEY_ID));
					String nameofprofile = c.getString(c
							.getColumnIndex(Constants.KEY_name_of_profile));
					int startinghour = c.getInt(c
							.getColumnIndex(Constants.KEY_Startinghour));
					int startingmin = c.getInt(c
							.getColumnIndex(Constants.KEY_Startingminute));
					// Log.i("inside the cursor starting minutes are",
					// String.valueOf(startingmin));
					int endinghour = c.getInt(c
							.getColumnIndex(Constants.KEY_Endinghour));
					int endingmin = c.getInt(c
							.getColumnIndex(Constants.KEY_Endingminute));
					int typeofprofile = c.getInt(c
							.getColumnIndex(Constants.KEY_spinboxno));
					int mon = c.getInt(c.getColumnIndex(Constants.KEY_Monday));
					int tues = c
							.getInt(c.getColumnIndex(Constants.KEY_Tuesday));
					int wed = c.getInt(c
							.getColumnIndex(Constants.KEY_Wednesday));
					int thurs = c.getInt(c
							.getColumnIndex(Constants.KEY_Thursday));
					int fri = c.getInt(c.getColumnIndex(Constants.KEY_Friday));
					int sat = c
							.getInt(c.getColumnIndex(Constants.KEY_Saturday));
					int sun = c.getInt(c.getColumnIndex(Constants.KEY_Sunday));
				//	int delim2=c.getInt(c.getColumnIndex(Constants.delim));
					// Log.i("day is (right answer) ", String.valueOf(tues));

					// Log.i("diaryAdapter 2", "inside the diary adapter");

					MyDiary temp = new MyDiary(nameofprofile, startinghour,
							startingmin, endinghour, endingmin, typeofprofile,
							mon, tues, wed, thurs, fri, sat, sun);
					diaries.add(temp);
					// Log.i("object of sunday contain",
					// String.valueOf(temp.tues));
					// Log.i("diaryAdapter 3", "inside the diary adapter");
				} while (c.moveToNext());
			}
		}

		@Override
		public int getCount() {

			// Log.i("pramod", "inside the getcount");
			return diaries.size();
		}

		@Override
		public MyDiary getItem(int i) {
			return diaries.get(i);
		}

		@Override
		public long getItemId(int i) {
			return i;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			{

				final ViewHolder holder;
				View v = arg1;
				View v2 = arg2; // created for testing
				if ((v == null) || (v.getTag() == null)) {
					v = mInflater.inflate(R.layout.listview_setting, null);
					holder = new ViewHolder();
					// holder.id = (TextView) v.findViewById(R.id.id1);

					holder.mname_of_profile = (TextView) v
							.findViewById(R.id.nameofprofile);
					holder.mstartinghour = (TextView) v
							.findViewById(R.id.startinghour);
					holder.mstartingmin = (TextView) v
							.findViewById(R.id.startingmin);
					holder.mendinghour = (TextView) v
							.findViewById(R.id.endinghour);
					holder.mendingmin = (TextView) v
							.findViewById(R.id.endingmin);
					holder.mtype_of_profile = (TextView) v
							.findViewById(R.id.typeofprofile);
					holder.mmon = (TextView) v.findViewById(R.id.mon);
					holder.AM_PM_Startinghour = (TextView) v
							.findViewById(R.id.am_pm_startinghour);
					holder.AM_PM_Endinghour = (TextView) v
							.findViewById(R.id.am_pm_endinghour);

					v.setTag(holder);
				} else {
					holder = (ViewHolder) v.getTag();
				}
				// TODO Auto-generated method stub
				try {
					holder.mdiary = getItem(arg0);
				} catch (Exception e) {
					flag = 1;
					// Log.i("Exception message", e.getMessage());
				}
				SharedPreferences prefs = getSharedPreferences("settingsdata",
						MODE_WORLD_READABLE);
				/*
				 * Time format is 0 for 12 hour and 1 for 24 hour
				 */
				flag_for_time_format = 0;
				int timeformat = prefs.getInt("time_format", 0);
				// Log.i("diaryAdapter 4 holder", "inside the diary adapter");
				if (flag == 0) {
					if (holder.mdiary.name_of_profile == " ") {
						// Log.i("name of profile", "null");
					}

					holder.mname_of_profile.setText(String
							.valueOf(holder.mdiary.name_of_profile));
					if (timeformat == 0) {
						if (holder.mdiary.startinghour < 12
								&& holder.mdiary.startinghour != 0) {
							holder.AM_PM_Startinghour.setText(R.string.AM);
							holder.mstartinghour.setText(String
									.valueOf(holder.mdiary.startinghour));

						} else if (holder.mdiary.startinghour >= 13) {
							// --------put the code here of pm
							int hour = holder.mdiary.startinghour - 12;
							// Log.i("strting hour in interface is ",
							// String.valueOf(hour));
							holder.mstartinghour.setText(String.valueOf(hour));

							holder.AM_PM_Startinghour.setText(R.string.PM);
						} else if (holder.mdiary.startinghour == 12
								&& holder.mdiary.startingmin > 0) {
							// put the condition for time b/w 12 and 13
							holder.AM_PM_Startinghour.setText(R.string.PM);
							holder.mstartinghour.setText(String
									.valueOf(holder.mdiary.startinghour));

						} else if (holder.mdiary.startinghour == 0
								&& holder.mdiary.startingmin > 0) {
							holder.AM_PM_Startinghour.setText(R.string.AM);
							holder.mstartinghour.setText("12");
						} else if (holder.mdiary.startinghour == 0
								&& holder.mdiary.startingmin == 0) {
							holder.mstartinghour.setText("12");
							holder.AM_PM_Startinghour.setText(R.string.AM);
						} else if (holder.mdiary.startinghour == 12
								&& holder.mdiary.startingmin == 0) {
							holder.mstartinghour.setText("12");
							holder.AM_PM_Startinghour.setText(R.string.PM);
						}

						// conditions of ending hours

						if (holder.mdiary.endinghour >= 13) {
							// ---------put the code here of PM management
							int hour2 = holder.mdiary.endinghour - 12;
							// Log.i("ending hour in interface is",
							// String.valueOf(hour2));
							holder.AM_PM_Endinghour.setText(R.string.PM);
							holder.mendinghour.setText(String.valueOf(hour2));

						} else if (holder.mdiary.endinghour == 0
								&& holder.mdiary.endingmin > 0) {
							holder.AM_PM_Endinghour.setText(R.string.AM);
							holder.mendinghour.setText("12");
						}

						else

						if (holder.mdiary.endinghour < 12
								&& holder.mdiary.endinghour != 0)

						{
							holder.AM_PM_Endinghour.setText(R.string.AM);
							holder.mendinghour.setText(String
									.valueOf(holder.mdiary.endinghour));
						} else if ((holder.mdiary.endinghour == 12)
								&& (holder.mdiary.endingmin > 0)) {
							// put the condition for time b/w 12 and 13
							// TODO Auto-generated method stub
							holder.AM_PM_Endinghour.setText(R.string.PM);
							holder.mendinghour.setText(String
									.valueOf(holder.mdiary.endinghour));
						} else if (holder.mdiary.endinghour == 0
								&& holder.mdiary.endingmin == 0) {
							holder.mendinghour.setText("12");
							holder.AM_PM_Endinghour.setText(R.string.AM);
						} else if (holder.mdiary.endinghour == 12
								&& holder.mdiary.endingmin == 0) {
							holder.mendinghour.setText("12");
							holder.AM_PM_Endinghour.setText(R.string.PM);
						}
					}
					// -------------------------------
					else {
						holder.mstartinghour.setText(String
								.valueOf(holder.mdiary.startinghour));
						holder.mendinghour.setText(String
								.valueOf(holder.mdiary.endinghour));
					}

					// //Log.i("diaryAdapter 6", "inside the diary adapter");
					if (holder.mdiary.startingmin < 10) {

						stmin[0] = "0";
						stmin[1] = String.valueOf(holder.mdiary.startingmin);

						holder.mstartingmin.setText(stmin[0] + stmin[1]);
					} else {

						holder.mstartingmin.setText(String
								.valueOf(holder.mdiary.startingmin));
					}

					if (holder.mdiary.endingmin < 10) {
						// TODO Auto-generated method stub

						etmin[0] = "0";
						etmin[1] = String.valueOf(holder.mdiary.endingmin);
						holder.mendingmin.setText(etmin[0] + etmin[1]);
					} else {
						holder.mendingmin.setText(String
								.valueOf(holder.mdiary.endingmin));

					}

					// holder.mtype_of_profile.setText(String.valueOf(holder.mdiary.type_of_profile));
					if (holder.mdiary.type_of_profile == 0) {
						holder.mtype_of_profile.setText(R.string.Ringtone);
					} else if (holder.mdiary.type_of_profile == 1) {
						holder.mtype_of_profile.setText(R.string.Vibrate);
					} else if (holder.mdiary.type_of_profile == 2) {
						holder.mtype_of_profile.setText(R.string.Silent);
					}else if(holder.mdiary.type_of_profile==3){
						holder.mtype_of_profile.setText(R.string.Airplane);
					}
					// holder.mmon.setText(" ");
					// //Log.i("mon", String.valueOf(holder.mdiary.mon));
					// //Log.i("Friday must come zero",
					// String.valueOf(holder.mdiary.fri));
					if (holder.mdiary.mon == 1) {
						holder.mmon.append("Mon ");
						// //Log.i("in the ", "Mon");
					}
					// holder.mmon.setText(String.valueOf(holder.mdiary.mon));

					if (holder.mdiary.tues == 1) {
						// //Log.i("in the ", "tues");
						holder.mmon.append("Tues  ");
					}
					// holder.mtues.setText(String.valueOf(holder.mdiary.tues));
					if (holder.mdiary.wed == 1) {
						// Log.i("in the ", "wed");
						holder.mmon.append("Wed  ");
					}
					// holder.mwed.setText(String.valueOf(holder.mdiary.wed));
					if (holder.mdiary.thurs == 1) {
						holder.mmon.append("Thurs  ");

						// Log.i("in the ", "thurs");
					}

					// holder.mthurs.setText(String.valueOf(holder.mdiary.thurs));
					if (holder.mdiary.fri == 1) {
						holder.mmon.append("Fri  ");
						// Log.i("in the ", "fri");
					}
					// holder.mfri.setText(String.valueOf(holder.mdiary.fri));
					if (holder.mdiary.sat == 1) {
						holder.mmon.append("Sat  ");
						// Log.i("in the ", "sat");
					}
					if (holder.mdiary.sun == 1) {
						holder.mmon.append("Sun  ");
						// Log.i("in the ", "sun");
					}
					// holder.id.setText(holder.mdiary.id);

					// Log.i("diaryAdapter 5", "inside the diary adapter");
					// holder.msat.setText(String.valueOf(holder.mdiary.sat));
					// holder.msun.setText(String.valueOf(holder.mdiary.sun));

					v.setTag(holder);
					return v;
				}
				// Log.i("diaryAdapter 6", "inside the diary adapter");

				return v2;

			}
		}

		public class ViewHolder {
			MyDiary mdiary;
			// TextView id;
			TextView mname_of_profile;
			TextView mstartinghour;
			TextView mstartingmin;
			TextView mendinghour;
			TextView mendingmin;
			TextView mtype_of_profile;
			TextView mmon;
			TextView AM_PM_Startinghour;
			TextView AM_PM_Endinghour;

		}
	}

	// language solution
	public void change_the_language(int item) {
		// Toast.makeText(getApplicationContext(), items[item],
		// Toast.LENGTH_SHORT)
		// .show();
		// //Log.i("selected item is ", String.valueOf(items[item]));
		settings.language_controler = 0;
		if (item == 0) {
			Locale appLoc = new Locale("en");
			Locale.setDefault(appLoc);
			Configuration appConfig = new Configuration();
			appConfig.locale = appLoc;
			getBaseContext().getResources().updateConfiguration(appConfig,
					getBaseContext().getResources().getDisplayMetrics());

		} else if (item == 1) {
			Locale appLoc = new Locale("fr");
			Locale.setDefault(appLoc);
			Configuration appConfig = new Configuration();
			appConfig.locale = appLoc;
			getBaseContext().getResources().updateConfiguration(appConfig,
					getBaseContext().getResources().getDisplayMetrics());
		} else if (item == 2) {
			Locale appLoc = new Locale("de");
			Locale.setDefault(appLoc);
			Configuration appConfig = new Configuration();
			appConfig.locale = appLoc;
			getBaseContext().getResources().updateConfiguration(appConfig,
					getBaseContext().getResources().getDisplayMetrics());
		} else if (item == 3) {// simplified chinease
			Locale appLoc = new Locale("zh", "CN");
			Locale.setDefault(appLoc);
			Configuration appConfig = new Configuration();
			appConfig.locale = appLoc;
			getBaseContext().getResources().updateConfiguration(appConfig,
					getBaseContext().getResources().getDisplayMetrics());
		} else if (item == 4) {
			// traditional chinease lang of taiwan
			Locale appLoc = new Locale("zh", "TW");
			Locale.setDefault(appLoc);
			Configuration appConfig = new Configuration();
			appConfig.locale = appLoc;
			getBaseContext().getResources().updateConfiguration(appConfig,
					getBaseContext().getResources().getDisplayMetrics());
		} else if (item == 5) {
			// japanease
			Locale appLoc = new Locale("ja");
			Locale.setDefault(appLoc);
			Configuration appConfig = new Configuration();
			appConfig.locale = appLoc;
			getBaseContext().getResources().updateConfiguration(appConfig,
					getBaseContext().getResources().getDisplayMetrics());
		} else

		// spanish
		if (item == 6) {
			Locale appLoc = new Locale("es");
			Locale.setDefault(appLoc);
			Configuration appConfig = new Configuration();
			appConfig.locale = appLoc;
			getBaseContext().getResources().updateConfiguration(appConfig,
					getBaseContext().getResources().getDisplayMetrics());
		} else if (item == 7 && Build.VERSION.SDK_INT > 8) {
			// right now it is default
			// would be swedish
			Locale appLoc = new Locale("sv", "SE");
			Locale.setDefault(appLoc);
			Configuration appConfig = new Configuration();
			appConfig.locale = appLoc;
			getBaseContext().getResources().updateConfiguration(appConfig,
					getBaseContext().getResources().getDisplayMetrics());
		}

	}

	public void first_start(){
	/*	Editor medit=start_first.edit();
	    first_start=start_first.getInt("start", 0);
		if(first_start==0)
		{
		//	start_service();
			medit.putInt("start", 1);
			
		
		}*/

		SharedPreferences ret=getSharedPreferences("mystartsettings", MODE_WORLD_READABLE);
        
		first_start=ret.getInt("start", 0);
        Log.i("value of first_start is ",String.valueOf(first_start));
        
		if(first_start==0){

			
			Log.i("first start","first start is running");
		Editor ps=pre.edit();
  		ps.putInt("start", 1);
 
		ps.commit();

		Log.i("inside if","would run only first time");
		start_service();
		}
		
		
	}
	
	public void dialog_of_main(int arg2) {
		// TODO Auto-generated method stub
		final int arg3 = arg2;
		final String[] items = { "Modify", "Delete" };
		int id2 = prefs2.getInt("language_selected", 0);

		if (id2 == 1) {
			items[0] = "modifier";
			items[1] = "supprimer";
		} else if (id2 == 2) {
			items[0] = "ändern";
			items[1] = "löschen";
		} else if (id2 == 3) {
			items[0] = "修改";
			items[1] = "删除";
		} else if (id2 == 4) {
			items[0] = "修改";
			items[1] = "刪除";
		} else if (id2 == 5) {
			items[0] = "変更";
			items[1] = "削除";
		} else if (id2 == 6) {
			items[0] = "modificar";
			items[1] = "borrar";
		} else if (id2 == 7) {
			items[0] = "ändra";
			items[1] = "Radera";
		}

		// getListView().getItemAtPosition(arg2);

		// int a=getListView().getMaxScrollAmount();
		// //Log.i("total no of rows are",String.valueOf(a));
		// Toast.makeText(this, String.valueOf(a),Toast.LENGTH_SHORT).show();

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				if (item == 0) {

					modify_profile(arg3);
				} else if (item == 1) {
					Delete_profile(arg3);
				}

			}
		});
		AlertDialog alert = builder.create();
		alert.show();

	}

	public void Delete_profile(int ar) {
		
		final int ar2 = ar;
		// Toast.makeText(getApplicationContext(), "Delete the profile",
		// Toast.LENGTH_SHORT).show();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.Delete)
				.setCancelable(false)
				.setPositiveButton(R.string.Yes,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// ///code for the deletion of row
								// TODO Auto-generated method stub
								// Log.i("pramod cursor", "before cursor");
								Cursor c = dba.getdiaries();

								// Log.i("pramod cursor", "after cursor");
								no_of_rows = c.getCount();

								// Log.i("pramod cursor",
								// "after getcount cursor");
								dba.deleterow(ar2 + 1);

								dba.update_row(ar2 + 1, no_of_rows);
								// modify_the_db();
								
								restart_the_synch();

								// MyActivity.this.finish();
							}
						})
				.setNegativeButton(R.string.No,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		AlertDialog alert = builder.create();
		alert.show();
		
	}

	public void modify_profile(int ar1) {

		// Toast.makeText(getApplicationContext(), "Modify the profile",
		// Toast.LENGTH_SHORT).show();
		Intent in = new Intent(AutomaticprofileChangerActivity.this,
				ModifyProfile.class);
		in.putExtra("position_is", ar1);
		startActivity(in);
	}

	// ===========================================================================================

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		//
		// ---------------
		menu.add(GROUP_1, start_service, 1, R.string.startservicestring)
				.setIcon(R.drawable.hstartservice);
		menu.add(GROUP_2, stop_service, 2, R.string.stopservicestring).setIcon(
				R.drawable.hstopservice);
		menu.add(GROUP_DEFAULT, about, 3, R.string.aboutstring).setIcon(
				R.drawable.about);
		menu.add(GROUP_DEFAULT, Helpscreen, 4, R.string.helpstring).setIcon(
				R.drawable.help);
		menu.add(GROUP_DEFAULT, settingscreen, 5, R.string.settingstring)
				.setIcon(R.drawable.settings);
		menu.add(GROUP_DEFAULT, upgradation, 5, R.string.upgradation)
		.setIcon(R.drawable.upgradation);

		// menu.add(GROUP_DEFAULT, More, 7, "More").setIcon(R.drawable.more);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		//itemno = prefs.getInt("item_value", 0);
		itemno = prefs.getInt("item_value", 1);

		if (itemno == 1) {
			menu.setGroupVisible(GROUP_2, true);
			menu.setGroupVisible(GROUP_1, false);
		} else if (itemno == 0) {
			menu.setGroupVisible(GROUP_1, true);
			menu.setGroupVisible(GROUP_2, false);
		}

		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case start_service:
			start_service();

			break;
		case stop_service:
			stop_service();
			break;
		case about:
			about();
			break;
		case Helpscreen:
			Helpscreen();
			break;
		// case donatescreen:
		// donatescreen();
		// break;
		case settingscreen:
			setting();
			break;
		case upgradation:
			more();

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	void  start_service() {

		itemno = 1;
		SharedPreferences pref3 = getSharedPreferences("settingsdata",
				MODE_WORLD_READABLE);
		int x = pref3.getInt("delay_time", 0);
		final int items[] = { 5, 10, 15, 30, 60, 180, 300, 900, 1800, 3600 };

		// Log.i("last value is ", String.valueOf(items[x]));

		medit.putInt("item_value", itemno);
		medit.commit();
		// Toast.makeText(this, "service is started 1",
		// Toast.LENGTH_SHORT).show();
		// startService(new Intent(this,BackgroundService.class));
		// PendingIntent pd=new
		// PendingIntent(AutomaticprofileChangerActivity.this,BackgroundService.class);
		// We want the alarm to go off 30 seconds from now.
		long firstTime = SystemClock.elapsedRealtime();

        Log.i("start_service","start service is running");
        Log.i("start_service","start service is running");

		// Schedule the alarm!
		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

		am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime,
				items[x] * 1000, mAlarmSender);

		// Tell the user about what we did.
		// Toast.makeText(AutomaticprofileChangerActivity.this,
		// "service is started of automatic profile changer",
		// Toast.LENGTH_LONG).show();

	}

	void Helpscreen() {
		Intent m = new Intent(this, helpscreen.class);
		startActivity(m);
	}

	// void donatescreen() {
	// Intent m1 = new Intent(this, donate.class);
	// startActivity(m1);
	//
	// }

	void setting() {
		Intent an1 = new Intent(AutomaticprofileChangerActivity.this,
				settings.class);
		startActivity(an1);
	}

	void stop_service() {
		itemno = 0;
		itemstring = null;
		medit.putInt("item_value", itemno);

		medit.commit();
		// Toast.makeText(this, "service is stopped of apc", Toast.LENGTH_SHORT)
		// .show();
		// stopService(new Intent(this,BackgroundService.class));
		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		am.cancel(mAlarmSender);
		refresh_set();

	}

	void refresh_set() {
		Editor med = prefs3.edit();
		med.putInt("set1", 0);
		med.putInt("set2", 0);
		med.putInt("set3", 0);
		med.putInt("set4", 0);
		med.putInt("set5", 0);
		med.putInt("set6", 0);
		med.commit();
	}

	void more() {
		// Intent in = new Intent(AutomaticprofileChangerActivity.this,
		// test.class);
		// startActivity(in);
		Uri ur=Uri.parse("http://www.handster.com/automatic_profile_changer.html");
		startActivity( new Intent( Intent.ACTION_VIEW, ur ) );

	}

	void about() {

		// Toast.makeText(this, "Its about the author",
		// Toast.LENGTH_SHORT).show();

		Intent ab = new Intent(AutomaticprofileChangerActivity.this,
				about.class);
		startActivity(ab);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub

		super.onDestroy();
	}

	@Override
	protected void onResume() {

		if (flag_for_time_format == 1) {
			myAdapter = new DiaryAdapter(this);
			this.setListAdapter(myAdapter);
		}

		// Log.i("value of flag2 is",
		// String.valueOf(launchwithservice.returnvalue()));
		if (launchwithservice.returnvalue() == 1) {
			myAdapter = new DiaryAdapter(this);
		}
		// condition is put to stop the setting of data in homescreen when db is
		if (flag == 0 && launchwithservice.returnvalue() == 1) {
			this.setListAdapter(myAdapter);

			// Log.i("this could run by ", "flag2");
		}
		if (ModifyProfile.returnvalue() == 1) {
			myAdapter = new DiaryAdapter(this);
			// would run only with with the modification of profile

		}
		if (flag == 0 && ModifyProfile.returnvalue() == 1) {
			this.setListAdapter(myAdapter);
			// to prevent database exception Null point exception

			// Log.i("this could run by ", "flag2");

		}
		// setting is working properly
		// condition is put to stop the starting of service when set to stop
		if (itemno == 1) {
			start_service();
			// Log.i("inside the last log", "yes");
		}
		if (settings.wheather_lang_chang() == 1) {
			// Log.i("pramod", "changing the language");
			prefs2 = getSharedPreferences("settingsdata", MODE_WORLD_READABLE);
			int id2 = prefs2.getInt("language_selected", 0);
			change_the_language(id2);
			setContentView(R.layout.main);

		}
		refresh_set();
		super.onResume();
	}

	public void restart_the_synch() {
		myAdapter = new DiaryAdapter(this);
		this.setListAdapter(myAdapter);

		// Log.i("inside the restart_the _synch method", "true");

	}

	public void modify_the_db() {
		/*
		 * this method would modify the db after the deletion of row from db
		 */

		// Log.i("inside the modify_the_db method", "true");
		// Cursor c = dba.getdiaries();
		// no_of_rows=c.getCount();
		// //Log.i("no of rows are",String.valueOf(no_of_rows));

	}

}
