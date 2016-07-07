package com.example.demo.application.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.example.demo.application.R;
import com.example.demo.application.utils.Data;


public class HorizontalTabAdapter extends BaseAdapter{  
    private String[] mTitles;  
    private Context mContext;  
    private LayoutInflater mInflater;  
    private int selectIndex = 0;
	private int[] Ids;
	private int[] Imgs;  
  
    public HorizontalTabAdapter(Context context, String[] titles,int[] Ids,int[] Imgs){  
        this.mContext = context;  
        this.Ids = Ids;
        this.Imgs = Imgs;
        this.mTitles = titles;  
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//LayoutInflater.from(mContext);  
    }  
    @Override  
    public int getCount() {  
        return mTitles.length;  
    }  
    @Override  
    public Object getItem(int position) {  
        return position;  
    }  
  
    @Override  
    public long getItemId(int position) {  
        return position;  
    }  
  
    @SuppressLint("ViewHolder")
	@Override  
    public View getView(int position, View convertView, ViewGroup parent) {  
  
        ViewHolder holder;  
        holder = new ViewHolder();  
        convertView = mInflater.inflate(R.layout.tab, null);
        holder.mTitle = (TextView)convertView.findViewById(R.id.title); 
        holder.tvWidth = (TextView)convertView.findViewById(R.id.tvWidth);
        holder.mImg = (ImageView) convertView.findViewById(R.id.imageView);
        changeView(holder.tvWidth);
        convertView.setTag(holder);  
        if(position == selectIndex){  
            convertView.setSelected(true);
            holder.mTitle.setText(mTitles[position]);
            holder.mTitle.setTextColor(Color.parseColor("#5D9618"));
            holder.mImg.setImageResource(Ids[position]);
        }else{  
            convertView.setSelected(false); 
            holder.mTitle.setText(mTitles[position]);
            holder.mTitle.setTextColor(Color.parseColor("#555152"));
            holder.mImg.setImageResource(Imgs[position]);
        }  
          
        
  
        return convertView;  
    }  
    private void changeView(View view){
    	LayoutParams playParams = (LayoutParams) view.getLayoutParams();
        playParams.width = Data.width/4-2;
        view.setLayoutParams(playParams);
	}
    private static class ViewHolder {  
        private TextView mTitle ; 
        private ImageView mImg;
        private TextView tvWidth;
    }  
      
    public void setSelectIndex(int i){  
        selectIndex = i;  
    }  
}
