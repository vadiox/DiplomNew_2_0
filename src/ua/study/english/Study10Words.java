package ua.study.english;

import java.util.ArrayList;

import ua.study.english.other.AdapterStudyLeft;
import ua.study.english.other.AdapterStudyRight;
import ua.study.english.other.ParseJSON;
import ua.study.english.other.WordTranslEnt;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Study10Words extends Activity {

	ListView lv_left, lv_right;
	ArrayList<WordTranslEnt> list;
	Context context;
	Button btn_left, btn_right;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study10);
        
        context = Study10Words.this;
        
        list = ParseJSON.get_10_words(getIntent().getExtras().getInt("number"));
        
        lv_left = (ListView)findViewById(R.id.ll_english);
        lv_right = (ListView)findViewById(R.id.ll_translate);
        btn_left = (Button)findViewById(R.id.btn_english_hide);
        btn_right = (Button)findViewById(R.id.btn_translate_hide);
        
        
        lv_left.setAdapter(new AdapterStudyLeft(context, list));
        lv_right.setAdapter(new AdapterStudyRight(context, list));
        
        btn_left.setOnClickListener(new OnClickListener() { //обробка кліку по кнопці "скрить" під лівою колонкою
			@Override
			public void onClick(View v) {
				if(lv_left.getVisibility()==View.INVISIBLE){ //якщо список слів невидимой, то 
					lv_left.setVisibility(View.VISIBLE);	//робимо його видимим
					btn_left.setText(R.string.hide);		//змінюємо текст на кнопці
				}else{
					lv_left.setVisibility(View.INVISIBLE);	//робимо список невидимим
					btn_left.setText(R.string.show);
				}
				
			}
		});
        btn_right.setOnClickListener(new OnClickListener() {//обробка кліку по кнопці "скрить" під правою колонкою
			@Override
			public void onClick(View v) {
				if(lv_right.getVisibility()==View.INVISIBLE){//якщо список слів невидимой, то 
					lv_right.setVisibility(View.VISIBLE);	//робимо його видимим
					btn_right.setText(R.string.hide);		//змінюємо текст на кнопці
				}else{
					lv_right.setVisibility(View.INVISIBLE);	//робимо список невидимим
					btn_right.setText(R.string.show);
				}
				
			}
		});
        Log.e("study", ""+ getIntent().getExtras().getInt("number"));
    }
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_study, menu);
        return true;
    }
	
	public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.item_save:
          Toast.makeText(this, "Сохранино", Toast.LENGTH_SHORT).show();
          return true;
        case R.id.item_menu:
          Intent intent = new Intent(Study10Words.this, MainMenu.class);
  		  startActivity(intent);
  		  finish();
          return true;
        case R.id.item_back:
          finish();
          return true;                     
        default:
          return super.onOptionsItemSelected(item);
        } 
    } 
}
