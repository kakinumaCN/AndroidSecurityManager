
package com.example.umpt;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


/**
 * @Filename GuideActivity.java
 * @Package cn.staray.guidetest
 * @Project Guidetest
 * @Create 2014-6-12 涓嬪崍2:44:23
 * @author Staray
 * @Description 寮曞鐣岄潰
 */

public class GuideActivity extends Activity {

    private ViewPager guideViewPager;
    private ViewPagerAdapter guideViewAdapter;
    private ArrayList<View> mViews;
    private final int images[] = {
            R.drawable.guide_page1, R.drawable.guide_page2, R.drawable.guide_page3
    };

    private ImageView[] guideDots;
    private int currentIndex;
    private Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);

        initView();

        initDot();

      
        guideViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                setCurrentDot(arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

       
        startBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
               
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                GuideActivity.this.finish();
            }
        });
    }


    private void initView() {
        guideViewPager = (ViewPager)findViewById(R.id.guide_view_pager);
        mViews = new ArrayList<View>();

        for (int i = 0; i < images.length; i++) {
            
            ImageView iv = new ImageView(GuideActivity.this);
            iv.setBackgroundResource(images[i]);
            mViews.add(iv);
        }

        View view = LayoutInflater.from(GuideActivity.this).inflate(R.layout.guide_content_view,
                null);
        mViews.add(view);

        startBtn = (Button)view.findViewById(R.id.start_btn);

       
        guideViewAdapter = new ViewPagerAdapter(mViews);

        guideViewPager.setAdapter(guideViewAdapter);
    }

    
    private void initDot() {
        LinearLayout layout = (LinearLayout)findViewById(R.id.guide_dots);

        guideDots = new ImageView[mViews.size()];

        for (int i = 0; i < mViews.size(); i++) {
            guideDots[i] = (ImageView)layout.getChildAt(i);
            guideDots[i].setSelected(false);
        }


        currentIndex = 0;
        guideDots[currentIndex].setSelected(true);
    }

    
    private void setCurrentDot(int position) {
        if (position < 0 || position > mViews.size() - 1 || currentIndex == position) {
            return;
        }

        guideDots[position].setSelected(true);
        guideDots[currentIndex].setSelected(false);

        currentIndex = position;
    }
}
