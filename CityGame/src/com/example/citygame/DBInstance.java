package com.example.citygame;

import java.io.IOException;

import com.example.db.DataBaseHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

public class DBInstance {
	private static DBInstance dbInstance;
	private DataBaseHelper db;
	//Singleton
	private DBInstance(Context context){
		db = new DataBaseHelper(context);
		
		// Create database if not exists
		try {
        	db.createDataBase();
      	} catch (IOException ioe) {
      		throw new Error("Unable to create database");
      	}
        
		//Open database
      	try {
      		db.openDataBase();      
      	}catch(SQLException sqle){      
      		throw sqle;      
      	}
      	
	}
	
	public static DBInstance getInstance(Context context){
		if(dbInstance == null){
			Log.v("dbInstance","Return new dbInstance");
			dbInstance = new DBInstance(context);
			return dbInstance;
		}else{
			Log.v("dbInstance","Return existing dbInstance");
			return dbInstance;
		}
	}
	
	public Cursor openCursor(String sqlStatement, String[] selectionArgs){
		return db.openCursor(sqlStatement, selectionArgs);
	}
	
	public void execSQL(String sqlStatement){
		db.execSQL(sqlStatement);
	}
	
	public void test(){
		db.test();
	}

}
