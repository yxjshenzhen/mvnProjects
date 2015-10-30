package com.example.hellojni;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	static{
		System.loadLibrary("Hello");
	}
	private Test test = new Test();
	String JNIresult = test.Hello();
	
	private TextView textView1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView1=(TextView)findViewById(R.id.textView1);
		Log.i("JNI result", JNIresult);
		textView1.setText(JNIresult);
	}


}
