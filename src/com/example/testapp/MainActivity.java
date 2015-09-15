package com.example.testapp;


import com.example.testapp.R;

import android.support.v7.app.ActionBarActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private int level2;
	private TextView tvBatteryafteresult;
	private Button btnSendValue;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tvBatteryafteresult = (TextView)findViewById(R.id.tv_Batteryafteresult);
		btnSendValue = (Button) findViewById(R.id.btn_SendValue);
		/*
		 * here button click event will start
		 */
		btnSendValue.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/*
				 * here will send value to a new activity
				 */
				
				Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				// the value_key better to define it as constant in real project
				// we will use the "value_name" in activity2
				intent.putExtra("value_name", level2);
				startActivity(intent);
			}
		});
		
		getBatteryPercentageafter();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/*
	 * you dont need to return any value from this function
	 * as the BroadcastReceiver will update the value at onReceive 
	 */
	public void getBatteryPercentageafter() {
		  BroadcastReceiver batteryLevelReceiver = new BroadcastReceiver() {

				@Override
				public void onReceive(Context context, Intent intent) {
					// TODO Auto-generated method stub
					context.unregisterReceiver(this);
		             int currentLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
		             int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
		              level2 = -1;
		             if (currentLevel >= 0 && scale > 0) {
		                  level2 = (currentLevel * 100) / scale;
		             }
		             tvBatteryafteresult.setText("Battery Level Remaining: " + level2 + "%");
					
				}
		     }; 
		     IntentFilter batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		     registerReceiver(batteryLevelReceiver, batteryLevelFilter);

//		     return level2;
		  }

}
