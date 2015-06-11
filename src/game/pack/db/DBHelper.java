package game.pack.db;

import game.pack.db.RoleInfoColumn;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


//Êý¾Ý¿â°ïÖúÀà

public class DBHelper extends SQLiteOpenHelper{
	public static final String DATABASE_NAME = "role.db";
	public static final int DATABASE_VERSION = 1;
	public static final String ROLELIB_TABLE = "roleinfo";
	
	private static final String CREATE_ROLEINFO_LIB = "CREATE TABLE " + ROLELIB_TABLE +" ("
	+ RoleInfoColumn._ID + " integer primary key autoincrement,"
	+ RoleInfoColumn.ROLE_NAME + " text,"
	+RoleInfoColumn.ROLE_HEAD_ADDR + " text,"
	+RoleInfoColumn.ROLE_BODY_ID + " integer,"
	+RoleInfoColumn.ROLE_PLAY_TIME + " integer,"
	+RoleInfoColumn.ROLE_LEVEL + " integer)";
	
	
	public DBHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);	
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_ROLEINFO_LIB);
		System.out.println("create a database");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//do something
	}
}