package ua.study.english;

import java.util.Timer;
import java.util.TimerTask;

import ua.study.english.other.ParseJSON;
import ua.study.english.other.ShPrefs;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class Cadr25 extends Activity{

	VideoView videoview;
	TextView tv_word;
	Button start;
	Timer t;  
	TimerTask task;
	final int COUNT_CARD = 25;					//кількість показу слів у секунду
	int word_pos, word_size;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kadr25);
		tv_word = (TextView)findViewById(R.id.tv_cadr_word);
		word_pos = ShPrefs.LoadIntPrefs(ShPrefs.VIDEO_POS, 0, Cadr25.this);	//зчитуємо дані з попередньої сесії
		word_size = ShPrefs.LoadIntPrefs(ShPrefs.MAX_POS, 0, Cadr25.this);
		videoview = (VideoView) findViewById(R.id.video);
		Uri video = Uri.parse("android.resource://"+getPackageName()+"/" + R.raw.test); //знаходимо наш відеофайл
        videoview.setVideoURI(video);     
        videoview.setOnPreparedListener(new OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);				//постійно повторюємо відео
            }
        });
        
        start = (Button)findViewById(R.id.btn_play_video);
        start.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(videoview.isPlaying()){			//якщо нажали паузу, то 
					videoview.pause();				//зупинили відео
					t.cancel();						//зупинка показу слів
					start.setText(R.string.start);
					ShPrefs.SaveIntPrefs(ShPrefs.VIDEO_POS, word_pos, Cadr25.this); //зберегли номер останього слова
				}else{
					start.setText(R.string.stop);
					videoview.start();				//запустили відео
					startTimer();					//запустили таймер для показу слів
				}
			}
		});
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_kadr, menu); //знайшли контекстне меню
        return true;
    }
	
	public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.item_back:
          finish();
          return true;
        case R.id.item_stat:
          Intent intent = new Intent(Cadr25.this, Statistics.class); //перехід в головне меню
  		  startActivity(intent);
  		  finish();
          return true;                    
        default:
          return super.onOptionsItemSelected(item);
        } 
    } 
	
	public void startTimer(){  
		  t = new Timer();     
		  task = new TimerTask() {  
		    @Override  
		   public void run() {  
		    runOnUiThread(new Runnable() {  
		      @Override  
		     public void run() {  
		    	  // відображаємо слово та переклад на монітор
		    	  tv_word.setText(ParseJSON.list.get(word_pos).getWord()+ " - " + ParseJSON.list.get(word_pos).getTranslate());
		    	  word_pos++; //збільшуємо індекс на 1
		    	  word_pos=word_pos%word_size;		//слідкуємо щою не вийти за межі масиву
		     }  
		    });  
		   }  
		  };  
		  t.scheduleAtFixedRate(task, 1000/COUNT_CARD, 1000/COUNT_CARD); //початок та період показу слів 
		 }  
	
}
