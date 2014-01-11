package com.example.profile;

import com.example.citygame.R;
import com.example.citygame.R.id;
import com.example.citygame.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class ProfileCreateActivity extends Activity implements OnClickListener{
	private ProfileManager profileManager;
	private EditText fieldName;
	private EditText fieldPassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_create);
		
		fieldName = (EditText)findViewById(R.id.createProfileName);
		fieldPassword = (EditText)findViewById(R.id.createProfilePassword);
		
		View okButton = findViewById(R.id.createProfileOk);
		okButton.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.createProfileOk:
				Log.v("Name",fieldName.getText().toString());
				Log.v("Password",fieldPassword.getText().toString());
				
				String login = fieldName.getText().toString().trim();
				String password = fieldPassword.getText().toString().trim();
				
				ProfileManager profileManager = new ProfileManager();
				if(profileManager.profileExists(login)){
					Log.v("ProfileExists", "ProfileExists");
				}else{
					profileManager.createProfile(login, password);
					Log.v("ProfileExists", "ProfileNotExists");
				}
				
				//Start ProfileMainActivity
				Intent intent = new Intent(this, ProfileMainActivity.class);
				startActivity(intent);
				break;
		}
	}
}
