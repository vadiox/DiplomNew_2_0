package ua.study.english;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainMenu extends Activity {
	
	Button btn_words, btn_kadr, btn_two_word, btn_asociat;
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meny);
		
		context = MainMenu.this;
		btn_words = (Button)findViewById(R.id.btn_words);
		btn_kadr = (Button)findViewById(R.id.btn_kadr);
		btn_two_word = (Button)findViewById(R.id.btn_two_word_card);
		btn_asociat = (Button)findViewById(R.id.btn_asociat_card);
		
		btn_words.setOnClickListener(new OnClickListener() {//������� ���� �� ������ �����
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, SelectWords.class);//��������� �� ����� ������ ���
				startActivity(intent);
			}
		});
		btn_two_word.setOnClickListener(new OnClickListener() { //������� ���� �� ������ �������. ������
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, CardTwoWord.class); //��������� �� ����� �������. ������
				startActivity(intent);
			}
		});
		btn_asociat.setOnClickListener(new OnClickListener() {//������� ���� �� ������ ����. ������
			@Override
			public void onClick(View v) {
			}
		});
		btn_kadr.setOnClickListener(new OnClickListener() {//������� ���� �� ������ 25 ����
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, Cadr25.class);//��������� �� ����� 25����
				startActivity(intent);
			}
		});
	}

}
