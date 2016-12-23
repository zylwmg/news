package com.xuliugen.news.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuliugen.news.R;
import com.xuliugen.news.model.News;
import com.xuliugen.news.utils.HttpUtils;

/**
 * 用于显示在界面上的item
 * 
 * @author piaodangdehun
 * 
 */
public class NewsAdapter extends BaseAdapter {

	private Context context;
	private List<News> newsList;

	/**
	 * 构造方法的时候传入newsList
	 * 
	 * @param context
	 * @param newsList  需要填入的news信息
	 */
	public NewsAdapter(Context context, List<News> newsList) {
		this.context = context;
		this.newsList = newsList;
	}

	@Override
	public int getCount() {
		return newsList.size();
	}

	@Override
	public News getItem(int position) {
		return newsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * 为news-item中的布局赋值
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) { //如果为空则重新创建
			convertView = LayoutInflater.from(context).inflate(R.layout.news_item, null);
		}
		// 获得news-item中的控件
		TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
		TextView tvDesc = (TextView) convertView.findViewById(R.id.tvDesc);
		TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);
		ImageView ivPic = (ImageView) convertView.findViewById(R.id.ivPic);

		News news = newsList.get(position);
		tvTitle.setText(news.getTitle());
		tvDesc.setText(news.getDesc());
		tvTime.setText(news.getTime());

		String pic_url = news.getPic_url();
		HttpUtils.setPicBitmap(ivPic, pic_url);

		return convertView;
	}

}
