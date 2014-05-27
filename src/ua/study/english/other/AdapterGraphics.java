package ua.study.english.other;

import java.util.ArrayList;

import ua.study.english.R;
import ua.study.english.other.AdapterSelectWord.ViewHolder;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AdapterGraphics extends BaseAdapter{
	
	static Context mContext;
    static ArrayList<RezultEnt> List;
	  LayoutInflater mInf;
	  ViewHolder vh1;

	//відповідає за відображення графіку та його звязок з даними користувача
    public AdapterGraphics(Context context, ArrayList<RezultEnt> list){
 	   this.mContext = context;
 	   this.List = list;
 	   mInf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 	  }

 	static class ViewHolder{
 		public TextView item_date;
 		public ProgressBar item_progress;
 	}

 	@Override
 	public int getCount() {
 		return List.size();
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
 			    convertView = mInf.inflate(R.layout.item_statistics, parent,false);
 			    vh1 = new ViewHolder();
 			    vh1.item_date = (TextView)convertView.findViewById(R.id.item_date);
 			    vh1.item_progress = (ProgressBar)convertView.findViewById(R.id.item_progress);
 			    convertView.setTag(vh1);
 		   }
 		  vh1.item_date.setText(List.get(position).getDate());
 		  vh1.item_progress.setProgress(List.get(position).getRezult());
 		  return convertView;
 	}	
}

