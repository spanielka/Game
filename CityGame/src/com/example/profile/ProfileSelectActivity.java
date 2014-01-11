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
	// ������ ��������� ��������
	private String[] profileLogins;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_select);
		
		// �������� ������ ������������ ��������
		ProfileManager profileManager = new ProfileManager();
		profileLogins = profileManager.getProfileLogins();
		
		int countProfileLogins = profileLogins.length;
		if(countProfileLogins != 0){
			LinearLayout layoutProfileLogins = (LinearLayout)findViewById(R.id.activity_profile_select_layout_for_logins);
			LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			// ��������� ������ � ��������������� ������� 
			for(int i=0; i< profileLogins.length; i++){
				Log.v("ProfileLogins",profileLogins[i]);
				Button buttonProfileLogin = new Button(this);
				buttonProfileLogin.setId(i);
				buttonProfileLogin.setText(profileLogins[i]);
				buttonProfileLogin.setOnClickListener(this);
				layoutProfileLogins.addView(buttonProfileLogin, layoutParams);
			}
		}else{
			// �������, ��� ��� ��������� �������� � ���������� �������
			Log.v("ProfileLogins","No profile exists");
		}
	}

	@Override
	public void onClick(View view) {
		ProfileManager profileManager = new ProfileManager();
		// id ��� ������ ��������� ������� ����������� �������� �������� � ������� profileLogins
		Log.v("ProfileSelected",profileLogins[view.getId()]);
		
		Integer id_Profile;
		id_Profile = profileManager.getIdProfileByLogin(profileLogins[view.getId()]);
		
		if(id_Profile != null){
			Log.v("Id_Profile",""+id_Profile);
			// ������������� ��� �������
			GameState gameState = GameState.getInstance();
			gameState.setId_Profile(id_Profile);
			
			// ��� �������� � �������� ������� ���������� ��� � ������ ������
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
