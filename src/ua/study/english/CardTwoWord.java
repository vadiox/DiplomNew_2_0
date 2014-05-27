package ua.study.english;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ua.study.english.other.DbHelper;
import ua.study.english.other.ParseJSON;
import ua.study.english.other.RezultEnt;
import ua.study.english.other.ShPrefs;
import ua.study.english.other.WordTranslEnt;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class CardTwoWord extends Activity{

	LinearLayout card_bg;
	TextView card_tv;
	RadioGroup r_group;
	RadioButton rb_yes, rb_no;
	int position, cur_pos=0, right_ansver=0;
	private final int SIZE = 20;			//������� ��� � 1 ������
	DbHelper dbHelper;
	private Animation scale_in=null;
	ArrayList<WordTranslEnt> list;
	String name;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.card_two_word);
		dbHelper = new DbHelper(CardTwoWord.this);
		name = ShPrefs.LoadStringPrefs(ShPrefs.NAME, "", CardTwoWord.this);
		list = dbHelper.get_all_words(name);
		
		card_bg = (LinearLayout)findViewById(R.id.ll_card_bg);
		card_tv = (TextView)findViewById(R.id.tv_card);
		r_group = (RadioGroup)findViewById(R.id.r_group);
		rb_yes = (RadioButton)findViewById(R.id.rb_yes);
		rb_no = (RadioButton)findViewById(R.id.rb_no);
		
		scale_in = AnimationUtils.loadAnimation(this, R.anim.scale_in); //��������� �������
		
		if(list.size()==0){
			Toast.makeText(CardTwoWord.this, R.string.no_added_words, Toast.LENGTH_SHORT).show();
		}else{
			position = ShPrefs.LoadIntPrefs(ShPrefs.CARD_TW_INTEX, 0, CardTwoWord.this); 	//������� ����� ��������� ����� ��� ������������
			load_new_word(position);	//�������� �����
		}
		
		card_bg.setOnClickListener(new OnClickListener() { //������� ���� �� ���� � ������
			
			@Override
			public void onClick(View v) {
				card_bg.startAnimation(scale_in);					//����� �������
				if(list.size()!=0){
					card_tv.setText(list.get(position).getTranslate());	//����� ����� �� ��������
				}
				
			}
		});
		r_group.setOnCheckedChangeListener(new OnCheckedChangeListener() { //������� ���������� ������ �����������
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==R.id.rb_yes){			//���� �� ��������� ������, �� 
					right_ansver++;					//�������� ������� ����� �������� �� 1
				}
				load_new_word(++position);			//������� ���� �����
			}
		});
	}
	
	public void load_new_word(int index){		//����� �� �������� ���� �����
		
		if(cur_pos<Math.min(SIZE, list.size())){				//���� �� �� ������� �� �����
			cur_pos++;					
			card_tv.setText(list.get(index).getWord()); //���������� ���� �����
		}else{ //��������� ��������� ����������� �� �������� ���� � ����
			RezultEnt rezult = new RezultEnt();
			rezult.setUser(ShPrefs.LoadStringPrefs(ShPrefs.NAME, "", CardTwoWord.this));
			rezult.setRezult(right_ansver);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
			String currentDate = sdf.format(new Date());
			rezult.setDate(currentDate);
			dbHelper.add_rezults(rezult);
			ShPrefs.SaveIntPrefs(ShPrefs.CARD_TW_INTEX, 0, CardTwoWord.this);
			restart_activity();					// ������������� ��������� ��� ������� ������ �����
		}
		rb_yes.setChecked(false);				//��������� ��������� ���� �����������
		rb_no.setChecked(false);				//��������� ��������� ���� �����������
	}
	public void restart_activity(){			//����������� ���������
		Intent intent = new Intent(CardTwoWord.this, CardTwoWord.class);
		startActivity(intent);
		finish();
	}
}
