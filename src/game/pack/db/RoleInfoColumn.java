package game.pack.db;

import android.provider.BaseColumns;


//sql数据库列常量

public class RoleInfoColumn implements BaseColumns{
	
	RoleInfoColumn(){}
	
	//列名
	public static final String ROLE_NAME="ROLE_NAME";
	public static final String ROLE_HEAD_ADDR="ROLE_HEAD_ADDR";
	public static final String ROLE_BODY_ID="ROLE_BODY_ID";
	public static final String ROLE_PLAY_TIME="ROLE_PLAY_TIME";
	public static final String ROLE_LEVEL="ROLE_LEVEL";
	
	//索引值
	
	public static final int _ID_ROLE = 0;
	public static final int ROLE_NAME_COLUMN=1;
	public static final int ROLE_HEAD_ADDR_COLUMN=2;
	public static final int ROLE_BODY_ID_COLUMN=3;
	public static final int ROLE_PLAY_TIME_COLUMN=4;
	public static final int ROLE_LEVEL_COLUMN=5;
	
	//查询结果集
	public static final String[] PROJECTION = 
	{
		_ID,					//0
		"ROLE_NAME",            //1
		"ROLE_HEAD_ADDR",       //2
		"ROLE_BODY_ID",         //3
		"ROLE_PLAY_TIME",       //4
		"ROLE_LEVEL",			//5
	};
	
	
	
}