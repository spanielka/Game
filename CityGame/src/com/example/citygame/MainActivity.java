package com.example.citygame;

import java.io.IOException;

import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

//import android.database.sqlite.SQLiteDatabase;
//import android.database.Cursor;
import android.database.SQLException;

import com.example.db.DataBaseHelper;
import com.example.profile.ProfileCreateActivity;
import com.example.profile.ProfileSelectActivity;

public class MainActivity extends Activity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Set up click listener for button
        View createProfileButton = findViewById(R.id.create_profile);
        createProfileButton.setOnClickListener(this);
        
        View selectProfileButton = findViewById(R.id.activity_main_select_profile);
        selectProfileButton.setOnClickListener(this);
        
        DBInstance dbInstance = DBInstance.getInstance(this);
        dbInstance.test();
        
        /*
        LinearLayout thisLayout = (LinearLayout)findViewById(R.id.activity_main_profiles_layout);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        
        Button myButton1 = new Button(this);
        myButton1.setText("My button1");
        thisLayout.addView(myButton1, layoutParams);
        
        Button myButton2 = new Button(this);
        myButton2.setText("My button2");
        thisLayout.addView(myButton2, layoutParams);
        */
        
        
        /*
        for (int i = 1; i <= 20; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            Button btn = new Button(this);
            btn.setId(i);
            final int id_ = btn.getId();
            btn.setText("button " + id_);
            btn.setBackgroundColor(Color.rgb(70, 80, 90));
            linear.addView(btn, params);
            btn1 = ((Button) findViewById(id_));
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),
                            "Button clicked index = " + id_, Toast.LENGTH_SHORT)
                            .show();
                }
            });
        }
        */
        
        //TODO Create method
        try {
	         
        /*
    	DataBaseHelper myDbHelper = new DataBaseHelper(this);
    	 
        try {
        	
        	myDbHelper.createDataBase();
        	//Log.v("path",this.getDatabasePath("cities23.db").toString());
      	} catch (IOException ioe) {
      		Log.v("DBException","Unable to create database");
      		throw new Error("Unable to create database");
      
      	}
             
      	try {
      		
      		myDbHelper.openDataBase();
      		//myDbHelper.execSQL("INSERT INTO PROFILE (ID_PROFILE, LOGIN, PASSWORD) VALUES (2, 'test_profile', 'test_password');");
      		myDbHelper.test();
      
      	}catch(SQLException sqle){
      
      		throw sqle;
      
      	}
	    */  	
	      	
        	
        	//SQLiteDatabase db;
	        //db = SQLiteDatabase.openDatabase ("cities.db", null, SQLiteDatabase.OPEN_READWRITE);
	        
	        /*
	        db = this.openOrCreateDatabase("database", MODE_PRIVATE, null);
	        db.execSQL("CREATE TABLE IF NOT EXISTS PROFILES (Login VARCHAR, Password VARCHAR);");
	        db.execSQL("DELETE FROM PROFILES");
	        db.execSQL("INSERT INTO PROFILES(Login,Password) values ('LPA', 'Test password');");
	        db.execSQL("INSERT INTO PROFILES(Login,Password) values ('Galya', 'Test password');");
	        db.execSQL("INSERT INTO PROFILES(Login,Password) values ('Kirill', 'Test password');");
	        Cursor cursor = db.rawQuery("SELECT * FROM PROFILES;", null);
	        int indexLoginColumn;
	        indexLoginColumn = cursor.getColumnIndex("Login");
	        cursor.moveToFirst();
	        if (cursor != null) {
	        	Integer count = cursor.getCount();
	        	Log.v("Count rows in cursor", count.toString());
	        	String login = "";
	        	do {
	        		login = cursor.getString(indexLoginColumn);
	        		Log.v("Login", login);
	        	}while (cursor.moveToNext());
	        }
	        */
        } catch (Exception e){
        	Log.v("DBException",e.toString());
        }
    }
    
    //
    public void onClick(View v){
    	switch (v.getId()){
    		case R.id.create_profile:
    			Intent profileCreateActivity = new Intent(this,ProfileCreateActivity.class);
    			startActivity(profileCreateActivity);
    			break;
    		case R.id.activity_main_select_profile:
    			Intent profileSelectActivity = new Intent(this, ProfileSelectActivity.class);
    			startActivity(profileSelectActivity);
    			break;
    			
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
