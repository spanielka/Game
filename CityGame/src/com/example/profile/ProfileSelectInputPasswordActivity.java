package com.example.profile;

import java.util.logging.ErrorManager;

import com.example.citygame.R;
import com.example.gamestate.GameState;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView.FixedViewInfo;
import android.widget.TextView;

public class ProfileSelectInputPasswordActivity extends Activity implements OnClickListener{
	private EditText fieldPassword;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_select_input_password);
		
		fieldPassword = (EditText)findViewById(R.id.activity_profile_select_input_pwd_input_field);
		
		Button buttonOk = (Button)findViewById(R.id.activity_profile_select_input_pwd_ok_button);
		buttonOk.setOnClickListener(this);
	}
	@Override
	public void onClick(View view) {
		switch(view.getId()){
			case R.id.activity_profile_select_input_pwd_ok_button:
				// Получаем УИД профиля
				GameState gameState = GameState.getInstance();
				int id_profile = gameState.getId_Profile();
				String password = fieldPassword.getText().toString();
				
				ProfileManager profileManager = new ProfileManager();
				if(profileManager.validatePassword(id_profile, password)){
					//Create ProfileMainActivity
					Intent profileMainActivity = new Intent(this, ProfileMainActivity.class);
					startActivity(profileMainActivity);
				}else{
					TextView errorMessage = (TextView)findViewById(R.id.activity_profile_select_input_pwd_error);
					errorMessage.setTextColor(Color.RED);
					errorMessage.setText("Неправильный пароль");
					fieldPassword.setText("");
				}
				
				break;
		}
	}
}
