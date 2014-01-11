package com.example.profile;

import com.example.citygame.MainActivity;
import com.example.citygame.R;
import com.example.citygame.R.layout;
import com.example.gameprocess.AnswerManager;
import com.example.gameprocess.GameProcessActivity;
import com.example.gamestate.GameState;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
 
//import android.widget.TextView;

public class ProfileMainActivity extends Activity implements OnClickListener{
	@Override
	protected void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_main);
		
		View buttonStartGame = findViewById(R.id.profile_main_start_game);
		buttonStartGame.setOnClickListener(this);
		
		View buttonChangeProfile = findViewById(R.id.profile_main_change_profile);
		buttonChangeProfile.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()){
			case R.id.profile_main_start_game:
				//TODO Remove
				AnswerManager.eraseAnswers();
				GameState gameState = GameState.getInstance();
				gameState.setLastChar(null);
				
				Intent gameProcessActivity = new Intent(this, GameProcessActivity.class);
				startActivity(gameProcessActivity);
				break;
			case R.id.profile_main_change_profile:
				Intent mainActivity = new Intent(this, MainActivity.class);
				startActivity(mainActivity);
				break;
		}
		
	}

}
