package com.xuliugen.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * 这里是每一个具体消息的展示
 * 
 * @author xuliugen
 * 
 */
public class BrowseNewsActivity extends Activity {

	private WebView webView;
	Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acitivy_browse_news);

		webView = (WebView) findViewById(R.id.webView);
		String pic_url = getIntent().getStringExtra("content_url");//获取传入的值
		//String pic_url= (String) intent.getExtras().get("content_url");
		webView.loadUrl(pic_url);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//如果有缓存则从缓存中读取出来
	}
}
