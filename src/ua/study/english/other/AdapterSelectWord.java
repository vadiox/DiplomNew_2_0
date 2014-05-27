package ua.study.english.other;

import java.util.ArrayList;

import ua.study.english.R;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterSelectWord extends BaseAdapter{
	
	static Context mContext;
    static ArrayList<WordTranslEnt> word_List;
	  LayoutInflater mInf;
	  ViewHolder vh1;
	//відповідає за відображення списку слів на екрані вибору слів
    public AdapterSelectWord(Context context, ArrayList<WordTranslEnt> list){
 	   this.mContext = context;
 	   this.word_List = list;
 	   mInf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 	  }

 	static class ViewHolder{
 		public TextView item_word;
 	}

 	@Override
 	public int getCount() {
 		return word_List.size();
 	}

 	@Override
 	public Object getItem(int position) {
 		return position;
 	}

 	@Override
 	public long getItemId(int position) {
 		return position;
 	}

 	@Override
 	public View getView(final int position, View convertView, ViewGroup parent) {
 		 final ViewHolder vh1;
 		   
 		   if(convertView != null){
 			    vh1 = (ViewHolder)convertView.getTag();
 		   }else{
 			    convertView = mInf.inflate(R.layout.item_one_word, parent,false);
 			    vh1 = new ViewHolder();
 			    vh1.item_word = (TextView)convertView.findViewById(R.id.tv_item_word);
 			    convertView.setTag(vh1);
 		   }
 		   vh1.item_word.setText(word_List.get(position).getWord());
 		   return convertView;
 	}	
 	
}
