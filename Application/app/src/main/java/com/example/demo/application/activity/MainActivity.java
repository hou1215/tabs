package com.example.demo.application.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;

import com.example.demo.application.R;
import com.example.demo.application.adapter.HorizontalTabAdapter;
import com.example.demo.application.fragment.Fragment01;
import com.example.demo.application.fragment.Fragment02;
import com.example.demo.application.fragment.Fragment03;
import com.example.demo.application.fragment.Fragment04;
import com.example.demo.application.utils.Config;
import com.example.demo.application.utils.Data;
import com.example.demo.application.utils.HorizontalListView;

public class MainActivity extends FragmentActivity {

    /**
     * mFragmentArray 用于存放 fragment 对象
     */
    private Fragment[] mFragmentArray=new Fragment[4];
    /**
     * mSelectedIndex 选择的下标
     * mCurrentIndex 当前的下标
     */
    private int mSelectedIndex = 0,mCurrentIndex = 0;

    private HorizontalListView listview;
    private HorizontalTabAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Data.height = wm.getDefaultDisplay().getHeight();
        Data.width = wm.getDefaultDisplay().getWidth();
        setView();

    }
    private void setView()
    {

        listview = (HorizontalListView) findViewById(R.id.horizontallistview);
        adapter = new HorizontalTabAdapter(this, Config.titles, Config.Ids,Config.Imgs);
        listview.setAdapter(adapter);


        mFragmentArray[0] = new Fragment01();
        mFragmentArray[1] = new Fragment02();
        mFragmentArray[2] = new Fragment03();
        mFragmentArray[3] = new Fragment04();

        mCurrentIndex = 0;
        mSelectedIndex = 0;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.relativeLayout, mFragmentArray[mSelectedIndex]);
        transaction.commit();

        setOnListener();

    }

    private void setOnListener() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position)
                {
                    case 0:
                        mSelectedIndex = 0;
                        break;
                    case 1:
                        mSelectedIndex = 1;
                        break;
                    case 2:
                        mSelectedIndex = 2;
                        break;
                    case 3:
                        mSelectedIndex = 3;
                        break;
                }
                if (mSelectedIndex != mCurrentIndex)
                {
                    //单击是别的按钮 container中的要变
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    if (mFragmentArray[mSelectedIndex].isAdded() == false)
                    {
                        //没添加过就添加fragment
                        transaction.add(R.id.relativeLayout, mFragmentArray[mSelectedIndex]);
                    }
                    transaction.hide(mFragmentArray[mCurrentIndex]);
                    transaction.show(mFragmentArray[mSelectedIndex]);
                    transaction.commit();
                    mCurrentIndex = mSelectedIndex;
                }
                adapter.setSelectIndex(position);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
