package com.xuliugen.news;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.xuliugen.news.adapter.NewsAdapter;
import com.xuliugen.news.model.News;
import com.xuliugen.news.utils.HttpUtils;

public class MainActivity extends Activity implements OnItemClickListener{
	
	private ListView lvNews;       //列表
	private NewsAdapter adapter;   //列表适配器
	private List<News> newsList;   //List集合,列表条目
	
	//此处需要修改为自己的服务器地址：也就是具体的服务器地址:这里不要写你的localhost或者127.0.0.1因为这是要在手机上运行的！
	public static final String GET_NEWS_URL = "http://192.168.0.101:88/php/NewsSrv.php";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lvNews = (ListView) findViewById(R.id.lvNews); //一条一条的消息展示消息
		newsList = new ArrayList<News>(); //初始化
		adapter = new NewsAdapter(this, newsList); //也是初始化，会在后期执行getNewsJSON()方法之后更新
		
		lvNews.setAdapter(adapter); //设置构造器
		lvNews.setOnItemClickListener(this);
		
		//这里执行了网络的的请求操作
		HttpUtils.getNewsJSON(GET_NEWS_URL, getNewsHandler);  //传入的一个handler对象
	}

	// 这里是访问网络数据的时候，返回的handler
	private Handler getNewsHandler = new Handler(){

			/**
			 * 这个方法是Handler自带的方法，用于接受返回的数据
			 */
			public void handleMessage(android.os.Message msg) {
				String jsonData = (String) msg.obj;
				System.out.println(jsonData);
				try {
					
	       //下边是解析json：数组型的json字符串，解成json组,再得到数组元素－－JSONArray--JSONArray.getJSONObject(i)--JSONObject
					JSONArray jsonArray = new JSONArray(jsonData);
					for (int i=0;i<jsonArray.length();i++){
						JSONObject object = jsonArray.getJSONObject(i);
						String title = object.getString("title");
						String desc = object.getString("desc");
						String time = object.getString("time");
						String content_url = object.getString("content_url");
						String pic_url = object.getString("pic_url");
						newsList.add(new News(title, desc, time, content_url, pic_url));
					}
					adapter.notifyDataSetChanged();//通知适配器数据发生变化
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		};
		
	/**
	 * 每一个条目的点击事件
	 */
	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
		
		//获取被点击的对象
		News news = newsList.get(position); 
		Intent intent = new Intent(this, BrowseNewsActivity.class);
		intent.putExtra("content_url", news.getContent_url()); //根据被点击的对象，获取其url
		startActivity(intent);
	}

}
