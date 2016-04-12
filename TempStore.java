package com.lovecust.app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;


/*
* TempStore
* For storing data more conveniently by SharedPreferences
*
* TempStore.getStore( Context context [, String name] );  // get the instance, default name is StoreDefault
*
*
* */
public class TempStore {
	public static final String sDefaultStoreName = "StoreDefault";

	private String name;
	private Context context;
	private SharedPreferences pm;
	private SharedPreferences.Editor editor;

	public static TempStore getStore( Context context, String name ) {
		if ( name == null || name.equals( "" ) ) name = sDefaultStoreName;
		return new TempStore( context, name );
	}
	public static TempStore getStore( Context context ) {
		return new TempStore( context, TempStore.sDefaultStoreName );
	}

	private TempStore( Context context, String name ) {
		this.context = context;
		this.name = name;

		pm = context.getSharedPreferences( name, Context.MODE_PRIVATE );
		editor = pm.edit();
	}


	public String getString( String key ) {
		return pm.getString( key, null );
	}
	public void setString( String key, String value ) {
		editor.putString( key, value );
		editor.commit();
	}
	public int getInt( String key ) {
		return pm.getInt( key, 0 );
	}
	public void setInt( String key, int value ) {
		editor.putInt( key, value );
		editor.commit();
	}

	public String[] getStrings( String key ) {
		try {
			String json = pm.getString( key, null );
			if ( json == null ) return null;
			JSONArray array = new JSONArray( json );
			String[] strings = new String[array.length()];
			for ( int i = 0; i < array.length(); i++ ) {
				strings[i] = array.getString( i );
			}
			return strings;
		} catch ( JSONException e ) {
			e.printStackTrace();
		}
		return null;
	}
	public void setStrings( String key, String[] strings ) {
		try {
			String json = new Gson().toJson( strings );
			editor.putString( key, json );
			editor.commit();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}

	public boolean contains( String key ) {
		return pm.contains( key );
	}
	public void remove( String key ) {
		if ( contains( key ) ) editor.remove( key );
	}
	public void clear( ) {
		editor.clear();
	}
	private String log( String msg ) {
		Log.v( this.getClass().getName() + " -->> ", msg + "" );
		return msg;
	}
}
