package com.example.githubsearchusers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

	private EditText mInputName;
	private Button mBtnSearch;
	private ListView userList;
	public static MainActivity singleActivity;
	private ProgressDialog mProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		singleActivity = this;
		mInputName = (EditText)findViewById(R.id.et);
		mBtnSearch = (Button)findViewById(R.id.btn);
		userList = (ListView)findViewById(R.id.list);
		
		mBtnSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String query = mInputName.getText().toString().trim();
				if(!query.equals("")){					
					new JsonHelper(MainActivity.this).getJsonArrayByName(query);
				}
				mProgressDialog = ProgressDialog.show(MainActivity.this, "Loading...", "Please wait...", true, false);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public static MainActivity getSingleActivity(){
		return singleActivity;
	}
	
	public ListView getListView(){
		mProgressDialog.dismiss();
		return userList;
	}
	
	public void dismissProgressDialog(){
		mProgressDialog.dismiss();
	}
}
