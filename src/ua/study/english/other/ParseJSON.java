package ua.study.english.other;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ua.study.english.Statistics;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
	
	//������� �� ������ � ������ JSON
public class ParseJSON {
	
	public static DbHelper mdb;
	static SQLiteDatabase sq_lite_db;
	static Context mContex;
	public static ArrayList<WordTranslEnt> list = new ArrayList<WordTranslEnt>();

	public static void parse_word(DbHelper db, final Context context, String user){
		 mContex = context;
		 	try {
				JSONArray jsonAr = new JSONArray(loadJSONFromAsset());	//����� ����� � ����� assets
				ShPrefs.SaveIntPrefs(ShPrefs.MAX_POS, jsonAr.length(), context);	//�������� ������� �������� � ����� � ����
				
				for(int i=0; i<jsonAr.length();i++){		//������� �� ����� � JSON-������ �� ��������� �� � ����� ��� 
					JSONObject object = new JSONObject();
					object=jsonAr.getJSONObject(i);
					WordTranslEnt word = new WordTranslEnt();
					word.setId(i);
					word.setWord(object.getString("text"));
					word.setTranslate(object.getString("word"));
					word.setUser(user);
					list.add(word);
				}
			} catch (JSONException e) {
				Log.e("error parse", "------------");		//�������� ����� ��� �������� ������� � ������ �����
				e.printStackTrace();
			}
	}

	public static String loadJSONFromAsset() {		//�����, �� ������� � ������ ����� ���� "word_translate.json"
		    String json = null;
		    try {
		        AssetManager assetManager = mContex.getResources().getAssets();
		        InputStream is = assetManager.open("word_translate.json");
		        int size = is.available();
		        byte[] buffer = new byte[size];
		        is.read(buffer);
		        is.close();
		        json = new String(buffer, "UTF-8");
		    } catch (IOException ex) {
		        ex.printStackTrace();
		        return null;
		    }
		    return json;
	}
	public static ArrayList<WordTranslEnt> get_10_words(int start){		//����� � ������ ������ ��� 10, ��������� � ���������� ������ �� ������� ��  
		ArrayList<WordTranslEnt> words = new ArrayList<WordTranslEnt>();
		int max = ShPrefs.LoadIntPrefs(ShPrefs.MAX_POS, 0, mContex);
			for (int i=start;i<start+10;i++){
				words.add(list.get(i));
			}
		return words;
	}
}
