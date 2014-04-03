package com.pramod;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class settings extends ListActivity {

	SharedPreferences prefs2;
	// private NotificationManager nMangaer;
	final int NOTIFY_ID = 1;

	static int language_controler = 0;
	int item2;
	final CharSequence[] items2 = { "English", "French", "German",
			"Simplified Chinease", "Traditional Chinease", "Japanease",
			"Spanish", "Swedish" };
	final CharSequence[] items1 = { "English", "French", "German",
			"Simplified Chinease", "Traditional Chinease", "Japanease",
			"Spanish" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.settingscreen);
		final String[] ACTIVITY_CHOICES = /* new String[] */{ "Time Format",
				"Delay Time", "Set the language" };
		prefs2 = getSharedPreferences("settingsdata", MODE_WORLD_READABLE);
		int id2 = prefs2.getInt("language_selected", 0);

		if (id2 == 1) {
			// final String[] ACTIVITY_CHOICES1=new String[];
			ACTIVITY_CHOICES[0] = "Format de l'heure";
			ACTIVITY_CHOICES[1] = "délai";
			ACTIVITY_CHOICES[2] = "Régler la langue";
			// setListAdapter(new ArrayAdapter<String>(this,
			// android.R.layout.simple_list_item_1, ACTIVITY_CHOICES1));
			// ACTIVITY_CHOICES ={ "Format de l'heure", "délai",
			// "Régler la langue"};

		} else if (id2 == 2) {
			ACTIVITY_CHOICES[0] = "Time Format";
			ACTIVITY_CHOICES[1] = "Delay Time";
			ACTIVITY_CHOICES[2] = "Stellen Sie die Sprache";

		} else if (id2 == 3) {
			ACTIVITY_CHOICES[0] = "时间格式";
			ACTIVITY_CHOICES[1] = "延迟时间";
			ACTIVITY_CHOICES[2] = "设置语言";

		} else if (id2 == 4) {
			ACTIVITY_CHOICES[0] = "時間格式";
			ACTIVITY_CHOICES[1] = "延遲時間";
			ACTIVITY_CHOICES[2] = "設置語言";

		} else if (id2 == 5) {
			ACTIVITY_CHOICES[0] = "時間のフォーマット";
			ACTIVITY_CHOICES[1] = "遅延時間";
			ACTIVITY_CHOICES[2] = "言語を設定する";

		} else if (id2 == 6) {
			ACTIVITY_CHOICES[0] = "Formato de hora";
			ACTIVITY_CHOICES[1] = "Tiempo de retardo";
			ACTIVITY_CHOICES[2] = "Establecer el idioma";

		} else if (id2 == 7) {
			ACTIVITY_CHOICES[0] = "Tidsformat";
			ACTIVITY_CHOICES[1] = "fördröjning";
			ACTIVITY_CHOICES[2] = "Ställ in språk";
		}

		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, ACTIVITY_CHOICES));
		// getListView().setTextFilterEnabled(true);
		// getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		getListView().setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:
					// Toast.makeText(this, "first", Toast.LENGTH_SHORT).show();
					setitem1();

					break;
				case 1:

					setitem2();
					break;
				case 2:

					setitem3();
				default:
					break;

				}
			}
		});

	}

	public void setitem1() {
		final String[] items = { "12 Hour", "24 Hour" };
		final SharedPreferences prefs = getSharedPreferences("settingsdata",
				MODE_WORLD_READABLE);
		int id2 = prefs2.getInt("language_selected", 0);
		if (id2 == 1) {
			items[0] = "12 heures ";
			items[1] = "24 heures ";
		} else if (id2 == 2) {
			items[0] = "12 Stunden ";
			items[1] = "24 Stunden ";// german
		} else

		if (id2 == 3) {
			items[0] = "12个小时的 ";
			items[1] = "24小时 ";// chinese
		} else if (id2 == 4) {
			items[0] = "12個小時的 ";
			items[1] = "24小時 ";
		} else if (id2 == 5) {
			items[0] = "12時間 ";
			items[1] = "24時間 ";
		} else if (id2 == 6) {
			items[0] = "12 horas ";
			items[1] = "24 horas ";
		} else if (id2 == 7) {
			items[0] = "12-timmars ";
			items[1] = "24-timmars ";
		}
		final Editor mEditor = prefs.edit();

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.set_time_format);
		int id = prefs.getInt("time_format", 0);
		// ////Log.i("value of time format is ", String.valueOf(id));

		// alert.onRestoreInstanceState(item2);

		builder.setSingleChoiceItems(items, id,
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int item) {
						// item=mEditor.
						// Toast.makeText(getApplicationContext(), items[item],
						// Toast.LENGTH_SHORT).show();
						item2 = item;
						mEditor.putInt("time_format", item);
						mEditor.commit();
						AutomaticprofileChangerActivity.flag_for_time_format = 1;
						finish();

					}
				});
		AlertDialog alert = builder.create();

		alert.show();

	}

	public void setitem2() {
		final String[] items = { "5 seconds", "10 Seconds", "15 Seconds",
				"30 Seconds", "1 minute", "3 Minutes", "5 Minutes",
				"15 Minutes", "30 Minutes", "1 hour" };
		final SharedPreferences prefs = getSharedPreferences("settingsdata",
				MODE_WORLD_READABLE);

		int id2 = prefs2.getInt("language_selected", 0);
		if (id2 == 1) {
			items[0] = "5 secondes";
			items[1] = "10 Deuxième";
			items[2] = "15 Deuxième";
			items[3] = "30 Deuxième";
			items[4] = "1 Minute";
			items[5] = "3 Minutes";
			items[6] = "5 Minutes";
			items[7] = "15 Minutes";
			items[8] = "30 Minutes";
			items[9] = "1 heure";

		} else if (id2 == 2) {
			items[0] = "5 Sekunden";
			items[1] = "10 Seconds";
			items[2] = "15 Seconds";
			items[3] = "30 Seconds";
			items[4] = "1 Minute";
			items[5] = "3 Minuten";
			items[6] = "5 Minuten";
			items[7] = "15 Minuten";
			items[8] = "30 Minuten";
			items[9] = "1 Stunde";

		} else if (id2 == 3) {// simplified chinese
			items[0] = "5秒";
			items[1] = "10秒";
			items[2] = "15秒";
			items[3] = "30秒";
			items[4] = "1分钟";
			items[5] = "3分钟";
			items[6] = "5分钟";
			items[7] = "15分钟";
			items[8] = "30分钟";
			items[9] = "1小时";

		} else if (id2 == 4) {
			items[0] = "5秒";
			items[1] = "10秒";
			items[2] = "15秒";
			items[3] = "30秒";
			items[4] = "1分鐘";
			items[5] = "3分鐘";
			items[6] = "5分鐘";
			items[7] = "15分鐘";
			items[8] = "30分鐘";
			items[9] = "1小時";

		} else if (id2 == 5) {
			items[0] = "5秒";
			items[1] = "10秒";
			items[2] = "15秒";
			items[3] = "30秒";
			items[4] = "1分";
			items[5] = "3分";
			items[6] = "5分";
			items[7] = "15分";
			items[8] = "30分";
			items[9] = "1時間";

		} else if (id2 == 6) {
			items[0] = "5 segundos";
			items[1] = "10 Segundos";
			items[2] = "15 Segundos";
			items[3] = "30 Segundos";
			items[4] = "1 minuto";
			items[5] = "3 Minutos";
			items[6] = "5 Minutos";
			items[7] = "15 Minutos";
			items[8] = "30 Minutos";
			items[9] = "1 hora";

		} else if (id2 == 7) {
			items[0] = "5 sekunder";
			items[1] = "10 sekunder";
			items[2] = "15 sekunder";
			items[3] = "30 sekunder";
			items[4] = "1 minut";
			items[5] = "3 minuter";
			items[6] = "5 minuter";
			items[7] = "15 minuter";
			items[8] = "30 minuter";
			items[9] = "1 timme";

		}

		final Editor mEditor = prefs.edit();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.set_delay_time);
		int id1 = prefs.getInt("delay_time", 0);

		builder.setSingleChoiceItems(items, id1,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						// Toast.makeText(getApplicationContext(), items[item],
						// Toast.LENGTH_SHORT).show();
						mEditor.putInt("delay_time", item);
						mEditor.commit();
						finish();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();

	}

	public void setitem3() {

		final SharedPreferences prefs = getSharedPreferences("settingsdata",
				MODE_WORLD_READABLE);
		final Editor mEditor = prefs.edit();
		// -------------notification service
		// String ns=Context.NOTIFICATION_SERVICE;
		// nMangaer=(NotificationManager)getSystemService(ns);
		// final Notification msg=new Notification(R.drawable.icon,
		// "You must restart the app to reflect the complete change",
		// System.currentTimeMillis());
		// Context context = getApplicationContext();
		// CharSequence contentTitle = "My notification";
		// CharSequence contentText = "Hello World!";
		// Intent notificationIntent = new Intent(this, null);
		// PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
		// notificationIntent, 0);
		// msg.setLatestEventInfo(context, contentTitle, contentText);

		// =--------------------------------
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		int id2 = prefs.getInt("language_selected", 0);
		builder.setTitle(R.string.set_lang);
		if (Build.VERSION.SDK_INT <= 8) {

			builder.setSingleChoiceItems(items1, id2,

			new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int item) {

					// change_the_language(item);
					language_controler = 1;
					mEditor.putInt("language_selected", item);
					mEditor.commit();
					// nMangaer.notify(NOTIFY_ID, msg);
					Toast_msg();
					finish();

				}
			});
		} else {
			builder.setSingleChoiceItems(items2, id2,

			new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int item) {

					// change_the_language(item);
					language_controler = 1;
					mEditor.putInt("language_selected", item);
					mEditor.commit();
					finish();

				}
			});
		}

		AlertDialog alert = builder.create();
		alert.show();

	}

	public void Toast_msg() {
		Toast.makeText(this, R.string.lang_notification, Toast.LENGTH_LONG)
				.show();
	}

	public static int wheather_lang_chang() {
		return language_controler;
	}

}