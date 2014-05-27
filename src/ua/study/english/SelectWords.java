package ua.study.english;

import java.util.ArrayList;

import ua.study.english.other.AdapterSelectWord;
import ua.study.english.other.DbHelper;
import ua.study.english.other.ParseJSON;
import ua.study.english.other.ShPrefs;
import ua.study.english.other.WordTranslEnt;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class SelectWords extends Activity {
	
	Button btn_prev, btn_next, btn_study;
	ListView list;
	int position;
	Context context;
	ArrayList<WordTranslEnt> mlist ;
	DbHelper dbhelper;
	int max_position;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_words);
		context = SelectWords.this;
		dbhelper = new DbHelper(context);
		position = ShPrefs.LoadIntPrefs(ShPrefs.POSITION, 0, context);
		max_position = ShPrefs.LoadIntPrefs(ShPrefs.MAX_POS, 0, context);
		
		btn_prev= (Button)findViewById(R.id.btn_prevs);
		btn_next = (Button)findViewById(R.id.btn_nexts);
		btn_study = (Button)findViewById(R.id.btn_study);
		list = (ListView)findViewById(R.id.list_words);
		
		mlist = ParseJSON.get_10_words(position);
		list.setAdapter(new AdapterSelectWord(SelectWords.this, mlist));
		
		Log.e("position", "" + position);
		
		btn_prev.setOnClickListener(new OnClickListener() {   //������� ���� �� ������ "����.10"
			@Override
			public void onClick(View v) {
				position = ShPrefs.LoadIntPrefs(ShPrefs.POSITION, 0, context);
				if(position>0){			//���� �� �� ����� �����, �� 
					mlist = ParseJSON.get_10_words(position-10); //������� �������� 10 ���
					list.setAdapter(new AdapterSelectWord(SelectWords.this, mlist)); //���������� ������
					ShPrefs.SaveIntPrefs(ShPrefs.POSITION, position-10, context); //�������� ���� �������
				}else{
					Toast.makeText(SelectWords.this, R.string.empty_words, Toast.LENGTH_SHORT).show(); //�������� �����, �� ���� ����� ���, ������� ������� � ���� �������
				}
			}
		});
		btn_next.setOnClickListener(new OnClickListener() {//������� ���� �� ������ "����.10"
			@Override
			public void onClick(View v) {
				position = ShPrefs.LoadIntPrefs(ShPrefs.POSITION, 0, context);
				if(position<(max_position-10)){		//���� �� �� ����� �����, �� 
					mlist = ParseJSON.get_10_words(position+10);	//������� ������� 10 ���
					list.setAdapter(new AdapterSelectWord(SelectWords.this, mlist));//���������� ������
					ShPrefs.SaveIntPrefs(ShPrefs.POSITION, position+10, context);//�������� ���� �������
				}else{
					Toast.makeText(context, R.string.empty_words, Toast.LENGTH_SHORT).show();//�������� �����, �� ���� ����� ���, ������� ������� � ���� �������
				}
			}
		});
		btn_study.setOnClickListener(new OnClickListener() {//������� ���� �� ������ �����
			@Override
			public void onClick(View v) {
				ArrayList<WordTranslEnt> words = ParseJSON.get_10_words(position);
				for(int i=0;i<words.size();i++){
					dbhelper.add_word_translate(words.get(i));
				}
				Intent intent = new Intent(context, Study10Words.class);	//���������� �� ��������� �����
				intent.putExtra("number", ShPrefs.LoadIntPrefs(ShPrefs.POSITION, 0, context)); //�������� ����� ������� ������� � 10 �������� ���
				startActivity(intent);
			}
		});
	}

}
