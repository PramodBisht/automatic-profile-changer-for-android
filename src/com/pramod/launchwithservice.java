package com.pramod;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class launchwithservice extends Activity {
	// private static final String[]
	// days={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	private static final String[] oceans = { "Ringtone", "Vibrate", "Silent",
			"Airplane" };
	private boolean c1 = false, c2 = false, c3 = false, c4 = false, c5 = false,
			c6 = false, c7 = false;
	private CharSequence name_of_profile;
	static int x = 0;
	static int flag2;
	int currenthour;

	private int Spinner_value;
	private int Timepicker1_hour;
	private int Timepicker1_minute;
	private int Timepicker2_hour;
	private int Timepicker2_minute;
	private int id;

	
	MyDB dba;
	MyDBhelper db;
	Thread th;
	//int saveflag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maininterfaceofservicestart);
		final EditText et = (EditText) findViewById(R.id.edit1);
		final TimePicker tp1 = (TimePicker) findViewById(R.id.timePicker1);
		currenthour = tp1.getCurrentHour();

		final TimePicker tp2 = (TimePicker) findViewById(R.id.timePicker2);
		//if (currenthour < 23) {
			tp2.setCurrentHour(currenthour + 1);
	//	}
//		 else if (currenthour == 23) {
//			tp2.setCurrentHour(0);
//		}

		final CheckBox cb1 = (CheckBox) findViewById(R.id.checkBox1);
		final CheckBox cb2 = (CheckBox) findViewById(R.id.checkBox2);
		final CheckBox cb3 = (CheckBox) findViewById(R.id.checkBox3);
		final CheckBox cb4 = (CheckBox) findViewById(R.id.checkBox4);
		final CheckBox cb5 = (CheckBox) findViewById(R.id.checkBox5);
		final CheckBox cb6 = (CheckBox) findViewById(R.id.checkBox6);
		final CheckBox cb7 = (CheckBox) findViewById(R.id.checkBox7);
		final Spinner sp = (Spinner) findViewById(R.id.spinner1);

		dba = new MyDB(this);
		// Log.i("pramod", "going to open the db conn.");

		dba.open();
		// -----------------------------------------------implementation of
		// button
		final SharedPreferences prefs = getSharedPreferences("settingsdata",
				MODE_WORLD_READABLE);
		Button btn = (Button) findViewById(R.id.button1);
		int id2 = prefs.getInt("language_selected", 0);
		if (id2 == 0) {
			oceans[0] = "Ringtone";
			oceans[1] = "Vibrate";
			oceans[2] = "Silent";
			oceans[3] = "Airplane";
		}
		if (id2 == 1) {
			oceans[0] = "sonnerie";
			oceans[1] = "vibrer";
			oceans[2] = "silencieuse";
			oceans[3] = "d'avion";
		} else if (id2 == 2) {
			oceans[0] = "Ringtone";
			oceans[1] = "vibrieren";
			oceans[2] = "still";
			oceans[3] = "Flugzeug";
		} else if (id2 == 3) {// chinese
			oceans[0] = "铃声";
			oceans[1] = "颤动";
			oceans[2] = "无声";
			oceans[3] = "飞机";
		} else if (id2 == 4) {
			oceans[0] = "鈴聲";
			oceans[1] = "顫動";
			oceans[2] = "無聲";
			oceans[3] = "飛機";
		} else if (id2 == 5) {
			// japanese
			oceans[0] = "着メロ";
			oceans[1] = "振動する";
			oceans[2] = "サイレント";
			oceans[3] = "飛行機";
		} else if (id2 == 6) {
			oceans[0] = "tono";
			oceans[1] = "vibrar";
			oceans[2] = "silencioso";
			oceans[3] = "avión";
		} else if (id2 == 7) {
			oceans[0] = "Ringtone";
			oceans[1] = "vibrera";
			oceans[2] = "tyst";
			oceans[3] = "flygplan";
		}

		ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this,
				R.layout.spinner_entry, oceans);
		mAdapter.setDropDownViewResource(R.layout.spinner_entry);

		sp.setAdapter(mAdapter);

		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Cursor c = dba.getdiaries();

				// Log.i("pramod cursor","after cursor");
				id = c.getCount();
				name_of_profile = et.getText();

				Timepicker1_hour = tp1.getCurrentHour();
				// Log.i(tag, msg)
				Timepicker1_minute = tp1.getCurrentMinute();
				Timepicker2_minute = tp2.getCurrentMinute();
				Timepicker2_hour = tp2.getCurrentHour();
//				if ((Timepicker2_hour + 0.01 * Timepicker2_minute) < (Timepicker1_hour + Timepicker1_minute * 0.01)) {
////					saveflag = 1;
//					saveflag = 0;
//					message_by_toast();
//				
//				} else {
//					saveflag = 0;
//				 //delim is the variable to distinguish wheather ending time is smaller than starting time
//				}
				// Log.i("pramod",String.valueOf(Timepicker1_hour+0.01*Timepicker1_minute));

				Spinner_value = sp.getSelectedItemPosition();
				if (cb1.isChecked()) {
					c1 = true;
				}
				if (cb2.isChecked())
					c2 = true;
				if (cb3.isChecked())
					c3 = true;
				if (cb4.isChecked())
					c4 = true;
				if (cb5.isChecked())
					c5 = true;
				if (cb6.isChecked())
					c6 = true;
				if (cb7.isChecked())
					c7 = true;

				// //Log.i("the value of launch.flag2 is ",String.valueOf(AutomaticprofileChangerActivity.flag2));
//				if (saveflag == 0) {
					saveItToDB();
					x = 1;
					AutomaticprofileChangerActivity.flag = 0;
					dba.close();

					finish();
			//	}
				// th.start();

			}
		});
		Button btn2 = (Button) findViewById(R.id.cancelbutton);
		btn2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				x = 0;
				finish();
			}
		});
	}
//public void message_by_toast(){
//	Toast.makeText(
//			this,
//			"You must set the ending time greater than starting time",
//			Toast.LENGTH_SHORT).show();
//}
	public void saveItToDB() {
		// Log.i("this is my message", "trying to connect the db");
		dba.insertdiary(id + 1, name_of_profile.toString(), Timepicker1_hour,
				Timepicker1_minute, Timepicker2_hour, Timepicker2_minute, c1,
				c2, c3, c4, c5, c6, c7, Spinner_value);

		// Log.i("this is my message 2", "connected with db");
		dba.close();

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		// AutomaticprofileChangerActivity a=new
		// AutomaticprofileChangerActivity();

		super.onDestroy();
	}

	public static int returnvalue() {
		if (x == 1) {
			AutomaticprofileChangerActivity.flag2 = 1;
		}
		return AutomaticprofileChangerActivity.flag2;
	}

}
