package com.example.testapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.testapp.R;

public class SecondActivity extends Activity{

	private TextView tvReceivedValue;
	
 	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		tvReceivedValue = (TextView) findViewById(R.id.tv_ReceivedValue);
	// here must take the same name as in activity 1
		int level2 = getIntent().getIntExtra("value_name", 0);
		
		tvReceivedValue.setText("We Got value = " + level2);
 	}
}
