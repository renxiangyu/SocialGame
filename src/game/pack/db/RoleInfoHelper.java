package game.pack.db;

import java.util.ArrayList;




import game.pack.GameActivity;
import game.pack.db.RoleInfo;
import game.pack.db.RoleInfoColumn;
import game.pack.db.RoleInfoHelper;

import game.pack.db.DBHelper;
import android.app.Activity;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

//数据库操作

public class RoleInfoHelper 
{
	private DBHelper dbHelper;
	private SQLiteDatabase roleDB;
	private Context context;
	
	public RoleInfoHelper( Context context )
	{
		this.context = context;
	}
	
	/**
	 * 初始化数据库连接
	 */
	public RoleInfoHelper open()
	{
		dbHelper = new DBHelper( this.context );
		roleDB = dbHelper.getWritableDatabase();
		return this;
	}
	
	/**
	 * 关闭连接
	 */
	public void close()
	{
		if(dbHelper!=null)
		{
			dbHelper.close();
		}
	}
	
	
	/**
	 * 创建一条记录
	
	 */
	public long create( RoleInfo roleInfo )
	{
		ContentValues values = new ContentValues();
		
		values.put(RoleInfoColumn.ROLE_NAME, roleInfo.getRoleName() );		
		values.put(RoleInfoColumn.ROLE_HEAD_ADDR, roleInfo.getRoleHeadAddr()  );
		values.put(RoleInfoColumn.ROLE_BODY_ID, roleInfo.getRoleBodyId()  );
		values.put(RoleInfoColumn.ROLE_PLAY_TIME, roleInfo.getRolePlayTime()  );
		values.put(RoleInfoColumn.ROLE_LEVEL, roleInfo.getRoleLevel()  );
		
		
		return roleDB.insert(DBHelper.ROLELIB_TABLE, null, values);
	}
	
	
	/**
	 * 更新
	 */
	public boolean update( RoleInfo roleInfo )
	{
		ContentValues values = new ContentValues();
		
		values.put(RoleInfoColumn.ROLE_NAME, roleInfo.getRoleName() );
		values.put(RoleInfoColumn.ROLE_HEAD_ADDR, roleInfo.getRoleHeadAddr()  );
		values.put(RoleInfoColumn.ROLE_BODY_ID, roleInfo.getRoleBodyId()  );
		values.put(RoleInfoColumn.ROLE_PLAY_TIME, roleInfo.getRolePlayTime()  );
		values.put(RoleInfoColumn.ROLE_LEVEL, roleInfo.getRoleLevel()  );
		
		String whereClause = RoleInfoColumn.ROLE_NAME + "=" + "\""+roleInfo.getRoleName()+"\"";
		
		return roleDB.update(DBHelper.ROLELIB_TABLE, values, whereClause, null) > 0;
	}
	
	public boolean grow(String roleName,Activity instance)
	{
		RoleInfo roleInfo= new RoleInfo();
		roleInfo= getRoleInfo(roleName);
		int time=roleInfo.getRolePlayTime()+1;
		roleInfo.setRolePlayTime(time);
		if((time%30)==0&&(time/30<4))
		{
			roleInfo.setRoleLevel(time/30);
			 Toast.makeText(instance, "恭喜你，升级啦！", Toast.LENGTH_SHORT).show();

		}
		
		 return update(roleInfo);
		
	}
	/**
	 * 获取全部RoleInfo信息
	 * @return
	 */
	public ArrayList<RoleInfo> getRoleInfos()
	{
		ArrayList<RoleInfo> list = new ArrayList<RoleInfo>();
		
		RoleInfo roleInfo = null;
		Cursor cursor = roleDB.query(DBHelper.ROLELIB_TABLE, RoleInfoColumn.PROJECTION, 
				null, null, null, null, null);
		
		if( cursor.getCount() > 0 )
	{
			for( cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext() )
			{
				roleInfo = new RoleInfo();			
				roleInfo.setRoleName( cursor.getString( RoleInfoColumn.ROLE_NAME_COLUMN ) );
				roleInfo.setRoleHeadAddr( cursor.getString( RoleInfoColumn.ROLE_HEAD_ADDR_COLUMN ) );
				roleInfo.setRoleBodyId( cursor.getInt( RoleInfoColumn.ROLE_BODY_ID_COLUMN ) );
				roleInfo.setRolePlayTime( cursor.getInt( RoleInfoColumn.ROLE_PLAY_TIME_COLUMN ) );
				roleInfo.setRoleLevel( cursor.getInt( RoleInfoColumn.ROLE_LEVEL_COLUMN ) );
				list.add(roleInfo);
			}
		}
		
		cursor.close();
		cursor = null;		
		return list;
	}
	
	
	/**
	 * 获取一条记录
	 * @param imageID
	 * @return
	 */
	
	public RoleInfo getRoleInfo( String roleName)
	{
		RoleInfo roleInfo = null;
		String selection = RoleInfoColumn.ROLE_NAME+"="+"\""+roleName+"\"";
		if(roleName==null)
			selection=null;
		Cursor cursor = roleDB.query(DBHelper.ROLELIB_TABLE, RoleInfoColumn.PROJECTION, 
				selection, null, null, null, null);
		
		
		if( cursor != null && cursor.getCount()>0 )
		{
			cursor.moveToFirst();
			roleInfo = new RoleInfo();
			roleInfo.setRoleName( cursor.getString( RoleInfoColumn.ROLE_NAME_COLUMN ) );
			roleInfo.setRoleHeadAddr( cursor.getString( RoleInfoColumn.ROLE_HEAD_ADDR_COLUMN ) );
			roleInfo.setRoleBodyId( cursor.getInt( RoleInfoColumn.ROLE_BODY_ID_COLUMN ) );
			roleInfo.setRolePlayTime( cursor.getInt( RoleInfoColumn.ROLE_PLAY_TIME_COLUMN ) );
			roleInfo.setRoleLevel( cursor.getInt( RoleInfoColumn.ROLE_LEVEL_COLUMN ) );
		}
		cursor.close();
		cursor = null;
		return roleInfo;
		}

	
	
	
	
	/**
	 * 删除
	 */
	public boolean delete()
	{
		int ret = roleDB.delete(DBHelper.ROLELIB_TABLE, null, null);
		return ret>0?true:false;
	}
}
	

	
