package com.example.profile;

import android.database.Cursor;

import com.example.citygame.DBInstance;

public class ProfileManager {
	/*
	 * Создание профиля по заданному логину и паролю
	 * Возвращает Id_Profile
	 */
	public int createProfile(String login, String password){
		int id_Profile;
		DBInstance db = DBInstance.getInstance(null);
		
		// Определяем следующее значение первичного ключа для таблицы PROFILE
		String sqlQuery = 
				"select max(Id_Profile) + 1 as MaxId_Profile" +
				"  from Profile;";
		
		Cursor cursor = db.openCursor(sqlQuery, null);
		cursor.moveToFirst();
		
		int columnIndex;
		columnIndex = cursor.getColumnIndex("MaxId_Profile");
		id_Profile = cursor.getInt(columnIndex);
		cursor.close();
		
		// Вставляем новую запись
		db.execSQL(
			"insert into Profile(" +
			"	Id_Profile, " +
			"	Login, " +
			"	Password)" +
			"values (" +
				id_Profile + ",'" +
				login + "','" +
				password + "');");
		
		return id_Profile;
		
	}
	//delete profile
	//change login
	//change password
	
	/*
	 * Проверка существования профиля игрока по логину
	 */
	public boolean profileExists(String login){
		DBInstance db = DBInstance.getInstance(null);
		String sqlQuery = 
				"select *" +
				"  from Profile" +
				" where Login = '" + login +"';";
		
		Cursor cursor = db.openCursor(sqlQuery, null);
		
		if(cursor.getCount() == 0){
			cursor.close();
			return false;
		}else{
			cursor.close();
			return true;
		}
	}
	
	// Проверка наличия пароля у профиля
	public boolean passwordExists(int id_Profile){
		DBInstance db = DBInstance.getInstance(null);
		String sqlQuery = 
				"select *" +
				"  from Profile" +
				" where Id_Profile = '" + id_Profile +"'" +
				"   and Password != '';";
		
		Cursor cursor = db.openCursor(sqlQuery, null);
		
		if(cursor.getCount() == 0){
			cursor.close();
			return false;
		}else{
			cursor.close();
			return true;
		}
	}
	
	// Проверка правильности пароля
	public boolean validatePassword(int id_Profile, String password){
		DBInstance db = DBInstance.getInstance(null);
		String sqlQuery = 
				"select *" +
				"  from Profile" +
				" where Id_Profile = '" + id_Profile +"'" +
				"   and Password = '" + password + "';";
		
		Cursor cursor = db.openCursor(sqlQuery, null);
		
		if(cursor.getCount() == 0){
			cursor.close();
			return false;
		}else{
			cursor.close();
			return true;
		}
	}
	
	/*
	 * Возвращает список имен профилей
	 */
	public String[] getProfileLogins(){
		String[] profileLogins;
		
		// Preparing to fetch
		String sqlStatement = "select Login from profile order by Login;";
		DBInstance db = DBInstance.getInstance(null);
		Cursor cursor = db.openCursor(sqlStatement, null);
		int countRows = cursor.getCount();
		profileLogins = new String[countRows];
		int columnIndex = cursor.getColumnIndex("Login");
		
		// Fetch all rows
		cursor.moveToFirst();
		for (int i=0; i<countRows; i++){
			profileLogins[i] = cursor.getString(columnIndex);
			cursor.moveToNext();
		}
		
		return profileLogins;
	}

	/*
	 * Возвращает Id_Profile по логину
	 */
	public Integer getIdProfileByLogin(String login){
		Integer id_Profile = null;
		
		// Preparing to fetch
		DBInstance db = DBInstance.getInstance(null);
		String sqlQuery = 
				"select Id_Profile" +
				"  from Profile" +
				" where Login = '" + login +"';";
		
		Cursor cursor = db.openCursor(sqlQuery, null);
		
		// If profile exists
		if(cursor.getCount() == 1){
			cursor.moveToFirst();
			int columnIndex = cursor.getColumnIndex("Id_Profile");
			id_Profile = cursor.getInt(columnIndex);
		}
		cursor.close();
		return id_Profile;
	}
}
