package com.lux.you.sm;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//import android.view.Menu;
//import android.view.MenuItem;

public class MainActivity extends Activity {

	private View view1, view2, view3, view4;
	private ViewPager viewPager1; // 左边对应的viewPager
	private List<View> viewList1;// 左边view数组
	
	private ViewPager viewPager2; // 左边对应的viewPager
	private List<View> viewList2;// 左边view数组
	
	private TextView found;
	private TextView shoucangjia;
	private TextView installedapp;
	private TextView weather;
	private TextView hearth;
	private TextView music;
	private TextView news;
	private TextView gupiao;
	private TextView user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		viewPager1 = (ViewPager) findViewById(R.id.viewpager_left);
		viewPager2 = (ViewPager) findViewById(R.id.viewpager_right);
		
		LayoutInflater inflater = getLayoutInflater();
		view1 = inflater.inflate(R.layout.viewpage_left_1, null);
		view2 = inflater.inflate(R.layout.viewpage_left_2, null);
		
		view3 = inflater.inflate(R.layout.viewpage_right_1, null);
		view4 = inflater.inflate(R.layout.viewpage_right_2, null);
		
		AssetManager mgr=getAssets();//得到AssetManager
		Typeface tf1=Typeface.createFromAsset(mgr, "fonts/lantingxiheiGBK.TTF");//根据路径得到Typeface
		Typeface tf2=Typeface.createFromAsset(mgr, "fonts/lantingcuheijian.TTF");//根据路径得到Typeface
		
		found = (TextView) view1.findViewById(R.id.foundText);
		shoucangjia = (TextView) view1.findViewById(R.id.shoucangjiaText);
		installedapp = (TextView) view1.findViewById(R.id.installedappText);
		
		found.setTypeface(tf1);//设置字体
		shoucangjia.setTypeface(tf1);
		installedapp.setTypeface(tf1);
		
		weather = (TextView) view1.findViewById(R.id.weatherText);
		hearth = (TextView) view1.findViewById(R.id.hearthText);
		gupiao = (TextView) view1.findViewById(R.id.gupiaoText);
		news = (TextView) view1.findViewById(R.id.newsText);
		music = (TextView) view1.findViewById(R.id.musicText);
		user = (TextView) view1.findViewById(R.id.userText);
		
		weather.setTypeface(tf1);
		hearth.setTypeface(tf1);
		gupiao.setTypeface(tf1);
		news.setTypeface(tf1);
		music.setTypeface(tf1);
		
		user.setTypeface(tf2);
		
		viewList1 = new ArrayList<View>();// 将要分页显示的View装入数组中
		viewList1.add(view1);
		viewList1.add(view2);
		
		viewList2 = new ArrayList<View>();
		viewList2.add(view3);
		viewList2.add(view4);
		
		PagerAdapter pagerAdapter1 = new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return viewList1.size();
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				// TODO Auto-generated method stub
				container.removeView(viewList1.get(position));
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// TODO Auto-generated method stub
				container.addView(viewList1.get(position));

				return viewList1.get(position);
			}
		};
		
		PagerAdapter pagerAdapter2 = new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return viewList2.size();
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				// TODO Auto-generated method stub
				container.removeView(viewList2.get(position));
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// TODO Auto-generated method stub
				container.addView(viewList2.get(position));

				return viewList2.get(position);
			}
		};

		viewPager1.setAdapter(pagerAdapter1);
		viewPager2.setAdapter(pagerAdapter2);

	}
}
