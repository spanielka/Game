package com.example.gameprocess;

import android.database.Cursor;
import android.util.Log;

import com.example.citygame.DBInstance;
import com.example.gamestate.GameState;

public class CityManager {
	// Возможные результаты проверки корректности хода игрока
	public enum ValidateCityResult {
		SUCCESS, 
		CITY_NOT_EXISTS,
		ANSWER_EXISTS,
		FIRST_CHAR_INCORRECT
	}
	
	// Проверка существования города в базе данных
	public static boolean cityExists(String cityName){
		DBInstance db = DBInstance.getInstance(null);
		String sqlQuery = 
				"select *" +
				"  from City" +
				" where Name = '" + cityName.toUpperCase() +"';";
		
		Cursor cursor = db.openCursor(sqlQuery, null);
		
		if(cursor.getCount() == 0){
			cursor.close();
			return false;
		}else{
			cursor.close();
			return true;
		}
	}
	
	// Возвращает первую букву в названии города
	public static String getFirstChar(String cityName){
		return cityName.substring(0,1).toUpperCase();
	}
	
	// Возвращает последнюю букву из названия города, с которой может начинаться название города
	public static String getLastChar(String cityName){
		String lastChar;
		
		// Prepare to fetch
		DBInstance db = DBInstance.getInstance(null);
		String sqlStatement = "select LastChar from City where Name = '" + cityName.toUpperCase() + "';";
		Cursor cursor = db.openCursor(sqlStatement, null);
		
		// Fetch row
		cursor.moveToFirst();
		int columnIndex = cursor.getColumnIndex("LastChar");
		lastChar = cursor.getString(columnIndex);
		cursor.close();
		
		return lastChar;
	}

	// Проверяет корректность введенного города
	public static ValidateCityResult validateCity(int id_Game, String cityName){
		if(CityManager.cityExists(cityName)){
			Log.v("cityExists","CityExists");
			
			// Проверка, что город не был введен ранее
			if(!AnswerManager.answerExists(cityName)){
				GameState gameState = GameState.getInstance();
				String lastChar = gameState.getLastChar();
				// Провекра, что первая буква введенного слова совпадает с последней буквой предыдущего
				if(getFirstChar(cityName).equals(lastChar) || lastChar == null){
					return ValidateCityResult.SUCCESS;
				}else{
					return ValidateCityResult.FIRST_CHAR_INCORRECT;
				}
			}else{
				return ValidateCityResult.ANSWER_EXISTS;
			}
		}else{
			return ValidateCityResult.CITY_NOT_EXISTS;
		}		
	}
}
