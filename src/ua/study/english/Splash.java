package ua.study.english;

import ua.study.english.other.DbHelper;
import ua.study.english.other.ParseJSON;
import ua.study.english.other.ShPrefs;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.os.Build;

public class Splash extends Activity {

	Context context;
	EditText et_name;
	Button btn_ok;
	LinearLayout ll_box;
	int TIME_OUT = 3;	
	DbHelper dbhelper;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        context = Splash.this;
        dbhelper = new DbHelper(context);
        

        et_name =(EditText)findViewById(R.id.et_name);
        btn_ok = (Button)findViewById(R.id.btn_ok);
        ll_box = (LinearLayout)findViewById(R.id.ll_add_name);
         
        btn_ok.setOnClickListener(new OnClickListener() { //обробка кліку по кнопці "ок"
			@Override
			public void onClick(View v) {
				if(et_name.getText().length()>0){ //якщо введено імя
					
					ShPrefs.SaveStringPrefs(ShPrefs.NAME,et_name.getText().toString(), context); //зберігаємо імя 
					go_to_menu(); //переходимо до екрану меню
					ParseJSON.parse_word(dbhelper, context, et_name.getText().toString());
				}else{
					Toast.makeText(context, R.string.empty_name, Toast.LENGTH_SHORT).show(); //показуєм діалог "пусте імя користувача"
				}
			}
		});
    }

    public void go_to_menu(){ 								//перходимо до меню
    	Intent intent = new Intent(context, MainMenu.class);
		startActivity(intent);
		finish();
    }

    

}
