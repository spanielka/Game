package com.example.gameprocess;

import com.example.citygame.R;
import com.example.gamestate.GameState;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class GameProcessActivity extends Activity implements OnClickListener{
	private EditText inputField;
	private TextView answerStatus;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_process);
		
		answerStatus = (TextView)findViewById(R.id.activity_game_process_answer_status);
		answerStatus.setText("������� �����");
		
		inputField = (EditText)findViewById(R.id.activity_game_process_input_field);
		
		View buttonOK = findViewById(R.id.activity_game_process_button_ok);
		buttonOK.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()){
			case R.id.activity_game_process_button_ok:
				buttonOkPressed();
				break;
		}
	}
	
	// ������ ������ ����� ������
	private void buttonOkPressed() {
		// �������� ��������� ���� � ��������� �������� ������
		GameState gameState = GameState.getInstance();
		String cityName = inputField.getText().toString();
		Log.v("cityName", cityName);
		
		// ��������� ������������ ���������� �������� ������
		CityManager.ValidateCityResult validateCityResult;
		validateCityResult = CityManager.validateCity(gameState.getId_Game(), cityName);
		
		switch(validateCityResult){
			case SUCCESS:
				AnswerManager.createAnswer(cityName);
				
				String lastChar = CityManager.getLastChar(cityName);
				gameState.setLastChar(lastChar);
				// ��������� ���� ����� ������
				inputField.setText("");
				inputField.append(lastChar);
				answerStatus.setTextColor(Color.BLACK);
				answerStatus.setText("��");
				break;
			case CITY_NOT_EXISTS:
				answerStatus.setTextColor(Color.RED);
				answerStatus.setText("����� �� ����������");
				break;
			case ANSWER_EXISTS:
				answerStatus.setTextColor(Color.RED);
				answerStatus.setText("����� ��� ���");
				break;
			case FIRST_CHAR_INCORRECT:
				answerStatus.setTextColor(Color.RED);
				answerStatus.setText("������ ����� �� ���������");
				break;
		}		
	}

}
