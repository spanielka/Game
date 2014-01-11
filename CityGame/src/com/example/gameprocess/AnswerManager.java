package com.example.gameprocess;

import android.database.Cursor;

import com.example.citygame.DBInstance;

public class AnswerManager {
	public static void createAnswer(String cityName){
		DBInstance db = DBInstance.getInstance(null);
		db.execSQL(("INSERT INTO PlayerAnswer(CityName) VALUES ('" + cityName.toUpperCase() + "');"));
	}
	
	public static void eraseAnswers(){
		DBInstance db = DBInstance.getInstance(null);
		db.execSQL("DELETE FROM PlayerAnswer;");
	}
	
	public static boolean answerExists(String cityName){
		DBInstance db = DBInstance.getInstance(null);
		String sqlQuery = 
				"select *" +
				"  from PlayerAnswer" +
				" where CityName = '" + cityName.toUpperCase() +"';";
		
		Cursor cursor = db.openCursor(sqlQuery, null);
		
		if(cursor.getCount() == 0){
			cursor.close();
			return false;
		}else{
			cursor.close();
			return true;
		}
	}

}
