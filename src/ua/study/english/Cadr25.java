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
	final int COUNT_CARD = 25;					//������� ������ ��� � �������
	int word_pos, word_size;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kadr25);
		tv_word = (TextView)findViewById(R.id.tv_cadr_word);
		word_pos = ShPrefs.LoadIntPrefs(ShPrefs.VIDEO_POS, 0, Cadr25.this);	//������� ��� � ���������� ���
		word_size = ShPrefs.LoadIntPrefs(ShPrefs.MAX_POS, 0, Cadr25.this);
		videoview = (VideoView) findViewById(R.id.video);
		Uri video = Uri.parse("android.resource://"+getPackageName()+"/" + R.raw.test); //��������� ��� ��������
        videoview.setVideoURI(video);     
        videoview.setOnPreparedListener(new OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);				//������� ���������� ����
            }
        });
        
        start = (Button)findViewById(R.id.btn_play_video);
        start.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(videoview.isPlaying()){			//���� ������ �����, �� 
					videoview.pause();				//�������� ����
					t.cancel();						//������� ������ ���
					start.setText(R.string.start);
					ShPrefs.SaveIntPrefs(ShPrefs.VIDEO_POS, word_pos, Cadr25.this); //�������� ����� ��������� �����
				}else{
					start.setText(R.string.stop);
					videoview.start();				//��������� ����
					startTimer();					//��������� ������ ��� ������ ���
				}
			}
		});
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_kadr, menu); //������� ���������� ����
        return true;
    }
	
	public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.item_back:
          finish();
          return true;
        case R.id.item_stat:
          Intent intent = new Intent(Cadr25.this, Statistics.class); //������� � ������� ����
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
		    	  // ���������� ����� �� �������� �� ������
		    	  tv_word.setText(ParseJSON.list.get(word_pos).getWord()+ " - " + ParseJSON.list.get(word_pos).getTranslate());
		    	  word_pos++; //�������� ������ �� 1
		    	  word_pos=word_pos%word_size;		//������� ��� �� ����� �� ��� ������
		     }  
		    });  
		   }  
		  };  
		  t.scheduleAtFixedRate(task, 1000/COUNT_CARD, 1000/COUNT_CARD); //������� �� ����� ������ ��� 
		 }  
	
}
