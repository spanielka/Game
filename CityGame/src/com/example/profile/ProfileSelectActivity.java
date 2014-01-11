package com.example.profile;

import com.example.citygame.R;
import com.example.gamestate.GameState;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class ProfileSelectActivity extends Activity implements OnClickListener{
	// Список доступных профилей
	private String[] profileLogins;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_select);
		
		// Получаем список существующих профилей
		ProfileManager profileManager = new ProfileManager();
		profileLogins = profileManager.getProfileLogins();
		
		int countProfileLogins = profileLogins.length;
		if(countProfileLogins != 0){
			LinearLayout layoutProfileLogins = (LinearLayout)findViewById(R.id.activity_profile_select_layout_for_logins);
			LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			// Добавляем кнопку с соответствующим логином 
			for(int i=0; i< profileLogins.length; i++){
				Log.v("ProfileLogins",profileLogins[i]);
				Button buttonProfileLogin = new Button(this);
				buttonProfileLogin.setId(i);
				buttonProfileLogin.setText(profileLogins[i]);
				buttonProfileLogin.setOnClickListener(this);
				layoutProfileLogins.addView(buttonProfileLogin, layoutParams);
			}
		}else{
			// Говорим, что нет созданных профилей и предлагаем создать
			Log.v("ProfileLogins","No profile exists");
		}
	}

	@Override
	public void onClick(View view) {
		ProfileManager profileManager = new ProfileManager();
		// id для кнопок доступных логинов установлены согласно индексам в массиве profileLogins
		Log.v("ProfileSelected",profileLogins[view.getId()]);
		
		Integer id_Profile;
		id_Profile = profileManager.getIdProfileByLogin(profileLogins[view.getId()]);
		
		if(id_Profile != null){
			Log.v("Id_Profile",""+id_Profile);
			// Устанавливаем УИД профиля
			GameState gameState = GameState.getInstance();
			gameState.setId_Profile(id_Profile);
			
			// Для профилей с непустым паролем необходимо еще и ввести пароль
			if(profileManager.passwordExists(id_Profile)){
				Intent inputPasswordActivity = new Intent(this, ProfileSelectInputPasswordActivity.class);
				startActivity(inputPasswordActivity);
			}else{
				//Create ProfileMainActivity
				Intent profileMainActivity = new Intent(this, ProfileMainActivity.class);
				startActivity(profileMainActivity);
			}
		}else{
			Log.v("Id_Profwile","Profile not exists");
		}
	}
	
}
