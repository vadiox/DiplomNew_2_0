package ua.study.english.other;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class ShPrefs {
	
	public static final String CURRENT_WORD_NUMBER = "current_number";
	public static final String TOTAL_WORD = "total_word";
	public static final String NAME = "name";
	public static final String POSITION = "position";
	public static final String MAX_POS = "max_position";
	public static final String CARD_TW_INTEX = "card_tw_intex";
	public static final String VIDEO_POS = "video_pos";
	

	public static  void SaveStringPrefs(String key, String value, Context context) {	//зберігає рядок по ключу у настройки 
		SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
		 SharedPreferences.Editor editor = preferences.edit();
		editor.putString(key, value);
		Log.e("SAVE " + key, value);
		editor.commit();	
	}
	public static  void SaveIntPrefs(String key, int value, Context context) {		//зберігає натуральне число по ключу в настройки
		SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putInt(key, value);
		Log.e("SAVE int " + key, "" + value);
		editor.commit();	
	}
	public static  String LoadStringPrefs(String key, String defvalue, Context context) {
		//зчитуємо з настройок рядок, що відповідує переданому ключу. якщо такого нема, то повертаємо defvalue
		SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
		preferences.getString(key, defvalue);
		Log.e("LoadPreference "+ key, preferences.getString(key, defvalue));
		return preferences.getString(key, defvalue);
	}
	public static  int LoadIntPrefs(String key, int defvalue, Context context) {
		//зчитуємо з настройок натуральне число, що відповідує переданому ключу. якщо такого нема, то повертаємо defvalue
		SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
		preferences.getInt(key, defvalue);
		Log.e("LoadPreference "+ key, "" + preferences.getInt(key, defvalue));
		return preferences.getInt(key, defvalue);
	}
}