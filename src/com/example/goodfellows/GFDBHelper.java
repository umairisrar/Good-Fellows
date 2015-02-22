package com.example.goodfellows;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class GFDBHelper {

	public static final String DBNAME = "MyDB";
	public static final int DB_VERSION = 2;
	
	public static final String TABLE_1 = "USERS";
	
	public static final String USERID = "userID";
	public static final String USERNAME = "userName";
	public static final String PASSWORD = "password";
	
	public static final String TABLE_2 = "FELLOW";
	
	public static final String FELLOWID = "fellowID";
	//public static final String USERNAME = "userName";
	public static final String DEFAULTLAT = "defaultLat";
	public static final String DEFAULTLONG = "defaultLong";
	public static final String PROFESSION = "profession";
	public static final String MARITALSTATUS = "maritalStatus";
	public static final String POSTSCOUNT = "postCount";
	public static final String ENDORSEMENTCOUNT = "endorsementCount";
	public static final String SPAMMEDCOUNT = "spammedCount";
	public static final String PICTURE = "picture";
	
	public static final String TABLE_3 = "POST";
	
	public static final String POSTID = "postID";
	//public static final String FELLOWID = "userName";
	public static final String CONTENTID = "contentID";
	public static final String CONTENTTYPE = "contentType";
	public static final String ISPLACE = "isPlace";
	public static final String ISPROBLEM = "isProblem";
	public static final String ISPROGRESS = "isProgress";
	//public static final String ENDORSEMENTCOUNT = "endorsementCount";
	//public static final String SPAMMEDCOUNT = "spammedCount";
	public static final String LOCATIONID = "locationID";
	
	public static final String TABLE_4 = "LOCATION";
	
	//public static final String LOCATIONID = "locationID";
	public static final String LOCATIONNAME = "locationName";
	public static final String LOCATIONLAT = "locationLat";
	public static final String LOCATIONLONG = "locationLong";
	

	
	String [] userColoumns = new String[]{USERID, USERNAME, PASSWORD};
	String [] fellowColoumns = new String[]{USERID, USERNAME, PASSWORD};
	
	private DBHelper helper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static class  DBHelper extends SQLiteOpenHelper{

		public DBHelper(Context context) {
			super(context, DBNAME, null, DB_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			//create user Table
			db.execSQL("CREATE TABLE " + TABLE_1 + " (" + USERID + " VARCHAR(45) PRIMARY KEY " + USERNAME + " VARCHAR(45) NOT NULL, "
					   + PASSWORD + " VARCHAR(45) NOT NULL);"
					   );
			//create fellow table
			db.execSQL("CREATE TABLE " + TABLE_2 + " (" + FELLOWID + " INTEGER PRIMARY KEY AUTOINCREMENT " + USERNAME + " VARCHAR(45) NOT NULL, "
					   + DEFAULTLAT + " VARCHAR(45)," + DEFAULTLONG + " VARCHAR(45)," + PROFESSION + " VARCHAR(45)," 
					   + MARITALSTATUS + " VARCHAR(45)," + POSTSCOUNT + " VARCHAR(45)," + ENDORSEMENTCOUNT + " VARCHAR(45),"
					   + SPAMMEDCOUNT + " VARCHAR(45)," + PICTURE + " VARCHAR(45));"
					   );
			//create post table
			db.execSQL("CREATE TABLE " + TABLE_3 + " (" + POSTID + " VARCHAR(45) NOT NULL PRIMARY KEY " + FELLOWID + " VARCHAR(45) NOT NULL, "
					   + CONTENTID + " VARCHAR(45) NOT NULL," + CONTENTTYPE + " VARCHAR(45)," + ISPLACE + " VARCHAR(45)," 
					   + ISPROBLEM + " VARCHAR(45)," + ISPROGRESS + " VARCHAR(45)," + ENDORSEMENTCOUNT + " VARCHAR(45),"
					   + SPAMMEDCOUNT + " VARCHAR(45)," + LOCATIONID + " VARCHAR(45));"
					   );
			//create post table
			db.execSQL("CREATE TABLE " + TABLE_4 + " (" + LOCATIONID + " VARCHAR(45) NOT NULL PRIMARY KEY " + LOCATIONNAME + " VARCHAR(45) NOT NULL, "
					   + LOCATIONLAT + " VARCHAR(45) NOT NULL," + LOCATIONLONG + " VARCHAR(45)NOT NULL);"
					   );
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_1);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_2);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_3);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_4);
			onCreate(db);
		}
	} // DBHelper Class
	
	public GFDBHelper(Context c) {
		// TODO Auto-generated constructor stub
		ourContext = c;
	}
	public GFDBHelper openConnection(){
		helper = new DBHelper(ourContext);

		ourDatabase = helper.getWritableDatabase();
		return this;
	}
	public void closeConnection(){
		helper.close();
	}
	public long registerUser(String userID, String userName, String password){
		//content values is bundle of data for sql
		ContentValues cv = new ContentValues();
		Log.d("insert", "user");
		cv.put(USERID, userID);
		cv.put(USERNAME, userName);
		cv.put(PASSWORD, password);
		return ourDatabase.insert(TABLE_1, null, cv);
	}
	public long registerFellow(String fellowID, String userName, String defaultLat, String defaultLong
			, String profession, String maritalStatus, String postC, String endorseC,
			String spamC, String pic){
		//content values is bundle of data for sql
		ContentValues cv = new ContentValues();
		Log.d("insert", "user");
		cv.put(FELLOWID, fellowID);
		cv.put(USERNAME, userName);
		cv.put(DEFAULTLAT, defaultLat);
		cv.put(DEFAULTLONG, defaultLong);
		cv.put(PROFESSION, profession);
		cv.put(MARITALSTATUS, maritalStatus);
		cv.put(POSTSCOUNT, postC);
		cv.put(ENDORSEMENTCOUNT, endorseC);
		cv.put(SPAMMEDCOUNT, spamC);
		cv.put(PICTURE, pic);
		return ourDatabase.insert(TABLE_2, null, cv);
	}
	public long registerPost(String postID, String fellowID, String contentID, String contentType
			, String isPlace, String isProblem, String isProgress, String endorseC,
			String spamC, String locationID){
		//content values is bundle of data for sql
		ContentValues cv = new ContentValues();
		Log.d("insert", "user");
		//cv.put(POSTID, postID);
		cv.put(FELLOWID, fellowID);
		cv.put(CONTENTID, contentID);
		cv.put(CONTENTTYPE, contentType);
		cv.put(ISPLACE, isPlace);
		cv.put(ISPROBLEM, isProblem);
		cv.put(ISPROGRESS, isProgress);
		cv.put(ENDORSEMENTCOUNT, endorseC);
		cv.put(SPAMMEDCOUNT, spamC);
		cv.put(LOCATIONID, locationID);
		return ourDatabase.insert(TABLE_1, null, cv);
	}
public String validateUser(String userID, String password){
		
		Cursor cursor = ourDatabase.query(TABLE_1, userColoumns,
				USERID + " = '" + userID + "' and " + PASSWORD + " = '" + password + "'",
				null	, null, null, null);
		if(cursor != null){
			cursor.moveToFirst();
			String pName = cursor.getString(1);
			return pName;
		}
		return "";
	}
}
