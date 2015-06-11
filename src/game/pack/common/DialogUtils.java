package game.pack.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;

/** 
 * ��˵����   �Ի��򵯳�������
 * @author  @Cundong
 * @weibo   http://weibo.com/liucundong
 * @blog    http://www.liucundong.com
 * @date    Apr 29, 2011 2:50:48 PM
 * @version 1.0
 */
public class DialogUtils 
{
	/**
	 * ����ѯ�ʴ���
	 * @param
	 * @param
	 */
	public static void dialogBuilder( Activity instance, String title, 
			String message,
			final DialogCallBack callBack)
	{
        AlertDialog.Builder builder = new Builder(instance);
        builder.setMessage( message );  
        builder.setTitle( title );  
        builder.setPositiveButton("ȷ��",  
        new android.content.DialogInterface.OnClickListener() 
        {  
            @Override
			public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss();  
                callBack.callBack();
                callBack.callAll();
            }  
        }); 
        
        builder.setNegativeButton("ȡ��",  
        new android.content.DialogInterface.OnClickListener() 
        {  
            @Override
			public void onClick(DialogInterface dialog, int which) { 
                dialog.dismiss(); 
                callBack.callCancel();
                callBack.callAll();
            }  
        }); 
        
        builder.create().show(); 
	}
	
	public interface DialogCallBack
	{
		public void callBack();
		public void callAll();
		public void callCancel();
	}
}